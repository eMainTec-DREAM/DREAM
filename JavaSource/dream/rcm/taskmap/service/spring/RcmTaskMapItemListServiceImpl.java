package dream.rcm.taskmap.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.taskmap.dao.RcmTaskMapItemListDAO;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.service.RcmTaskMapItemListService;

/**
 * 답변 목록 serviceimpl
 * @author kim21017
 * @version $Id: RcmTaskMapItemListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmTaskMapItemListServiceTarget"
 * @spring.txbn id="rcmTaskMapItemListService"
 * @spring.property name="rcmTaskMapItemListDAO" ref="rcmTaskMapItemListDAO"
 */
public class RcmTaskMapItemListServiceImpl implements RcmTaskMapItemListService
{
    private RcmTaskMapItemListDAO rcmTaskMapItemListDAO = null;

    public RcmTaskMapItemListDAO getRcmTaskMapItemListDAO() {
		return rcmTaskMapItemListDAO;
	}
	public void setRcmTaskMapItemListDAO(RcmTaskMapItemListDAO rcmTaskMapItemListDAO) {
		this.rcmTaskMapItemListDAO = rcmTaskMapItemListDAO;
	}
	
	public List findItemList(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, RcmTaskMapItemListDTO rcmTaskMapItemListDTO, User user)
    {      
        return rcmTaskMapItemListDAO.findItemList(rcmTaskMapCommonDTO, rcmTaskMapItemListDTO,  user);
    
    }
	
	public int deleteItemList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + rcmTaskMapItemListDAO.deleteItemList(deleteRows[i]);
        }
        
        return result;
    }
	@Override
	public String findTotalCount(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, RcmTaskMapItemListDTO rcmTaskMapItemListDTO, User user) {
        return rcmTaskMapItemListDAO.findTotalCount(rcmTaskMapCommonDTO, rcmTaskMapItemListDTO, user);
	}
}

