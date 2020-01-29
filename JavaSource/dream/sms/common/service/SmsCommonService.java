package dream.sms.common.service;

import java.util.List;

/**
 * SMS ���� �������̽�
 * @author kim21017
 *
 */
public interface SmsCommonService
{
	/**
	 * SMS ���� ����Ʈ
	 * @throws Exception
	 */
	public List findSmsMessageList() throws Exception;
	/**
	 * SMS���� ����� ������Ʈ
	 * @param id
	 * @param code
	 * @param failMsg
	 * @return
	 */
	public int updateMailMessageList(String id, int code, String failMsg);
}
