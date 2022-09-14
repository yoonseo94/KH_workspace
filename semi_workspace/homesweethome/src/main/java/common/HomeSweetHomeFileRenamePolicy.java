package common;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class HomeSweetHomeFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		File newFile = null;
		do {
			// 파일명 재지정 yyyyMMdd_HHmmssSSS_123
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
			DecimalFormat df = new DecimalFormat("000");

			// 확장자 가져오기
			String oldName = oldFile.getName();
			String ext = "";
			int dotIndex = oldName.lastIndexOf(".");
			if(dotIndex > -1)
				ext = oldName.substring(dotIndex); 

			String newName = sdf.format(new Date()) + df.format(Math.random() * 1000) + ext;
			newFile = new File(oldFile.getParent(), newName);

		} while(!createNewFile(newFile));

		return newFile;
	}

	/**
	 * File#createNewFile
	 * - 해당파일이 존재하지 않으면 파일을 생성후 true를 리턴
	 * - 해당파일이 존재하면 IOException 유발
	 *  
	 * @param f
	 * @return
	 */
	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		} catch (IOException ignored) {
			return false;
		}
	}

}
