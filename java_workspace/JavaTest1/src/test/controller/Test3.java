package test.controller;

public class Test3 {

	public static void main(String[] args) {
		double sum = 0.0;
		double avg = 0.0;
		int n = 1;
		
		while(n <= 100){
			sum += n;
			n++;
		}
		
		avg = sum / 100; 
		
		System.out.println("합계 : "+ sum);
		System.out.println("평균 : " + avg);		
	}

}
