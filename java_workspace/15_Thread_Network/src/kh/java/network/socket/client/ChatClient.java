package kh.java.network.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	final String SERVER_HOST = "khmclass.iptime.org"; // 내 컴퓨터를 가리키는 호스트명
	final int SERVER_PORT = 7777;
	
	public static void main(String[] args) {
		new ChatClient().start();
	}

	private void start() {
		// 클라이언트에서 서버소켓 연결요청
		try {
			Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
			System.out.println("[" + SERVER_HOST + ":" + SERVER_PORT + "] 연결되었습니다.");
			
			// 입출력스트림 준비
			try(BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter pw = new PrintWriter(clientSocket.getOutputStream())){
				
				// 클라이언트의 키보드입력
				Scanner sc = new Scanner(System.in);
				String inMsg = null;
				while((inMsg = br.readLine()) != null) {
					System.out.println("서버 : " + inMsg);
					System.out.print("클라이언트 : ");
					String outMsg = sc.nextLine();
					pw.println(outMsg);
					pw.flush();	// 강제출력
					
					if("exit".equals(outMsg)) {
						System.out.println("접속을 종료합니다!!!");
						break;
					}
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
