package common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class BoardFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		File newFile = null;
		do {
			// 파일명 재지정 yyyyMMdd_HHmmssSSS_123
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
			DecimalFormat df = new DecimalFormat("000");
			
			// 확장자 가져오기
			String oldName = oldFile.getName();		 // abc.txt
			String ext = "";
			int dotIndex = oldName.lastIndexOf("."); // 3
			if(dotIndex > -1)
				ext = oldName.substring(dotIndex); // .txt
			
			String newName = sdf.format(new Date()) + df.format(Math.random() * 1000) + ext;
			newFile = new File(oldFile.getParent(), newName);
			System.out.println("newFile@HelloMvcFileRenamePolicy" + newFile);
			
			
		} while(!createNewFile(newFile));
		
		return newFile;
	}
	
	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		} catch (IOException ignored) {
			return false;
		}
	}
}
