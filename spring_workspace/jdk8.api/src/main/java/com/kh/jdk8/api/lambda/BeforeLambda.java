package com.kh.jdk8.api.lambda;

/**
 * a^2 + b^2 = c^2
 *
 */
public class BeforeLambda {

	public static void main(String[] args) {
		System.out.println(new BeforeLambda().pita(3, 4)); // 5.0
		
		Pita pita = new Pita() {
			@Override
			public double calc(double a, double b) {
				return Math.sqrt(a * a + b * b);
			}
		};
		
		System.out.println(pita.calc(3, 4)); // 5.0
	}

	public double pita(double a, double b) {
		return Math.sqrt(a * a + b * b);
	}
}

