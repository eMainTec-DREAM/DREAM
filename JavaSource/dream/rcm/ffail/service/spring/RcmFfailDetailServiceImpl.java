package dream.rcm.ffail.service.spring;

import dream.rcm.ffail.dao.RcmFfailDetailDAO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.dto.RcmFfailDetailDTO;
import dream.rcm.ffail.service.RcmFfailDetailService;

/**
 * 질의 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: RcmFfailDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFfailDetailServiceTarget"
 * @spring.txbn id="rcmFfailDetailService"
 * @spring.property name="rcmFfailDetailDAO" ref="rcmFfailDetailDAO"
 */
public class RcmFfailDetailServiceImpl implements RcmFfailDetailService
{
    private RcmFfailDetailDAO rcmFfailDetailDAO = null;
    
    public RcmFfailDetailDAO getRcmFfailDetailDAO() {
		return rcmFfailDetailDAO;
	}

	public void setRcmFfailDetailDAO(RcmFfailDetailDAO rcmFfailDetailDAO) {
		this.rcmFfailDetailDAO = rcmFfailDetailDAO;
	}

	public RcmFfailDetailDTO findDetail(RcmFfailCommonDTO rcmFfailCommonDTO)throws Exception
    {
        return rcmFfailDetailDAO.findDetail(rcmFfailCommonDTO);
    }
	
	public int updateDetail(RcmFfailDetailDTO rcmFfailDetailDTO) throws Exception
    {        
        return rcmFfailDetailDAO.updateDetail(rcmFfailDetailDTO);
    }
	public int confirmDetail(RcmFfailDetailDTO rcmFfailDetailDTO) throws Exception
    {        
        return rcmFfailDetailDAO.confirmDetail(rcmFfailDetailDTO);
    }
	public int insertDetail(RcmFfailDetailDTO rcmFfailDetailDTO) throws Exception
    {        
        return rcmFfailDetailDAO.insertDetail(rcmFfailDetailDTO);
    }
}
