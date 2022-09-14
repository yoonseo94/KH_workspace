package kh.java.io._char.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRWStudy {

	public static void main(String[] args) {
		FileRWStudy study = new FileRWStudy();
//		study.test1();
//		study.test2();
		study.test3();
	}
	
	/**
	 * try with resource 절
	 * - try() 안에 선언한 스트림객체는 자동으로 반납
	 * - 1.7부터 지원
	 */
	private void test3() {
		File inFile = new File("sample.txt");
		File outFile = new File("sampleCopyCopy.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(inFile));
			 BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));) {
			
			String data = null;
			while((data = br.readLine()) != null) {
				System.out.println(data);
				bw.write(data); // 개행문자가 제거되어 있음.
				bw.write("\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 
	 */
	private void test2() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			File inFile = new File("sample.txt");
			File outFile = new File("sampleCopyCopy.txt");
			br = new BufferedReader(new FileReader(inFile));
			bw = new BufferedWriter(new FileWriter(outFile));
			
			String data = null;
			while((data = br.readLine()) != null) {
				System.out.println(data);
				bw.write(data); // 개행문자가 제거되어 있음.
				bw.write("\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 텍스트파일을 읽는 데 특화된 문자기반스트림
	 * - 주스트림 FileReader | FileWriter
	 * - 보조스트림 BufferedReader | BuffererWriter
	 */
	private void test1() {
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			File inFile = new File("sample.txt"); // 존재하는/존재하지 않는 파일/디렉토리을 가리키는 자바객체
			File outFile = new File("sampleCopyCopy.txt");
			
			fr = new FileReader(inFile);
			fw = new FileWriter(outFile);
			
			int data = 0;
			while((data = fr.read()) != -1) {
				System.out.println((char) data);
				fw.write(data);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
