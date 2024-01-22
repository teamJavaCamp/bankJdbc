package com.bank.manager;

import com.bank.controller.AdminController;
import com.bank.controller.SignController;
import com.bank.model.MemberDTO;
import com.bank.view.MainMenu;

import java.util.Scanner;

public class MemberManager {
    public void signUp() {

        Scanner sc = new Scanner(System.in);

        SignController signController = new SignController();

        System.out.println("===== 회원가입 =====");
        System.out.print("이름 : ");
        String name = sc.nextLine();

        System.out.print("나이 : ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("성별 : ");
        String gender = sc.nextLine();

        System.out.print("id : ");
        String id = sc.nextLine();

        System.out.print("password : ");
        String pwd = sc.nextLine();

        String account = signController.createAccount(); //계좌 생성

        MemberDTO member = new MemberDTO(name,age,gender,id,pwd,account,0L);
        int result = signController.signUp(member);     //회원 가입


        if (result > 0) {
            System.out.println();
            System.out.println(" ** 회원가입이 완료되었습니다. **");
            System.out.println();
        } else {
            System.out.println();
            System.out.println(" ** 회원가입 실패 **");
            System.out.println();
        }
    }

    public void signIn() {
        Scanner sc = new Scanner(System.in);
        SignController signController = new SignController();
        MainMenu mainMenu = new MainMenu();

        System.out.print("id : ");
        String id = sc.nextLine();
        System.out.print("pwd : ");
        String pwd = sc.nextLine();

        int result = signController.signIn(id,pwd);

        if(result > 0){
            MemberDTO member = signController.getMember(id);
            mainMenu.mainMenu(member);
        }else{
            System.out.println("아이디 또는 비밀번호가 틀립니다.");
        }
    }


    public void printInfo(MemberDTO member) {
        System.out.println(member);
    }

    public void showAllMember() {
        AdminController adminController = new AdminController();

        adminController.showAllMember();
    }

}
