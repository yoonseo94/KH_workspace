package com.io.employee.run;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.io.employee.model.vo.Employee;

/**
 * [실습문제 3]
 * <p>
 * - 클래스 : 07_OOP프로젝트 com.oop.employee.model.vo.Employee 복사 ->
 * com.io.employee.model.vo.Employee생성. - 실행용클래스 :
 * com.io.employee.run.EmployeeTest 메소드 : saveEmployee(); => Employee객체배열을 사용할
 * 것. => 5개의 Employee객체생성후 DataOutputStream을 통해서 empoloyee.dat에 쓰기.
 *
 * <p>
 * 메소드 : loadEmployee(); => employee.dat 파일의 내용을 데이터 종류별로 읽어서 => Employee 객체에
 * 저장하고 => 화면 출력 확인함
 *
 * 
 * @author shqkel1863
 *
 */
public class EmployeeTest {

	public static void main(String[] args) {
		saveEmployee();
		loadEmployee();
	}

	public static void loadEmployee() {
		Employee[] empArr = new Employee[5];
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("emp/employee.dat")))) {
			for (int i = 0; i < empArr.length; i++) {
				empArr[i] = (Employee) ois.readObject();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 확인
		for (int i = 0; i < empArr.length; i++) {
			empArr[i].printEmployee();
		}
	}

	public static void saveEmployee() {
		Employee[] empArr = new Employee[5];
		empArr[0] = new Employee(0, "제갈량", '남', "010-3131-3131", "영업부", 30000000, 0.15);
		empArr[1] = new Employee(1, "조자룡", '남', "010-8901-2345", "영업부", 20000000, 0.1);
		empArr[2] = new Employee(2, "유비", '남', "010-1234-5678", "영업부", 50000000, 0.2);
		empArr[3] = new Employee(3, "조조", '남', "010-5454-4545", "영업부", 34000000, 0.05);
		empArr[4] = new Employee(4, "초선", '여', "010-8787-7878", "영업부", 24000000, 0.1);

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("emp/employee.dat")))) {
			for (int i = 0; i < empArr.length; i++) {
				Employee emp = empArr[i];
				oos.writeObject(emp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("직원저장완료!");

	}

}
