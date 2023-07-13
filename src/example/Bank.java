package example;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {

	public Map<Integer, BankAccount> account; //　口座たち
	
	public Bank() {
		account = new HashMap<>();
//		try (BufferedReader br = new BufferedReader(new FileReader("account.txt"))) {
//			String check;
//			String[] data = new String[4];
//			
//			int rd = 0;
//			
//			while ((check = br.readLine()) != null) {
//				if (check.equals("") == true) {
//					int acc = Integer.parseInt(data[0]);
//					int lock = Integer.parseInt(data[1]);
//					String pwd = data[2];
//					int balance = Integer.parseInt(data[3]);
//					
//					account.put(acc, new BankAccount(pwd, balance, lock));
//					rd = 0;
//					
//				} else {
//					
//					if (rd <= 3) {
//						data[rd] = check;
//						++rd;
//					}
//					
//					else {
//						
//					}
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		try (Scanner fp = new Scanner(new File("account.txt"))) {
			int acc;
			int lock;
			String pwd;
			int balance;
			
			while (fp.hasNextLine()) {
				acc = Integer.parseInt(fp.nextLine());
				lock = Integer.parseInt(fp.nextLine());
				pwd = fp.nextLine();
				balance = Integer.parseInt(fp.nextLine());
				
				fp.nextLine();
				
				account.put(acc, new BankAccount(pwd, balance, lock));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		int n = 0;
		for (int key : account.keySet()) {
			account.get(key).save(key, n);
			++n;
		}
	}
	
	public void bank() {
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		while (choice != -1) {
			
			System.out.println("\n");
			System.out.println("行動を選んでください。　1:口座を作る 　　　2:口座管理 　　　-1:出る");
			choice = scanner.nextInt();
			
			if (choice == 1) {
				make_account();
			}
			
			else if (choice == 2) {
				manage_account();
			}
			
			else if (choice == -1) {
				save();
				scanner.close();
			}
		}
		
		System.out.println("終了");
		scanner.close();
	}
	
	public void make_account() { // 口座を作る
		Scanner scanner = new Scanner(System.in);
		
		String pwd = null;
		int acc = 0;
		int choice = 0;
		
		while (true) {
			System.out.print("口座番号を入力: ");
			acc = scanner.nextInt();
			
			if (account.get(acc) == null) {
				break;
			} else {
				System.out.println("既に存在する口座番号です。");
			}
		}
		
		
		scanner = new Scanner(System.in);
		System.out.print("口座のパスワードを入力: ");
		pwd = scanner.nextLine();
		
		System.out.println();
		System.out.println("口座番号: " + acc + "   パスワード: " + pwd + "   口座が生成されました。");
		
		while (choice != 1 && choice != 2) // ローンを借りるかどうか
		{
			System.out.println();
			System.out.println("ローンを借りますか？     1.YES     2.NO");
			choice = scanner.nextInt();
			
			if (choice == 1) {
				int loan = 0;
				int amount;
				int years;
				
				double[] rate = new double[2];
				rate[0] = 0.2;
				rate[1] = 0.9;
				
				while (loan != 1 && loan != 2) 
				{
					System.out.println();
					System.out.println("ローンを選んでください。     1.住宅ローン(金利:0.2%)     2.オートローン(金利:0.9%)");
					loan = scanner.nextInt();
				}
				
				System.out.println();
				System.out.println("借りる金額を入力してください: ");
				amount = scanner.nextInt();
				
				System.out.println();
				System.out.println("期限(年)を入力してください: ");
				years = scanner.nextInt();
				
				System.out.println();
				System.out.println("加入が終わりました。");
				
				account.put(acc, new LoanAccount(pwd, 0, 0, amount, rate[loan-1], years));
			} 
			
			else if (choice == 2) { // 普通
				account.put(acc, new BankAccount(pwd, 0, 0));
			}
		}
	}
	
	public void manage_account() { // 口座管理
		Scanner scanner = new Scanner(System.in);
		
		int acc = 0;
		
		System.out.print("あなたの口座番号を入力: ");
		acc = scanner.nextInt();
		
		if (account.get(acc) != null) {
			if (account.get(acc).login() == 1) {
				account.get(acc).manage();
			}
		}
		
		else {
			System.out.println("口座を見つけられません。");
			System.out.println();
		}
	}
}
