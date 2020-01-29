package dream.rcm.funceq.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.funceq.dao.RcmFuncEqListDAO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.service.RcmFuncEqListService;

/**
 * 질의 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: RcmFuncEqListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmFuncEqListServiceTarget"
 * @spring.txbn id="rcmFuncEqListService"
 * @spring.property name="rcmFuncEqListDAO" ref="rcmFuncEqListDAO"
 */
public class RcmFuncEqListServiceImpl implements RcmFuncEqListService
{
    private RcmFuncEqListDAO rcmFuncEqListDAO = null;

    public RcmFuncEqListDAO getRcmFuncEqListDAO() {
		return rcmFuncEqListDAO;
	}

	public void setRcmFuncEqListDAO(RcmFuncEqListDAO rcmFuncEqListDAO) {
		this.rcmFuncEqListDAO = rcmFuncEqListDAO;
	}

	public List findQnaList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO)
    {      
        return rcmFuncEqListDAO.findQnaList(rcmFuncEqCommonDTO);
    }
	
	public int deleteQna(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + rcmFuncEqListDAO.deleteQna(id,user);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, User user) {
		return rcmFuncEqListDAO.findTotalCount(rcmFuncEqCommonDTO, user);
	}
}

