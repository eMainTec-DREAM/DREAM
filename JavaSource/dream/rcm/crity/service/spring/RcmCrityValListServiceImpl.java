package dream.rcm.crity.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityValListDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;
import dream.rcm.crity.service.RcmCrityValListService;

/**
 * Criticality Matrix Val Page - List Service implements
 * @author kim21017
 * @version $Id: RcmCrityValListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityValListServiceTarget"
 * @spring.txbn id="rcmCrityValListService"
 * @spring.property name="rcmCrityValListDAO" ref="rcmCrityValListDAO"
 */
public class RcmCrityValListServiceImpl implements RcmCrityValListService
{
	private RcmCrityValListDAO rcmCrityValListDAO = null;

	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityValListDTO rcmCrityValListDTO, User user) throws Exception
    {      
        return rcmCrityValListDAO.findList(rcmCrityCommonDTO,rcmCrityValListDTO,user);
    }

	public RcmCrityValListDAO getRcmCrityValListDAO() {
		return rcmCrityValListDAO;
	}

	public void setRcmCrityValListDAO(RcmCrityValListDAO rcmCrityValListDAO) {
		this.rcmCrityValListDAO = rcmCrityValListDAO;
	}

	@Override
	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityValListDTO rcmCrityValListDTO,
			User user) {
        return rcmCrityValListDAO.findTotalCount(rcmCrityCommonDTO, rcmCrityValListDTO, user);
        
	}

}

