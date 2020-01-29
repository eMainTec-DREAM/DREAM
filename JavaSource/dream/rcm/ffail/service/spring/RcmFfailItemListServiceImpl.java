package dream.rcm.ffail.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.ffail.dao.RcmFfailItemListDAO;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.service.RcmFfailItemListService;

/**
 * 답변 목록 serviceimpl
 * @author kim21017
 * @version $Id: RcmFfailItemListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmFfailItemListServiceTarget"
 * @spring.txbn id="rcmFfailItemListService"
 * @spring.property name="rcmFfailItemListDAO" ref="rcmFfailItemListDAO"
 */
public class RcmFfailItemListServiceImpl implements RcmFfailItemListService
{
    private RcmFfailItemListDAO rcmFfailItemListDAO = null;

    public RcmFfailItemListDAO getRcmFfailItemListDAO() {
		return rcmFfailItemListDAO;
	}
	public void setRcmFfailItemListDAO(RcmFfailItemListDAO rcmFfailItemListDAO) {
		this.rcmFfailItemListDAO = rcmFfailItemListDAO;
	}
	
	public List findItemList(RcmFfailCommonDTO rcmFfailCommonDTO, RcmFfailItemListDTO rcmFfailItemListDTO)
    {      
        return rcmFfailItemListDAO.findItemList(rcmFfailCommonDTO, rcmFfailItemListDTO);
    }
	
	public int deleteItemList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + rcmFfailItemListDAO.deleteItemList(deleteRows[i]);
        }
        
        return result;
    }
	@Override
	public String findTotalCount(RcmFfailCommonDTO rcmFfailCommonDTO, RcmFfailItemListDTO rcmFfailItemListDTO,
			User user) {
		return rcmFfailItemListDAO.findTotalCount(rcmFfailCommonDTO, rcmFfailItemListDTO, user);
	}
}

