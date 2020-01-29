package dream.rcm.crity.service.spring;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityColDetailDAO;
import dream.rcm.crity.dto.RcmCrityColDetailDTO;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.service.RcmCrityColDetailService;

/**
 * Criticality Matrix Col Page - Detail Service implements
 * @author kim21017
 * @version $Id: RcmCrityColDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityColDetailServiceTarget"
 * @spring.txbn id="rcmCrityColDetailService"
 * @spring.property name="rcmCrityColDetailDAO" ref="rcmCrityColDetailDAO"
 */
public class RcmCrityColDetailServiceImpl implements RcmCrityColDetailService
{
    private RcmCrityColDetailDAO rcmCrityColDetailDAO = null;
    
    public RcmCrityColDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityColListDTO rcmCrityColListDTO, User user) throws Exception
    {
    	return rcmCrityColDetailDAO.findDetail(rcmCrityCommonDTO,rcmCrityColListDTO, user);
    }
    
    public int insertDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user) throws Exception
    {
    	rcmCrityColDetailDAO.insertDetail(rcmCrityCommonDTO,rcmCrityColDetailDTO, user);
    	String[] rowArr = rcmCrityColDetailDAO.findRowList(rcmCrityCommonDTO, user);

        int result = 0;
        
     	for (int i = 0; i < rowArr.length; i++) {
     		result += rcmCrityColDetailDAO.updateValue(rcmCrityCommonDTO, rcmCrityColDetailDTO, user, rowArr[i]);
     	}
    	 
     	return result;
    }
    
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user) throws Exception
    {
    	rcmCrityColDetailDAO.updateDetail(rcmCrityCommonDTO,rcmCrityColDetailDTO, user);
    	String[] rowArr = rcmCrityColDetailDAO.findRowList(rcmCrityCommonDTO, user);

        int result = 0;
        
     	for (int i = 0; i < rowArr.length; i++) {
     		result += rcmCrityColDetailDAO.updateValue(rcmCrityCommonDTO, rcmCrityColDetailDTO, user, rowArr[i]);
     	}
    	 
     	return result;
    }

	public RcmCrityColDetailDAO getRcmCrityColDetailDAO() {
		return rcmCrityColDetailDAO;
	}

	public void setRcmCrityColDetailDAO(RcmCrityColDetailDAO rcmCrityColDetailDAO) {
		this.rcmCrityColDetailDAO = rcmCrityColDetailDAO;
	}
    

}
