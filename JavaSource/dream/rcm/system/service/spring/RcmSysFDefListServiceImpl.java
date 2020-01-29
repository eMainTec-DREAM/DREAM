package dream.rcm.system.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.system.service.RcmSysFDefListService;
import dream.rcm.system.dao.RcmSysFDefListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysFDefListDTO;

/**
 * 기능정의 목록
 * @author kim21017
 * @version $Id: RcmSysFDefListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysFDefListServiceTarget"
 * @spring.txbn id="rcmSysFDefListService"
 * @spring.property name="rcmSysFDefListDAO" ref="rcmSysFDefListDAO"
 */
public class RcmSysFDefListServiceImpl implements RcmSysFDefListService
{
    private RcmSysFDefListDAO rcmSysFDefListDAO = null;


	public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFDefListDTO rcmSysFDefListDTO, User loginUser)
    {      
        return rcmSysFDefListDAO.findList(rcmSysCommonDTO, rcmSysFDefListDTO, loginUser);
    }

	public RcmSysFDefListDAO getRcmSysFDefListDAO() {
		return rcmSysFDefListDAO;
	}

	public void setRcmSysFDefListDAO(RcmSysFDefListDAO rcmSysFDefListDAO) {
		this.rcmSysFDefListDAO = rcmSysFDefListDAO;
	}
	
	public int deleteList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + rcmSysFDefListDAO.deleteList(id, compNo);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFDefListDTO rcmSysFDefListDTO, User loginUser) {
		return rcmSysFDefListDAO.findTotalCount(rcmSysCommonDTO, rcmSysFDefListDTO, loginUser);
	}
}

