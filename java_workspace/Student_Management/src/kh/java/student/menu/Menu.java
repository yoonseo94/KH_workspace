//package kh.java.student.menu;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//import kh.java.student.manager.StudentManager;
//import kh.java.student.model.vo.Student;
//
//public class Menu {
//    
//    Scanner sc = new Scanner(System.in);
//    
//    StudentManager manager = new StudentManager();
//    
//    String menu = "---------------- 학생 관리 목록 ----------------\n"
//                    + "1. 학생 정보 등록\n"
//                    + "2. 학생 정보 조회\n"
//                    + "3. 학생 정보 삭제\n"
//                    + "4. 학생 전체 목록\n"
//                    + "5. 학생 정보 수정\n"
//                    + "6. 학생 정보 출력\n"
//                    + "7. 종료\n"
//                    + "> 번호 선택 : ";
//    
//    public void mainMenu() {
//        
//        while(true) {
//            System.out.print(menu);
//            String choice = sc.next();
//            outer:
//            switch(choice)
//            {
//            case "1" :
//                manager.addStudent(inputStudent());
//                break;
//            case "2" :
//                selectOne();
//                break;
//            case "3" :
//                deleteStudent();
//                break;
//            case "4" :
//                selectAll();
//                break;
//            case "5" :
//                updataStudent();
//                break;
//            case "6" :
//            	manager.printStudent();
//            	System.out.println("출력완료");
//            	break;
//            case "7" :
//                System.out.println("프로그램 종료");
//                return;
//            default : System.out.println("잘못 입력 하셨습니다."); break outer;
//            }
//        }
//    }
//    
//    public void selectAll() { // 학생 전체 출력
//    	
//    	System.out.println("---------------- 학생 정보 출력 ----------------");
//        System.out.println("이름 \t나이 \t학교 \t학번 \t\t학과 \t전화번호 \t\t주소");
//        for(Student s : manager.selectAll()) {
//        	System.out.println(s.toString());
//        }
//    }
//    
//    public void selectOne() { // 학생 선택 출력
//        
//    	while(true) {
//       	 System.out.println("---------------- 학생 정보 선택 출력 ----------------");
//       	 try {
//       		 int no = inputNo();
//       		 if(manager.selectOne(no))
//       		 {
//       			 manager.selectOnePrint(no);
//       			 break;
//       		 }
//       			 
//   	         if(!manager.selectOne(no))
//   	        	System.out.println("존재하지 않는 학번입니다. ");
//   	        	continue;
//   	        	
//   	    	 }catch(InputMismatchException e) {
//   	    		 sc = new Scanner(System.in);
//   	    		 System.out.println("잘못 입력하셨습니다. ex)학번 : 12345678");
//   	    	 }
//       	 }
//    }
//    
//    public void deleteStudent() {
//    	
//   	 if(manager.removeStudent(inputNo()))
//		 System.out.println("삭제 완료!");
//	 else
//		 System.out.println("존재하지 않는 학번입니다. 삭제에 실패했습니다.");
//    }
//    
//    private int inputNo() {
//    	
//   	 System.out.println("학번 입력 : ");
//   	 int n = sc.nextInt();
//   	 return n;
//	}
//    
//    public Student inputStudent() {
//    	
//   	 System.out.print("이름 입력 : ");
//   	 String name = sc.next();
//   	 
//   	 System.out.print("나이 입력 : ");
//   	 int age = sc.nextInt();
//           
//        System.out.print("학교 입력 : ");
//        String school = sc.next();
//           
//        int no;
//        
//        while (true) {
//            System.out.print("학번 입력 : ");
//            no = sc.nextInt();
//            
//            if((int) (Math.log10(no) + 1) != 8) {    // 학번의 자리수 체크
//                System.out.println("학번은 8자리여야 합니다.");
//                continue;
//            }
//            
//            break;    // 정상 수행시 조건문 탈출
//        }
//        
//        for (Student s : manager.selectAll())
//            if (s.getNo() == no) {
//                System.out.println("중복된 학번입니다!");
//                return null;
//            }
//        
//        System.out.print("학과 입력 : ");
//        String major = sc.next();
//        
//        System.out.print("전화번호 입력 : ");
//        String phone = sc.next();
//       
//        sc.nextLine();
//        System.out.print("주소 입력 : ");
//        String address = sc.nextLine();
//
//        return new Student(name, age, school, no, major, phone, address);
//    }
//    
//    public void updataStudent() {
//    	System.out.println("---------------- 학생 정보 수정 ----------------");
//    	
//    	while(true) {
//    		
//    		System.out.println("수정할 학생의 학번 입력 : ");
//    		int inputStudentNo = sc.nextInt();
//    		Student stu = manager.modifyStudent(inputStudentNo);
//    		if(stu.getNo() != inputStudentNo) {
//    			System.out.println("검색한 학생 정보가 존재하지 않습니다.");
//    			continue;
//    		}
//    		else 
//    		{
//    			System.out.printf("< 1. 이름:%s / 2.나이:%d / 3.학교:%S / 4.학번:%d / 5. 학과:%s / 6. 전화번호:%s / 7. 주소:%s / 8. 이전메뉴로 돌아가기>%n",
//    					stu.getName(),stu.getAge(),stu.getSchool(),stu.getNo(),stu.getMajor(),stu.getPhone(),stu.getAddress());
//    			System.out.print("수정할 항목 선택 :");
//    			int choice = sc.nextInt();
//    			switch (choice) {
//    			case 1 : 
//    				System.out.println("수정할 이름 입력 :");
//    				stu.setName(sc.next());
//    				break;
//    			case 2 : 
//    				System.out.println("수정할 나이 입력 :");
//    				stu.setAge(sc.nextInt());
//    				break;
//    			case 3 : 
//    				System.out.println("수정할 학교 입력 :");
//    				stu.setSchool(sc.next());
//    				break;
//    			case 4 : 
//    				int no;
//    				while (true) {
//    			           System.out.print("학번 입력 : ");
//    			           no = sc.nextInt();
//    			            
//    			           if((int) (Math.log10(no) + 1) != 8) {    // 학번의 자리수 체크
//    			               System.out.println("학번은 8자리여야 합니다.");
//    			               System.out.println("------------------------------------------------");
//    			               continue;
//    			           }
//    			           else
//    			           {
//    			        	   stu.setNo(no);
//    			           }
//    			           
//    			           break;    // 정상 수행시 조건문 탈출
//    			       }
//    				break;
//    			case 5 : 
//    				System.out.println("수정할 학과 입력 :");
//    				stu.setMajor(sc.next());
//    				break;
//    			case 6 : 
//    				System.out.println("수정할 전화번호 입력 :");
//    				stu.setPhone(sc.next());
//    				break;
//    			case 7 : 
//    				System.out.println("수정할 주소 입력 :");
//    				stu.setAddress(sc.next());
//    				break;
//    			case 8 : 
//    				System.out.println("이전 메뉴로 돌아가기");
//    				mainMenu();
//				default:
//					System.out.println("1 ~ 7번중에서 선택해주세요");
//					break;
//    			}
//    			boolean result = manager.updateStudent(stu);
//    			if(result == true) {
//    				System.out.println("수정을 성공하였습니다.");
//    				mainMenu();
//    			}
//    			else
//    				System.out.println("수정을 실패하였습니다.");
//    			continue;
//    			
//    		}
//    	}
//    }
//}

