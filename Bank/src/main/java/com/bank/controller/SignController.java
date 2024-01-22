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

public class SignController {

    //회원가입
    public int signUp(MemberDTO member){
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        Properties prop = new Properties();

        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/bank/mapper/menu-query.xml"));
            String query = prop.getProperty("signUp");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPwd());
            pstmt.setString(3, member.getName());
            pstmt.setInt(4,member.getAge());
            pstmt.setString(5,member.getGender());
            pstmt.setString(6,member.getAccNo());
            pstmt.setLong(7,member.getBalance());

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

    //로그인
    public int signIn(String id, String pwd){

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Properties prop = new Properties();

        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/bank/mapper/menu-query.xml"));

            String query = prop.getProperty("SignIn");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, id);
            pstmt.setString(2, pwd);

            rset = pstmt.executeQuery();

            if(rset.next()){
                result = 1;
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
        return result;
    }

    public MemberDTO getMember(String id){
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Properties prop = new Properties();

        MemberDTO member = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/bank/mapper/menu-query.xml"));

            String query = prop.getProperty("getMember");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, id);

            rset = pstmt.executeQuery();

            if(rset.next()){
                member = new MemberDTO();
                member.setName(rset.getString("name"));
                member.setAge(rset.getInt("age"));
                member.setGender(rset.getString("gender"));
                member.setId(rset.getString("id"));
                member.setPwd(rset.getString("pwd"));
                member.setAccNo(rset.getString("account"));
                member.setBalance(rset.getLong("balance"));
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

        return member;
    }

    public String createAccount(){

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String accN = "";

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/bank/mapper/menu-query.xml"));

            String query = prop.getProperty("checkAccount");

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            List<String> accList = new ArrayList<>();   //받아온 계좌 넣어둘 리스트

            while(rset.next()){
                accList.add(rset.getString("account"));
            }

            int[] accNum = new int [7];

            for(int i = 0; i < accList.size() + 1; i++){
                accN = "";

                for(int j = 0; j < accNum.length; j++){
                    accNum[j] = (int) (Math.random() * 9);
                    accN += accNum[j]+"";
                }

                for(int k = 0; k < i; k++){
                    if(accList.get(k).equals(accN)){
                        i--;
                        break;
                    }
                }
            }// 계좌 난수 생성
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
            close(con);
        }
        return accN;
    }




}
