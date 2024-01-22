package com.bank.view;

import com.bank.manager.BankManager;
import com.bank.manager.MemberManager;
import com.bank.model.MemberDTO;

import java.util.Scanner;

public class MainMenu {

    public void mainMenu(MemberDTO member){
        BankManager bankManager = new BankManager();
        MemberManager memberManager = new MemberManager();
        ProductMenu productMenu = new ProductMenu();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println(" ===== 메인메뉴 =====");
            System.out.println("1. 계좌 조회");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 계좌이체");
            System.out.println("5. 마이페이지");
            System.out.println("6. 상품페이지");
            System.out.println("0. 로그아웃");
            System.out.print("메뉴를 선택하세요 : ");

            int num = sc.nextInt();

            switch(num){
                case 1:
                    bankManager.showAcc(member);
                    break;
                case 2 :
                    bankManager.deposit(member);
                    break;
                case 3 :
                    bankManager.withdraw(member);
                    break;
                case 4 :
                    bankManager.transfer(member);
                    break;
                case 5 :
                    memberManager.printInfo(member);
                    break;
                case 6 :
                    productMenu.productMain(member);
                    break;
                case 0 :
                    return;
                default :
                    System.out.println("잘못된 입력입니다.");
                    break;
            }

        }
    }
}
