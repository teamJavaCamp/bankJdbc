package com.bank.controller;

import com.bank.model.TransactionDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static com.bank.common.JDBCTemplate.close;
import static com.bank.common.JDBCTemplate.getConnection;

public class BankController {

    public void getAcc(String id){
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String accNo = "";
        long balance = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/bank/mapper/menu-query.xml"));

            String query = prop.getProperty("showAccount");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, id);

            rset = pstmt.executeQuery();

            if(rset.next()){
                accNo = rset.getString("account");
                balance = rset.getLong("balance");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        System.out.println("계좌 번호 : " + accNo);
        System.out.println("잔고 : " + balance);
    }

    public int updateAccount(String id, long balance){
        Connection con = getConnection();
        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/bank/mapper/account-query.xml"));

            String query = prop.getProperty("updateAccount");
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1, balance);
            pstmt.setString(2, id);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(con);
        }
        return result;
    }

    public void saveHistory(TransactionDTO transaction){
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/bank/mapper/account-query.xml"));

            String query = prop.getProperty("saveHistory");

            pstmt = con.prepareStatement(query);

            SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String dates = date.format(transaction.getDate());


            pstmt.setString(1, dates);
            pstmt.setString(2, transaction.getId());
            pstmt.setString(3, transaction.getTransaction());
            pstmt.setLong(4, transaction.getAmount());

            pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(con);
        }

    }


}
