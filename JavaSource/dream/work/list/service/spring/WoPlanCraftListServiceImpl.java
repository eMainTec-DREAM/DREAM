package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.WoPlanCraftListDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.dto.WoPlanCraftListDTO;
import dream.work.list.service.WoPlanCraftDetailService;
import dream.work.list.service.WoPlanCraftListService;

/**
 * 작업계획목록 - 작업자 목록
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="woPlanCraftListServiceTarget"
 * @spring.txbn id="woPlanCraftListService"
 * @spring.property name="woPlanCraftListDAO" ref="woPlanCraftListDAO"
 * @spring.property name="woPlanCraftDetailService" ref="woPlanCraftDetailService"
 */
public class WoPlanCraftListServiceImpl implements WoPlanCraftListService
{
    private WoPlanCraftListDAO woPlanCraftListDAO = null;
    private WoPlanCraftDetailService woPlanCraftDetailService = null;


	public WoPlanCraftDetailService getWoPlanCraftDetailService()
    {
        return woPlanCraftDetailService;
    }

    public void setWoPlanCraftDetailService(
            WoPlanCraftDetailService woPlanCraftDetailService)
    {
        this.woPlanCraftDetailService = woPlanCraftDetailService;
    }

    public List findCraftList(WoPlanCommonDTO woPlanCommonDTO, WoPlanCraftListDTO woPlanCraftListDTO, User loginUser)
    {      
        return woPlanCraftListDAO.findCraftList(woPlanCommonDTO, woPlanCraftListDTO, loginUser);
    }

	public WoPlanCraftListDAO getWoPlanCraftListDAO() {
		return woPlanCraftListDAO;
	}

	public void setWoPlanCraftListDAO(WoPlanCraftListDAO woPlanCraftListDAO) {
		this.woPlanCraftListDAO = woPlanCraftListDAO;
	}
	
	public int deleteCraftList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + woPlanCraftListDAO.deleteCraftList(id, compNo);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanCraftListDTO woPlanCraftListDTO, User user)
			throws Exception {
		return woPlanCraftListDAO.findTotalCount(woPlanCommonDTO, woPlanCraftListDTO, user);
	}

    @Override
    public int inputCraftList(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO,User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = woPlanCraftDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            woPlanCraftDetailDTO.setEmpId(multiKey[i]);
            String cnt = woPlanCraftDetailService.validEmp(woPlanCraftDetailDTO, woPlanCommonDTO, user);
            if("0".equals(cnt))
            {
                woPlanCraftDetailDTO.setWoCraftId(woPlanCraftListDAO.getNextSequence("SQAWOCRAFT_ID"));
                result = result + woPlanCraftDetailService.insertDetail(woPlanCraftDetailDTO, woPlanCommonDTO);
            }
        }
        
        return result;
    }
}

