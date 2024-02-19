package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

                // INSERT YOUR CODE HERE
                
                //Get the meta data from ResultSet
                 ResultSetMetaData metaData = rs.getMetaData();
                
                
                //Loop through the result set *make sure to create and loop through each column of the set
                while(rs.next()){
                    JsonObject dataRows = new JsonObject(); //Holds row info
                    
                    for(int i = 1; i <= metaData.getColumnCount(); i++){
                        //Get the names of the colums as well as values **Gets stores in dataRows
                        String columnName = metaData.getColumnName(i);
                        Object columnVals = rs.getObject(i).toString(); // converting to string as well
                        dataRows.put(columnName, columnVals);
                        
                    }
                    //Add the data to the records array 
                    records.add(dataRows);
                }
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
