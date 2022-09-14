package kh.order.Manager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import kh.order.vo.Order;
import kh.order.vo.menu.Beverage;
import kh.order.vo.menu.Pasta;
import kh.order.vo.menu.Pizza;
import kh.order.vo.menu.Risotto;

public class TablePosManager {
	ArrayList<Order> addList = new ArrayList<Order>();		// 추가 주문 리스트
	ArrayList<Order> allList = new ArrayList<Order>();		// 총 주문 리스트
	DecimalFormat df = new DecimalFormat("###,###");
	Socket socket;
	Scanner sc = new Scanner(System.in);

	public TablePosManager() {
		try {
			// Client Socket 연결
			socket = new Socket("127.0.0.1", 7777);
			System.out.println("테이블 1 연결되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		boolean loopFlag = true;

		while(loopFlag) {
			//처음 시작부분
			printMenu();
			int select = sc.nextInt();
			
			// 서영님 작성내용 - 처음 switch
			switch(select) {
			case 1:	
			    //메뉴 선택 하위 메뉴
				selectMenu();
			   break;
			          
			case 2:
				// 추가한 주문 출력
				printAddMenu();
				break;	//switch에 대한 break
			case 3:
			    // 총 주문 출력
				 checkMenu();
				 break;
			case 4:
				// 추가 주문 보내기
				System.out.print("위 내용을 주문하시겠습니까? (y/n) : ");
                char YN = sc.next().charAt(0);
                   if(YN == 'y') {
                        sendOrder(addList);
                   }
				break;
			case 5:
			       System.out.println("요청사항을 입력해주세요.");
			       String chat = sc.nextLine(); 
			       break;
			 case 6:
			       System.out.print("주문을 마치시겠습니까? (y/n) : ");
			       char yn = sc.next().charAt(0); 
			       
			       if(yn == 'y') {
			    	   loopFlag = false;
			       }
			       break;
			       
			 default : System.out.println("잘못 누르셨습니다.");
			 	break;
			}

		}
	}
	public void printMenu() {
		System.out.println("==============================");
		System.out.println(" 1. 메뉴 선택");
		System.out.println(" 2. 추가 주문 확인(출력)");
		System.out.println(" 3. 총 내역 확인(출력)");
		System.out.println(" 4. 추가주문 보내기");
		System.out.println(" 5. 채팅 요청");
		System.out.println(" 6. 끝내기");
		System.out.println("==============================");
	}
	
	// 보미님 작성내용 - 1. 메뉴 선택 하위 메뉴
	public void selectMenu() {
		boolean loopFlag = true;
		
	      String menu = "***** Menu *****\r\n"
	            +" 1. 파스타\r\n"
	            +" 2. 피자\r\n"
	            +" 3. 리조또\r\n"
	            +" 4. 음료\r\n"
	            +" 5. 종료\r\n"
	            +"****************\r\n"
	            +">>선택 :  ";
	      
	      String pasta = "***** Pasta *****\r\n"
                  +"1. 로제 파스타 --------------------12,000\r\n"
                  +"2. 토마토 파스타 --------------------12,000\r\n"
                  +"3. 알리오올리오 --------------------11,000\r\n"
                  +"4. 까르보나라 --------------------12,000\r\n"
                  +">>선택 :  ";

	      String pizza = "***** Pizza *****\r\n"
			   +"1. 마르게리따 --------------------14,000\r\n"
			   +"2.불고기 피자 --------------------14,000\r\n"
			   +"3.포테이토 피자 --------------------15,000\r\n"
			   +"4.고구마 피자 --------------------15,000\r\n"
			   +"5.고르곤졸라 --------------------14,000\r\n"
			   +">>선택 :  ";

	      String riso = "***** Risotto *****\r\n"
			   +"1. 게살 로제 리조또 --------------------12,000\r\n"
			   +"2. 크림 리조또 --------------------11,000\r\n"
			   +"3. 해산물 토마토 리조또 --------------------13,000\r\n"
			   +"4. 불고기 리조또 --------------------12,000\r\n"
			   +">>선택 :  ";

	      String beverage = "***** Beverage *****\r\n"
			   +"1. 콜라 --------------------2,000\r\n"
			   +"2. 사이다 --------------------2,000\r\n"
			   +"3. 레몬 에이드 --------------------4,000\r\n"
			   +"4. 청포도 에이드 --------------------4,000\r\n"
			   +"5. 자몽 에이드 --------------------4,000\r\n"
			   +">>선택 :  ";
	      
	      while(loopFlag) {
	         System.out.print(menu);
	         int choice = sc.nextInt();
	         String spicy = "";
	         int menuCode;
	         String menuName = "";
	         int price = 0;
	         
	         switch(choice) {
	         case 1:		
	        	 //파스타 선택 메뉴
	        	 System.out.print(pasta);
	        	 Pasta p = new Pasta();
	        	 
	        	 System.out.print(">> 선택(숫자) : ");
	        	 menuCode=sc.nextInt(); //1.로제 2.토마토 ...
	        	 sc.nextLine();	// 버퍼 제거
	        	 
	        	 // 가격 가져오기 코드 ex) int price = 몇번을 선택했으면 얼마
	        	 p.menuSet(menuCode);
	        	 
	        	 //보미님 작성내용 - 맵기 선택 코드
	        	 System.out.print(">> 음식의 맵기를 입력하세요 (상 중 하) : ");
	        	 spicy = sc.nextLine();
	        	 
	        	 //출력
	        	 System.out.println("선택하신 "+p.getMenuNum()+" 번 "+p.getMenuName() +"의 금액은 "+df.format(p.getPrice())+"원, 맵기는 "+spicy+" 입니다.");
	        	 
	        	 //음식의 맵기 설정 코드
	        	 p.setSpicy(spicy);
	        	 
	        	 //추가 주문 List에 추가
	        	 addList.add(p);
	        	 
	        	 break;
	            
	         case 2:	//피자 선택 메뉴
	        	 System.out.print(pizza);
	        	 Pizza pzz = new Pizza();
	        	   
	        	 System.out.print(">> 선택(숫자) : ");
	        	 menuCode=sc.nextInt(); //1.마르 2.불고기
	        	 sc.nextLine();   // 버퍼 제거
	        	   
	        	 pzz.menuSet(menuCode);
	        	   
	        	 System.out.println("선택하신 "+pzz.getMenuNum()+" 번 "+pzz.getMenuName() +"의 금액은 "+df.format(pzz.getPrice())+"원 입니다.");
	        	 
	        	 //추가 주문 List에 추가
	        	 addList.add(pzz);
	        	 
	        	 break;
	            
	         case 3:	//리조또 선택 메뉴
	        	 System.out.print(riso);
	        	 Risotto r = new Risotto();
	        	 
	        	 System.out.print(">> 선택(숫자) : ");
	        	 menuCode=sc.nextInt();
	        	 sc.nextLine();   // 버퍼 제거
	        	 
	        	 //****//
		         System.out.print(">> 음식의 맵기를 입력하세요 (상 중 하) : ");
		         spicy = sc.nextLine();
		         
		         r.menuSet(menuCode);
		         System.out.println("선택하신 "+r.getMenuNum()+" 번 "+r.getMenuName() +"의 금액은 "+df.format(r.getPrice())+"원, 맵기는 "+spicy+" 입니다.");
		         
		         addList.add(r);
		         break;
		         
	         case 4:
	        	 System.out.print(beverage);
	        	 Beverage b = new Beverage();
	        	 
	        	 System.out.print(">> 선택(숫자) : ");
	        	 menuCode=sc.nextInt();
	        	 sc.nextLine();   // 버퍼 제거
	        	 
	        	 b.menuSet(menuCode);
	        	 System.out.println("선택하신 "+b.getMenuNum()+" 번 "+b.getMenuName() +"의 금액은 "+df.format(b.getPrice())+"원 입니다.");
	        	 
	        	 addList.add(b);
	        	 break;
	        	 
	         case 5:
	        	 System.out.println("돌아갑니다.");
	        	 loopFlag = false;
	        	 break;
	        	 
	         default:
	        	 System.out.println("잘못 입력되었습니다. 다시 입력해주세요.");
	        	 break;
	         }
	      }
	}
	// 서영님 작성내용 - 2. 추가 주문 확인 (가져오는거)
	public void printAddMenu() {
		//추가 주문 리스트에서 주문을 하나씩 가져온다(for) 리스트에서 주문을 하나씩
		// 가져온 주문들의 내역을 출력한다
		for(int i=0; i<addList.size(); i++) {
			Order order = addList.get(i);
			System.out.println("[" + (i+1) +"] " + order.getOrderList());
		}
	}
	
	// 3. 총 내역 확인
	public void checkMenu() {
		int sum = 0;
		System.out.println("============총 주문 내역 ===========");
		for(int i=0; i< allList.size(); i++) {
			Order order = allList.get(i);
			sum += order.getPrice();
			System.out.println("[" + (i+1) +"] " + order.getOrderList());
		}
		//allList에서 order를 하나씩 뽑은 것에서 order에서 price를 뽑아 합계에 더해준다.
		System.out.println("===============================");
		System.out.println("총 금액 : " + df.format(sum) + "원");
		System.out.println("===============================");
	}
	
	// 4. 주문 추가한 리스트 보내기
	public void sendOrder(ArrayList<Order> addOrder) {	
		try {
			//택배 포장지 준비
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			
			//택배 박스 포장
			SerialObject box = new SerialObject();
			box.setList(addOrder);
			
			//택배 보내기
			objectOutputStream.writeObject(box);
	    	objectOutputStream.flush();
			
	    	//총 주문 내역에 추가주문 추가 - 리스트에 리스트 추가
	    	allList.addAll(addList);
	    	
	    	//추가 주문 보냈으니 지우기
	    	addList.clear();

		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	// 5. 채팅
	public static void main(String[] args) {
		new TablePosManager();
	}

}
