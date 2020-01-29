/**
 * 
 */
package mobile.dream.login.service.spring;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import common.bean.User;
import common.session.SessionTable;
import common.struts.BaseAction;
import mobile.dream.login.dao.MobileLoginDAO;
import mobile.dream.login.dto.MobileLoginDTO;
import mobile.dream.login.service.MobileLoginService;

/**
 * Login Service Impl
 * @author  javaworker
 * @version $Id: LoginServiceImpl.java,v 1.1 2013/08/30 09:14:13 javaworker Exp $
 * @since   1.0
 * @spring.bean id="mobileLoginServiceTarget"
 * @spring.txbn id="mobileLoginService"
 * @spring.property name="mobileLoginDAO" ref="mobileLoginDAO"
 */
public class MobileLoginServiceImpl implements MobileLoginService
{
    private static final Logger logger = Logger.getLogger(MobileLoginServiceImpl.class);

    /**
     * User ������ �������� DAO
     */
    private MobileLoginDAO mobileLoginDAO = null;
    

    public MobileLoginDAO getMobileLoginDAO() {
		return mobileLoginDAO;
	}

	public void setMobileLoginDAO(MobileLoginDAO mobileLoginDAO) {
		this.mobileLoginDAO = mobileLoginDAO;
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
                 * ���� userId�� �α��� �Ǿ��ִ� session id ��
                 * ���� userId�� �α��� �õ��ϴ� session id �� ���ٸ�
                 * session�� invalidate() ��Ű�� �ʴ´�.
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

    public List findUserInfo(MobileLoginDTO loginDTO)
    {
        return mobileLoginDAO.findUserInfo(loginDTO);
    }
    
    public void setLoginDate(MobileLoginDTO loginDTO)
    {
    	mobileLoginDAO.setLoginDate(loginDTO);
    }
    
    public void insertLogHist(User loginUser, HttpServletRequest request)
    {
    	String loginTerminal = "WEB";
    	String terminalNo = getTerminalNo(request);
    	if(getTerminalType(request)==1) loginTerminal="MOBILE";
    	
    	mobileLoginDAO.insertLogHist(loginUser, loginTerminal, terminalNo);
    }
    /**
     * ������������� ���������� �˾Ƴ���
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
    		// ����Ϸ� �������� ��
    		isMobile = 1;
    		break;
    		}
    	}

    	return isMobile;
    }
    
    /**
     * ����������̸� ��� Serial
     * �� �����̸� IP�ּ�
     * ���
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
    		// ����Ϸ� �������� ��
    		isMobile = 1;
    		terminalNo = mobileos[i]+"("+request.getRemoteAddr()+")";
    		break;
    		}
    	}
    	if(isMobile!=1) terminalNo = request.getRemoteAddr();

    	return terminalNo;
    }

	public List findIfUserInfo(MobileLoginDTO loginDTO) 
	{
		 return mobileLoginDAO.findIfUserInfo(loginDTO);
	}
}
