package intf.dream.cricket.login.service.spring;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import common.bean.User;
import common.session.SessionTable;
import common.struts.BaseAction;
import dream.login.login.service.spring.LoginServiceImpl;
import intf.dream.cricket.login.dao.CricketLoginDAO;
import intf.dream.cricket.login.service.CricketLoginService;

/**
 * Android Login Service Impl
 * @author  javaworker
 * @version $Id: CricketLoginServiceImpl.java,v 1.1 2013/08/30 09:14:13 javaworker Exp $
 * @since   1.0
 * @spring.bean id="cricketLoginServiceTarget"
 * @spring.txbn id="cricketLoginService"
 * @spring.property name="cricketLoginDAO" ref="cricketLoginDAO"
 */
public class CricketLoginServiceImpl implements CricketLoginService
{
    private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

    /**
     * User 정보를 가져오는 DAO
     */
    private CricketLoginDAO cricketLoginDAO = null;
    

    public CricketLoginDAO getCricketLoginDAO() {
		return cricketLoginDAO;
	}

	public void setCricketLoginDAO(CricketLoginDAO cricketLoginDAO) {
		this.cricketLoginDAO = cricketLoginDAO;
	}

	public boolean isLogin(String sessionID)
    {
        boolean isLogin = false;
        Enumeration e = BaseAction.getLoginUsers().keys();
        String key = "";
        while(e.hasMoreElements())
        {
            key = (String)e.nextElement();
            if(sessionID.equals(key))
            {
                isLogin = true;
            }
        }
        return isLogin;
    }

    public boolean isUsing(String userID)
    {
        boolean isUsing = false;
        Enumeration e = BaseAction.getLoginUsers().keys();
        String key = "";
        while (e.hasMoreElements())
        {
            key = (String)e.nextElement();
            
            User user = (User)BaseAction.getLoginUsers().get(key);
            if (userID.equals(user.getUserNo()) && "dream".equals(user.getPageType()))
            {
                isUsing = true;
            }
        }
        return isUsing;
    }
    
    public boolean isForceLogOut(String userNo, String currentSessionId)
    {
        boolean isUsing = false;
        Enumeration e = BaseAction.getLoginUsers().keys();
        String key = "";
        
        while(e.hasMoreElements())
        {
            key = (String)e.nextElement();
            User user = (User)BaseAction.getLoginUsers().get(key);
            if (userNo.equals(user.getUserNo()))
            {
                isUsing = true;
                
                /*
                 * 현재 userId로 로그인 되어있는 session id 와
                 * 같은 userId로 로그인 시도하는 session id 가 같다면
                 * session을 invalidate() 시키지 않는다.
                 */
                if (!key.equals(currentSessionId))
                {
                    SessionTable.removeSession(key);
                }
                
                break;
            }
        }
        
        return isUsing;
    }

    public void unBound(HttpSession session)
    {
        if(session!=null) 
        {
            try
            {
                //BaseAction.getLoginUsers().remove(session.getId());
                session.invalidate();
            }
            catch(IllegalStateException e)
            {
                logger.debug(e.getStackTrace());
            }
        }
    }

    public List findUserInfo(Map map)
    {
        return cricketLoginDAO.findUserInfo(map);
    }
    public List findDeviceInfo(Map map)
    {
        return cricketLoginDAO.findDeviceInfo(map);
    }
    public void setLoginDate(Map map)
    {
    	cricketLoginDAO.setLoginDate(map);
    }
    
    public void insertLogHist(User loginUser, HttpServletRequest request)
    {
    	String loginTerminal = "Android";
    	String terminalNo = getTerminalNo(request);
    	if(getTerminalType(request)==1) loginTerminal="MOBILE";
    	
    	cricketLoginDAO.insertLogHist(loginUser, loginTerminal, terminalNo);
    }
    /**
     * 모바일접속인지 웹접속인지 알아내기
     */
    public int getTerminalType(HttpServletRequest request){
    	int isMobile = 0;
    	String agent = request.getHeader("USER-AGENT");
    	String[] mobileos = {"iPhone","iPod","iPad","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
    	int j = -1;
    	for(int i=0 ; i<mobileos.length ; i++) {
    		j=agent.indexOf(mobileos[i]);
    		if(j > -1 )
    		{
    		// 모바일로 접근했을 때
    		isMobile = 1;
    		break;
    		}
    	}

    	return isMobile;
    }
    
    /**
     * 모바일접속이면 기계 Serial
     * 웹 접속이면 IP주소
     * 얻기
     */
    public String getTerminalNo(HttpServletRequest request){
    	int isMobile = 0;
    	String terminalNo = "";
    	String agent = request.getHeader("USER-AGENT");
    	String[] mobileos = {"iPhone","iPod","iPad","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
    	int j = -1;
    	for(int i=0 ; i<mobileos.length ; i++) {
    		j=agent.indexOf(mobileos[i]);
    		if(j > -1 )
    		{
    		// 모바일로 접근했을 때
    		isMobile = 1;
    		terminalNo = mobileos[i]+"("+request.getRemoteAddr()+")";
    		break;
    		}
    	}
    	if(isMobile!=1) terminalNo = request.getRemoteAddr();

    	return terminalNo;
    }

	public List findIfUserInfo(Map map) 
	{
		 return cricketLoginDAO.findIfUserInfo(map);
	}
}
