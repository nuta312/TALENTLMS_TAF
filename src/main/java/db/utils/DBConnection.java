package db.utils;


import db.config_reader.DBConfigReader;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class DBConnection {
    private static Connection connection;
    private static Statement statement;
    private static final Logger log = Logger.getLogger(DBConnection.class.getName());

    private DBConnection(){
     }

     private static PGSimpleDataSource getBaseDataSource(String database){
            PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource(){{
                setServerName(DBConfigReader.getValue("server"));
                setPortNumber(Integer.parseInt(DBConfigReader.getValue("port")));
                setUser(DBConfigReader.getValue("user"));
                setPassword(DBConfigReader.getValue("password"));
                setDatabaseName(database);
            }};
            return pgSimpleDataSource;
     }

     public static void openConnection(String database) throws SQLException {
        if (connection == null){
            connection = getBaseDataSource(database).getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
     }

     public static ResultSet query(String query, Object ... params) throws SQLException {
        if (params.length == 0){
            return statement.executeQuery(query);
        }
        else{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement.executeQuery();
        }
     }

     public static void close(){
        try{
            if(statement != null){
                statement.close();
                statement = null;
            }
            if (connection !=null){
                connection.close();
                connection = null;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
     }

    public static Connection getConnection() {
        return connection;
    }

    // Создать универсальный метод, который будет создавать, удалять, обновлять классы из бинов

    public static boolean createObject(String tableName, String [] columns, Object []values) throws SQLException {
       if (columns.length != values.length){
           throw new IllegalArgumentException("Number of columns and values must match");
       }
        StringBuilder columnNames = new StringBuilder();
        StringBuilder placeHolder = new StringBuilder();
        for (int i = 0; i < columns.length; i++){
            columnNames.append(columns[i]);
            placeHolder.append("?");
            if (i < columns.length - 1){
                columnNames.append(", ");
                placeHolder.append(", ");
            }
        }
        String query = String.format("Insert into %s (%s) values (%s);", tableName, columnNames, placeHolder);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            for (int i = 0; i < values.length; i++){
                preparedStatement.setObject(i+1, values[i]);
            }
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static void updateObject(String tableName, String setClause, String whereClause) throws SQLException{
        String query = "UPDATE " + tableName + " SET " + setClause + " WHERE " + whereClause;
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteObject(String tableName, String whereClause) throws SQLException{
        String query = "DELETE FROM " + tableName + " WHERE " + whereClause;
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
        }
    }

    public static void executeAndPrintSQLQuery(String sqlQuery) throws SQLException {
        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sqlQuery)){
            boolean hasResult = preparedStatement.execute();
            if (hasResult){
                ResultSet resultSet = preparedStatement.getResultSet();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSetMetaData.getColumnName(i) + "\t");
                }
                System.out.println();
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } else{
                int rowsAffected = preparedStatement.getUpdateCount();
            }
        }
    }

    // Подключиться к базе , открыть таблицу актеры -> проверить названия колонок -> создать актера Дженифер Лопес
    // Проверить что имя Дженифер находится в поле first_name and last_name
    // 1. Проверить названия колонок
    // 2. Внести изменения
    // 3. Проверить изменения согласно своих столбцов

    public static void retrieveColumnInfo(String tableName) throws SQLException {
        String query = "Select * from " + tableName;
        ResultSet resultSet = DBConnection.query(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        List<Object> columnNames = new ArrayList<>();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
            columnNames.add(resultSetMetaData.getColumnName(i));
        }
        System.out.println(columnNames);
//        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
//            resultSetMetaData.getColumnName(i);
//            System.out.println(resultSetMetaData.getColumnName(i));
////            String columnName = resultSetMetaData.getColumnName(i);
////            System.out.println(columnName);
//        }
    }

    public static void retrieveColumnInfo2(String tableName) throws SQLException {
        String query = "Select * from " + tableName;
        ResultSet resultSet = DBConnection.query(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next()){
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                String columnValue = resultSet.getString(i);
                System.out.println("Имя столбца: " + columnName + " Значение: " + columnValue);
            }
        }
    }

    public static void retrieveColumnInfo3(String tableName) throws SQLException {
        String query = "Select * from " + tableName;
        ResultSet resultSet = DBConnection.query(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next()){
            resultSet.updateString("first_name", "Jennifer");
            resultSet.updateString("last_name", "Lopez");
            resultSet.insertRow();
            System.out.println(resultSet);
        }
    }

    public static void sqlToCSV (String query, String baseFilename){
        log.info("creating csv file: " + baseFilename);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = baseFilename + "_" + timestamp;
        try {
            FileWriter fw = new FileWriter(filename + ".csv");
            if(connection.isClosed()){
                statement = connection.createStatement();
            }
            ResultSet resultSet = statement.executeQuery(query);

            int columnCount = resultSet.getMetaData().getColumnCount();

            for(int i = 1; i <= columnCount; i ++){
                fw.append(escapeCSV(resultSet.getMetaData().getColumnLabel(i)));
                if(i < columnCount) fw.append(',');
                else fw.append('\n');
            }

            while (resultSet.next()) {

                for(int i = 1; i <= columnCount; i ++){
                    fw.append(escapeCSV(resultSet.getString(i)));
                    if(i < columnCount) fw.append(',');
                }
                fw.append('\n');
            }
            fw.flush();
            fw.close();
            log.info("CSV File is created successfully.");
            connection.close();
        } catch (Exception e) {
            log.severe("Error while closing connection: " + e.getMessage());
        }
    }
    private static String escapeCSV(String value) {
        if (value == null) return "";
        if (value.contains(";") || value.contains("\"") || value.contains("\n")) {
            value = "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
