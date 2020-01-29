package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.WoPlanPartListDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanPartListDTO;
import dream.work.list.service.WoPlanPartListService;

/**
 * 작업계획목록 투입부품 목록
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="woPlanPartListServiceTarget"
 * @spring.txbn id="woPlanPartListService"
 * @spring.property name="woPlanPartListDAO" ref="woPlanPartListDAO"
 */
public class WoPlanPartListServiceImpl implements WoPlanPartListService
{
    private WoPlanPartListDAO woPlanPartListDAO = null;


	public List findPartList(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, User loginUser)
    {      
        return woPlanPartListDAO.findPartList(woPlanCommonDTO, woPlanPartListDTO, loginUser);
    }

	public WoPlanPartListDAO getWoPlanPartListDAO() {
		return woPlanPartListDAO;
	}

	public void setWoPlanPartListDAO(WoPlanPartListDAO woPlanPartListDAO) {
		this.woPlanPartListDAO = woPlanPartListDAO;
	}
	
	public int deletePartList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String woPartId : deleteRows)
            {
            	//woPlanPartListDAO.updateEmgPart(woPartId, compNo);
            	
                result = result + woPlanPartListDAO.deletePartList(woPartId, compNo);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, User user)
			throws Exception {
		return woPlanPartListDAO.findTotalCount(woPlanCommonDTO, woPlanPartListDTO, user);
	}

    @Override
    public int inputPartList(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO) throws Exception
    {
        int result = 0;
        
        String[] multiKey = woPlanPartListDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            String cnt = woPlanPartListDAO.validWoPart(woPlanCommonDTO, multiKey[i]);
            if("0".equals(cnt))
            {
                woPlanPartListDTO.setWoPartId(woPlanPartListDAO.getNextSequence("SQAWOPART_ID"));
                result = result + woPlanPartListDAO.inputPartList(woPlanCommonDTO, woPlanPartListDTO, multiKey[i]);
                woPlanPartListDAO.updateEmgPart(woPlanCommonDTO, woPlanPartListDTO, multiKey[i]);
            }
        }
        
        return result;
    }
}

