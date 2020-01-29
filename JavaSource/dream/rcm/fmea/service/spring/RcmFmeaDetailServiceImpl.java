package dream.rcm.fmea.service.spring;

import dream.rcm.fmea.dao.RcmFmeaDetailDAO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaDetailDTO;
import dream.rcm.fmea.service.RcmFmeaDetailService;

/**
 * »ó¼¼ serviceimpl 
 * @author  kim21017
 * @version $Id: RcmFmeaDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFmeaDetailServiceTarget"
 * @spring.txbn id="rcmFmeaDetailService"
 * @spring.property name="rcmFmeaDetailDAO" ref="rcmFmeaDetailDAO"
 */
public class RcmFmeaDetailServiceImpl implements RcmFmeaDetailService
{
    private RcmFmeaDetailDAO rcmFmeaDetailDAO = null;
    
    public RcmFmeaDetailDAO getRcmFmeaDetailDAO() {
		return rcmFmeaDetailDAO;
	}

	public void setRcmFmeaDetailDAO(RcmFmeaDetailDAO rcmFmeaDetailDAO) {
		this.rcmFmeaDetailDAO = rcmFmeaDetailDAO;
	}

	public RcmFmeaDetailDTO findDetail(RcmFmeaCommonDTO rcmFmeaCommonDTO)throws Exception
    {
        return rcmFmeaDetailDAO.findDetail(rcmFmeaCommonDTO);
    }
	
	public int updateDetail(RcmFmeaDetailDTO rcmFmeaDetailDTO) throws Exception
    {        
        return rcmFmeaDetailDAO.updateDetail(rcmFmeaDetailDTO);
    }

	public int insertDetail(RcmFmeaDetailDTO rcmFmeaDetailDTO) throws Exception
    {        
        return rcmFmeaDetailDAO.insertDetail(rcmFmeaDetailDTO);
    }
}
