package dream.rcm.system.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.system.service.RcmSysEmpListService;
import dream.rcm.system.dao.RcmSysEmpListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEmpListDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;

/**
 * 자분석 목록
 * @author kim21017
 * @version $Id: RcmSysEmpListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysEmpListServiceTarget"
 * @spring.txbn id="rcmSysEmpListService"
 * @spring.property name="rcmSysEmpListDAO" ref="rcmSysEmpListDAO"
 */
public class RcmSysEmpListServiceImpl implements RcmSysEmpListService
{
    private RcmSysEmpListDAO rcmSysEmpListDAO = null;


	public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, User loginUser)
    {      
        return rcmSysEmpListDAO.findList(rcmSysCommonDTO, rcmSysEmpListDTO, loginUser);
    }

	public RcmSysEmpListDAO getRcmSysEmpListDAO() {
		return rcmSysEmpListDAO;
	}

	public void setRcmSysEmpListDAO(RcmSysEmpListDAO rcmSysEmpListDAO) {
		this.rcmSysEmpListDAO = rcmSysEmpListDAO;
	}
	
	public int deleteList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + rcmSysEmpListDAO.deleteList(id, compNo);
            }
        
        return result;
    }
	
	public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO) throws Exception
    {   
		int result = 0;
		
		String[] multiKey = rcmSysEmpListDTO.getMultiKey().split("\\+");
		
		for(int i=0; multiKey.length > i; i++)
		{
			rcmSysEmpListDTO.setRcmEmpId(rcmSysEmpListDAO.getNextSequence("SQARCMFENV_ID"));
			result = result + rcmSysEmpListDAO.inputList(rcmSysCommonDTO, rcmSysEmpListDTO, multiKey[i]);
		}
		
		return result;
    }

	@Override
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, User user) {
		return rcmSysEmpListDAO.findTotalCount(rcmSysCommonDTO, rcmSysEmpListDTO, user);
	}
}

