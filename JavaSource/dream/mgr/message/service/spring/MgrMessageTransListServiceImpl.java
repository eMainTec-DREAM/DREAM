package dream.mgr.message.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.message.dao.MgrMessageTransListDAO;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;
import dream.mgr.message.service.MgrMessageTransListService;

/**
 * Message Transfer Page - List Service implements
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrMessageTransListServiceTarget"
 * @spring.txbn id="mgrMessageTransListService"
 * @spring.property name="mgrMessageTransListDAO" ref="mgrMessageTransListDAO"
 */
public class MgrMessageTransListServiceImpl implements MgrMessageTransListService
{
	private MgrMessageTransListDAO mgrMessageTransListDAO = null;

	public MgrMessageTransListDAO getMgrMessageTransListDAO() {
		return mgrMessageTransListDAO;
	}

	public void setMgrMessageTransListDAO(MgrMessageTransListDAO mgrMessageTransListDAO) {
		this.mgrMessageTransListDAO = mgrMessageTransListDAO;
	}

	public List findList(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user) throws Exception
    {      
        return mgrMessageTransListDAO.findList(mgrMessageTransCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrMessageTransListDAO.deleteList(id, user);
            }
        return result;
    }
	
	public String findTotalCount(MgrMessageTransCommonDTO mgrMessageTransCommonDTO,User user) throws Exception
    {
        return mgrMessageTransListDAO.findTotalCount(mgrMessageTransCommonDTO, user);
    }
}

