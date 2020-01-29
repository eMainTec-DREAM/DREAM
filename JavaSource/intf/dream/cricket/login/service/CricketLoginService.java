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
     * 해당 세션에서 이미 로그인을 했는지 안했는지를 첵크
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param sessionID
     * @return
     */
    public boolean isLogin(String sessionID);
    
    /**
     * 해당 아이디의 동시 사용을 막기위해 이미 사용중인 아이디인지를 알아본다
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param userID
     * @return
     */
    public boolean isUsing(String userID);

    /**
     * Session 을 invalidate 한다.
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param session
     */
    public void unBound(HttpSession session);
    
    /**
     * 로그인이 되어있는 경우 다시 로그인을 할 경우 
     * 기존에 로그인이 되어 있던 데이터를 제거하고 
     * 다시 로그인을 할 경우 실행된다. 
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
     * index 에서 id, password 를 
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
     * 최근 접속일 저장
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
     * 로그인 유저 정보 저장
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @param request 
     */
    public void insertLogHist(User loginUser, HttpServletRequest request);
    
    
    /**
     * 인터페이스 로그인 유저 정보
     * @param loginDTO
     * @return
     */
    public List findIfUserInfo(Map map);
    
    public int getTerminalType(HttpServletRequest request);
    
    /**
     * 모바일접속이면 기계 Serial
     * 웹 접속이면 IP주소
     * 얻기
     */
    public String getTerminalNo(HttpServletRequest request);
}
