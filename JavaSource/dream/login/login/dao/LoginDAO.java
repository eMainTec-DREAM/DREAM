package dream.login.login.dao;
import java.util.List;

import common.bean.User;
import dream.login.login.dto.LoginDTO;

/**
 * Login DAO
 * @author  javaworker
 * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 */
public interface LoginDAO
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
    public List findUserInfo(LoginDTO loginDTO);
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
    public void setLoginDate(LoginDTO loginDTO);

	/**
	 * �������̽� Authorization 
	 * @param loginDTO
	 * @return
	 */
	public List findIfUserInfo(LoginDTO loginDTO) ;
	/**
	 * ���� device id ã��
	 * @param loginDTO
	 * @return
	 */
	public List findIfDeviceInfo(LoginDTO loginDTO) ;
	
	public String  countNamedUserCnt();
	
	/**
	 * �α��� �õ� �̷� ����
	 * @author  youngjoo38
	 * @version $Id$
	 * @since   1.0
	 * 
	 * @param loginUser
	 * @param loginTerminal 
	 * @param terminalNo 
	 */
	public void insertLoginTryLog(LoginDTO loginDTO, String loginTerminal, String terminalNo, String isSuccess, String paramLog);
	public void updateFailCnt(LoginDTO loginDTO, String isSuccess);
	public void updateLockUser(LoginDTO loginDTO);
	
	/**
	 * Sso Service�� Ÿ������ CompNo ����
	 * @author js.lee
	 * @since   1.0
	 *
	 * @param loginDTO
	 * @return
	 */
	public List findCompInfo(LoginDTO loginDTO);
}