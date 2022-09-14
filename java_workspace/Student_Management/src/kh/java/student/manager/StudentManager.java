//package kh.java.student.manager;
//
//import java.io.BufferedOutputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import kh.java.student.model.vo.Student;
//
////1. 등록 2. 삭제, 3. 조회 4. 전체 출력, 5. 정보수정 
//
//public class StudentManager {
//    
//    Scanner sc = new Scanner(System.in);
//    
//    private ArrayList<Student> studentList;
//    
//    public StudentManager() {
//        studentList = new ArrayList<>();
//        studentList.add(new Student("이름1", 23, "kh대학교", 20112323, "학과1", "010-0001-0000", "주소1"));
//        studentList.add(new Student("이름2", 24, "kh대학교", 20134242, "학과2", "010-0002-0000", "주소2"));
//        studentList.add(new Student("이름3", 25, "kh대학교", 20152323, "학과3", "010-0003-0000", "주소3"));
//    }
//    
//    public void printStudent() { // .txt 파일 출력
//    	OutputStream out = null;
//    	try 
//    	{
//			out = new BufferedOutputStream(new FileOutputStream("student.txt"));
//			
//			String title = "===================== kh대학교 학생 명단 =====================\n"
//						  +"이름 \t나이 \t학교 \t학번 \t\t학과 \t전화번호 \t\t주소\n";
//			byte[] a = title.getBytes();
//			out.write(a);
//			
//			for(int i = 0; i < studentList.size(); i++)
//			{
//				
//				String str = studentList.get(i).getName() + ",\t" + studentList.get(i).getAge() + ",\t" 
//							 + studentList.get(i).getSchool() + ",\t" + studentList.get(i).getNo() + ",\t"
//							 + studentList.get(i).getMajor() + ",\t" + studentList.get(i).getPhone() + ",\t"
//							 + studentList.get(i).getAddress() + "\r\n";
//				
//				byte[] b = str.getBytes();
//				out.write(b);						
//			}
//		} 
//    	catch (IOException e) 
//    	{
//			e.printStackTrace();
//		}
//    	finally
//    	{
//    		try 
//    		{
//				out.close();
//			} 
//    		catch (IOException e) 
//    		{
//				e.printStackTrace();
//			}
//    	}
//    }
//
//    
//    public void addStudent(Student s) { // 학생 등록 메소드
//        if(s != null) {
//            studentList.add(s);
//            System.out.println("학생 정보가 등록되었습니다.");
//        }
//    }
//    
//    public boolean removeStudent(int no) { // 학생 삭제 메소드
//        for(Student s : studentList)
//            if(s.getNo() == no) {
//                studentList.remove(s);
//                return true;
//            }
//        return false;
//    }
//    
//    public boolean updateStudent(Student no) { // 학생 정보 수정 학번 체크
//        boolean result = false;
//            for(int i = 0; i < studentList.size(); i++)
//            {
//                if(studentList.get(i).getNo() == no.getNo())
//                {
//                    result = true;
//                }                        
//            }  
//            return result;
//    }
//    
//    public ArrayList<Student> selectAll() { // 학생 전체 조회 메소드
//        ArrayList<Student> copyList = new ArrayList<Student>(); 
//        //원본에는 접근할 수 없게 카피본을 하나 만듬
//        copyList = studentList;
//        return copyList;
//
//    }
//    
//    public boolean selectOne(int no) { // 학생 한명 조회 메소드
//        for(Student s : studentList)
//            if(s.getNo() == no) {
//                return true;
//            }
//        return false;
//    } 
//    
//    public void selectOnePrint(int no) { // 학생 한명 조회 출력 메소드
//        for(Student s : studentList) {
//            if(s.getNo() == no) {
//                System.out.println(s.toString());
//            }
//        }
//    }
//    
//    public Student modifyStudent(int studentNo) { 
//        int num = 0;
//        for (int i = 0; i < studentList.size(); i++) {
//            boolean boo = studentList.get(i).getNo() == studentNo;
//            if (boo == true) {
//                num = i;
//                break;
//            }
//        }
//        return studentList.get(num);
//    }
//    
//    public boolean isNumber(String str){ //입력 받은게 숫자인지 확인
//        char check;
//        
//        if(str.equals("")) {
//            //문자열이 공백인지 확인
//            return false;
//        }
//        for(int i = 0; i<str.length(); i++){
//            check = str.charAt(i);
//            if( check < 48 || check > 58) {
//                //해당 char값이 숫자가 아닐 경우
//                return false;
//                }
//        }
//        return true;
//    }
//}

