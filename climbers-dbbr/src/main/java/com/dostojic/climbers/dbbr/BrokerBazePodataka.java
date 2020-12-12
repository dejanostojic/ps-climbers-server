/* BrokerBazePodataka.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package com.dostojic.climbers.dbbr;


import com.dostojic.climbers.dbbr.GeneralDObject;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.List;
//import org.json.JSONArray;
//import org.json.JSONObject;


public abstract class BrokerBazePodataka 
{   public abstract boolean makeConnection();
    public abstract boolean insertRecord(GeneralDObject odo);
    public abstract boolean updateRecord(GeneralDObject odo,GeneralDObject odoold);
    public abstract boolean updateRecord(GeneralDObject odo); 
    public abstract boolean deleteRecord(GeneralDObject odo);
    public abstract boolean deleteRecords(GeneralDObject odo,String where); 
    public abstract GeneralDObject findRecord(GeneralDObject odo);
    public abstract List<GeneralDObject> findRecord(GeneralDObject odo,String where);
    public abstract boolean commitTransation();
    public abstract boolean rollbackTransation();
    public abstract void closeConnection();
    public abstract GeneralDObject getRecord(GeneralDObject odo,int index);
    public abstract int getRecordsNumber(GeneralDObject odo); 
    public abstract int findRecordPosition(GeneralDObject odo); 
    public abstract int getNewKeyByColumn(GeneralDObject odo); 
    public abstract List<GeneralDObject> updatePrimaryKey(GeneralDObject odo);
//    public abstract JSONArray findRecords(String sql);
//    public abstract boolean insertRecord(JSONObject jsO);
    String dbName;
}
