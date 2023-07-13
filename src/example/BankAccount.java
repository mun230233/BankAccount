package example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BankAccount {

	public int lock;
	public String pwd;
	public int balance;
	
	public BankAccount(String pwd, int balance, int lock) {
		this.pwd = pwd;
		this.balance = balance;
		this.lock = lock;
	}
	
	public void save(int account, int num) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("account.txt", (num==0?false:true)))) {
			bw.write(account + "\n");
			bw.write(lock + "\n");
			bw.write(pwd + "\n");
			bw.write(balance + "\n");
			bw.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int login() {
		int count = 0; // LOCK　count
		String check = null;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("");
		
		if (lock == 0) // lockされない場合だけ
		{
			while (pwd.equals(check) == false) {
				System.out.println("パスワードを入力してください。");
				check = scanner.nextLine();
				++count;
				
				if (count == 3) { // ３回間違えるとLOCKされる
					System.out.println("3回パスワードを間違えたので口座を閉めます。");
					lock = 1;
					
					return 0;
				}
			}
			
			return 1;
		}
		
		else {
			System.out.println("この口座はLOCKされました。近くの銀行に問い合わせてください。");
			return 0;
		}
	}
	
	public void manage() {
		Scanner scanner = new Scanner(System.in);
		int ch = 0;
		int money = 0;
		
		while (ch != -1) {
			System.out.println();
			info();
			System.out.println("1.預金 　　　　2.出金       -1.出る");
			ch = scanner.nextInt();
			
			if (ch == 1 || ch == 2) {
				System.out.print("金額を入力してください");
				money = scanner.nextInt();
				
				if (ch == 1) {
					deposit(money);
				} else {
					withdrawal(money);
				}
				
			}
		}
	}
	
	public void info() {
		System.out.println();
		System.out.println("現在残高: " + balance + "円");		
	}
	
	public void deposit(int money) {
		balance += money;
		System.out.println();
		System.out.println(money + "円を預金しました。");
	}
	
	public void withdrawal(int money) {
		System.out.println();
		
		if (balance >= money) {
			balance -= money;
			System.out.println(money + "円を出金しました。");
		}
		
		else {
			System.out.println("お金が足りなくて引き出せないです。");
		}
	}
}






