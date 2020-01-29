package dream.sms.common.service.spring;

import java.util.List;

import dream.sms.common.dao.SmsCommonDAO;
import dream.sms.common.service.SmsCommonService;

/**
 * SMS 서비스 구현
 * 
 * @author kim21017
 * @version $Id: SmsCommonServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp
 *          $
 * @since 1.0
 * @spring.bean id="smsCommonServiceTarget"
 * @spring.txbn id="smsCommonService"
 * @spring.property name="smsCommonDAO" ref="smsCommonDAO"
 */
public class SmsCommonServiceImpl implements SmsCommonService {
	private SmsCommonDAO SmsCommonDAO = null;
	
	
	
	public SmsCommonDAO getSmsCommonDAO() {
		return SmsCommonDAO;
	}

	public void setSmsCommonDAO(SmsCommonDAO smsCommonDAO) {
		SmsCommonDAO = smsCommonDAO;
	}

	@Override
	public List findSmsMessageList() throws Exception {
		return SmsCommonDAO.findSmsMessageList();
	}

	@Override
	public int updateMailMessageList(String id, int code, String failMsg) {
		return SmsCommonDAO.updateMailMessageList(id, code, failMsg);
	}
}
