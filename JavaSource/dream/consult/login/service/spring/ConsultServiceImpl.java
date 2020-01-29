/**
 * 
 */
package dream.consult.login.service.spring;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import common.bean.User;
import common.session.SessionTable;
import common.struts.BaseAction;
import dream.consult.login.dao.ConsultDAO;
import dream.consult.login.dto.ConsultDTO;
import dream.consult.login.service.ConsultService;

/**
 * Consult Login Service Impl
 * @author  jung7126
 * @version $Id: LoginServiceImpl.java,v 1.1 2013/08/30 09:14:13 javaworker Exp $
 * @since   1.0
 * @spring.bean id="consultServiceTarget"
 * @spring.txbn id="consultService"
 * @spring.property name="consultDAO" ref="consultDAO"
 */
public class ConsultServiceImpl implements ConsultService
{
    private static final Logger logger = Logger.getLogger(ConsultServiceImpl.class);

    /**
     * User ������ �������� DAO
     */
    private ConsultDAO consultDAO = null;
    public ConsultDAO getConsultDAO()
    {
        return consultDAO;
    }

    public void setConsultDAO(ConsultDAO consultDAO)
    {
        this.consultDAO = consultDAO;
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
            if (userID.equals(user.getUserNo()) && "consult".equals(user.getPageType()))
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

    public List findUserInfo(ConsultDTO consultDTO)
    {
        return consultDAO.findUserInfo(consultDTO);
    }

    public ArrayList findConsultMenuList(User loginUser)
    {
        return consultDAO.findConsultMenuList(loginUser);
    }

    @Override
    public Hashtable findPageFields(User loginUser)
    {
        Hashtable pageFields = new Hashtable();

        List allPageFields = consultDAO.findPageFields(loginUser);

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
            
            //�� �������� �ʵ� ����Ʈ�� ���õ� 
            pageFields.put(fileName, resultList);
        }
        return pageFields;
    }
}
