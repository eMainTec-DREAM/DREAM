package dream.invt.prc.service.spring;

import dream.invt.prc.dao.InvtPrcTpDetailDAO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpDetailDTO;
import dream.invt.prc.service.InvtPrcTpDetailService;

/**
 * 구매절차 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: InvtPrcTpDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="invtPrcTpDetailServiceTarget"
 * @spring.txbn id="invtPrcTpDetailService"
 * @spring.property name="invtPrcTpDetailDAO" ref="invtPrcTpDetailDAO"
 */
public class InvtPrcTpDetailServiceImpl implements InvtPrcTpDetailService
{
    private InvtPrcTpDetailDAO invtPrcTpDetailDAO = null;
    
    public InvtPrcTpDetailDAO getInvtPrcTpDetailDAO() {
		return invtPrcTpDetailDAO;
	}

	public void setInvtPrcTpDetailDAO(InvtPrcTpDetailDAO invtPrcTpDetailDAO) {
		this.invtPrcTpDetailDAO = invtPrcTpDetailDAO;
	}

	public InvtPrcTpDetailDTO findDetail(InvtPrcTpCommonDTO invtPrcTpCommonDTO)throws Exception
    {
        return invtPrcTpDetailDAO.findDetail(invtPrcTpCommonDTO);
    }
	
	public int updateDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO) throws Exception
    {        
        return invtPrcTpDetailDAO.updateDetail(invtPrcTpDetailDTO);
    }
	public int confirmDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO) throws Exception
    {        
        return invtPrcTpDetailDAO.confirmDetail(invtPrcTpDetailDTO);
    }
	public int insertDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO) throws Exception
    {        
        return invtPrcTpDetailDAO.insertDetail(invtPrcTpDetailDTO);
    }
}
