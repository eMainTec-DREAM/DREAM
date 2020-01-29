package dream.rcm.taskmap.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.taskmap.dao.RcmTaskMapListDAO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.service.RcmTaskMapListService;

/**
 * 질의 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: RcmTaskMapListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmTaskMapListServiceTarget"
 * @spring.txbn id="rcmTaskMapListService"
 * @spring.property name="rcmTaskMapListDAO" ref="rcmTaskMapListDAO"
 */
public class RcmTaskMapListServiceImpl implements RcmTaskMapListService
{
    private RcmTaskMapListDAO rcmTaskMapListDAO = null;

    public RcmTaskMapListDAO getRcmTaskMapListDAO() {
		return rcmTaskMapListDAO;
	}

	public void setRcmTaskMapListDAO(RcmTaskMapListDAO rcmTaskMapListDAO) {
		this.rcmTaskMapListDAO = rcmTaskMapListDAO;
	}

	public List findQnaList(RcmTaskMapCommonDTO rcmTaskMapCommonDTO)
    {      
        return rcmTaskMapListDAO.findQnaList(rcmTaskMapCommonDTO);
    }
	
	public int deleteQna(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + rcmTaskMapListDAO.deleteQna(id,user);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, User user) {
		 return rcmTaskMapListDAO.findTotalCount(rcmTaskMapCommonDTO, user);
	}
}

