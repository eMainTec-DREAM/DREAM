package common.session;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.http.HttpSession;
import common.struts.BaseAction;

/**
 * �α��ε� session ����
 * @author  javaworker
 * @version $Id: SessionTable.java,v 1.1 2013/08/30 09:12:52 javaworker Exp $
 * @since   1.0
 */
public class SessionTable 
{
    /** session id key �� session ���� */
    private static Hashtable aTable;
    
    /**
     * ������
     */
    public SessionTable() 
    { 
        if (SessionTable.aTable == null)
        {
            SessionTable.aTable = new Hashtable();
        }
    }
    
    /**
     * key�� ���ǰ� �ϳ� ���
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
     * ���ǳֱ�
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
     * ���� ����� (Ű������ ã�Ƽ�..)
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
         * invalidate ��Ű�鼭
         * UserSessionListener.sessionDestroyed() �ȿ��� ȣ��ȴ�.
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
     * sesstion table �� ��ϵ� session ��
     * BaseAction.loginUsers �� ��ϵ��� ���� session�� ��� �����Ѵ�.
     * @author  javaworker
     * @version $Id: SessionTable.java,v 1.1 2013/08/30 09:12:52 javaworker Exp $
     * @since   1.0
     *
     */
    public static void arrangeSesstion()
    {
        Hashtable loginUsersTable = BaseAction.getLoginUsers();
        Hashtable sessionTable    = SessionTable.getATable();
        
        // session table �� ��� Key : session Id
        Enumeration sessionEm = sessionTable.keys();
        
        while (sessionEm.hasMoreElements())
        {
            // Login User�� session Id
            String sessionKey = (String)sessionEm.nextElement();
            
            Enumeration loginUsersEm = loginUsersTable.keys();
            
            // ���� session �� ���� ����
            boolean inValidSession = true;
            
            while (loginUsersEm.hasMoreElements())
            {
                // session table�� session Id 
                String loginUserKey = (String)loginUsersEm.nextElement();
                
                // session table�� session id�� login user �� session id�� ������ ���Ѵ�.
                if (sessionKey.equals(loginUserKey))
                {
                    // session ��ȿ
                    inValidSession = false;
                    break;  // exit : while (loginUsersEm.hasMoreElements())
                }
            }
            
            // session�� �����Ѵ�.
            if (inValidSession)
            {
                try
                {
                    HttpSession session = (HttpSession)sessionTable.get(sessionKey);
                    session.invalidate();
                }
                catch(Exception ex)
                {
                    // session �̹� invalid �� ��� or �ش� Key ��  Session�� ���� ���
                    loginUsersTable.remove(sessionKey);
                    sessionTable.remove(sessionKey);
                }
            }
        }
    }
} 

