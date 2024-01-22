package com.bank.controller;

import com.bank.model.MemberDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.bank.common.JDBCTemplate.close;
import static com.bank.common.JDBCTemplate.getConnection;

public class AdminController {

    public void showAllMember() {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        Properties prop = new Properties();

        List<MemberDTO> memberList = null;
        MemberDTO member = null;



        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/bank/mapper/menu-query.xml"));

            String query = prop.getProperty("showAllMember");

            rset = pstmt.executeQuery();

            memberList = new ArrayList<>();

            while(rset.next()){
                member = new MemberDTO();

                member.setName(rset.getString("name"));
                member.setAge(rset.getInt("age"));
                member.setGender(rset.getString("gender"));
                member.setId(rset.getString("id"));
                member.setPwd(rset.getString("pwd"));
                member.setAccNo(rset.getString("account"));
                member.setBalance(rset.getLong("balance"));

                memberList.add(member);
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
        for(MemberDTO memberDTO : memberList){
            System.out.println(memberDTO);
        }
    }

    public int editInfo(){

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream(""));

            String query = prop.getProperty("");

            pstmt = con.prepareStatement(query);



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
}
