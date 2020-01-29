package dream.invt.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dao.InvtItemsListDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.service.InvtItemsListService;

/**
 * 格废 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="invtItemsListServiceTarget"
 * @spring.txbn id="invtItemsListService"
 * @spring.property name="invtItemsListDAO" ref="invtItemsListDAO"
 * @spring.property name="invtDetailDAO" ref="invtDetailDAO"
 * 
 */
public class InvtItemsListServiceImpl implements InvtItemsListService
{
    private InvtItemsListDAO invtItemsListDAO = null;
    private InvtDetailDAO invtDetailDAO = null;

    public InvtItemsListDAO getInvtItemsListDAO() {
		return invtItemsListDAO;
	}
	public void setInvtItemsListDAO(InvtItemsListDAO invtItemsListDAO) {
		this.invtItemsListDAO = invtItemsListDAO;
	}
	public InvtDetailDAO getInvtDetailDAO()
    {
        return invtDetailDAO;
    }
    public void setInvtDetailDAO(InvtDetailDAO invtDetailDAO)
    {
        this.invtDetailDAO = invtDetailDAO;
    }
	
	public List findList(InvtCommonDTO invtCommonDTO, User user)
    {      
        return invtItemsListDAO.findList(invtCommonDTO, user);
    }
	
	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            String invtListId = invtDetailDAO.getInvtListId(deleteRows[i], user);
            
            //备概亲格 昏力
            result = result + invtItemsListDAO.deleteList(deleteRows[i], user);
        }
        
        return result;
    }
	public String findTotalCount(InvtCommonDTO invtCommonDTO,User user)  throws Exception
    {
        return invtItemsListDAO.findTotalCount(invtCommonDTO, user);
    }
}

