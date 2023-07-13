package example;

public class Loan {
	public int amount; // 借りた金額
	public int years; // 期限
	public double rate; // 金利
	
	public Loan(int amount, double rate, int years) {
		this.amount = amount;
		this.rate = rate;
		this.years = years;
	}
	
	public int amount_operation() { // 返す金額計算
		return amount + (int)((rate/100 * amount) * years);
	}
	
	public void info() {
		if (amount > 0) {
			System.out.println("借りた金額: " + amount + "円       戻す金額: " + amount_operation() + "円");
			System.out.println("金利: " + rate + "%       期限: " + years + "年");	
		}
		
		else {
			System.out.println("ローン: なし");
		}
	}
	
	public void pay_back(int ver, int m) { // 返す
		System.out.println();
		
		if (ver == 0) {
			amount = 0;
			System.out.println("全額支払いました。");
		} else {
			amount -= m;
			System.out.println(m + "円支払いました。残った金額は" + amount + "円です。");
		}
	}
}
