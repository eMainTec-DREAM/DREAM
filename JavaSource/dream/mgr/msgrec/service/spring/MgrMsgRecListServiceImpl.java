package dream.mgr.msgrec.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.msgrec.dao.MgrMsgRecListDAO;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;
import dream.mgr.msgrec.service.MgrMsgRecListService;

/**
 * 메시지 수신설정 Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrMsgRecListServiceTarget"
 * @spring.txbn id="mgrMsgRecListService"
 * @spring.property name="mgrMsgRecListDAO" ref="mgrMsgRecListDAO"
 */
public class MgrMsgRecListServiceImpl implements MgrMsgRecListService
{
    private MgrMsgRecListDAO mgrMsgRecListDAO = null;

    public List findList(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user) throws Exception
    {      
        return mgrMsgRecListDAO.findList(mgrMsgRecCommonDTO,user);
    }

    public MgrMsgRecListDAO getMgrMsgRecListDAO() {
        return mgrMsgRecListDAO;
    }

    public void setMgrMsgRecListDAO(MgrMsgRecListDAO mgrMsgRecListDAO) {
        this.mgrMsgRecListDAO = mgrMsgRecListDAO;
    }    
    
    public String findTotalCount(MgrMsgRecCommonDTO mgrMsgRecCommonDTO,User user)  throws Exception
    {
        return mgrMsgRecListDAO.findTotalCount(mgrMsgRecCommonDTO, user);
    }

	public int deleteList(String[] deleteRows, User user) throws Exception 
	{
		int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrMsgRecListDAO.deleteList(id, user);
            }
        return result;
	}

}