package kh.java.student.manager;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import kh.java.student.model.vo.Student;

//1. 등록 2. 삭제, 3. 조회 4. 전체 출력, 5. 정보수정 

public class StudentManager {
    
    Scanner sc = new Scanner(System.in);
    
    private ArrayList<Student> studentList;
    
    public StudentManager() {
        studentList = new ArrayList<>();
        studentList.add(new Student("이름1", 23, "kh대학교", 20112323, "학과1", "010-0001-0000", "주소1"));
        studentList.add(new Student("이름2", 24, "kh대학교", 20134242, "학과2", "010-0002-0000", "주소2"));
        studentList.add(new Student("이름3", 25, "kh대학교", 20152323, "학과3", "010-0003-0000", "주소3"));
    }
    
    public void printStudent() { // .txt 파일 출력
    	BufferedOutputStream out = null;
    	try 
    	{
			out = new BufferedOutputStream(new FileOutputStream("student.txt"));
			
			String title = "======================= kh대학교 학생 명단 =======================\n"
						  +"이름 \t나이 \t학교 \t\t학번 \t\t학과 \t전화번호 \t\t주소\n";
			byte[] a = title.getBytes();
			out.write(a);
			
			for(int i = 0; i < studentList.size(); i++)
			{
				
				String str = studentList.get(i).getName() + " \t" + studentList.get(i).getAge() + " \t" 
							 + studentList.get(i).getSchool() + " \t\t" + studentList.get(i).getNo() + " \t"
							 + studentList.get(i).getMajor() + " \t" + studentList.get(i).getPhone() + " \t"
							 + studentList.get(i).getAddress() + "\r\n";
				byte[] b = str.getBytes();
				out.write(b);						
			}
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    	finally
    	{
    		try 
    		{
				out.close();
			} 
    		catch (IOException e) 
    		{
				e.printStackTrace();
			}
    	}
    }

    
    public void addStudent(Student s) { // 학생 등록 메소드
        if(s != null) {
            studentList.add(s);
            System.out.println("(☞ﾟヮﾟ)☞ 학생 정보가 등록되었습니다.");
            System.out.println();
        }
    }
    
    public boolean removeStudent(int no) { // 학생 삭제 메소드
        for(Student s : studentList)
            if(s.getNo() == no) {
                studentList.remove(s);
                return true;
            }
        return false;
    }
    
    public boolean updateStudent(int no) { // 변경할 학번이 이미 리스트에 있는지 확인
        boolean result;
            for(int i = 0; i < studentList.size(); i++)
            {
                if(studentList.get(i).getNo() == no)
                {
                    return result = false;
                }                        
            }  
            return result = true;
    }
    
    public ArrayList<Student> selectAll() { // 학생 전체 조회 메소드
        ArrayList<Student> copyList = new ArrayList<Student>(); 
        //원본에는 접근할 수 없게 카피본을 하나 만듬
        copyList = studentList;
        return copyList;

    }
    
    public boolean selectOne(int no) { // 학생 한명 조회 메소드
        for(Student s : studentList)
            if(s.getNo() == no) {
                return true;
            }
        return false;
    } 
    
    public void selectOnePrint(int no) { // 학생 한명 조회 출력 메소드
        for(Student s : studentList) {
            if(s.getNo() == no) {
                System.out.println(s.toString());
            }
        }
    }
    
    public Student modifyStudent(int studentNo) { 
        int num = 0;
        for (int i = 0; i < studentList.size(); i++) {
            boolean boo = studentList.get(i).getNo() == studentNo;
            if (boo == true) {
                num = i;
                break;
            }
        }
        return studentList.get(num);
    }
}
