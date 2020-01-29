package dream.rcm.system.service.spring;

import common.bean.User;

import dream.rcm.system.dao.RcmSysFEnvDetailDAO;
import dream.rcm.system.dto.RcmSysFEnvDetailDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.service.RcmSysFEnvDetailService;

/**
 * 운전환경 - 수신자
 * @author kim2107
 * @version $Id: RcmSysFEnvDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysFEnvDetailServiceTarget"
 * @spring.txbn id="rcmSysFEnvDetailService"
 * @spring.property name="rcmSysFEnvDetailDAO" ref="rcmSysFEnvDetailDAO"
 */
public class RcmSysFEnvDetailServiceImpl implements RcmSysFEnvDetailService
{
    private RcmSysFEnvDetailDAO rcmSysFEnvDetailDAO = null;
    
    public RcmSysFEnvDetailDAO getRcmSysFEnvDetailDAO() {
		return rcmSysFEnvDetailDAO;
	}

	public void setRcmSysFEnvDetailDAO(RcmSysFEnvDetailDAO rcmSysFEnvDetailDAO) {
		this.rcmSysFEnvDetailDAO = rcmSysFEnvDetailDAO;
	}

	public RcmSysFEnvDetailDTO findDetail(RcmSysFEnvListDTO rcmSysFEnvListDTO, RcmSysCommonDTO rcmSysCommonDTO, User user)throws Exception
    {
        return rcmSysFEnvDetailDAO.findDetail(rcmSysFEnvListDTO, rcmSysCommonDTO, user);
    }
    
	public int updateDetail(RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO, RcmSysCommonDTO rcmSysCommonDTO) throws Exception
    {        
        return rcmSysFEnvDetailDAO.updateDetail(rcmSysFEnvDetailDTO, rcmSysCommonDTO);
    }
	public int insertDetail(RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO, RcmSysCommonDTO rcmSysCommonDTO) throws Exception
    {        
        return rcmSysFEnvDetailDAO.insertDetail( rcmSysFEnvDetailDTO, rcmSysCommonDTO);
    }
}