package kh.java.student.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import kh.java.student.manager.StudentManager;
import kh.java.student.model.vo.Student;

public class Menu {
    
    Scanner sc = new Scanner(System.in);
    
    StudentManager manager = new StudentManager();
    
    String menu = "┏━━━━━━ <<학생 관리 목록>> ━━━━━━━┓\n"
                + "┃1. 학생 정보 등록                ┃\n"
                + "┃2. 학생 정보 조회                ┃\n"
                + "┃3. 학생 정보 삭제                ┃\n"
                + "┃4. 학생 전체 목록                ┃\n"
                + "┃5. 학생 정보 수정                ┃\n"
                + "┃6. 학생 정보 출력                ┃\n"
                + "┃7. 프로그램 종료                 ┃\n"
                + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n"
                + ">> 번호 선택 : ";
    
    public void mainMenu() {
        
        while(true) {
            System.out.print(menu);
            String choice = sc.next();
            outer:
            switch(choice)
            {
            case "1" :
                manager.addStudent(inputStudent());
                break;
            case "2" :
                selectOne();
                break;
            case "3" :
                deleteStudent();
                break;
            case "4" :
                selectAll();
                break;
            case "5" :
                updataStudent();
                System.out.println("정보 수정 완료!");
                break;
            case "6" :
            	manager.printStudent();
            	System.out.println("student.txt 파일에 저장되었습니다!");
            	break;
            case "7" :
                System.out.println("프로그램 종료");
                return;
            default : System.out.println("[오류발생](☞ﾟヮﾟ)☞ 잘못 입력 하셨습니다."); break outer;
            }
        }
    }
    
