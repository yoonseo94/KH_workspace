package com.io.test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [실습문제 1] 키지 : com.io.test1 래스 : Test1 >> main() 포함 => fileSave() 메소드 실행함
 * 
 * >> 메소드 추가함 + fileSave() : void 
 * => 키보드로 사용할 파일명을 입력받음 BufferedReader의 readLine() 사용함 
 * => 파일출력용 스트림 객체 생성함 FileWriter 사용함 
 * => 화면에 "파일에 저장할 내용을 입력하시오."
 * 출력 입력값을 읽어들여서 바로 파일에 기록 저장처리 반복실행함 
 * => "exit"가 입력되면, 반복은 종료하고 화면에 "파일에 성공적으로 저장되었습니다." 출력하고 
 * => 파일출력 스트림을 닫음.
 * 
 * [실습문제 2] - 위에서 작성한 클래스에 메소드 추가함 
 * >> 메소드 + fileRead() : void 
 * => 키보드로 읽을 대상파일명을 입력받음 
 * => 파일 읽기용 스트림 객체 생성함 : FileReader 사용 
 * => 파일 안의 내용을 읽어서, StringBuilder에 보관함 
 * => 다 읽은 다음, StringBuilder 에 보관된 값을 String으로 바꾸어 화면에 출력함
 * 
 * @author shqkel1863
 *
 */
public class Test1 {

	public static void main(String[] args) {
		Test1 t1 = new Test1();
//		t1.fileSave();
		t1.fileRead();

	}

	public void fileSave() {
		String fileName = null;
		BufferedWriter fbw = null;

		// BufferedReader는 문자기반 보조스트림. readLine() 메소드로 줄단위로 읽어올 수 있다.
		// InputStreamReader는 바이트기반스트림을 문자기반으로 연결하는 브릿지역할을 해준다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("> 작성할 파일명을 입력하세요 : ");
			fileName = br.readLine();

			// test1폴더가 존재한다는 가정하에 진행한다.
			fbw = new BufferedWriter(new FileWriter("test1/" + fileName));
			String data = "";
			System.out.println("파일에 저장할 내용을 입력하시오(종료:exit+엔터) : ");
			while ((data = br.readLine()) != null) {
				// 사용자의 입력내용이 exit이면, 쓰기작업을 종료함.
				if ("exit".equals(data))
					break;

				fbw.write(data + "\n"); // 13 \r, 10 \n
				// fbw.flush();

			}

			System.out.println("파일에 성공적으로 저장했습니다.");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fbw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void fileRead() {
		FileReader fr = null;
		BufferedReader fbr = null;
		String fileName = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("> 읽어올 파일명을 입력하세요 : ");

		try {
			fileName = br.readLine();

			// FileReader는 파일에 읽기용 문자기반스트림.
			fbr = new BufferedReader(new FileReader("test1/" + fileName));

			// 읽어온 데이터를 담을 정수형변수
			String data = null;

			StringBuilder sb = new StringBuilder();
			while ((data = fbr.readLine()) != null) {
				System.out.println(data);
				sb.append(data);
				sb.append("\n");
			}

			// 출력
			System.out.println(sb.toString());

		} catch (FileNotFoundException e) {
			System.out.println("[" + fileName + "] 해당파일을 찾을 수 없습니다. ");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fbr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
