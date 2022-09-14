package kh.order.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import kh.order.Manager.SerialObject;
import kh.order.serverTable.AllOrder;
import kh.order.vo.Order;

//사장님
public class PosServer {
	public final static int PORT = 7777;
	//ArrayList<ServerThread> socketList = new ArrayList<>();
	AllOrder allOrder = new AllOrder();
	int table = 1;
	
	public PosServer() {
		// 결국은 메인함수가 됨.
		
		//소켓 대기 및 연결
		this.connectTable();
		
		// 프로그램
		this.showMenu();
		
	}
	// 클라이언트 연결	(직원배정)
	public void connectTable() {
		//서버 소켓 지정
		try {
			ServerSocket server = new ServerSocket(PORT);
			
			//대기중 => 연결 요청 받아요.
			// 1. 연결을 받는다.
			Socket socket = server.accept();
			System.out.println("테이블 1 연결되었습니다.");
			
			// Runnable 구현 Thread는 new Thread로 한번 더 포장(형 변환)해야된다.
			// 2. 넘겨서 전담해서 처리한다.
			
			// 사장님이 서빙직원을 배정
			Thread t1 = new Thread(new GetOrder(socket, allOrder));
			t1.start();
				
			//사장님이 POS 봄
			//Thread t1 = new Thread(new Menu());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	// POS 보기 (사장님)
	public void showMenu() {
		Thread t2 = new Thread(new ShowMenu(allOrder));
		t2.start();
	}
	
	public static void main(String[] args) {
		new PosServer();
	}
	// 서버 POS
	// 1. 전체 테이블의 주문 보기
	// 2. 몇 번 테이블 주문 확인하기
	// 3. 추가로 들어온 주문 확인
	// 4. 요청 사항(보류)
	// 5. 총 매출 확인
}

//서버 스레드 - 클라이언트 하나의 연결을 전담을 해서 작동~ - 메시지 받는거만 처리할 예정
class GetOrder implements Runnable{
	Socket c_socket;
	AllOrder allOrder;
	int tableNum = 1;
	public GetOrder(Socket socket, AllOrder allOrder) {
		this.c_socket = socket;
		this.allOrder = allOrder;
	}
	
	@Override
	public void run() {
		while(true) {
			//클라이언트로부터 무한으로 메뉴 받기
			try {
				//서버에서 택배 박스를 받는다.
				ObjectInputStream objectInputStream = new ObjectInputStream(c_socket.getInputStream());
				
				// 직렬화라는 택배 포장 서비스를 이용을 해야한다.
				SerialObject box = (SerialObject)objectInputStream.readObject();
				
				// 택배 안에 들어있는 내용물인 '추가주문 리스트'를 꺼낸다.
				ArrayList<Order> addOrder = (ArrayList) box.getList();
				
				//추가 주문 리스트에 추가
				allOrder.setAddList(addOrder);
				
				//보미님 - 총 주문 리스트에 추가
				allOrder.AddToAll();
				
				//Map에 추가
				allOrder.addOrder(tableNum);
				allOrder.allOrder(tableNum);
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
// POS기 기능 구현
class ShowMenu implements Runnable{
	AllOrder allOrder;
	Scanner sc = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("###,###");
	int tableNum = 1;
	public ShowMenu(AllOrder allOrder) {
		this.allOrder = allOrder;
	}
	
	@Override
	public void run() {
		// 서버에서 계속 메뉴 선택하기
	   boolean loopFlag = true;

	   while(loopFlag){
		   //서영님 - 처음 시작부분
		   posMenu();
		   int select = sc.nextInt();

		   switch(select){
		   case 1:      
		    	// 1. 전체 테이블의 주문 보기
		    	selectAllOrder();       
		    	break;
		   case 2:
			   //2. 몇 번 테이블 주문 확인하기
			   selectOrderByTable();
			   break;        
		   case 3:    	
		    	// 3. 추가로 들어온 주문 확인
		    	selectAddOrder();
		    	break;
		       
	//		    case 4:
		      // 4. 요청 사항(보류)
	//		        OrderEct();
	//		       break;
		    case 5:
		    	//총 매출 확인
		    	getTotalSales();
		        break;
		          
		    default: 
		    	System.out.println("잘못 누르셨습니다.");
		    	break;
		   }
	   }
	}
	public void posMenu() {
	      System.out.println("==============================");
	      System.out.println(" 1. 전체 테이블의 주문 확인");
	      System.out.println(" 2. 테이블별 주문 확인");
	      System.out.println(" 3. 추가 추문 내역");
//	      System.out.println(" 4. 요청 사항");
	      System.out.println(" 5. 총 매출액");
	      System.out.println("==============================");
	      }
	public void selectAllOrder() {
    	//보미님 -  1. 전체 테이블의 주문 보기
		// 1. 테이블 (하나씩 하나씩) 총 주문을 가져온다
		// 2. 테이블 하나씩 (총 주문을 출력)해준다.
		
		
		// 맵 가져오기
		Map<Integer, ArrayList> allOrderMap = allOrder.getAllOrderMap();
		
		// Set => 키의 집합 => 총 테이블
		for(int key : allOrderMap.keySet()) {
			ArrayList<Order> allList = allOrderMap.get(key);
			allList = allOrder.getAllList();
			
			System.out.println("---- " + key + "번 테이블 주문 ----");
			for (int i = 0; i < allList.size(); i++) {
				Order order = allList.get(i);
				System.out.println(order.getOrderList());
			}
		}
	}
	public void selectOrderByTable() {
		//2. 몇 번 테이블 전체 주문 확인하기
		// 테이블 선택하기.
		System.out.print(">> 테이블 번호 입력: ");
		int num = sc.nextInt();
		
		// 맵 가져오기
		Map<Integer, ArrayList> allOrderMap = allOrder.getAllOrderMap();
		
		//ArrayList<Order> 선언
		if(allOrderMap.get(num) != null) {
			ArrayList<Order> allList = allOrderMap.get(num);
			
			System.out.println("---- " + num +"번 테이블 주문 ----");
			for (int i = 0; i < allList.size(); i++) {
				Order order = allList.get(i);

				System.out.println(order.getOrderList());
		}
      }
	}
	public void selectAddOrder() {
    	// 서영님 - 3. 추가로 들어온 주문 확인
		//전체 출력
		// 맵가져오기
		Map<Integer, ArrayList> addOrderMap = allOrder.getAddOrderMap();
		
		// Map에서 addList 하나씩 가져오기
		for(int key : addOrderMap.keySet()) {
			ArrayList<Order> addList = addOrderMap.get(key);
			System.out.println("---- " + key + "번 테이블 추가 주문 ----");
			for (int i = 0; i < addList.size(); i++) {
				Order order = addList.get(i);

				System.out.println(order.getOrderList());
			}
		}
	}
	public void getTotalSales() {

		int total = 0;			// 전체 매출
		
		// 테이블 마다 총 매출액
		//  ~~테이블 총 합계 : ~원
		// 이중 for문
		Map<Integer, ArrayList> allOrderMap = allOrder.getAllOrderMap();
		// 각 테이블 하나씩 빼오기
		for (int key : allOrderMap.keySet()) {
		    ArrayList<Order> allList = allOrderMap.get(key);
		    int sum = 0;
		    
			// 각 테이블 for문돌려서 가격 더해준다.
		    System.out.println("====각 테이블 총 매출====");
		    for (int i = 1; i < allList.size(); i++) {

		        Order order = allList.get(i);
		        sum += order.getPrice();
		    }
			// 출력해준다.
		    System.out.println("====" + key + "번 테이블 합계 : " + df.format(sum) + "원");
		    total += sum;
		}
		//전제 매출 표시
		System.out.println("========================");
		System.out.println("합계 : " + total);
		System.out.println("========================");
	}
}