package com.exception.charcheck;

public class CharacterProcess {

	/**
	 * 문자열 s 중에서 알파벳 수를 리턴하는 메소드
	 * 
	 * checked 예외 : 예외처리강제화(예외처리하던지, throws 구문을 작성하던지)
	 * unchecked 예외 : 예외처리강제화하지 않음.
	 * 
	 * @param s
	 * @return
	 * @throws CharCheckException
	 */
	public int countAlpha(String s) {
		// 문자열 메소드 string.indexOf(검사문자열)은 검사문자열의 index를 리턴한다.
		// string내에 검사문자열이 존재하지 않으면 -1을 리턴한다.
//		if (s.indexOf(" ") > -1)
		if (s.contains(" "))
			throw new CharCheckException("체크할 문자열 안에 공백 포함할 수 없습니다.");

		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i)) || Character.isLowerCase(s.charAt(i)))
				cnt++;
		}
		return cnt;
	}

}
