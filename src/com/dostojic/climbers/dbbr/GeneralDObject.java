/* GeneralDObject.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package com.dostojic.climbers.dbbr;



import java.io.Serializable;
import java.sql.*;

// Operacije navedenog interfejsa je potrebno da implementira svaka od domenskih klasa,
// koja zeli da joj bude omogucena komunikacija sa Database broker klasom.
public interface GeneralDObject extends Serializable
{ abstract public String getAtrValue();//{return "";}
  abstract public String setAtrValue();//{return "";}
  abstract public String getClassName();//{return "";}
  abstract public String getWhereCondition();//{return "";}
  abstract public String getNameByColumn(int column);//{return "";}
  abstract public GeneralDObject getNewRecord(ResultSet rs) throws SQLException; //{return null;}
  abstract public int getPrimaryKey();
  abstract public void setVariablePrimaryKeyAttribute(int id);
  abstract public String poruka1();
  abstract public String poruka2();
  abstract public String poruka3();
  abstract public String poruka4();
  abstract public String poruka5();
  abstract public String poruka6();
  abstract public String poruka7();
  abstract public String poruka8();
  abstract public String poruka9();
  abstract public String porukaX0();
  abstract public String uzmiStanje();
  abstract public void postaviStanje(String stanje);
  abstract public String getNewKey_ColumnName();
  abstract public String getNewKey_Where();
  abstract public String getPrimaryKey1();
  abstract public void print();
}
