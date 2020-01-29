package dream.sms.common.dao;

import java.util.List;

/**
 * SMS Common DAO
 * @author  kim21017
 * @version $Id: MaBatchDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface SmsCommonDAO
{
	/**
	 * ���Ϻ��� ����Ʈ ���
	 * @return
	 */
	public List findSmsMessageList();
	/**
	 * ����� update
	 * @param id
	 * @param code
	 * @param failMsg
	 * @return
	 */
	public int updateMailMessageList(String id, int code, String failMsg);
}