    public void updataStudent() {
    	System.out.printf("┏━━━━━━━━━━━━━━━━┓\n");
    	System.out.printf("┃   학생 정보 수정  ┃\n");
    	System.out.printf("┗━━━━━━━━━━━━━━━━┛\n");
    	
    	while(true) {
    		
    		System.out.print("수정할 학생의 학번 입력 > ");
    		int inputStudentNo = sc.nextInt();
    		Student stu = manager.modifyStudent(inputStudentNo);
    		if(stu.getNo() != inputStudentNo) {
    			System.out.println("[오류발생](☞ﾟヮﾟ)☞ 검색한 학생 정보가 존재하지 않습니다.");
    			continue;
    		}
    		else 
    		{
    			System.out.printf("┏━━━━━━━━━━━━━━━━━━━━━━┓\n"
								+ "┃      정보 조회 결과     ┃\n"
								+ "┗━━━━━━━━━━━━━━━━━━━━━━┛\n");
    			
    			System.out.printf("1. 이름:%s\n"
								+ "2. 나이:%d\n"
								+ "3. 학교:%s\n"
								+ "4. 학번:%d\n"
								+ "5. 학과:%s\n"
								+ "6. 전화번호:%s\n"
								+ "7. 주소:%s\n"
								+ "8. 이전메뉴로 돌아가기\n",
    					stu.getName(),stu.getAge(),stu.getSchool(),stu.getNo(),stu.getMajor(),stu.getPhone(),stu.getAddress());
    			System.out.print("수정할 항목 선택 > ");
    			int choice = sc.nextInt();
    			switch (choice) {
    			case 1 : 
    				System.out.print("수정할 이름 입력 > ");
    				stu.setName(sc.next());
    				return;
    			case 2 : 
    				System.out.print("수정할 나이 입력 > ");
    				stu.setAge(sc.nextInt());
    				return;
    			case 3 : 
    				System.out.print("수정할 학교 입력 > ");
    				stu.setSchool(sc.next());
    				return;
    			case 4 : 
    				int no;
    				while (true) {
    			           System.out.print("학번 입력 >  ");
    			           no = sc.nextInt();  
    			           
    			           if((int) (Math.log10(no) + 1) != 8 || manager.updateStudent(no) == false) {    // 학번의 자리수 체크
    			               System.out.println("[오류발생](☞ﾟヮﾟ)☞ 학번이 8자리가 아니거나 이미 존재하는 학번입니다.");
    			               System.out.println("------------------------------------------------");
    			               continue;
    			           }
    			           else
    			           {
    			        	   stu.setNo(no);
    			           }
    			           
    			           break;    // 정상 수행시 조건문 탈출
    			       }
    				return;
    			case 5 : 
    				System.out.print("수정할 학과 입력 >");
    				stu.setMajor(sc.next());
    				return;
    			case 6 : 
    				System.out.print("수정할 전화번호 입력 >");
    				stu.setPhone(sc.next());
    				return;
    			case 7 : 
    				System.out.print("수정할 주소 입력 >");
    				stu.setAddress(sc.next());
    				return;
    			case 8 : 
    				System.out.println("(☞ﾟヮﾟ)☞ 이전 메뉴로 돌아가기");
    				mainMenu();
				default:
					System.out.println("[오류발생](☞ﾟヮﾟ)☞ 1 ~ 7번중에서 선택해주세요");
					break;
    			}			
    		}
    	}
    }
    
