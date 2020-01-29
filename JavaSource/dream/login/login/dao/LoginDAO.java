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
     * 해당 User Id 의 정보를 조회한다.         2013.10.02 로그인만 가능하게 수정하였음. 꼭 다시 작업해야함 .   이규선
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
     * 로그인 정보 저장
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
     * 로그인 날짜저장
     * @author  javaworker
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginDTO
     */
    public void setLoginDate(LoginDTO loginDTO);

	/**
	 * 인터페이스 Authorization 
	 * @param loginDTO
	 * @return
	 */
	public List findIfUserInfo(LoginDTO loginDTO) ;
	/**
	 * 허용된 device id 찾기
	 * @param loginDTO
	 * @return
	 */
	public List findIfDeviceInfo(LoginDTO loginDTO) ;
	
	public String  countNamedUserCnt();
	
	/**
	 * 로그인 시도 이력 저장
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
	 * Sso Service를 타기위한 CompNo 셋팅
	 * @author js.lee
	 * @since   1.0
	 *
	 * @param loginDTO
	 * @return
	 */
	public List findCompInfo(LoginDTO loginDTO);
}