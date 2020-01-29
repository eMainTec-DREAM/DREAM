package common.session;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import common.bean.User;
import common.session.service.SessionService;
import common.struts.BaseAction;

/**
 * Session Listener
 * session 생성시, session 제거시 자동으로 호출된다.
 * web.xml 에 정의 되어 있다.
 * @author  javawokrer
 * @version $Id: UserSessionListener.java,v 1.1 2013/08/30 09:12:52 javaworker Exp $
 * @since   1.0
 */
public class UserSessionListener
        implements HttpSessionListener
{
    private SessionTable sessionTable;
    
    public UserSessionListener()
    {
        sessionTable = new SessionTable();
    }
    
    /**
     * 세션 생성시 호출된다.
     */
    public void sessionCreated(HttpSessionEvent event)
    {
        sessionTable.putSession(event.getSession());
    }

    /**
     * 세션이 제거 되면 호출된다.
     */
    public void sessionDestroyed(HttpSessionEvent event)
    {
        HttpSession session = event.getSession();
        
        String pageType = String.valueOf(session.getAttribute("pageType"));
        pageType = pageType == "null"?"":pageType;
        
        /*String str;
        Hashtable loginUserList =  BaseAction.getLoginUsers();
        Set<String> keys = loginUserList.keySet();
        Iterator<String> itr = keys.iterator();
     
        //Displaying Key and value pairs
        while (itr.hasNext()) { 
           // Getting Key
           str = itr.next();

           System.out.println("Session Id:"+session.getId()+"  Key: "+str+" & Value: "+((User)loginUserList.get(str)).getUserName());
        } */
        
        // 현재 로그아웃하는 유저
        User logoutUser = (User)BaseAction.getLoginUsers().get(session.getId());
        if (logoutUser == null) return;
        
        // 로그아웃 정보를 저장한다.
        SessionService sessionService = (SessionService)BaseAction.ctx.getBean("sessionService");
        // 로그아웃 정보 기록
        if(pageType == "gaia")
        {
            sessionService.saveGaiaLogoutHist(logoutUser);
        }
        else sessionService.saveLogoutHist(logoutUser);
        
        // 현재 로그인 유저에 값을 삭제한다.
        BaseAction.getLoginUsers().remove(session.getId());
        
        // session table안의 session을 삭제한다.
        SessionTable.getATable().remove(session.getId());
        
    }
}
