package dream.invt.list.service.spring;

import common.bean.User;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dao.InvtItemsDetailDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtItemsDetailDTO;
import dream.invt.list.service.InvtItemsDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="invtItemsDetailServiceTarget"
 * @spring.txbn id="invtItemsDetailService"
 * @spring.property name="invtItemsDetailDAO" ref="invtItemsDetailDAO"
 * @spring.property name="invtDetailDAO" ref="invtDetailDAO"
 */
public class InvtItemsDetailServiceImpl implements InvtItemsDetailService
{
    private InvtItemsDetailDAO invtItemsDetailDAO = null;
    private InvtDetailDAO invtDetailDAO = null;
    
    public InvtDetailDAO getInvtDetailDAO()
    {
        return invtDetailDAO;
    }

    public void setInvtDetailDAO(InvtDetailDAO invtDetailDAO)
    {
        this.invtDetailDAO = invtDetailDAO;
    }

    public InvtItemsDetailDAO getInvtItemsDetailDAO() {
		return invtItemsDetailDAO;
	}

	public void setInvtItemsDetailDAO(InvtItemsDetailDAO invtItemsDetailDAO) {
		this.invtItemsDetailDAO = invtItemsDetailDAO;
	}

	public InvtItemsDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)throws Exception
    {
        return invtItemsDetailDAO.findDetail(invtCommonDTO, user);
    }
    
	public int updateDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception
    {        
		return invtItemsDetailDAO.updateDetail(invtItemsDetailDTO, invtCommonDTO, user);
    }
	
	public int insertDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception
    {        
		return invtItemsDetailDAO.insertDetail(invtItemsDetailDTO, invtCommonDTO, user);
    }
	
	public String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception
	{
		return invtItemsDetailDAO.copyDetail(oldInvtId, newInvtId, oldKeyId, newKeyId, user);
	}

}
