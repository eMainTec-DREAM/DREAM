package intf.dream.cricket.login.service;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.bean.User;

/**
 * Android Login Service
 * @author  javaworker
 * @version $Id: CricketLoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
 * @since   1.0
 *
 */
public interface CricketLoginService
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
     * @return
     */
    public boolean isUsing(String userID);

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
    public boolean isForceLogOut(String userId, String sessionId);
    
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
    public List findUserInfo(Map map);
    public List findDeviceInfo(Map map);
    
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
    public void setLoginDate(Map map);

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
    public List findIfUserInfo(Map map);
    
    public int getTerminalType(HttpServletRequest request);
    
    /**
     * ����������̸� ��� Serial
     * �� �����̸� IP�ּ�
     * ���
     */
    public String getTerminalNo(HttpServletRequest request);
}
