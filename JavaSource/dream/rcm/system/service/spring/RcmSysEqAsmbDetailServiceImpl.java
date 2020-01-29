package dream.rcm.system.service.spring;

import common.bean.User;

import dream.rcm.system.dao.RcmSysEqAsmbDetailDAO;
import dream.rcm.system.dto.RcmSysEqAsmbDetailDTO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.service.RcmSysEqAsmbDetailService;

/**
 * 설비부위 - 수신자
 * @author kim2107
 * @version $Id: RcmSysEqAsmbDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysEqAsmbDetailServiceTarget"
 * @spring.txbn id="rcmSysEqAsmbDetailService"
 * @spring.property name="rcmSysEqAsmbDetailDAO" ref="rcmSysEqAsmbDetailDAO"
 */
public class RcmSysEqAsmbDetailServiceImpl implements RcmSysEqAsmbDetailService
{
    private RcmSysEqAsmbDetailDAO rcmSysEqAsmbDetailDAO = null;
    
    public RcmSysEqAsmbDetailDAO getRcmSysEqAsmbDetailDAO() {
		return rcmSysEqAsmbDetailDAO;
	}

	public void setRcmSysEqAsmbDetailDAO(RcmSysEqAsmbDetailDAO rcmSysEqAsmbDetailDAO) {
		this.rcmSysEqAsmbDetailDAO = rcmSysEqAsmbDetailDAO;
	}

	public RcmSysEqAsmbDetailDTO findDetail(RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO, RcmSysCommonDTO rcmSysCommonDTO, User user)throws Exception
    {
        return rcmSysEqAsmbDetailDAO.findDetail(rcmSysEqAsmbListDTO, rcmSysCommonDTO, user);
    }
    
	public int updateDetail(RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO, RcmSysCommonDTO rcmSysCommonDTO) throws Exception
    {        
        return rcmSysEqAsmbDetailDAO.updateDetail(rcmSysEqAsmbDetailDTO, rcmSysCommonDTO);
    }
	public int insertDetail(RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO, RcmSysCommonDTO rcmSysCommonDTO) throws Exception
    {        
        return rcmSysEqAsmbDetailDAO.insertDetail( rcmSysEqAsmbDetailDTO, rcmSysCommonDTO);
    }
}
