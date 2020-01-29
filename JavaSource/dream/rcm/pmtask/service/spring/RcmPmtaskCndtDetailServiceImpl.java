package dream.rcm.pmtask.service.spring;

import dream.rcm.pmtask.dao.RcmPmtaskCndtDetailDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;
import dream.rcm.pmtask.service.RcmPmtaskCndtDetailService;

/**
 * ¼ö½ÅÀÚ
 * @author kim2107
 * @version $Id: RcmPmtaskCndtDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmPmtaskCndtDetailServiceTarget"
 * @spring.txbn id="rcmPmtaskCndtDetailService"
 * @spring.property name="rcmPmtaskCndtDetailDAO" ref="rcmPmtaskCndtDetailDAO"
 */
public class RcmPmtaskCndtDetailServiceImpl implements RcmPmtaskCndtDetailService
{
    private RcmPmtaskCndtDetailDAO rcmPmtaskCndtDetailDAO = null;
    
    public RcmPmtaskCndtDetailDAO getRcmPmtaskCndtDetailDAO() {
		return rcmPmtaskCndtDetailDAO;
	}

	public void setRcmPmtaskCndtDetailDAO(RcmPmtaskCndtDetailDAO rcmPmtaskCndtDetailDAO) {
		this.rcmPmtaskCndtDetailDAO = rcmPmtaskCndtDetailDAO;
	}

	public RcmPmtaskCndtDetailDTO findDetail(RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)throws Exception
    {
        return rcmPmtaskCndtDetailDAO.findDetail(rcmPmtaskCndtListDTO, rcmPmtaskCommonDTO);
    }
    
	public int updateDetail(RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO) throws Exception
    {        
        return rcmPmtaskCndtDetailDAO.updateDetail(rcmPmtaskCndtDetailDTO, rcmPmtaskCommonDTO);
    }
	public int insertDetail(RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO) throws Exception
    {        
        return rcmPmtaskCndtDetailDAO.insertDetail( rcmPmtaskCndtDetailDTO, rcmPmtaskCommonDTO);
    }
}
