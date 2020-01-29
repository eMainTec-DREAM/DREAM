package dream.rcm.system.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.system.dao.RcmSysEqAsmbListDAO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.service.RcmSysEqAsmbListService;

/**
 * 설비부위 목록 serviceimpl
 * @author kim21017
 * @version $Id: RcmSysEqAsmbListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysEqAsmbListServiceTarget"
 * @spring.txbn id="rcmSysEqAsmbListService"
 * @spring.property name="rcmSysEqAsmbListDAO" ref="rcmSysEqAsmbListDAO"
 */
public class RcmSysEqAsmbListServiceImpl implements RcmSysEqAsmbListService
{
    private RcmSysEqAsmbListDAO rcmSysEqAsmbListDAO = null;

    public RcmSysEqAsmbListDAO getRcmSysEqAsmbListDAO() {
		return rcmSysEqAsmbListDAO;
	}
	public void setRcmSysEqAsmbListDAO(RcmSysEqAsmbListDAO rcmSysEqAsmbListDAO) {
		this.rcmSysEqAsmbListDAO = rcmSysEqAsmbListDAO;
	}
	
	public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO, User user)
    {      
        return rcmSysEqAsmbListDAO.findList(rcmSysCommonDTO, rcmSysEqAsmbListDTO, user);
    }
	
	public int deleteList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + rcmSysEqAsmbListDAO.deleteList(deleteRows[i]);
        }
        
        return result;
    }
	
	public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO) throws Exception{
        int result = 0;
        
        String[] multiKey = rcmSysEqAsmbListDTO.getMultiKey().split("\\+");
		
		for(int i=0; i < multiKey.length; i++)
		{
			rcmSysEqAsmbListDTO.setRcmEqAsmbId(rcmSysEqAsmbListDAO.getNextSequence("SQARCMEQASMB_ID"));
			result += rcmSysEqAsmbListDAO.inputList(rcmSysCommonDTO, rcmSysEqAsmbListDTO, multiKey[i]);
		}
		
        return result;
    }
}

