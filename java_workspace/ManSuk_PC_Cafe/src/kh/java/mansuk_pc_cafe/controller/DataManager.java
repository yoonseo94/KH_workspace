package kh.java.mansuk_pc_cafe.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import kh.java.mansuk_pc_cafe.vo.MemberAccount;

/**
 * 데이터 관리용 클래스
 * 
 * @author 수진
 */
public class DataManager {
	
	/*
	 * 회원목록파일 저장
	 */
	public static void saveMember(String fileName, ArrayList<MemberAccount> memberList) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));			
			oos.writeObject(memberList);
			oos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 회원목록파일 읽기
	 */
	public static ObjectInputStream loadMember(String fileName) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ois;
	}
	
	/*
	 * 회원정보 출력
	 */
	public static void printList(ArrayList<MemberAccount> memberList) {
		String[] memDataSet = {"회원번호", "이름", "나이", "성별", "아이디", "비밀번호",
								"전화번호", "이메일", "잔여시간", "접속상태", "회원여부"};
		String[] memData = new String[11];
		
		System.out.println("===============　회원　목록　===============");
		
		for(int i = 0; i < memberList.size(); i++) {
			memData[0] = Integer.toString(memberList.get(i).getMemberNo());
			memData[1] = memberList.get(i).getName();
			memData[2] = Integer.toString(memberList.get(i).getAge());
			memData[3] = Character.toString(memberList.get(i).getGender());
			memData[4] = memberList.get(i).getId();
			memData[5] = memberList.get(i).getPwd();
			memData[6] = memberList.get(i).getPhone();
			memData[7] = memberList.get(i).getEmail();
			memData[8] = memberList.get(i).getRemnants() + "분";
			memData[9] = memberList.get(i).isState() ? "로그인" : "로그아웃";
			memData[10] = memberList.get(i).isMemberYN() ? "회원" : "비회원";
			System.out.println(memData);
		}
		
		System.out.println("==========================================");
	}
	
	/*
	 * 인덱스로 회원정보 찾기
	 */
	public static void searchMember(int index, ArrayList<MemberAccount> memberList) {
		String[] memDataSet = {"회원번호", "이름", "나이", "성별", "아이디", "비밀번호",
		   						"전화번호", "이메일", "잔여시간", "접속상태", "회원여부"};
		String[] memData = new String[11];
		
		System.out.println("===============　회원　목록　===============");
		
		memData[0] = Integer.toString(memberList.get(index).getMemberNo());
		memData[1] = memberList.get(index).getName();
		memData[2] = Integer.toString(memberList.get(index).getAge());
		memData[3] = Character.toString(memberList.get(index).getGender());
		memData[4] = memberList.get(index).getId();
		memData[5] = memberList.get(index).getPwd();
		memData[6] = memberList.get(index).getPhone();
		memData[7] = memberList.get(index).getEmail();
		memData[8] = memberList.get(index).getRemnants() + "분";
		memData[9] = memberList.get(index).isState() ? "로그인" : "로그아웃";
		memData[10] = memberList.get(index).isMemberYN() ? "회원" : "비회원";
		System.out.println(memData);
		
		System.out.println("==========================================");
	}
	
	/*
	 * 특정 회원정보 찾기
	 */
	public static void searchMember(MemberAccount member) {
		String[] memDataSet = {"회원번호", "이름", "나이", "성별", "아이디", "비밀번호",
   								"전화번호", "이메일", "잔여시간", "접속상태", "회원여부"};
		String[] memData = new String[11];
		
		System.out.println("===============　회원　목록　===============");
		
		memData[0] = Integer.toString(member.getMemberNo());
		memData[1] = member.getName();
		memData[2] = Integer.toString(member.getAge());
		memData[3] = Character.toString(member.getGender());
		memData[4] = member.getId();
		memData[5] = member.getPwd();
		memData[6] = member.getPhone();
		memData[7] = member.getEmail();
		memData[8] = member.getRemnants() + "분";
		memData[9] = (member.isState()) ? "로그인" : "로그아웃";
		memData[10] = (member.isMemberYN()) ? "회원" : "비회원";
		System.out.println(memData);
		
		System.out.println("==========================================");
	}
	
}
