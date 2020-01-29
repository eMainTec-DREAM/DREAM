package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.WoPlanWoLetDetailDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;
import dream.work.list.service.WoPlanWoLetDetailService;

/**
 * 작업계획목록 - 안전작업 상세
 * @author syyang
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="woPlanWoLetDetailServiceTarget"
 * @spring.txbn id="woPlanWoLetDetailService"
 * @spring.property name="woPlanWoLetDetailDAO" ref="woPlanWoLetDetailDAO"
 */
public class WoPlanWoLetDetailServiceImpl implements WoPlanWoLetDetailService
{
    private WoPlanWoLetDetailDAO woPlanWoLetDetailDAO = null;
    
    public WoPlanWoLetDetailDAO getWoPlanWoLetDetailDAO() {
		return woPlanWoLetDetailDAO;
	}

	public void setWoPlanWoLetDetailDAO(WoPlanWoLetDetailDAO woPlanWoLetDetailDAO) {
		this.woPlanWoLetDetailDAO = woPlanWoLetDetailDAO;
	}

	public WoPlanWoLetDetailDTO findDetail(String wkOrId, String woWoLetListId, User user)throws Exception
    {
        return woPlanWoLetDetailDAO.findDetail(wkOrId, woWoLetListId, user);
    }
    
	public int updateDetail(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user) throws Exception
    {        
        return woPlanWoLetDetailDAO.updateDetail(woPlanCommonDTO, woPlanWoLetDetailDTO, user);
    }
	public int insertDetail(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user) throws Exception
    {        
        return woPlanWoLetDetailDAO.insertDetail(woPlanCommonDTO, woPlanWoLetDetailDTO, user);
    }

	@Override
	public String validWoLet(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user)
	{
		return woPlanWoLetDetailDAO.validWoLet(woPlanCommonDTO, woPlanWoLetDetailDTO, user);
	}
	
}
