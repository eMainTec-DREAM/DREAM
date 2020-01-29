package dream.invt.prc.service.spring;

import common.bean.User;
import dream.invt.prc.dao.InvtPrcTpItemDetailDAO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemDetailDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;
import dream.invt.prc.service.InvtPrcTpItemDetailService;

/**
 * 구매절차 Item 상세 serviceimpl
 * @author hyosung
 * @version $Id: InvtPrcTpItemDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 hyosung Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtPrcTpItemDetailServiceTarget"
 * @spring.txbn id="invtPrcTpItemDetailService"
 * @spring.property name="invtPrcTpItemDetailDAO" ref="invtPrcTpItemDetailDAO"
 */
public class InvtPrcTpItemDetailServiceImpl implements InvtPrcTpItemDetailService
{
    private InvtPrcTpItemDetailDAO invtPrcTpItemDetailDAO = null;
    
    public InvtPrcTpItemDetailDAO getInvtPrcTpItemDetailDAO() {
		return invtPrcTpItemDetailDAO;
	}

	public void setInvtPrcTpItemDetailDAO(InvtPrcTpItemDetailDAO invtPrcTpItemDetailDAO) {
		this.invtPrcTpItemDetailDAO = invtPrcTpItemDetailDAO;
	}

	public InvtPrcTpItemDetailDTO findDetail(InvtPrcTpItemListDTO invtPrcTpItemListDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO,User user)throws Exception
    {
        return invtPrcTpItemDetailDAO.findDetail(invtPrcTpItemListDTO, invtPrcTpCommonDTO,user);
    }
    
	public int updateDetail(InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user) throws Exception
    {        
        return invtPrcTpItemDetailDAO.updateDetail(invtPrcTpItemDetailDTO, invtPrcTpCommonDTO, user);
    }
	public int insertDetail(InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user) throws Exception
    {        
        return invtPrcTpItemDetailDAO.insertDetail( invtPrcTpItemDetailDTO, invtPrcTpCommonDTO, user);
    }
}
