package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.WoPlanCraftDetailDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.service.WoPlanCraftDetailService;

/**
 * 작업계획목록 - 작업자 상세
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="woPlanCraftDetailServiceTarget"
 * @spring.txbn id="woPlanCraftDetailService"
 * @spring.property name="woPlanCraftDetailDAO" ref="woPlanCraftDetailDAO"
 */
public class WoPlanCraftDetailServiceImpl implements WoPlanCraftDetailService
{
    private WoPlanCraftDetailDAO woPlanCraftDetailDAO = null;
    
    public WoPlanCraftDetailDAO getWoPlanCraftDetailDAO() {
		return woPlanCraftDetailDAO;
	}

	public void setWoPlanCraftDetailDAO(WoPlanCraftDetailDAO woPlanCraftDetailDAO) {
		this.woPlanCraftDetailDAO = woPlanCraftDetailDAO;
	}

	public WoPlanCraftDetailDTO findDetail(String wkOrId, String woCraftId, String compNo)throws Exception
    {
        return woPlanCraftDetailDAO.findDetail(wkOrId, woCraftId, compNo);
    }
    
	public int updateDetail(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO) throws Exception
    {        
        return woPlanCraftDetailDAO.updateDetail(woPlanCraftDetailDTO, woPlanCommonDTO);
    }
	public int insertDetail(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO) throws Exception
    {        
        return woPlanCraftDetailDAO.insertDetail( woPlanCraftDetailDTO, woPlanCommonDTO);
    }
	public String validEmp(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO, User loginUser){
		return woPlanCraftDetailDAO.validEmp(woPlanCraftDetailDTO, woPlanCommonDTO, loginUser);
	}
}
