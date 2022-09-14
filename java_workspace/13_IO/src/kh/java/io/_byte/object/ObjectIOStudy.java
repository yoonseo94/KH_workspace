package kh.java.io._byte.object;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIOStudy {

	public static void main(String[] args) {
		ObjectIOStudy study = new ObjectIOStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
		study.test5();
		
	}
	
	/**
	 * User[] 안의 User객체를 깊은 복사하기
	 * - 내용이 동일한 User객체 새배열 담기
	 */
	public void test5() {
	
		User[] users = new User[3];
		users[0] = new User("honggd", "1234", 1000);
		users[1] = new User("ssinsa", "1234", 2000);
		users[2] = new User("sejong", "1234", 3000);
		
		// 복사배열
		User[] copyUsers = new User[users.length];
		for(int i = 0; i < users.length; i++) {
			copyUsers[i] = new User(users[i]); 
		}
		
		for(User u : users)
			System.out.println(u.hashCode());
		for(User u : copyUsers)
			System.out.println(u.hashCode());
	
	}
	
	/**
	 * User[]을 ObjectOutputStream으로 출력하기
	 */
	private void test4() {
		User[] users = new User[3];
		users[0] = new User("honggd", "1234", 1000);
		users[1] = new User("sinsa", "1234", 2000);
		users[2] = new User("sejong", "1234", 3000);

		System.out.println("출력전!");
		for(User user : users) {
			System.out.println(user.hashCode());
		}
		
		// usersArr.ser 파일에 User[]객체를 출력
		// 1.스트림생성 - try with resource절
		// 2.출력
		// 3.스트림반납 - try with resource절
		
		try(ObjectOutputStream oos = 
				new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("usersArr.ser")));){
			oos.writeObject(users);
		} catch (IOException e) {
			e.printStackTrace(); // 예외발생시 callstack 확인, 예외객체, 에외메세지 확인
		}
		
		System.out.println("User[] 출력 완료!");
		
		// usersArr.ser 파일로부터 User[] 읽어오기
		// 1.스트림생성 - try with resource절
		// 2.입력
		// 3.스트림반납 - try with resource절
		try(ObjectInputStream ois = 
				new ObjectInputStream(new BufferedInputStream(new FileInputStream("usersArr.ser")));){
			User[] usersArr = (User[]) ois.readObject();
			
			for(User user : usersArr) {
				System.out.println(user.hashCode());
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 저장된 객체의 수를 모를때 처리 
	 */
	public void test3() {
		User[] users = new User[100]; // 모자라지 않을 크기로 선언
		int index = 0; // 인덱스관리 변수, 입력된 객체수
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"));){
			while(true) {
				User user = (User) ois.readObject(); // 더이상 읽을 객체가 없을때 EOFException (End Of File) 
				users[index++] = user;
			}
		} catch (EOFException e) {
			// 모든 객체를 다 읽었을때
			for(int i = 0; i < index; i++) {
				System.out.println(users[i]);
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 같은 파일에 대해서 입출력스트림을 동시에 처리하지 말것.
	 */
	public void test2() {
//		FileInputStream fis = new FileInputStream("users.ser");
//		ObjectInputStream ois = new ObjectInputStream(fis);
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"));){
			User[] users = new User[3];
			for(int i = 0; i < users.length; i++) {
				users[i] = (User) ois.readObject(); // down casting
			}
			// users확인
			for(User user : users) {
				System.out.println(user);
			}
		} catch (ClassNotFoundException | IOException e) {
			// multi catch절(1.7) - 관련없는 예외클래스를 묶어서 처리할 수 있다.
			e.printStackTrace();
		}
	}
	
	/**
	 * ObjectInputStream | ObjectOutputStream
	 * - 객체단위로 읽고 쓰기 가능한 보조스트림.
	 * - FileInputStream | FileOutputStream 주스트림과 함께 사용해야 한다.
	 * 
	 * - 읽고 쓰기할 객체의 클래스는 Serializable인터페이스를 반드시 구현해야한다.
	 * - 직렬화 기능 반드시 필요.
	 */
	private void test1() {
		
		User[] users = new User[3];
		users[0] = new User("honggd", "1234", 1000);
		users[1] = new User("sinsa", "1234", 2000);
		users[2] = new User("sejong", "1234", 3000);
		
//		FileOutputStream fos = new FileOutputStream("users.ser");
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"));){
			for(int i = 0; i < users.length; i++) {
				System.out.println(i);
				oos.writeObject(users[i]); // User 객체 단위로 출력!
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
