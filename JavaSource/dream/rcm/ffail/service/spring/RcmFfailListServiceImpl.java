package dream.rcm.ffail.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.ffail.dao.RcmFfailListDAO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.service.RcmFfailListService;

/**
 * 질의 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: RcmFfailListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmFfailListServiceTarget"
 * @spring.txbn id="rcmFfailListService"
 * @spring.property name="rcmFfailListDAO" ref="rcmFfailListDAO"
 */
public class RcmFfailListServiceImpl implements RcmFfailListService
{
    private RcmFfailListDAO rcmFfailListDAO = null;

    public RcmFfailListDAO getRcmFfailListDAO() {
		return rcmFfailListDAO;
	}

	public void setRcmFfailListDAO(RcmFfailListDAO rcmFfailListDAO) {
		this.rcmFfailListDAO = rcmFfailListDAO;
	}

	public List findQnaList(RcmFfailCommonDTO rcmFfailCommonDTO)
    {      
        return rcmFfailListDAO.findQnaList(rcmFfailCommonDTO);
    }
	
	public int deleteQna(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + rcmFfailListDAO.deleteQna(id,user);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(RcmFfailCommonDTO rcmFfailCommonDTO, User user) {
		return rcmFfailListDAO.findTotalCount(rcmFfailCommonDTO, user);
	}
}

