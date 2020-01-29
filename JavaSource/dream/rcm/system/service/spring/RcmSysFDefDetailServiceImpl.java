package dream.rcm.system.service.spring;

import dream.rcm.system.dao.RcmSysFDefDetailDAO;
import dream.rcm.system.dto.RcmSysFDefDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.service.RcmSysFDefDetailService;

/**
 * 기능정의
 * @author kim21017
 * @version $Id: RcmSysFDefDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysFDefDetailServiceTarget"
 * @spring.txbn id="rcmSysFDefDetailService"
 * @spring.property name="rcmSysFDefDetailDAO" ref="rcmSysFDefDetailDAO"
 */
public class RcmSysFDefDetailServiceImpl implements RcmSysFDefDetailService
{
    private RcmSysFDefDetailDAO rcmSysFDefDetailDAO = null;
    
    public RcmSysFDefDetailDAO getRcmSysFDefDetailDAO() {
		return rcmSysFDefDetailDAO;
	}

	public void setRcmSysFDefDetailDAO(RcmSysFDefDetailDAO rcmSysFDefDetailDAO) {
		this.rcmSysFDefDetailDAO = rcmSysFDefDetailDAO;
	}

	public RcmSysFDefDetailDTO findDetail(String rcmListId, String rcmFuncId, String compNo)throws Exception
    {
        return rcmSysFDefDetailDAO.findDetail(rcmListId, rcmFuncId, compNo);
    }
    
	public int updateDetail(RcmSysFDefDetailDTO rcmSysFDefDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return rcmSysFDefDetailDAO.updateDetail(rcmSysFDefDetailDTO, maPmMstrCommonDTO);
    }
	public int insertDetail(RcmSysFDefDetailDTO rcmSysFDefDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return rcmSysFDefDetailDAO.insertDetail( rcmSysFDefDetailDTO, maPmMstrCommonDTO);
    }
}
