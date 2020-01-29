package mobile.dream.login.dao;
import java.util.List;

import common.bean.User;
import mobile.dream.login.dto.MobileLoginDTO;

/**
 * Login DAO
 * @author  javaworker
 * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 */
public interface MobileLoginDAO
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
    public List findUserInfo(MobileLoginDTO loginDTO);
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
    public void setLoginDate(MobileLoginDTO loginDTO);

	/**
	 * �������̽� Authorization 
	 * @param map
	 * @return
	 */
	public List findIfUserInfo(MobileLoginDTO loginDTO) ;
}