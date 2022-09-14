package kh.java;

import java.util.Arrays;
import java.util.Random;

public class LottoGenerator {

	/**
	 * 1 ~ 45사이의 랜덤한수 6개 뽑기
	 * - 난수 중복이 없어야 한다.
	 * - 오름차순정렬 처리
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LottoGenerator lottoGenerator = new LottoGenerator();
		int[] lotto = lottoGenerator.generate();
		lotto = lottoGenerator.sort(lotto);
		
		System.out.println(Arrays.toString(lotto));
	}
	
	public int[] sort(int[] lotto) {
		return null;
	}
	
	public int[] generate() {
		int[] lotto = new int[6];
		Random rnd = new Random();
		
		
		outer:
		for(int i = 0;;) {
			int n = rnd.nextInt(45) + 1;
			
			// 중복검사
			for(int j = 0; j < i; j++) {
				if(lotto[j] == n)
					continue outer;
			}
			
			lotto[i++] = n;
			
			if(i == lotto.length)
				break;
		}
		
		return lotto;
	}

}
