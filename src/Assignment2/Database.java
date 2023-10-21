package Assignment2;

import java.sql.*;

public class Database {
    public static void record(String cal) {
        String url = "jdbc:mysql://localhost:3306"; // 数据库连接URL
        String username = "root"; // 数据库用户名
        String password = "Xingkong.52684"; // 数据库密码

        try {
            // 连接到数据库
            Connection connection = DriverManager.getConnection(url, username, password);

            // 创建数据库
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS Calculator_two";
            Statement createDatabaseStatement = connection.createStatement();
            createDatabaseStatement.executeUpdate(createDatabaseQuery);
            System.out.println("数据库创建成功");

            // 切换到创建的数据库
            String useDatabaseQuery = "USE Calculator_two";
            Statement useDatabaseStatement = connection.createStatement();
            useDatabaseStatement.executeUpdate(useDatabaseQuery);

            // 创建表格
            String createTableQuery = "CREATE TABLE IF NOT EXISTS History_table ("
                    + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "Calculation VARCHAR(255),"
                    + "Date DATE)";
            Statement createTableStatement = connection.createStatement();
            createTableStatement.executeUpdate(createTableQuery);
            System.out.println("表格创建成功");

            //记录计算过程！
            String calculation = cal;

            // 获取当前日期
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            // 插入数据到表格
            String insertQuery = "INSERT INTO History_table (Calculation, Date) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, calculation);
            preparedStatement.setDate(2, currentDate);
            preparedStatement.executeUpdate();
            System.out.println("数据插入成功");

            // 关闭连接和Scanner
            preparedStatement.close();
            connection.close();


            // 关闭连接
            createDatabaseStatement.close();
            useDatabaseStatement.close();
            createTableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void print(String cal) {
        String url = "jdbc:mysql://localhost:3306"; // 数据库连接URL
        String username = "root"; // 数据库用户名
        String password = "Xingkong.52684"; // 数据库密码
        try {
            // 连接到数据库
            Connection connection = DriverManager.getConnection(url, username, password);

            // 查询表格中的数据
            String selectQuery = "SELECT * FROM History_table";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // 遍历结果集并打印数据
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String calculation = resultSet.getString("Calculation");
                Date date = resultSet.getDate("Date");

                System.out.println("ID: " + id);
                System.out.println("Calculation: " + calculation);
                System.out.println("Date: " + date);
                System.out.println("--------------------");
            }

            // 关闭连接和Statement
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
