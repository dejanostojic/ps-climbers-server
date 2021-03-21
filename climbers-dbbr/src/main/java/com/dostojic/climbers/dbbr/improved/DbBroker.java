/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.dbbr.improved;

import com.dostojic.climbers.dbbr.improved.annotation.Column;
import com.dostojic.climbers.dbbr.improved.annotation.CompositeId;
import com.dostojic.climbers.dbbr.improved.annotation.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.lang.model.type.TypeVariable;

/**
 *
 * @author Dejan.Ostojic
 */
public abstract class DbBroker<Dto, PkType> {

    private String getCommaSepColumnList() {
        return commaSepColumnList;
    }

    private void setCommaSepColumnList(String commaSepColumnList) {
        this.commaSepColumnList = commaSepColumnList;
    }

    private void processMethod(Method method, Map<String, Method> getters, Map<String, Method> setters) {
        ProcessedMethod processedName = processName(method);
        String propertyName = processedName.getName();
        ProcessedMethod.Type methodType = processedName.getType();

        if (methodType.equals(ProcessedMethod.Type.getter)) {
            getters.put(propertyName, method);
        } else if (methodType.equals(ProcessedMethod.Type.setter)) {
            setters.put(propertyName, method);
        }    
    }

    private abstract class ColumnMapper {

        String columnName;

        public ColumnMapper(String columnName) {
            this.columnName = QueryUtils.mySqlEscapeLiteral(columnName);
        }

        public String getColumnName() {
            return columnName;
        }

        public abstract void resultSetToDto(ResultSet rs, Dto dto, int index) throws Exception;

        public abstract void dtoToParam(Dto dto, PreparedStatement statement, int paramIndex) throws Exception;

        public boolean isPrimaryKey() {
            return false;
        }
    }

