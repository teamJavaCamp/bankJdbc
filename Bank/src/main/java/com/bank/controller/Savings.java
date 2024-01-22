package com.bank.controller;



import com.bank.model.SavingsDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.bank.common.JDBCTemplate.close;
import static com.bank.common.JDBCTemplate.getConnection;


public class Savings {
    Scanner sc = new Scanner(System.in);
    SavingsDTO savings = new SavingsDTO();

    public void savingsMainMenu() {
        long deposit = 0;
        while (true) {
            System.out.println("*** 적금 가입 메뉴  ***");
            System.out.print("월 납입 할 금액을 입력하세요 (한도 : 50만원), (단위 : 만원) : ");
            deposit = sc.nextLong();          // 납입 금액
            sc.nextLine();
            if (deposit > 50) {
                System.out.println();
                System.out.println("납입 한도를 초과했습니다. 다시 입력해주세요.");
                System.out.println();
            } else if (deposit <= 0) {
                System.out.println();
                System.out.println("잘못된 입력입니다.");
                System.out.println();
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("***** 상품의 기간 선택 *****");
            System.out.println("1) 12개월 (금리 4%)");
            System.out.println("2) 24개월 (금리 4.5%)");
            System.out.println("3) 48개월 (금리 5%)");
            System.out.print("상품 기간을 선택해 주세요 : ");
            int yearsToDeposit = sc.nextInt();

            switch (yearsToDeposit) {
                case 1:
                    SavingsinterestRateAndPeriod(new SavingsDTO(1, 12, 4, deposit));
                    break;
                case 2:
                    SavingsinterestRateAndPeriod(new SavingsDTO(1, 24, 4.5, deposit));
                    break;
                case 3:
                    SavingsinterestRateAndPeriod(new SavingsDTO(1, 48, 5, deposit));
                    break;
                default:
                    System.out.println();
                    System.out.println(" ** 잘못된 입력입니다. **");
                    System.out.println();
                    break;
            }
        }
    }

    public void SavingsinterestRateAndPeriod(SavingsDTO savingsDTO) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        ResultSet rset = null;
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("SavingsInterestRateAndDuration");

            pstmt = con.prepareStatement(query);

            pstmt.setDouble(1,savingsDTO.getInterestRate()); // set loan rate
            pstmt.setInt(2,savingsDTO.getPeriod());
            pstmt.setLong(3,savingsDTO.getSavingsAmount());

            rset = pstmt.executeQuery();

            if (rset.next()) {
                checkSavingsStatus(savingsDTO);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(pstmt);
            close(con);
            close(rset);
        }
    }

    public void checkSavingsStatus(SavingsDTO savings) {

        System.out.println("========= 가입한 상품 =========");
        System.out.println("댜출한 금액 : " + savings.getSavingsAmount() + "만원");
        System.out.println("금리 : " + savings.getInterestRate() + "%");
        System.out.println("대출 기간 : " + savings.getPeriod() + "개월");
        System.out.println("==============================");

        System.out.println("선택한 상품이 맞습니까 ?");
        System.out.print("1) 네    2) 아니요    : ");
        int num = sc.nextInt();
        switch (num) {
            case 1:
                System.out.println("가입이 완료되었습니다."); break;
            case 2:
                System.out.println("취소합니다."); break;
            default:
                System.out.println();
                System.out.println(" ** 잘못된 입력입니다. **");
                System.out.println();
                break;
        }
    }
}




