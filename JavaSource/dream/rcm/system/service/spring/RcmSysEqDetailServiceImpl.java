package dream.rcm.system.service.spring;

import common.bean.User;

import dream.rcm.system.dao.RcmSysEqDetailDAO;
import dream.rcm.system.dto.RcmSysEqDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.service.RcmSysEqDetailService;

/**
 * 설비설정
 * @author kim21017
 * @version $Id: RcmSysEqDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysEqDetailServiceTarget"
 * @spring.txbn id="rcmSysEqDetailService"
 * @spring.property name="rcmSysEqDetailDAO" ref="rcmSysEqDetailDAO"
 */
public class RcmSysEqDetailServiceImpl implements RcmSysEqDetailService
{
    private RcmSysEqDetailDAO rcmSysEqDetailDAO = null;
    
    public RcmSysEqDetailDAO getRcmSysEqDetailDAO() {
		return rcmSysEqDetailDAO;
	}

	public void setRcmSysEqDetailDAO(RcmSysEqDetailDAO rcmSysEqDetailDAO) {
		this.rcmSysEqDetailDAO = rcmSysEqDetailDAO;
	}

	public RcmSysEqDetailDTO findDetail(String rcmListId, String rcmEqId, User user)throws Exception
    {
        return rcmSysEqDetailDAO.findDetail(rcmListId, rcmEqId, user);
    }
    
	public int updateDetail(RcmSysEqDetailDTO rcmSysEqDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return rcmSysEqDetailDAO.updateDetail(rcmSysEqDetailDTO, maPmMstrCommonDTO);
    }
	public int insertDetail(RcmSysEqDetailDTO rcmSysEqDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return rcmSysEqDetailDAO.insertDetail( rcmSysEqDetailDTO, maPmMstrCommonDTO);
    }
}