    public void selectAll() { // 학생 전체 출력
    	System.out.printf("┏━━━━━━━━━━━━━━━━┓\n");
    	System.out.printf("┃   학생 정보 출력  ┃\n");
    	System.out.printf("┗━━━━━━━━━━━━━━━━┛\n");
        System.out.println("이름 \t나이 \t학교 \t학번 \t\t학과 \t전화번호 \t\t주소");
        System.out.println();
        for(Student s : manager.selectAll()) {
        	System.out.println(s.toString());
        }
    }
    
    public void selectOne() { // 학생 선택 출력
        
    	while(true) {
    		System.out.printf("┏━━━━━━━━━━━━━━━━┓\n");
        	System.out.printf("┃   학생 정보 조회  ┃\n");
        	System.out.printf("┗━━━━━━━━━━━━━━━━┛\n");
       	 try {
       		 int no = inputNo();
       		 if(manager.selectOne(no))
       		 {
       			 manager.selectOnePrint(no);
       			 System.out.println();
       			 break;
       		 }
       			 
   	         if(!manager.selectOne(no))
   	        	System.out.println("[오류발생](☞ﾟヮﾟ)☞ 존재하지 않는 학번입니다. ");
   	        	continue;
   	        	
   	    	 }catch(InputMismatchException e) {
   	    		 sc = new Scanner(System.in);
   	    		 System.out.println("[오류발생](☞ﾟヮﾟ)☞ 잘못 입력하셨습니다. ex)학번 : 12345678");
   	    	 }
       	 }
    }
    
    public void deleteStudent() {
    	
	   	if(manager.removeStudent(inputNo())) {
	   		System.out.println(">> 삭제 완료!");
	   		System.out.println();	   	 }
		 else {
			 System.out.println("[오류발생](☞ﾟヮﾟ)☞ 존재하지 않는 학번입니다. 삭제에 실패했습니다.");
			 System.out.println();
		 }
    }
    
    private int inputNo() {
    	
   	 System.out.print("학번 입력 > ");
   	 int n = sc.nextInt();
   	 return n;
	}
    
    public Student inputStudent() {
   	 System.out.print("이름 입력 > ");
   	 String name = sc.next();
   	 
   	 System.out.print("나이 입력 > ");
   	 int age = sc.nextInt();
           
     System.out.print("학교 입력 > ");
     String school = sc.next();
       
     int no;
        
        while (true) {
            System.out.print("학번 입력 > ");
            no = sc.nextInt();
            
            if((int) (Math.log10(no) + 1) != 8) {    // 학번의 자리수 체크
                System.out.println("[오류발생](☞ﾟヮﾟ)☞ 학번은 8자리여야 합니다.");
                continue;
            }
            
            break;    // 정상 수행시 조건문 탈출
        }
        
        for (Student s : manager.selectAll())
            if (s.getNo() == no) {
                System.out.println("[오류발생](☞ﾟヮﾟ)☞ 중복된 학번입니다!");
                return null;
            }
        
        System.out.print("학과 입력 > ");
        String major = sc.next();
        
        System.out.print("전화번호 입력 > ");
        String phone = sc.next();
       
        sc.nextLine();
        System.out.print("주소 입력 > ");
        String address = sc.nextLine();

        return new Student(name, age, school, no, major, phone, address);
    }
    

}
