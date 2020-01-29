package dream.sms.common.service;

import java.util.List;

/**
 * SMS 서비스 인터페이스
 * @author kim21017
 *
 */
public interface SmsCommonService
{
	/**
	 * SMS 보낼 리스트
	 * @throws Exception
	 */
	public List findSmsMessageList() throws Exception;
	/**
	 * SMS보낸 결과값 업데이트
	 * @param id
	 * @param code
	 * @param failMsg
	 * @return
	 */
	public int updateMailMessageList(String id, int code, String failMsg);
}
