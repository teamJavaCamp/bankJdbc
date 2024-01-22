package com.bank.manager;

import com.bank.controller.BankController;
import com.bank.model.MemberDTO;
import com.bank.model.TransactionDTO;

import java.util.Date;
import java.util.Scanner;

public class BankManager {

    public void showAcc(MemberDTO member){
        BankController bankController = new BankController();
        bankController.getAcc(member.getId());
    }

    public void deposit(MemberDTO member){
        Scanner sc = new Scanner(System.in);

        BankController bankController = new BankController();

        System.out.print("입금할 금액 : ");
        long deposit = sc.nextLong();

        if(deposit <= 0) {
            System.out.println();
            System.out.println(" ** 잘못된 금액입니다. ** ");
            System.out.println();
            return;
        }
        
        member.setBalance(member.getBalance() + deposit);
        int result = bankController.updateAccount(member.getId(), member.getBalance());

        if(result > 0){
            TransactionDTO transactionDTO = new TransactionDTO(new Date(), member.getId(), "입금" ,deposit); //내역 저장
            bankController.saveHistory(transactionDTO);

            System.out.println();
            System.out.println(" ** 입금 완료 **");
            System.out.println();
        }else{
            System.out.println();
            System.out.println(" ** 입금 실패 **");
            System.out.println();
        }

    }

    public void withdraw(MemberDTO member) {

        Scanner sc = new Scanner(System.in);

        BankController bankController = new BankController();

        System.out.print("출금할 금액 : ");
        long withdraw = sc.nextLong();

        if(withdraw <= 0){
            System.out.println();
            System.out.println(" ** 잘못된 금액입니다. ** ");
            System.out.println();
            return;
        }
        if(member.getBalance() < withdraw){
            System.out.println();
            System.out.println(" ** 출금할 금액이 잔고보다 큽니다. ** ");
            System.out.println();
        }else{
            member.setBalance(member.getBalance() - withdraw);

            int result = bankController.updateAccount(member.getId(), member.getBalance());

            if(result > 0){
                TransactionDTO transactionDTO = new TransactionDTO(new Date(), member.getId(), "출금" ,withdraw * -1); //내역 저장
                bankController.saveHistory(transactionDTO);

                System.out.println();
                System.out.println(" ** 출금 완료 ** ");
                System.out.println();
            }else{
                System.out.println();
                System.out.println(" ** 출금 실패 ** ");
                System.out.println();
            }
        }
    }

    public void transfer(MemberDTO member) {
        Scanner sc = new Scanner(System.in);
        BankController bankController = new BankController();

        System.out.print("어떤 계좌로 돈을 보낼까요? : ");
        String accNum = sc.nextLine();
        System.out.println();

        if(member.getAccNo().equals(accNum)) {                //내 계좌로는 보낼 수 없다.
            System.out.println();
            System.out.println(" ** 이 계좌로 보낼 수 없습니다. **");
            System.out.println();
            return;
        }

        System.out.print("이체할 금액 : ");
        long transfer = sc.nextLong();

        if(member.getBalance() < transfer){
            System.out.println();
            System.out.println(" ** 이체할 금액이 잔고보다 큽니다. ** ");
            System.out.println();
        }else {
            member.setBalance(member.getBalance() - transfer);
            int result = bankController.updateAccount(member.getId(), member.getBalance());
            if (result > 0) {
                TransactionDTO transactionDTO = new TransactionDTO(new Date(), member.getId(), "계좌이체" ,transfer * -1); //내역 저장
                bankController.saveHistory(transactionDTO);

                System.out.println();
                System.out.println(" ** 성공적으로 이체 되었습니다. ** ");
                System.out.println();
            } else {
                System.out.println();
                System.out.println(" ** 이체 실패 ** ");
                System.out.println();
            }
        }
    }
}
