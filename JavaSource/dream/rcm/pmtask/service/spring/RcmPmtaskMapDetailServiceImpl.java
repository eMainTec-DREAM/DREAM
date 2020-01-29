package dream.rcm.pmtask.service.spring;

import dream.rcm.pmtask.dao.RcmPmtaskMapDetailDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;
import dream.rcm.pmtask.service.RcmPmtaskMapDetailService;

/**
 * ¼ö½ÅÀÚ
 * @author kim2107
 * @version $Id: RcmPmtaskMapDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmPmtaskMapDetailServiceTarget"
 * @spring.txbn id="rcmPmtaskMapDetailService"
 * @spring.property name="rcmPmtaskMapDetailDAO" ref="rcmPmtaskMapDetailDAO"
 */
public class RcmPmtaskMapDetailServiceImpl implements RcmPmtaskMapDetailService
{
    private RcmPmtaskMapDetailDAO rcmPmtaskMapDetailDAO = null;
    
    public RcmPmtaskMapDetailDAO getRcmPmtaskMapDetailDAO() {
		return rcmPmtaskMapDetailDAO;
	}

	public void setRcmPmtaskMapDetailDAO(RcmPmtaskMapDetailDAO rcmPmtaskMapDetailDAO) {
		this.rcmPmtaskMapDetailDAO = rcmPmtaskMapDetailDAO;
	}

	public RcmPmtaskMapDetailDTO findDetail(RcmPmtaskMapListDTO rcmPmtaskMapListDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)throws Exception
    {
        return rcmPmtaskMapDetailDAO.findDetail(rcmPmtaskMapListDTO, rcmPmtaskCommonDTO);
    }
    
	public int updateDetail(RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO) throws Exception
    {        
        return rcmPmtaskMapDetailDAO.updateDetail(rcmPmtaskMapDetailDTO, rcmPmtaskCommonDTO);
    }
	public int insertDetail(RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO) throws Exception
    {        
        return rcmPmtaskMapDetailDAO.insertDetail( rcmPmtaskMapDetailDTO, rcmPmtaskCommonDTO);
    }
}
