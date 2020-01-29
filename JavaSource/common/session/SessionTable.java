package common.session;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.http.HttpSession;
import common.struts.BaseAction;

/**
 * 로그인된 session 관리
 * @author  javaworker
 * @version $Id: SessionTable.java,v 1.1 2013/08/30 09:12:52 javaworker Exp $
 * @since   1.0
 */
public class SessionTable 
{
    /** session id key 로 session 저장 */
    private static Hashtable aTable;
    
    /**
     * 생성자
     */
    public SessionTable() 
    { 
        if (SessionTable.aTable == null)
        {
            SessionTable.aTable = new Hashtable();
        }
    }
    
    /**
     * key로 세션값 하나 얻기
     * @author  oikesis
     * @version $Id: SessionTable.java,v 1.1 2013/08/30 09:12:52 javaworker Exp $
     * @since   1.0
     * 
     * @param key
     * @return
     */
    public HttpSession getSession(String key) 
    { 
        if( key == null) 
        {
            return null;
        }
        return  (HttpSession)(SessionTable.aTable.get(key)) ; 
    } 

    /**
     * 세션넣기
     * @author  oikesis
     * @version $Id: SessionTable.java,v 1.1 2013/08/30 09:12:52 javaworker Exp $
     * @since   1.0
     * 
     * @param session
     * @return
     */
    public boolean putSession(HttpSession session) 
    { 
        if( session == null) 
        {
            return false;
        }
        
        HttpSession crashSession = (HttpSession)(SessionTable.aTable.put(session.getId(), session)); 
        
        if(crashSession == null) 
        { 
            return true; 
        }

        try
        {
            crashSession.invalidate();
        }
        catch(Exception ex){}
        
        crashSession = null; 
        return false; 
    } 

    /**
     * 세션 지우기 (키값으로 찾아서..)
     * @author  oikesis
     * @version $Id: SessionTable.java,v 1.1 2013/08/30 09:12:52 javaworker Exp $
     * @since   1.0
     * 
     * @param key
     * @return
     */
    public static boolean removeSession(String key) 
    { 
        if( key == null) return false; 
    
        /*
         * invalidate 시키면서
         * UserSessionListener.sessionDestroyed() 안에서 호출된다.
         */
        Object sessionObj = SessionTable.getATable().get(key); 
        
        if( sessionObj == null ) 
        {
            return false; 
        }
        
        HttpSession session = (HttpSession)sessionObj;
        
        try
        {
            session.invalidate();
        }
        catch(Exception ex){}
        
        session = null; 
        return true; 
    } 

    public static Hashtable getATable()
    {
        return aTable;
    }

    /**
     * sesstion table 에 등록된 session 중
     * BaseAction.loginUsers 에 등록되지 않은 session은 모두 삭제한다.
     * @author  javaworker
     * @version $Id: SessionTable.java,v 1.1 2013/08/30 09:12:52 javaworker Exp $
     * @since   1.0
     *
     */
    public static void arrangeSesstion()
    {
        Hashtable loginUsersTable = BaseAction.getLoginUsers();
        Hashtable sessionTable    = SessionTable.getATable();
        
        // session table 에 담긴 Key : session Id
        Enumeration sessionEm = sessionTable.keys();
        
        while (sessionEm.hasMoreElements())
        {
            // Login User의 session Id
            String sessionKey = (String)sessionEm.nextElement();
            
            Enumeration loginUsersEm = loginUsersTable.keys();
            
            // 현재 session 의 제거 여부
            boolean inValidSession = true;
            
            while (loginUsersEm.hasMoreElements())
            {
                // session table의 session Id 
                String loginUserKey = (String)loginUsersEm.nextElement();
                
                // session table의 session id가 login user 의 session id가 같은지 비교한다.
                if (sessionKey.equals(loginUserKey))
                {
                    // session 유효
                    inValidSession = false;
                    break;  // exit : while (loginUsersEm.hasMoreElements())
                }
            }
            
            // session을 제거한다.
            if (inValidSession)
            {
                try
                {
                    HttpSession session = (HttpSession)sessionTable.get(sessionKey);
                    session.invalidate();
                }
                catch(Exception ex)
                {
                    // session 이미 invalid 된 경우 or 해당 Key 가  Session에 없는 경우
                    loginUsersTable.remove(sessionKey);
                    sessionTable.remove(sessionKey);
                }
            }
        }
    }
} 

