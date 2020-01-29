/**
 * 
 */
package gaia.gaia.service.spring;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import common.bean.User;
import common.session.SessionTable;
import common.struts.BaseAction;
import dream.login.login.service.LoginService;
import gaia.gaia.dao.GaiaDAO;
import gaia.gaia.dto.GaiaDTO;
import gaia.gaia.service.GaiaService;

/**
 * Gaia Login Service Impl
 * @author  jung7126
 * @version $Id: LoginServiceImpl.java,v 1.1 2013/08/30 09:14:13 javaworker Exp $
 * @since   1.0
 * @spring.bean id="gaiaServiceTarget"
 * @spring.txbn id="gaiaService"
 * @spring.property name="gaiaDAO" ref="gaiaDAO"
 * @spring.property name="loginService" ref="loginService"
 */
public class GaiaServiceImpl implements GaiaService
{
    private static final Logger logger = Logger.getLogger(GaiaServiceImpl.class);

    /**
     * User 정보를 가져오는 DAO
     */
    private GaiaDAO gaiaDAO = null;
    
    private LoginService loginService = null;

    public LoginService getLoginService()
    {
        return loginService;
    }

    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }

    public GaiaDAO getGaiaDAO()
    {
        return gaiaDAO;
    }

    public void setGaiaDAO(GaiaDAO gaiaDAO)
    {
        this.gaiaDAO = gaiaDAO;
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
            if (userID.equals(user.getUserNo()) && "gaia".equals(user.getPageType()))
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

    public List findUserInfo(GaiaDTO gaiaDTO)
    {
        return gaiaDAO.findUserInfo(gaiaDTO);
    }

    public ArrayList findGaiaMenuList(User loginUser)
    {
        return gaiaDAO.findGaiaMenuList(loginUser);
    }

    @Override
    public Hashtable findPageFields(User loginUser)
    {
        Hashtable pageFields = new Hashtable();

        List allPageFields = gaiaDAO.findPageFields(loginUser);

        String fileName;
        for(Object ofields: allPageFields)
        {
            Map fields = (Map)ofields;
            fileName = String.valueOf(fields.get("FILENAME"));
            
            if(pageFields.containsKey(fileName)) continue;
            
            ArrayList resultList = new ArrayList();

            for(Object fieldTems: allPageFields)
            {
                Map fieldTem = (Map)fieldTems;
                
                if(fileName.equals(fieldTem.get("FILENAME")))
                {
                    resultList.add(fieldTem);
                }
            }
            
            //각 페이지에 필드 리스트가 세팅됨 
            pageFields.put(fileName, resultList);
        }
        return pageFields;
    }

    @Override
    public void insertLogHist(User loginUser, HttpServletRequest request)
    {
        
        String loginTerminal = "WEB";
        String terminalNo = loginService.getTerminalNo(request);
        if(loginService.getTerminalType(request)==1) loginTerminal="MOBILE";
        
        gaiaDAO.insertLogHist(loginUser, loginTerminal, terminalNo);
    }
}
