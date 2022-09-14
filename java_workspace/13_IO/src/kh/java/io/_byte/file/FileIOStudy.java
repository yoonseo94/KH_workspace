package kh.java.io._byte.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOStudy {

	public static void main(String[] args) {
		FileIOStudy study = new FileIOStudy();
		study.test1();
	}

	/**
	 * 대상이 file인 입출력스트림
	 * - 기본스트림 FileInputStream | FileOutputStream
	 * - 보조스트림 BufferedInputStream | BufferedOutputStream
	 * 
	 */
	private void test1() {
		// 파일경로는 프로젝트 루트기준으로 조회한다.
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			// 절대주소 : 파일시스템 디렉토리의 최상위구조 C:/~(Windows), /(맥/리눅스)
			// 상대주소 : 프로젝트디렉토리기준  C:/, /로 시작하지 않는 경우
			bis = new BufferedInputStream(new FileInputStream("C:/Users/user1/Desktop/qrcode.pdf"));
			bos = new BufferedOutputStream(new FileOutputStream("qrcode.pdf"));
			
			// 이 파일이 존재하지 않으면 새로 생성
			// 해당경로가 디렉토리 또는 권한 부족시 FileNotFoundException을 던진다.
					
			int len = 0; // 읽어온 byte수
			byte[] bytes = new byte[8192]; // buffered reader 내부적으로 사용하는 버퍼크기
			// 파일을 모두 읽었으면 값없음(-1)을 리턴
			while((len = bis.read(bytes)) != -1) {
				System.out.println(len);
				bos.write(bytes, 0, len); // sampleCopy.txt에 출력 
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원은 반드시 반납해야 한다.
			// 보조스트림만 반납하면 주스트림도 내부적으로 함께 반납된다.
			try {
				bis.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}
