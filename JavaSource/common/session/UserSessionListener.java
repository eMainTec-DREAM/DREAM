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
 * session ������, session ���Ž� �ڵ����� ȣ��ȴ�.
 * web.xml �� ���� �Ǿ� �ִ�.
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
     * ���� ������ ȣ��ȴ�.
     */
    public void sessionCreated(HttpSessionEvent event)
    {
        sessionTable.putSession(event.getSession());
    }

    /**
     * ������ ���� �Ǹ� ȣ��ȴ�.
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
        
        // ���� �α׾ƿ��ϴ� ����
        User logoutUser = (User)BaseAction.getLoginUsers().get(session.getId());
        if (logoutUser == null) return;
        
        // �α׾ƿ� ������ �����Ѵ�.
        SessionService sessionService = (SessionService)BaseAction.ctx.getBean("sessionService");
        // �α׾ƿ� ���� ���
        if(pageType == "gaia")
        {
            sessionService.saveGaiaLogoutHist(logoutUser);
        }
        else sessionService.saveLogoutHist(logoutUser);
        
        // ���� �α��� ������ ���� �����Ѵ�.
        BaseAction.getLoginUsers().remove(session.getId());
        
        // session table���� session�� �����Ѵ�.
        SessionTable.getATable().remove(session.getId());
        
    }
}
