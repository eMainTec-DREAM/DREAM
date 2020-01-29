package dream.rcm.taskmap.service.spring;

import dream.rcm.taskmap.dao.RcmTaskMapDetailDAO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapDetailDTO;
import dream.rcm.taskmap.service.RcmTaskMapDetailService;

/**
 * 질의 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: RcmTaskMapDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmTaskMapDetailServiceTarget"
 * @spring.txbn id="rcmTaskMapDetailService"
 * @spring.property name="rcmTaskMapDetailDAO" ref="rcmTaskMapDetailDAO"
 */
public class RcmTaskMapDetailServiceImpl implements RcmTaskMapDetailService
{
    private RcmTaskMapDetailDAO rcmTaskMapDetailDAO = null;
    
    public RcmTaskMapDetailDAO getRcmTaskMapDetailDAO() {
		return rcmTaskMapDetailDAO;
	}

	public void setRcmTaskMapDetailDAO(RcmTaskMapDetailDAO rcmTaskMapDetailDAO) {
		this.rcmTaskMapDetailDAO = rcmTaskMapDetailDAO;
	}

	public RcmTaskMapDetailDTO findDetail(RcmTaskMapCommonDTO rcmTaskMapCommonDTO)throws Exception
    {
        return rcmTaskMapDetailDAO.findDetail(rcmTaskMapCommonDTO);
    }
	
	public int updateDetail(RcmTaskMapDetailDTO rcmTaskMapDetailDTO) throws Exception
    {        
        return rcmTaskMapDetailDAO.updateDetail(rcmTaskMapDetailDTO);
    }

	public int insertDetail(RcmTaskMapDetailDTO rcmTaskMapDetailDTO) throws Exception
    {   		
        return rcmTaskMapDetailDAO.insertDetail(rcmTaskMapDetailDTO);
    }
}
