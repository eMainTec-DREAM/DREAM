package dream.rcm.crity.service.spring;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityDetailDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityDetailDTO;
import dream.rcm.crity.service.RcmCrityDetailService;

/**
 * Criticality Matrix Page - Detail Service implements
 * @author kim21017
 * @version $Id: RcmCrityDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityDetailServiceTarget"
 * @spring.txbn id="rcmCrityDetailService"
 * @spring.property name="rcmCrityDetailDAO" ref="rcmCrityDetailDAO"
 */
public class RcmCrityDetailServiceImpl implements RcmCrityDetailService
{
    private RcmCrityDetailDAO rcmCrityDetailDAO = null;
    
    public RcmCrityDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, User user) throws Exception
    {
    	return rcmCrityDetailDAO.findDetail(rcmCrityCommonDTO, user);
    }
    
    public int insertDetail(RcmCrityDetailDTO rcmCrityDetailDTO, User user) throws Exception
    {
    	return rcmCrityDetailDAO.insertDetail(rcmCrityDetailDTO, user);
    }
    
    public int updateDetail(RcmCrityDetailDTO rcmCrityDetailDTO, User user) throws Exception
    {
    	 return rcmCrityDetailDAO.updateDetail(rcmCrityDetailDTO, user);
    }

	public RcmCrityDetailDAO getRcmCrityDetailDAO() {
		return rcmCrityDetailDAO;
	}

	public void setRcmCrityDetailDAO(RcmCrityDetailDAO rcmCrityDetailDAO) {
		this.rcmCrityDetailDAO = rcmCrityDetailDAO;
	}
}
