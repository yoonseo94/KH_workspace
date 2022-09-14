package kh.firstmini.run;

import kh.firstmini.manager.MenuIO;
import kh.firstmini.view.ChoiceView;

public class Run {
	public static void main(String[] args) {
		
		// 프로그램이 관리하는 점포, 메뉴 정보파일 생성
		new MenuIO().printStoreOnly();
		new MenuIO().printMenuOnly();

		ChoiceView cv = new ChoiceView();
		cv.ChoiceViewMain();
	}

}
