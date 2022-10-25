package com.fastcampus.ch4.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
    public static void main(String[] args) throws SQLException {
        String sql = "SELECT * FROM user_info";
        String driver = "org.mariadb.jdbc.Driver";
        String dbUrl = "jdbc:mysql://thddbap.cafe24.com:3306/thddbap";
        try {
            Class.forName(driver);
            System.out.println("driver loading");

            Connection con = DriverManager.getConnection(dbUrl, "thddbap", "yourTREE123");
            System.out.println("DB connection:" + con);

            Statement st = con.createStatement();
            // 쿼리를 실행하여 결과 집합을 얻어온다.
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) { // 다음 값을 가져옴 Bof ~ Eof
                String ex1 = rs.getString("pwd");
                System.out.println(ex1);
            }

            rs.close();
            st.close();
            con.close();

            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}