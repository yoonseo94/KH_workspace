package customerservice.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;

import customerservice.model.dao.CustomerServiceDao;
import customerservice.model.dto.CsEmailLogExt;
import customerservice.model.dto.CsEmailImage;


public class CustomerServiceService {
	private CustomerServiceDao customerServiceDao = new CustomerServiceDao();
	
	
	public int insertEmailLog(CsEmailLogExt emailFormExt) {
			Connection conn = getConnection();
			int result = 0;
			try {
				result = customerServiceDao.insertEmailLog(conn, emailFormExt);
				int currentNo = customerServiceDao.findEmailFrmCurrentNo(conn);
				emailFormExt.setCsEmailLogNo(currentNo);
				
				CsEmailImage emailImage = emailFormExt.getEmailImage();
				if(emailImage != null ) {
						emailImage.setCsEmailLogNo(currentNo);
						result = customerServiceDao.enrollEmailImages(conn, emailImage);
					}
				commit(conn);
			} catch (Exception e) {
				rollback(conn);
				throw e;
			} finally {
				close(conn);
			}
			return result;

	}

}
