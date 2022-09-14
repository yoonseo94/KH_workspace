package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpServletResponse;

public class HomeSweetHomeUtils {

	public static String encrypt(String password, String salt) {
		// 1. 암호화 Hashing
		MessageDigest md = null;
		byte[] encrypted = null;
		try {
				md = MessageDigest.getInstance("SHA-512");
				byte[] input = password.getBytes("utf-8");
				byte[] saltBytes = salt.getBytes("utf-8");
				md.update(saltBytes); 
				encrypted = md.digest(input);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	
		// 2. 인코딩 (단순문자변환)
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(encrypted);
	}
	
	public static String getPagebar(int cPage, int numPerPage, int totalContents, String url) {
		StringBuilder pagebar = new StringBuilder();
		int totalPages = (int) Math.ceil((double) totalContents / numPerPage); // 전체 페이지수
		int pagebarSize = 5;
		int pagebarStart = (cPage -1) / pagebarSize * pagebarSize + 1;
		int pagebarEnd = pagebarStart + pagebarSize - 1;
		int pageNo = pagebarStart;
		
		url += "?cPage=";
		
		// 이전- prev 버튼
		if(pageNo == 1) {
			// prev  버튼 비활성화
		}
		else {
			// prev 버튼 활성화
			pagebar.append("<a href='" + url + (pageNo - 1) +"'>prev</a>");
			pagebar.append("\n");
		}	
	    // 페이지 번호 pageNo
		while(pageNo <= pagebarEnd && pageNo <= totalPages) {
			if(pageNo == cPage) {
				// 현재 페이지인 경우
				pagebar.append("<span class='cPage'>" + pageNo + "</span>");
				pagebar.append("\n");			
			}
			else {
				// 현재 페이지가 아닌 경우
				pagebar.append("<a href='" + url + pageNo +"'>"+ pageNo +"</a>");
				pagebar.append("\n");
			}
			pageNo++;
		}
		
		// 다음 next
		if(pageNo > totalPages) {
			// next 버튼 비활성화
		}
		else {
			// next 버튼 활성화
			pagebar.append("<a href='" + url + pageNo +"'>next</a>");
			pagebar.append("\n");
		}
		return pagebar.toString();
	}

	public static void fileDownload(HttpServletResponse response, String saveDirectory, String originalFilename,
			String renamedFilename) throws UnsupportedEncodingException, IOException, FileNotFoundException{
		// 헤더작성
				response.setContentType("application/octet-stream"); // 응답데이터 타입
				// Content-Disposition 첨부파일인 경우, 브라우져 다운로드(Sava as)처리 명시
				String resFilename = new String(originalFilename.getBytes("utf-8"), "iso-8859-1"); // tomcat 기본인코딩
				response.setHeader("Content-Disposition", "attachment;filename=" + resFilename);
				
				// 파일을 읽어서(input) 응답메세지에 쓴다(output)
				File file = new File(saveDirectory, renamedFilename);
				
				// 기본스트림 - 대상과 연결
				// 보조스트림 - 기본스트림과 연결. 보조스트림을 제어
				try (
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
					BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
				){
					byte[] buffer = new byte[8192];
					int len = 0; // 읽어낸 byte수
					while((len = bis.read(buffer)) != -1) {
						bos.write(buffer, 0, len); // buffer의 0번지부터 len(읽은 개수)만치 출력
					}
				}
		
	}


}
