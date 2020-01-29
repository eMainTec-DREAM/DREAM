package dream.rcm.crity.service.spring;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityValDetailDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityValDetailDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;
import dream.rcm.crity.service.RcmCrityValDetailService;

/**
 * Criticality Matrix Val Page - Detail Service implements
 * @author kim21017
 * @version $Id: RcmCrityValDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityValDetailServiceTarget"
 * @spring.txbn id="rcmCrityValDetailService"
 * @spring.property name="rcmCrityValDetailDAO" ref="rcmCrityValDetailDAO"
 */
public class RcmCrityValDetailServiceImpl implements RcmCrityValDetailService
{
    private RcmCrityValDetailDAO rcmCrityValDetailDAO = null;
    
    public RcmCrityValDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityValListDTO rcmCrityValListDTO, User user) throws Exception
    {
    	return rcmCrityValDetailDAO.findDetail(rcmCrityCommonDTO,rcmCrityValListDTO, user);
    }
    
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityValDetailDTO rcmCrityValDetailDTO, User user) throws Exception
    {
     	return rcmCrityValDetailDAO.updateDetail(rcmCrityCommonDTO, rcmCrityValDetailDTO, user);
    }

	public RcmCrityValDetailDAO getRcmCrityValDetailDAO() {
		return rcmCrityValDetailDAO;
	}

	public void setRcmCrityValDetailDAO(RcmCrityValDetailDAO rcmCrityValDetailDAO) {
		this.rcmCrityValDetailDAO = rcmCrityValDetailDAO;
	}
    

}
