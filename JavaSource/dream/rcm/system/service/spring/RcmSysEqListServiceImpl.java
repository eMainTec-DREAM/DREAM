package dream.rcm.system.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.system.service.RcmSysEqListService;
import dream.rcm.system.dao.RcmSysEqListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqListDTO;

/**
 * 설비설정 목록
 * @author kim21017
 * @version $Id: RcmSysEqListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysEqListServiceTarget"
 * @spring.txbn id="rcmSysEqListService"
 * @spring.property name="rcmSysEqListDAO" ref="rcmSysEqListDAO"
 */
public class RcmSysEqListServiceImpl implements RcmSysEqListService
{
    private RcmSysEqListDAO rcmSysEqListDAO = null;

	public RcmSysEqListDAO getRcmSysEqListDAO() {
		return rcmSysEqListDAO;
	}

	public void setRcmSysEqListDAO(RcmSysEqListDAO rcmSysEqListDAO) {
		this.rcmSysEqListDAO = rcmSysEqListDAO;
	}
	
	public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, User loginUser)
    {      
        return rcmSysEqListDAO.findList(rcmSysCommonDTO, rcmSysEqListDTO, loginUser);
    }
	
	public int deleteList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	result += rcmSysEqListDAO.deleteList(id, compNo);
            	rcmSysEqListDAO.deleteAsmbList(id, compNo);
            }
        
        return result;
    }
	
	public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO)
    {   
		int result = 0;
		
		String[] multiKey = rcmSysEqListDTO.getMultiKey().split("\\+");
		
		for(int i=0; i < multiKey.length; i++)
		{
			rcmSysEqListDTO.setRcmEqId(rcmSysEqListDAO.getNextSequence("SQARCMEQ_ID"));
			result += rcmSysEqListDAO.inputList(rcmSysCommonDTO, rcmSysEqListDTO, multiKey[i]);
		}
		
		return result;
    }

	@Override
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, User user) {
		return rcmSysEqListDAO.findTotalCount(rcmSysCommonDTO, rcmSysEqListDTO, user);
	}
}

