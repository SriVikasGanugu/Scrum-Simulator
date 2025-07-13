package com.deepti.scrumsimulator;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author soumi
 */
public class DatabaseConnection {

    private static final JSONParser jsonParser = new JSONParser();

    public static String generateInsertCommand(String tableName, String jsonData) {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
            String columns = String.join(", ", jsonObject.keySet());
            String values = getValuesFromJson(jsonObject);

            return String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, columns, values);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateInsertMultipleRowsCommand(String tableName, String jsonArrayData) {
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonArrayData);
            if (jsonArray.isEmpty()) {
                return null; // No data to insert
            }

            JSONObject firstRow = (JSONObject) jsonArray.get(0);
            String columns = String.join(", ", firstRow.keySet());

            List<String> valuesList = new ArrayList<>();
            for (Object obj : jsonArray) {
                JSONObject row = (JSONObject) obj;
                String values = getValuesFromJson(row);
                valuesList.add("(" + values + ")");
            }

            return String.format("INSERT INTO %s (%s) VALUES %s;", tableName, columns, String.join(", ", valuesList));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateSelectCommand(String tableName, String condition) {
        return String.format("SELECT * FROM %s WHERE %s;", tableName, condition);
    }

    public static String generateSelectColumnsCommand(String tableName, String columns, String condition) {
        return String.format("SELECT %s FROM %s WHERE %s;", columns, tableName, condition);
    }

    public static String generateSelectCountCommand(String tableName, String condition) {
        return String.format("SELECT COUNT(*) FROM %s WHERE %s;", tableName, condition);
    }

    // Helper method to get comma-separated values from JSON
    private static String getValuesFromJson(JSONObject jsonObject) {
        StringBuilder values = new StringBuilder();
        for (Object value : jsonObject.values()) {
            values.append("'").append(value).append("', ");
        }
        // Remove the trailing comma and space
        values.delete(values.length() - 2, values.length());
        return values.toString();
    }

    // Helper method to get column-value pairs for UPDATE command
    private static String getSetClauseFromJson(JSONObject jsonObject) {
        StringBuilder setClause = new StringBuilder();
        for (Object key : jsonObject.keySet()) {
            setClause.append(key).append(" = '").append(jsonObject.get(key)).append("', ");
        }
        // Remove the trailing comma and space
        setClause.delete(setClause.length() - 2, setClause.length());
        return setClause.toString();
    }
    
    private final String tableName;

    public DatabaseConnection(String tableName) {
        this.tableName = tableName;
    }
    public static void main(String[] args) {
    // Provide the table name you want to check the connection for
    String tableName = "userauthentication"; // Replace with the actual table name

    DatabaseConnection connection = new DatabaseConnection(tableName);
    Connection dbConnection = connection.createConnection();

    if (dbConnection != null) {
        System.out.println("Connection successful!");
        // Add any additional logic to perform with the connection
        // For example, you can query the database or perform other operations
    } else {
        System.out.println("Connection failed!");
        }
    }
    public Connection createConnection() {
        try {
            // Get the current working directory
            String currentDirectory = System.getProperty("user.dir");

            // Create the file path using the current working directory
            
            String filePath = currentDirectory + "/src/main/java/com/deepti/scrumsimulator/DatabaseCredentials.json";

            // Read JSON file
            JSONParser parser = new JSONParser();
            JSONObject credentials = (JSONObject) parser.parse(new FileReader(filePath));
            
//            JSONParser parser = new JSONParser();
//            JSONObject credentials = (JSONObject) parser.parse(new FileReader("DatabaseCredentials.json"));
            String url = (String)credentials.get("url");
            String user = (String)credentials.get("user");
            String password = (String)credentials.get("password");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            
            System.out.println("Connection successful!");
            // Optionally, you can close the connection here.
            // connection.close();
            return connection;
        } catch (IOException | SQLException | ParseException e) {
            System.err.println("Connection failed. Error: " + e.getMessage());
            e.printStackTrace();
            return null;
            
        }
        
    }
}
