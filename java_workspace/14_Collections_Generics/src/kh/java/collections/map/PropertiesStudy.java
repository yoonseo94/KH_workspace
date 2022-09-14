package kh.java.collections.map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * Properties
 * - K, V의 타입으로 String, String으로 단순화 시킨 컬렉션클래스
 * - 설정정보 표현에 최적화된 타입
 * 
 * Properties객체 -> 설정파일 출력
 * 설정파일 입력 -> Properties객체 
 */
public class PropertiesStudy {

	public static void main(String[] args) {
		PropertiesStudy study = new PropertiesStudy();
		study.test1();
		study.test2();
	}
	
	/**
	 * 설정파일 입력 -> Properties객체
	 */
	private void test2() {
		Properties prop = new Properties();
		
		try(FileReader fr = new FileReader("user-info.properties");
			FileInputStream fis = new FileInputStream("user-info.xml")){
//			prop.load(fr);			
			prop.loadFromXML(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("id"));
		System.out.println(prop.getProperty("password"));
		System.out.println(prop.getProperty("birthday"));
		
		System.out.println(prop);
		
		// 모든 요소 열람
		Set<String> keySet = prop.stringPropertyNames();
		for(String key : keySet) {
//			System.out.println(key);
			String value = prop.getProperty(key);
			System.out.println(key + " = " + value);
		}
	}


	/**
	 * Properties객체 -> 설정파일 출력 
	 */
	private void test1() {
		Properties prop = new Properties();
		
		// 요소 저장 setProperty
		prop.setProperty("id", "honggd");
		prop.setProperty("password", "1234");
		prop.setProperty("birthday", "1999/09/09");
		
		// 요소 가져오기 getProperty
		System.out.println(prop.getProperty("id"));
		System.out.println(prop.getProperty("password"));
		System.out.println(prop.getProperty("birthday"));
		
		System.out.println(prop);
		
		// 설정파일 출력
		try (FileWriter fw = new FileWriter("user-info.properties");
			FileOutputStream xmlfos = new FileOutputStream("user-info.xml");
				){
//			prop.store(fw, "user-info"); // 파일출력스트림, 주석
			prop.storeToXML(xmlfos, "user-info"); // xml문서 출력용
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		
	}

}






