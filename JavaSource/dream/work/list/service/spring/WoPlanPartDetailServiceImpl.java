package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.WoPlanPartDetailDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;
import dream.work.list.service.WoPlanPartDetailService;

/**
 * 작업계획목록 - 투입부품 상세
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="woPlanPartDetailServiceTarget"
 * @spring.txbn id="woPlanPartDetailService"
 * @spring.property name="woPlanPartDetailDAO" ref="woPlanPartDetailDAO"
 */
public class WoPlanPartDetailServiceImpl implements WoPlanPartDetailService
{
    private WoPlanPartDetailDAO woPlanPartDetailDAO = null;
    
    public WoPlanPartDetailDAO getWoPlanPartDetailDAO() {
		return woPlanPartDetailDAO;
	}

	public void setWoPlanPartDetailDAO(WoPlanPartDetailDAO woPlanPartDetailDAO) {
		this.woPlanPartDetailDAO = woPlanPartDetailDAO;
	}

	public WoPlanPartDetailDTO findDetail(String wkOrId, String woPartId, User user)throws Exception
    {
        return woPlanPartDetailDAO.findDetail(wkOrId, woPartId, user);
    }
    
	public int updateDetail(WoPlanPartDetailDTO woPlanPartDetailDTO, WoPlanCommonDTO woPlanCommonDTO) throws Exception
    {        
		
        return woPlanPartDetailDAO.updateDetail(woPlanPartDetailDTO, woPlanCommonDTO);
    }
	public int insertDetail(WoPlanPartDetailDTO woPlanPartDetailDTO, WoPlanCommonDTO woPlanCommonDTO) throws Exception
    {   		
		
		//Serial Parts 아니면 Serial 삭제 
		
        return woPlanPartDetailDAO.insertDetail( woPlanPartDetailDTO, woPlanCommonDTO);
    }
	public String getStockQty(WoPlanPartDetailDTO woPlanPartDetailDTO, User loginUser){
		return woPlanPartDetailDAO.getStockQty(woPlanPartDetailDTO, loginUser);
	}
}
