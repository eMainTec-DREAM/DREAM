package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.WoPlanWoLetListDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;
import dream.work.list.dto.WoPlanWoLetListDTO;
import dream.work.list.service.WoPlanWoLetDetailService;
import dream.work.list.service.WoPlanWoLetListService;

/**
 * 작업계획목록 - 안전작업 목록
 * @author syyang
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="woPlanWoLetListServiceTarget"
 * @spring.txbn id="woPlanWoLetListService"
 * @spring.property name="woPlanWoLetListDAO" ref="woPlanWoLetListDAO"
 * @spring.property name="woPlanWoLetDetailService" ref="woPlanWoLetDetailService"
 */
public class WoPlanWoLetListServiceImpl implements WoPlanWoLetListService
{
    private WoPlanWoLetListDAO woPlanWoLetListDAO = null;
    private WoPlanWoLetDetailService woPlanWoLetDetailService = null;


	public WoPlanWoLetDetailService getWoPlanWoLetDetailService()
    {
        return woPlanWoLetDetailService;
    }

    public void setWoPlanWoLetDetailService(
            WoPlanWoLetDetailService woPlanWoLetDetailService)
    {
        this.woPlanWoLetDetailService = woPlanWoLetDetailService;
    }

    public List findWoLetList(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User loginUser)
    {      
        return woPlanWoLetListDAO.findWoLetList(woPlanCommonDTO, woPlanWoLetListDTO, loginUser);
    }

	public WoPlanWoLetListDAO getWoPlanWoLetListDAO() {
		return woPlanWoLetListDAO;
	}

	public void setWoPlanWoLetListDAO(WoPlanWoLetListDAO woPlanWoLetListDAO) {
		this.woPlanWoLetListDAO = woPlanWoLetListDAO;
	}
	
	public int deleteWoLetList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + woPlanWoLetListDAO.deleteWoLetList(id, compNo);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User user)
			throws Exception {
		return woPlanWoLetListDAO.findTotalCount(woPlanCommonDTO, woPlanWoLetListDTO, user);
	}

    @Override
    public int inputWoLetList(WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, WoPlanCommonDTO woPlanCommonDTO,User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = woPlanWoLetDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            woPlanWoLetDetailDTO.setWoLetCtgId(multiKey[i]);
            
            String cnt = woPlanWoLetDetailService.validWoLet(woPlanCommonDTO, woPlanWoLetDetailDTO, user);
            if("0".equals(cnt))
            {
                woPlanWoLetDetailDTO.setWoWoLetListId(woPlanWoLetListDAO.getNextSequence("SQAWOWOLETLIST_ID"));
                result = result + woPlanWoLetDetailService.insertDetail(woPlanCommonDTO, woPlanWoLetDetailDTO, user);
            }
        }
        
        return result;
    }
}

