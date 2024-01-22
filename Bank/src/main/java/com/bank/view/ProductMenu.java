package com.bank.view;


import com.bank.controller.Loan;
import com.bank.controller.Savings;
import com.bank.manager.MemberManager;
import com.bank.model.LoanDTO;
import com.bank.model.MemberDTO;
import com.bank.model.SavingsDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.bank.common.JDBCTemplate.close;
import static com.bank.common.JDBCTemplate.getConnection;


public class ProductMenu {
    Scanner sc = new Scanner(System.in);

    public void productMain(MemberDTO member){
        while(true){
            System.out.println(" ===== 상품메뉴 =====");
            System.out.println(" 1. 내 가입 상품");
            System.out.println(" 2. 적금");
            System.out.println(" 3. 대출");
            System.out.println(" 0. 뒤로 ");
            System.out.print("메뉴 선택 : ");
            int num = sc.nextInt();

            switch (num) {
                case 1 :
                    checkProductList(member);
                    break;
                case 2 :
                    ProductManageSavings(member); // erd에 product_id에 1번 (적금) 입력
                    break;
                case 3 :
                    ProductManageLoan(member); // erd에 product_id에 2번 (대출) 입력
                    break;
                default :
                    System.out.println();
                    System.out.println(" ** 잘못된 입력입니다. **");
                    System.out.println();
                    return;
            }
        }

    }
    public static void ProductManageSavings(MemberDTO member) {
        Savings savings = new Savings();

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        ResultSet rset = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("SelectWhichProduct");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1,member.getId()); // id를 넣어야 한다
            pstmt.setInt(2,1); // number 1 : savings

            rset = pstmt.executeQuery();

            if (rset.next()) {
                savings.savingsMainMenu(); // 성공을 하면 savings의 main menu로 이동
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
    }
    private void ProductManageLoan(MemberDTO member) {

        Loan loan = new Loan();

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        ResultSet rset = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("SelectWhichProduct");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1,member.getId()); // id를 넣어야 한다
            pstmt.setInt(2,2); // number 2 : loan

            rset = pstmt.executeQuery();

            if (rset.next()) {
                loan.loanMainMenu(member); // 성공을 하면 loan의 main menu로 이동을 한다..!
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
    }

    private void checkProductList(MemberDTO member) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();

        List<SavingsDTO> savingsList = null;
        List<LoanDTO> loanList = null;
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("checkLoanStatus");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, member.getId());
            rset = pstmt.executeQuery();
            loanList = new ArrayList<>();

            while (rset.next()) {
                LoanDTO loan = new LoanDTO();
                loan.setLoanAmount(rset.getLong("loan_amount"));
                loan.setInterestRate(rset.getDouble("interest_rate"));
                loan.setPeriod(rset.getInt("duration"));

                loanList.add(loan);
            }
            String query1 = prop.getProperty("checkSavingsStatus");

            pstmt = con.prepareStatement(query1);
            pstmt.setString(1, member.getId());
            rset = pstmt.executeQuery();
            savingsList = new ArrayList<>();

            while (rset.next()) {
                SavingsDTO savings = new SavingsDTO();
                savings.setSavingsAmount(rset.getLong("savings_amount"));
                savings.setInterestRate(rset.getDouble("interest_rate"));
                savings.setPeriod(rset.getInt("duration"));

                savingsList.add(savings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        {
            for (SavingsDTO savings : savingsList) {
                System.out.println("적금\n" + savings);
            }
            for (LoanDTO loan : loanList) {
                System.out.println("대출\n" + loan);
            }
        }
    }
}
