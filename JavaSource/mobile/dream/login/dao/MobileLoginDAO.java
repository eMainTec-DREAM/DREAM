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
     * 해당 User Id 의 정보를 조회한다.         2013.10.02 로그인만 가능하게 수정하였음. 꼭 다시 작업해야함 .   이규선
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
    public void setLoginDate(MobileLoginDTO loginDTO);

	/**
	 * 인터페이스 Authorization 
	 * @param map
	 * @return
	 */
	public List findIfUserInfo(MobileLoginDTO loginDTO) ;
}