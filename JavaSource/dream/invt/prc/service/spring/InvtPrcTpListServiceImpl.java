package dream.invt.prc.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.prc.dao.InvtPrcTpListDAO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.service.InvtPrcTpListService;

/**
 * 구매절차 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: InvtPrcTpListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtPrcTpListServiceTarget"
 * @spring.txbn id="invtPrcTpListService"
 * @spring.property name="invtPrcTpListDAO" ref="invtPrcTpListDAO"
 */
public class InvtPrcTpListServiceImpl implements InvtPrcTpListService
{
    private InvtPrcTpListDAO invtPrcTpListDAO = null;

    public InvtPrcTpListDAO getInvtPrcTpListDAO() {
		return invtPrcTpListDAO;
	}

	public void setInvtPrcTpListDAO(InvtPrcTpListDAO invtPrcTpListDAO) {
		this.invtPrcTpListDAO = invtPrcTpListDAO;
	}

	public List findInvtPrcTpList(InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user)
    {      
        return invtPrcTpListDAO.findInvtPrcTpList(invtPrcTpCommonDTO, user);
    }
	
	public int deleteQna(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + invtPrcTpListDAO.deleteQna(id,user);
                result = result + invtPrcTpListDAO.deleteTpItem(id, user);
                result = result + invtPrcTpListDAO.deleteDoc(id, user);
                result = result + invtPrcTpListDAO.deleteObjDoc(id, user);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user)
	{
		return invtPrcTpListDAO.findTotalCount(invtPrcTpCommonDTO, user);
	}
}