    protected Dto newDto() {
        try {
            return dtoClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    ;
    
    private String tableName;

    protected String getTableName() {
        return tableName;
    }

    protected void setTableName(String tableName) {
        this.tableName = QueryUtils.mySqlEscapeLiteral(tableName);
    }

    private boolean autoIncrement;

    protected boolean isAutoIncrement() {
        return autoIncrement;
    }

    protected void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    protected void setPrimaryKey(Dto dto, int id) {
        try {
            pkSeter.invoke(dto, id);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not set primary key! " + ex.getMessage(), ex);
        }
    }

    protected void setPrimaryKey(Dto dto, PkType id) {
        try {
            pkSeter.invoke(dto, id);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not set primary key! " + ex.getMessage(), ex);
        }
    }

    private final List<ColumnMapper> fields = new ArrayList<>();

    private void add(ColumnMapper f) {
        fields.add(f);
    }

    private String commaSepColumnList;
    private String cloumnParamList;
    private int columnCount;
    private String pkClosure;
    private int pkCount;
    private String nonPkSetters;
    private int nonPkCount;
    private List<ColumnMapper> pkFields;
    private List<ColumnMapper> nonPkFields;
    private Method pkSeter = null;
    private Method pkGetter = null;

    protected String getPkClosure() {
        return pkClosure;
    }

    protected int getPkCount() {
        return pkCount;
    }

    protected List<ColumnMapper> getPkFields() {
        return pkFields;
    }

    private static class ProcessedMethod {

        String name;
        Type type = Type.other;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public enum Type {
            getter, setter, other
        }
    }

    /**
     * Finds getter and setter methods for declared fields
     *
     * @param method
     * @param fieldColumnNames
     * @return ProcessedMethod containing name and type of method
     */
    private static ProcessedMethod processName(Method method) {

        String methodName = method.getName();
        int paramCount = method.getParameterTypes().length;

        ProcessedMethod ret = new ProcessedMethod();

        String propertyName = null;
        if (methodName.startsWith("get") && paramCount == 0) {
            propertyName = methodName.substring(3);
            ret.setType(ProcessedMethod.Type.getter);
        }
        if (methodName.startsWith("is") && paramCount == 0) {
            propertyName = methodName.substring(2);
            ret.setType(ProcessedMethod.Type.getter);
        }
        if (methodName.startsWith("set") && paramCount == 1) {
            propertyName = methodName.substring(3);
            ret.setType(ProcessedMethod.Type.setter);
        }

        if (propertyName != null && !propertyName.isEmpty()) {
            propertyName = Character.toLowerCase(propertyName.charAt(0)) + propertyName.substring(1);

            ret.setName(propertyName);
        }

        return ret;
    }

    private void annotationConfig() {
        Class dtoType = dtoClass;
        if (!dtoType.isAnnotationPresent(Table.class)) {
            throw new RuntimeException(String.format("%s is not annotated with @Table!", dtoType.getSimpleName()));
        }

        Table table = (Table) dtoType.getAnnotation(Table.class);
        setTableName(table.name());
        setAutoIncrement(table.autoIncrement());
        System.out.println("DEBUG: annotation config: " + tableName);

        Method[] methods = dtoType.getMethods();
        Map<String, Method> getters = new HashMap<>();
        Map<String, Method> setters = new HashMap<>();

        for (Method method : methods) {
            processMethod(method, getters, setters);
        }

        Optional<Field> optCompositeId = Stream.of(dtoType.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(CompositeId.class))
                .findFirst();

         boolean hasCompositeId = optCompositeId.isPresent();

        if (hasCompositeId) {
            Field compositeId = optCompositeId.get();
            Optional<Method> compositePkSetter = optCompositeId.map(f -> setters.get(f.getName()));
            if (!compositePkSetter.isPresent()) {
                throw new RuntimeException("Composite id must have setter");
            }
            pkSeter = compositePkSetter.get();
            pkGetter = getters.get(compositeId.getName());
            Optional<Field[]> compositePkFields = optCompositeId.map(f -> f.getType().getDeclaredFields());
            if (compositePkFields.isPresent()) {
                System.out.println("composite pk are present: " + optCompositeId.get().getType().getSimpleName());
                for (Method method: optCompositeId.get().getType().getMethods()){
                    processMethod(method, getters, setters);
                }
                
                for (Field pkField : compositePkFields.get()) {
                    System.out.println("checking: " + pkField.getName());
                    
                    if (pkField.isAnnotationPresent(Column.class)){
                        System.out.println("checking: " + pkField.getName() + " is annotated with @Column");

                        handleColumnField(pkField, getters, setters, hasCompositeId);
                    }
                }

            }
        }

        Stream.of(dtoType.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> handleColumnField(field, getters, setters, hasCompositeId));
    }

    private void handleColumnField(Field field, Map<String, Method> getters, Map<String, Method> setters, boolean hasCompositePk) {

        Column column = field.getAnnotation(Column.class);
        final String fieldName = field.getName(); // name of field
        String columnName = column.name(); // name of col in db
        final boolean primaryKey = column.isPrimaryKey();
        final Class<?> type = field.getType();
        final Method getter = getters.get(fieldName);
        final Method setter = setters.get(fieldName);

        if (setter == null) {
            throw new RuntimeException("field " + fieldName + " annotated as column has no declared public setter method and can't be read from database!");
        }

        if (getter == null) {
            throw new RuntimeException("field " + fieldName + " annotated as column has no declared public getter method and can't be persisted to database!");
        }

        if (primaryKey && !hasCompositePk) {
            pkSeter = setter;
        }

        add(new ColumnMapper(columnName) {

            @Override
            public boolean isPrimaryKey() {
                return primaryKey;
            }

            @Override
            public void resultSetToDto(ResultSet rs, Dto dto, int index) throws Exception {

                try {
                    // PROBLEM WHEN USING @CompositeId, it is no longer Dto for getter
                    if (hasCompositePk && primaryKey){
                        // get composite id object
                        Object compositeId = pkGetter.invoke(dto);
                        
                        // if null create new instance
                        if(compositeId == null){
                            compositeId = pkGetter.getReturnType().newInstance();
                        }
                        // set value to the composite id field
                        setter.invoke(compositeId, rs.getObject(index, type));
                        
                        // set composite id to the main object
                        pkSeter.invoke(dto, compositeId);

                    }else{
                        System.out.println("field name: " + fieldName);
                        if (setter.getParameterTypes()[0].isAssignableFrom(type)) {
                            setter.invoke(dto, rs.getObject(index, type));
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
                    
                    throw new RuntimeException(String.format("error reading from database %s.%s", tableName, columnName)
                            .concat(" Trying to set " + setter.getDeclaringClass().getName())
                            .concat(". Fetched from DB " + rs.getObject(index))
                            .concat(". Expected type: " + type.getName()), ex);
                }
            }

            @Override
            public void dtoToParam(Dto dto, PreparedStatement statement, int paramIndex) throws Exception {

                try { // TODO: PROBLEM WHEN USING @CompositeId, it is no longer Dto for getter
                    if (hasCompositePk && primaryKey){
                        System.out.println("setting to @CompositeId");
                        // if it has composite pk and it is pk field than get composite pk and set value
                        Object compositeId = pkGetter.invoke(dto);
                        if(compositeId == null){
                            compositeId = pkGetter.getReturnType().newInstance();
                            pkSeter.invoke(dto, compositeId);
                        }
                        
                        statement.setObject(paramIndex, getter.invoke(pkGetter.invoke(dto)));
                    }else{
                        statement.setObject(paramIndex, getter.invoke(dto));
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(String.format("error setting to database %s.%s", tableName, columnName), ex);
                }
            }

        });

    }

    Class<Dto> dtoClass;

    public DbBroker(Class<Dto> dtoClass) {
        this.dtoClass = dtoClass;
        annotationConfig();
        init();
    }

    private void init() {
        StringBuilder columnListBuilder = new StringBuilder();
        StringBuilder columnParamBuilder = new StringBuilder();
        StringBuilder pkListBuilder = new StringBuilder();
        StringBuilder nonPkListBuilder = new StringBuilder();
        StringBuilder nonPkSettersBuilder = new StringBuilder();
        StringBuilder pkClosureBuilder = new StringBuilder();

        pkFields = new ArrayList<>();
        nonPkFields = new ArrayList<>();

        columnCount = 0;
        pkCount = 0;
        nonPkCount = 0;
        for (ColumnMapper f : fields) {
            if (++columnCount != 1) {
                columnListBuilder.append(",");
                columnParamBuilder.append(",");
            }
            columnListBuilder.append(getTableName()).append(".").append(f.getColumnName());
            columnParamBuilder.append("?");
            if (f.isPrimaryKey()) {
                if (++pkCount != 1) {
                    pkListBuilder.append(",");
                    pkClosureBuilder.append(" and ");
                }
                pkListBuilder.append(f.getColumnName());
                pkClosureBuilder.append(getTableName()).append(".").append(f.getColumnName()).append("=?");
                pkFields.add(f);
            } else {
                if (++nonPkCount != 1) {
                    nonPkListBuilder.append(",");
                    nonPkSettersBuilder.append(",");
                }
                nonPkListBuilder.append(f.getColumnName());
                nonPkSettersBuilder.append(f.getColumnName()).append("=?");
                nonPkFields.add(f);
            }
        }
        System.out.println("DEBUG: mapping table: " + tableName);
        setCommaSepColumnList(columnListBuilder.toString());
        System.out.println("DEBUG: commaSepColumnList: " + getCommaSepColumnList());
        nonPkSetters = nonPkSettersBuilder.toString();
        System.out.println("DEBUG: nonPkSetters: " + nonPkSetters);
        pkClosure = pkClosureBuilder.toString();
        System.out.println("DEBUG: pkClosure: " + pkClosure);
        cloumnParamList = columnParamBuilder.toString();
        System.out.println("DEBUG: cloumnParamList: " + cloumnParamList);
    }

    public void loadFromResultSet(ResultSet rs, Dto dto) throws Exception {
        try {
            for (int i = 0; i < fields.size(); i++) {
                fields.get(i).resultSetToDto(rs, dto, i + 1);
            }
        } catch (SQLException e) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    public List<Dto> loadList(String whereCondition, String orderBy, int offset, int count) throws Exception {
        final List<Dto> list = new ArrayList<>();
        String sql = makeSql(whereCondition, orderBy, offset, count);

        QueryUtils.forEach(sql, (ResultSet rs) -> {

            while (rs.next()) {
                Dto dto = newDto();
                loadFromResultSet(rs, dto);
                list.add(dto);
            }
        });
        return list;
    }

    public <T extends Dto> List<T> loadList(String whereCondition, String orderBy, int offset, int count, final Class<T> c) throws Exception {
        final List<T> list = new ArrayList<>();
        String sql = makeSql(whereCondition, orderBy, offset, count);

        QueryUtils.forEach(sql, (ResultSet rs) -> {
            try {
                T dto = c.newInstance();
                loadFromResultSet(rs, dto);
                list.add(dto);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return list;
    }

    public List<Dto> loadList(String whereCondition, String orderBy) throws Exception {
        return loadList(whereCondition, orderBy, 0, 0);
    }

    public <T extends Dto> List<T> loadList(String whereCondition, String orderBy, final Class<T> c) throws Exception {
        return loadList(whereCondition, orderBy, 0, 0, c);
    }

    public List<Dto> loadAll() throws Exception {
        return loadList(null, null, 0, 0);
    }

    public <T extends Dto> List<T> laodAll(final Class<T> c) throws Exception {
        return loadList(null, null, 0, 0, c);
    }

    public Dto loadFirst(String whereCondition) throws Exception {
        List<? extends Dto> tmp = loadList(whereCondition, null, 0, 1);
        Dto ret = null;

        if (!tmp.isEmpty()) {
            ret = tmp.get(0);
        }

        return ret;
    }

    public Dto loadFirst(String whereCondition, String orderBy) throws Exception {
        List<? extends Dto> tmp = loadList(whereCondition, orderBy, 0, 1);
        Dto ret = null;

        if (!tmp.isEmpty()) {
            ret = tmp.get(0);
        }

        return ret;
    }

    public <T extends Dto> T loadFirst(String whereCondition, String orderBy, Class<T> c) throws Exception {
        List<T> tmp = loadList(whereCondition, orderBy, 0, 1, c);
        T ret = null;

        if (!tmp.isEmpty()) {
            ret = tmp.get(0);
        }

        return ret;
    }

    public Dto loadByPk(PkType id) throws Exception {
        Dto dto = newDto();
        setPrimaryKey(dto, id);

        if (load(dto)) {
            return dto;
        } else {
            return null;
        }
    }

    private boolean load(Dto dto) throws Exception {
        String sql
                = "select " + getCommaSepColumnList() + " "
                + "from " + getTableName() + " "
                + "where " + pkClosure;
        try (PreparedStatement st = DbbrTransactionManager.getCurrent().prepareStatement(sql);) {
            for (int i = 0; i < pkCount; i++) {
                pkFields.get(i).dtoToParam(dto, st, i + 1);
            }
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                loadFromResultSet(rs, dto);
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public int count(String whereCondition) throws Exception {
        try (Statement st = DbbrTransactionManager.getCurrent().createStatement()) {
            int count = 0;
            String sql
                    = "select count(*) "
                    + "from " + getTableName() + " "
                    + (isEmpty(whereCondition)
                    ? "" : "where " + whereCondition);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public int countAll() throws Exception {
        return count(null);
    }

    public boolean delete(String whereCondition) throws Exception {
        try {
            String sql
                    = "delete "
                    + "from " + getTableName() + " "
                    + (isEmpty(whereCondition)
                    ? "" : "where " + whereCondition);

            try (Statement st = DbbrTransactionManager.getCurrent().createStatement()) {
                int n = st.executeUpdate(sql);
                return n > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean delete(Dto dto) throws Exception {
        try {
            String sql
                    = "delete from " + getTableName() + " "
                    + "where " + pkClosure;
            System.out.println("DEBUGG: " + sql);
            try (PreparedStatement st = DbbrTransactionManager.getCurrent().prepareStatement(sql)) {
                for (int i = 0; i < pkCount; i++) {
                    pkFields.get(i).dtoToParam(dto, st, i + 1);
                }
                int n = st.executeUpdate();
                return n > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    public boolean deleteByPk(PkType primaryKey) throws Exception {
        Dto dto = newDto();
        setPrimaryKey(dto, primaryKey);

        return delete(dto);
    }

    public boolean update(Dto dto) throws Exception {
        String updateSql
                = "update " + getTableName() + " "
                + "set " + nonPkSetters + " "
                + "where " + pkClosure;
        try (PreparedStatement updateStatement = DbbrTransactionManager.getCurrent().prepareStatement(updateSql)) {
            for (int i = 0; i < nonPkCount; i++) {
                nonPkFields.get(i).dtoToParam(dto, updateStatement, i + 1);
            }
            for (int j = 0; j < pkCount; j++) {
                pkFields.get(j).dtoToParam(dto, updateStatement, j + nonPkCount + 1);
            }
            int n = updateStatement.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    public Dto insert(Dto dto) throws Exception {

        String insertSql
                = "insert into  " + getTableName() + " "
                + "(" + getCommaSepColumnList() + ") "
                + "values (" + cloumnParamList + ")";

        System.out.println("DEBUGG insert sql: " + insertSql);
        DbbrTransactionManager tm = DbbrTransactionManager.getCurrent();
        try (PreparedStatement insertStatement = isAutoIncrement()
                ? tm.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)
                : tm.prepareStatement(insertSql);) {
            for (int i = 0; i < columnCount; i++) {
                System.out.println("debug: inserting column: " + i);
                System.out.println("debug: inserting column: " + fields.get(i).columnName);
                fields.get(i).dtoToParam(dto, insertStatement, i + 1);
            }
            insertStatement.executeUpdate();
            if (isAutoIncrement()) {
                ResultSet rs = insertStatement.getGeneratedKeys();
                rs.next();
                setPrimaryKey(dto, rs.getInt(1));
            }
            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateOrInsert(Dto dto) throws Exception {
        if (!update(dto)) {
            insert(dto);
        }
    }

    private String makeSql(String whereCondition, String orderBy, int offset, int count) {
        String sql
                = "select " + getCommaSepColumnList() + " "
                + "from " + getTableName() + " "
                + (isEmpty(whereCondition) ? "" : "where " + whereCondition + " ")
                + (isEmpty(orderBy) ? "" : "order by " + orderBy)
                + (count > 0 ? " limit " + offset + "," + count : "");
        Logger.getLogger(DbBroker.class.getName()).log(Level.INFO, sql);
        System.out.println("makeSql: " + sql);
        return sql;
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /*
     * Not finished! What if not all elements are not insered because of constrains?!
     * 
     public void insert(List<Dto> entities) throws Exception {
     try {
     Connection conn = Database.getCurrent().getConnection();
     PreparedStatement insertStatement = null;
     String insertSql =
     "insert into  " + getTableName() + " "
     + "(" + getCommaSepColumnList() + ") "
     + "values (" + cloumnParamList + ")";
        
     if (isAutoIncrement()) {
     insertStatement = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
     } else {
     insertStatement = conn.prepareStatement(insertSql);
     }
        
     for (int j = 0; j < entities.size(); j++) {
     try {
     for (int i = 0; i < columnCount; i++) {
     fields.get(i).dtoToParam(entities.get(j), insertStatement, i + 1);
     }
     insertStatement.addBatch();

     } finally {
     insertStatement.close();
     }

     }

     insertStatement.executeBatch();
     if (isAutoIncrement()) {
     ResultSet rs = insertStatement.getGeneratedKeys();
     for (int j = 0; j < entities.size(); j++) {
     rs.next();
     setPrimaryKey(entities.get(j), rs.getLong(1));
     }
     }
     } catch (SQLException e) {
     throw new WrappedException(e);
     }
     }
     */
}
