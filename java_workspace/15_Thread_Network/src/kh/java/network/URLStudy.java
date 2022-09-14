package kh.java.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLStudy {

	String str = "https://docs.oracle.com:443/javase/8/docs/api/java/lang/String.html?name=abcde&lang=kr#String--";

	public static void main(String[] args) {
		URLStudy study = new URLStudy();
//		study.test1();
		study.test2();
	}


	/**
	 * URL
	 * URLConnection
	 */
	private void test2() {
		try {
			URL url = new URL(str);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream(); // 바이트기반 입력스트림
			
			try(BufferedReader br = new BufferedReader(new InputStreamReader(is));
				BufferedWriter bw = new BufferedWriter(new FileWriter("String.html"))){
				
				String data = null;
				while((data = br.readLine()) != null) {
					System.out.println(data);
					bw.write(data);
					bw.write("\n");
				}
			}
			
			System.out.println("응답 완료!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * URL
	 */
	private void test1() {
		
		try {
			URL url = new URL(str);
			
			System.out.println(url.getProtocol()); 	// https 프로토콜(통신규약) http, ftp...
			System.out.println(url.getHost());		// docs.oracle.com (host - docs, domain - oracle.com)
			System.out.println(url.getPort());		// 443 포트(특정서비스) 80(http), 443(https) 웹서버 기본포트
			System.out.println(url.getPath());		// /javase/8/docs/api/java/lang/String.html 특정파일경로(실제 디렉토리구조일수도 아닐수도 있다)
			System.out.println(url.getRef());		// String-- bookmark(anchor)
			System.out.println(url.getQuery());		// name=abcde&lang=kr 사용자입력값
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	

}
