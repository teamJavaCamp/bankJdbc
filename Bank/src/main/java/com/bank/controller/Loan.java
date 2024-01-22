package com.bank.controller;

import com.bank.model.LoanDTO;
import com.bank.model.MemberDTO;



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

public class Loan {
    Scanner sc = new Scanner(System.in);

        int takeLoan;       //대출받을 금액
    public void loanMainMenu(MemberDTO member) {
        LoanDTO loan = new LoanDTO();

        if (member.getAge() < 19) {
            System.out.println();
            System.out.println(" ** 미성년자는 대출 받기가 어렵습니다. ** ");
            System.out.println();
            return;
        }

        while (true) {

            int a;

            if (member.getAge() < 29) {
                a = 1000;
            } else if (member.getAge() < 39) {
                a = 3000;
            } else if (member.getAge() < 49) {
                a = 5000;
            } else {
                a = 3000;
            }

            System.out.println(" ====== 대출 메뉴 ======");
            System.out.println(" ** 최대 대출 가능 금액은 " + a + "만원 입니다. ** ");
            System.out.print(" 대출받을 금액을 입력하세요 (단위 : 만원): ");
            takeLoan = sc.nextInt();

            if (takeLoan <= a && takeLoan > 0) {
                loan.setLoanAmount(takeLoan);
                break;
            }
        }

        while(true){
            System.out.println("===== 대출메뉴 =====");
            System.out.println(" 1) 12개월 (이자율 4.0%) ");
            System.out.println(" 2) 24개월 (이자율 4.18%) ");
            System.out.println(" 3) 36개월 (이자율 4.36%) ");
            System.out.println(" 4) 48개월 (이자율 4.5%) ");
            System.out.println(" 0) 메인메뉴로 ");

            System.out.print(" 대출 상환 기간 선택 :  ");

            int select = sc.nextInt();

            switch (select){
                case 1:
                    LoaninterestRateAndPeriod(new LoanDTO(1,12,4, takeLoan));
                    member.setBalance(member.getBalance() + (takeLoan * 10000));
                    System.out.println("대출이 완료되었습니다.");
                    return;
                case 2:
                    LoaninterestRateAndPeriod(new LoanDTO(1,24,4.18, takeLoan));
                    member.setBalance(member.getBalance() + (takeLoan * 10000));
                    System.out.println("대출이 완료되었습니다.");
                    return;
                case 3:
                    LoaninterestRateAndPeriod(new LoanDTO(1,36,4.36, takeLoan));
                    member.setBalance(member.getBalance() + (takeLoan * 10000));
                    System.out.println("대출이 완료되었습니다.");
                    return;
                case 4:
                    LoaninterestRateAndPeriod(new LoanDTO(1,48,4.5, takeLoan));
                    member.setBalance(member.getBalance() + (takeLoan * 10000));
                    System.out.println("대출이 완료되었습니다.");
                    return;

                case 0: return;
                default:
                    System.out.println();
                    System.out.println(" ** 잘못된 입력입니다. ** ");
                    System.out.println();
                    break;
            }
        }
    }
    public void LoaninterestRateAndPeriod(LoanDTO loanDTO) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        ResultSet rset = null;
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("LoanInterestRateAndDuration");

            pstmt = con.prepareStatement(query);

            pstmt.setDouble(1,loanDTO.getInterestRate()); // set loan rate
            pstmt.setInt(2,loanDTO.getPeriod());
            pstmt.setLong(3,loanDTO.getLoanAmount());

            rset = pstmt.executeQuery();

            if (rset.next()) {
                checkLoanStatus(loanDTO);
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
    public void checkLoanStatus(LoanDTO loan) {

        System.out.println("========= 가입한 상품 =========");
        System.out.println("댜출한 금액 : " + loan.getLoanAmount() + "만원");
        System.out.println("금리 : " + loan.getInterestRate() + "%");
        System.out.println("대출 기간 : " + loan.getPeriod() + "개월");
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
