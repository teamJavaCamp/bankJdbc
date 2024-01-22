package com.bank.view;

import com.bank.manager.MemberManager;

import java.util.Scanner;

public class LogInMenu {

    public void loginMenu(){
        Scanner sc = new Scanner(System.in);
        MemberManager memberManager = new MemberManager();
        AdminMenu adminMenu = new AdminMenu();

        while(true){
            System.out.println("======로그인 메뉴=====");
            System.out.println("    1. 회원가입");
            System.out.println("    2. 로그인");
            System.out.println("    3. 관리자 모드");
            System.out.println("    0. 종료");
            System.out.println("===================");
            System.out.print("메뉴를 선택하세요 : ");

            int num = sc.nextInt();

            switch (num){
                case 1 :
                    memberManager.signUp();
                    break;
                case 2 :
                    memberManager.signIn();
                    break;
                case 3 :
                    adminMenu.adminMenu();
                    break;
                case 0 :
                    return;
                default :
                    System.out.println();
                    System.out.println(" ** 잘못된 입력입니다. **");
                    System.out.println();
                    break;
            }
        }
    }
}
