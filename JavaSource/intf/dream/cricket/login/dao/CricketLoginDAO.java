package intf.dream.cricket.login.dao;
import java.util.List;
import java.util.Map;

import common.bean.User;

/**
 * Android Login DAO
 * @author  javaworker
 * @version $Id: CricketLoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 */
public interface CricketLoginDAO
{
    /**
     * �ش� User Id �� ������ ��ȸ�Ѵ�.         2013.10.02 �α��θ� �����ϰ� �����Ͽ���. �� �ٽ� �۾��ؾ��� .   �̱Լ�
     * @author  javaworker
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginDTO
     * @return
     * @throws Exception
     */
    public List findUserInfo(Map map);
    public List findDeviceInfo(Map map);
    /**
     * �α��� ���� ����
     * @author  javaworker
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @param loginTerminal 
     * @param terminalNo 
     */
    public void insertLogHist(User loginUser, String loginTerminal, String terminalNo);
    
    /**
     * �α��� ��¥����
     * @author  javaworker
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginDTO
     */
    public void setLoginDate(Map map);

	/**
	 * �������̽� Authorization 
	 * @param map
	 * @return
	 */
	public List findIfUserInfo(Map map) ;
}