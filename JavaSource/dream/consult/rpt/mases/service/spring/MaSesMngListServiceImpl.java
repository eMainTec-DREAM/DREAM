package dream.consult.rpt.mases.service.spring;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import common.session.SessionTable;
import common.struts.BaseAction;
import dream.consult.rpt.mases.service.MaSesMngListService;

/**
 * 실시간 사용자 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaSesMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maSesMngListServiceTarget"
 * @spring.txbn id="maSesMngListService"
 */
public class MaSesMngListServiceImpl implements MaSesMngListService
{
	public int deleteSes(String[] deleteRows) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {

                Hashtable loginUsersTable = BaseAction.getLoginUsers();
                loginUsersTable.remove(id);
                
                Hashtable sessionTable = SessionTable.getATable();
                try
                {
                    HttpSession session = (HttpSession)sessionTable.get(id);
                    session.invalidate();
                }
                catch(Exception ex)
                {
                    sessionTable.remove(id);
                }
                result++;
          	
            }
        return result;
    }
}

