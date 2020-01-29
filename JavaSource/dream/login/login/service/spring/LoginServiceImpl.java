/**
 * 
 */
package dream.login.login.service.spring;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import common.bean.MwareConfig;
import common.bean.User;
import common.session.SessionTable;
import common.struts.BaseAction;
import dream.login.login.dao.LoginDAO;
import dream.login.login.dto.LoginDTO;
import dream.login.login.service.LoginService;

/**
 * Login Service Impl
 * @author  javaworker
 * @version $Id: LoginServiceImpl.java,v 1.1 2013/08/30 09:14:13 javaworker Exp $
 * @since   1.0
 * @spring.bean id="loginServiceTarget"
 * @spring.txbn id="loginService"
 * @spring.property name="loginDAO" ref="loginDAO"
 */
public class LoginServiceImpl implements LoginService
{
    private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

    /**
     * User ������ �������� DAO
     */
    private LoginDAO loginDAO = null;
    public LoginDAO getLoginDAO()
    {
        return loginDAO;
    }

    public void setLoginDAO(LoginDAO loginDAO)
    {
        this.loginDAO = loginDAO;
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

    public boolean isUsing(String userID, String compNo)
    {
        boolean isUsing = false;
        Enumeration e = BaseAction.getLoginUsers().keys();
        String key = "";
        userID = userID.toUpperCase();
        
        while (e.hasMoreElements())
        {
            key = (String)e.nextElement();
            User user = (User)BaseAction.getLoginUsers().get(key);
            
            //������ �ҹ��ڸ� �빮�ڷ� ��ȯ�Ǿ� ������ userID ���� �񱳰� �ȵǾ� ������ �α����� �����Ͽ�, ������.
            if (userID.equals(user.getUserNo().toUpperCase()) && "dream".equals(user.getPageType()) && compNo.equals(user.getCompNo()))
            {
                isUsing = true;
            }
        }
        return isUsing;
    }
    
    public boolean isForceLogOut(String userNo, String compNo, String currentSessionId)
    {
        boolean isUsing = false;
        Enumeration e = BaseAction.getLoginUsers().keys();
        String key = "";
        
        while(e.hasMoreElements())
        {
            key = (String)e.nextElement();
            User user = (User)BaseAction.getLoginUsers().get(key);
           
            if (userNo.equals(user.getUserNo().toUpperCase()) && "dream".equals(user.getPageType()) && compNo.equals(user.getCompNo()) && !key.equals(currentSessionId))
            {
                isUsing = true;
                
                SessionTable.removeSession(key);
            }
            /*if (userNo.equals(user.getUserNo()))
            {
                isUsing = true;
                
                
                 * ���� userId�� �α��� �Ǿ��ִ� session id ��
                 * ���� userId�� �α��� �õ��ϴ� session id �� ���ٸ�
                 * session�� invalidate() ��Ű�� �ʴ´�.
                 
                if (!key.equals(currentSessionId))
                {
                    SessionTable.removeSession(key);
                }
                
                break;
            }*/
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

    public List findUserInfo(LoginDTO loginDTO)
    {
        return loginDAO.findUserInfo(loginDTO);
    }
    
    public void setLoginDate(LoginDTO loginDTO)
    {
        loginDAO.setLoginDate(loginDTO);
    }
    
    public void insertLogHist(User loginUser, HttpServletRequest request)
    {
    	String loginTerminal = "WEB";
    	String terminalNo = getTerminalNo(request);
    	if(getTerminalType(request)==1) loginTerminal="MOBILE";
    	
        loginDAO.insertLogHist(loginUser, loginTerminal, terminalNo);
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

	public List findIfUserInfo(LoginDTO loginDTO) 
	{
		 return loginDAO.findIfUserInfo(loginDTO);
	}
	public List findIfDeviceInfo(LoginDTO loginDTO) 
	{
		 return loginDAO.findIfDeviceInfo(loginDTO);
	}
	
	public String countNamedUserCnt() 
	{
		return loginDAO.countNamedUserCnt();
	}
	
	public void insertLoginTryLog(LoginDTO loginDTO, HttpServletRequest request, String isSuccess)
	{
		String loginTerminal = "WEB";
		String terminalNo = getTerminalNo(request);
		if(getTerminalType(request)==1) loginTerminal="MOBILE";
		
		Enumeration params = request.getParameterNames();		
		StringBuffer  encValues = null;
		Map paramLogMap = new HashMap();
		while (params.hasMoreElements()) 
        {
            String name = (String)params.nextElement();
            String []values = request.getParameterValues(name);
            if (values==null)
            {
                String value = request.getParameter(name);
                logger.debug(name + "=" + value);
                encValues = new StringBuffer();
                encValues.append(value);
            }
            else
            {
                encValues = new StringBuffer();
                for (int i=0; i<values.length; i++)
                {
                	logger.debug(name + "[" + i + "]=" + values[i]);

                    encValues.append(i==0? values[i]:"^|^"+ values[i]);

                }
                paramLogMap.put(name, encValues.toString());
            }
        }
		
		loginDAO.insertLoginTryLog(loginDTO, loginTerminal, terminalNo, isSuccess, paramLogMap.toString());
		
		// TAUSER.LOGIN_FAIL_CNT ����
		loginDAO.updateFailCnt(loginDTO, isSuccess);
		
		// �α��� ���� & TACONFIG.PW_FAIL_LIMIT_CNT�� 0�� �ƴ� ���
		if("N".equals(isSuccess)&& !"0".equals(MwareConfig.getPwFailLimitCnt()))
		{
			// TACONFIG.PW_FAIL_LIMIT_CNT �ʰ��� TAUSER.IS_LOCKED�� Y�� ������Ʈ
			loginDAO.updateLockUser(loginDTO);
		}
	}

	@Override
	public List findCompInfo(LoginDTO loginDTO) {
		
		return loginDAO.findCompInfo(loginDTO);
	}
}
