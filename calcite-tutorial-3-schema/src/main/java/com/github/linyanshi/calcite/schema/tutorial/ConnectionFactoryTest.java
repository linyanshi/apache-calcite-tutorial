package com.github.linyanshi.calcite.schema.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author linyanshi
 * @date 2022-04-28 17:06:00
 */
public class ConnectionFactoryTest {

    public Connection createSchemaFactoryConnection() throws SQLException {
        Properties info = new Properties();
        info.put("lex", "mysql");
        String model = "calcite-tutorial-3-schema/src/main/resources/model.json";
        info.put("model", model);
       return DriverManager.getConnection("jdbc:calcite:", info);
    }

    public Connection createTableFactoryConnection() throws SQLException {
        Properties info = new Properties();
        info.put("lex", "mysql");
        String model = "calcite-tutorial-3-schema/src/main/resources/model.yaml";
        info.put("model", model);
       return DriverManager.getConnection("jdbc:calcite:", info);
    }

    public static void main(String[] args) throws SQLException {
        Connection schemaCon = new ConnectionFactoryTest().createSchemaFactoryConnection();
        Connection tableCon = new ConnectionFactoryTest().createTableFactoryConnection();
        ResultSet resultSet =  schemaCon.getMetaData().getTables("tutorial",null,null,null);
        while (resultSet.next()) {
            final String catalogName = resultSet.getString(1);
            final String schemaName = resultSet.getString(2);
            final String tableName = resultSet.getString(3);
            final String tableTypeName = resultSet.getString(4);
            System.out.println("catalogName: " + catalogName + " schemaName: " + schemaName + " tableName: " + tableName + " tableTypeName: " + tableTypeName);
        }
        System.out.println();
        System.out.println();

        ResultSet resultSet2 =  tableCon.getMetaData().getTables("tutorial",null,null,null);
        while (resultSet2.next()) {
            final String catalogName = resultSet2.getString(1);
            final String schemaName = resultSet2.getString(2);
            final String tableName = resultSet2.getString(3);
            final String tableTypeName = resultSet2.getString(4);
            System.out.println("catalogName: " + catalogName + " schemaName: " + schemaName + " tableName: " + tableName + " tableTypeName: " + tableTypeName);
        }

    }
}
