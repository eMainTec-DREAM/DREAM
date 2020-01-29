package dream.rcm.system.service.spring;

import java.util.List;

import common.bean.User;

import dream.rcm.system.dao.RcmSysFEnvListDAO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.service.RcmSysFEnvListService;

/**
 * 운전환경 목록 serviceimpl
 * @author kim21017
 * @version $Id: RcmSysFEnvListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysFEnvListServiceTarget"
 * @spring.txbn id="rcmSysFEnvListService"
 * @spring.property name="rcmSysFEnvListDAO" ref="rcmSysFEnvListDAO"
 */
public class RcmSysFEnvListServiceImpl implements RcmSysFEnvListService
{
    private RcmSysFEnvListDAO rcmSysFEnvListDAO = null;

    public RcmSysFEnvListDAO getRcmSysFEnvListDAO() {
		return rcmSysFEnvListDAO;
	}
	public void setRcmSysFEnvListDAO(RcmSysFEnvListDAO rcmSysFEnvListDAO) {
		this.rcmSysFEnvListDAO = rcmSysFEnvListDAO;
	}
	
	public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFEnvListDTO rcmSysFEnvListDTO, User user)
    {      
        return rcmSysFEnvListDAO.findList(rcmSysCommonDTO, rcmSysFEnvListDTO, user);
    }
	
	public int deleteList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + rcmSysFEnvListDAO.deleteList(deleteRows[i]);
        }
        
        return result;
    }
	
	public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFEnvListDTO rcmSysFEnvListDTO)
    {   
		int result = 0;
		
		String[] multiKey = rcmSysFEnvListDTO.getMultiKey().split("\\+");
		
		for(int i=0; multiKey.length > i; i++)
		{
			rcmSysFEnvListDTO.setRcmFEnvId(rcmSysFEnvListDAO.getNextSequence("SQARCMFENV_ID"));
			result = result + rcmSysFEnvListDAO.inputList(rcmSysCommonDTO, rcmSysFEnvListDTO, multiKey[i]);
		}
		
		return result;
    }
}

