package com.ssafy.di3;

public class Test {
	public static void main(String[] args) {
		Desktop computer1 = new Desktop();
		Laptop computer2 = new Laptop();
		Programmer p1 = new Programmer(computer1);
//		p1.coding();
		
		p1.setComputer(computer2);
		p1.coding();
		
		
		Programmer p2 = new Programmer(computer2);
//		p2.coding();
	}
}
