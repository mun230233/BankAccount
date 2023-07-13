package example;

import java.util.Scanner;

public class LoanAccount extends BankAccount {

	public Loan loan;
	
	public LoanAccount(String pwd, int balance, int amount, int lock, double rate, int years) {
		super(pwd, balance, lock);
		loan = new Loan(amount, rate, years);
	}
	
	public void info() { // 情報出力
		System.out.println();
		System.out.println("現在残高: " + balance + "円");
		
		loan.info(); // ローンの情報
	}
	
	public void manage() { // 口座管理
		Scanner scanner = new Scanner(System.in);
		int ch = 0;
		int money = 0;
		
		while (ch != -1) {
			System.out.println();
			info(); // 口座情報出力
			
			System.out.println("1.預金 　　　　2.出金       3.返す       -1.出る");
			ch = scanner.nextInt();
			
			if (ch == 1 || ch == 2) { // 預金　出金
				System.out.print("金額を入力してください");
				money = scanner.nextInt();
				
				if (ch == 1) {
					deposit(money);
				} else {
					withdrawal(money);
				}
				
			} else if (ch == 3) { // 返す
				while (ch != 1 && ch != 2 && ch != -1) {
					System.out.println();
					System.out.println("支払い方法を選択してください。   1.全額返す　　 　2.返す金額を入力     -1:出る");
					ch = scanner.nextInt();
					
					System.out.println();
					
					if (ch == 1) { // 全額返す
						if (balance >= loan.amount_operation()) {
							loan.pay_back(0, 0);
						} else {
							System.out.println("お金が足りないです。");
						}
					}
					
					else if (ch == 2) { // 金額を入力
						System.out.print("返す金額を入力してください");
						money = scanner.nextInt();
						
						if (money < 0) {
							System.out.println("正数を入力してください。");
						}
						
						else if (money > balance) {
							System.out.println("お金が足りないです。");
						}
						
						else {
							loan.pay_back(1, money);
						}
					}	
				}
			}
		}
	}
}
