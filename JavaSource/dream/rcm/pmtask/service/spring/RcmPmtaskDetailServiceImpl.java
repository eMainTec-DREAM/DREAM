package dream.rcm.pmtask.service.spring;

import dream.rcm.pmtask.dao.RcmPmtaskDetailDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskDetailDTO;
import dream.rcm.pmtask.service.RcmPmtaskDetailService;

/**
 * »ó¼¼ serviceimpl 
 * @author  kim21017
 * @version $Id: RcmPmtaskDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmPmtaskDetailServiceTarget"
 * @spring.txbn id="rcmPmtaskDetailService"
 * @spring.property name="rcmPmtaskDetailDAO" ref="rcmPmtaskDetailDAO"
 */
public class RcmPmtaskDetailServiceImpl implements RcmPmtaskDetailService
{
    private RcmPmtaskDetailDAO rcmPmtaskDetailDAO = null;
    
    public RcmPmtaskDetailDAO getRcmPmtaskDetailDAO() {
		return rcmPmtaskDetailDAO;
	}

	public void setRcmPmtaskDetailDAO(RcmPmtaskDetailDAO rcmPmtaskDetailDAO) {
		this.rcmPmtaskDetailDAO = rcmPmtaskDetailDAO;
	}

	public RcmPmtaskDetailDTO findDetail(RcmPmtaskCommonDTO rcmPmtaskCommonDTO)throws Exception
    {
        return rcmPmtaskDetailDAO.findDetail(rcmPmtaskCommonDTO);
    }
	
	public int updateDetail(RcmPmtaskDetailDTO rcmPmtaskDetailDTO) throws Exception
    {        
        return rcmPmtaskDetailDAO.updateDetail(rcmPmtaskDetailDTO);
    }

	public int insertDetail(RcmPmtaskDetailDTO rcmPmtaskDetailDTO) throws Exception
    {        
        return rcmPmtaskDetailDAO.insertDetail(rcmPmtaskDetailDTO);
    }
}
