package com.bank.view;

import com.bank.manager.MemberManager;

import java.util.Scanner;

public class AdminMenu {
    public void adminMenu() {
        Scanner sc = new Scanner(System.in);

        MemberManager memberManager = new MemberManager();

        while(true){
            System.out.println("관리자 모드");
            System.out.println("1. 회원 전체 조회");
            System.out.println("0. 뒤로");
            System.out.print("메뉴 선택 : ");

            int num = sc.nextInt();
            switch (num){
                case 1 :
                    memberManager.showAllMember();
                    break;
                default :
                    return;
            }

        }
    }



}
