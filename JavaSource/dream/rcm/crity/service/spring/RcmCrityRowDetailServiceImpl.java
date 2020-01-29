package dream.rcm.crity.service.spring;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityRowDetailDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowDetailDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;
import dream.rcm.crity.service.RcmCrityRowDetailService;

/**
 * Criticality Matrix Row Page - Detail Service implements
 * @author kim21017
 * @version $Id: RcmCrityRowDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityRowDetailServiceTarget"
 * @spring.txbn id="rcmCrityRowDetailService"
 * @spring.property name="rcmCrityRowDetailDAO" ref="rcmCrityRowDetailDAO"
 */
public class RcmCrityRowDetailServiceImpl implements RcmCrityRowDetailService
{
    private RcmCrityRowDetailDAO rcmCrityRowDetailDAO = null;
    
    public RcmCrityRowDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityRowListDTO rcmCrityRowListDTO, User user) throws Exception
    {
    	return rcmCrityRowDetailDAO.findDetail(rcmCrityCommonDTO,rcmCrityRowListDTO, user);
    }
    
    public int insertDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user) throws Exception
    {
    	rcmCrityRowDetailDAO.insertDetail(rcmCrityCommonDTO,rcmCrityRowDetailDTO, user);
    	String[] colArr = rcmCrityRowDetailDAO.findColList(rcmCrityCommonDTO, user);

        int result = 0;
        
     	for (int i = 0; i < colArr.length; i++) {
     		result += rcmCrityRowDetailDAO.updateValue(rcmCrityCommonDTO, rcmCrityRowDetailDTO, user, colArr[i]);
     	}
    	 
     	return result;
    }
    
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user) throws Exception
    {
    	 rcmCrityRowDetailDAO.updateDetail(rcmCrityCommonDTO,rcmCrityRowDetailDTO, user);
    	 String[] colArr = rcmCrityRowDetailDAO.findColList(rcmCrityCommonDTO, user);

         int result = 0;
         
      	for (int i = 0; i < colArr.length; i++) {
      		result += rcmCrityRowDetailDAO.updateValue(rcmCrityCommonDTO, rcmCrityRowDetailDTO, user, colArr[i]);
      	}
     	 
      	return result;
    }

	public RcmCrityRowDetailDAO getRcmCrityRowDetailDAO() {
		return rcmCrityRowDetailDAO;
	}

	public void setRcmCrityRowDetailDAO(RcmCrityRowDetailDAO rcmCrityRowDetailDAO) {
		this.rcmCrityRowDetailDAO = rcmCrityRowDetailDAO;
	}
    

}
