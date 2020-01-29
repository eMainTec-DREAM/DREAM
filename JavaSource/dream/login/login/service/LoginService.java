/**
 * 
 */
package dream.login.login.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.bean.User;
import dream.login.login.dto.LoginDTO;

/**
 * Login Service
 * @author  javaworker
 * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
 * @since   1.0
 *
 */
public interface LoginService
{
    /**
     * �ش� ���ǿ��� �̹� �α����� �ߴ��� ���ߴ����� ýũ
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param sessionID
     * @return
     */
    public boolean isLogin(String sessionID);
    
    /**
     * �ش� ���̵��� ���� ����� �������� �̹� ������� ���̵������� �˾ƺ���
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param userID
     * @param compNo 
     * @return
     */
    public boolean isUsing(String userID, String compNo);

    /**
     * Session �� invalidate �Ѵ�.
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param session
     */
    public void unBound(HttpSession session);
    
    /**
     * �α����� �Ǿ��ִ� ��� �ٽ� �α����� �� ��� 
     * ������ �α����� �Ǿ� �ִ� �����͸� �����ϰ� 
     * �ٽ� �α����� �� ��� ����ȴ�. 
     * @author  mentor
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param userId
     * @param sessionId 
     * @return
     */
    public boolean isForceLogOut(String userId, String compNo, String sessionId);
    
    /**
     * index ���� id, password �� 
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @param localeId 
     * @param compNo 
     * @since   1.0
     * 
     * @param loginDTO
     * @return
     * @throws Exception
     */
    public List findUserInfo(LoginDTO loginDTO);
    
    /**
     * �ֱ� ������ ����
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @param localeId 
     * @param compNo 
     * @since   1.0
     * 
     * @param loginDTO
     * @return
     * @throws Exception
     */
    public void setLoginDate(LoginDTO loginDTO);

    /**
     * �α��� ���� ���� ����
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @param request 
     */
    public void insertLogHist(User loginUser, HttpServletRequest request);
    
    
    /**
     * �������̽� �α��� ���� ����
     * @param loginDTO
     * @return
     */
    public List findIfUserInfo(LoginDTO loginDTO);
    
    
    public int getTerminalType(HttpServletRequest request);
    
    /**
     * ����������̸� ��� Serial
     * �� �����̸� IP�ּ�
     * ���
     */
    public String getTerminalNo(HttpServletRequest request);
    
    /**
     * ���� device ã�� 
     */
    public List findIfDeviceInfo(LoginDTO loginDTO);
    
    
    public String countNamedUserCnt();
    
    /**
     * �α��� �õ� �̷� ����
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param loginUser
     * @param request 
     */
    public void insertLoginTryLog(LoginDTO loginDTO, HttpServletRequest request, String isSuccess);
    
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
