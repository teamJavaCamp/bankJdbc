package com.bank.model;
public class MemberDTO implements java.io.Serializable{
    private String name;        //이름
    private int age;            //나이
    private String gender;        //성별
    private String id;          //아이디
    private String pwd;         //패스워드
    private String accNo;      //계좌번호
    private Long balance;        //잔고

    public MemberDTO() {
    }

    public MemberDTO(String name, int age, String gender, String id, String pwd, String accNum, Long balance) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.pwd = pwd;
        this.accNo = accNum;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", accNum='" + accNo + '\'' +
                ", balance=" + balance +
                '}';
    }
}
