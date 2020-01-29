package dream.rcm.crity.service.spring;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityMatrixDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityMatrixDTO;
import dream.rcm.crity.service.RcmCrityMatrixService;

/**
 * Criticality Matrix Page - Matrix Service implements
 * @author kim21017
 * @version $Id: RcmCrityMatrixServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityMatrixServiceTarget"
 * @spring.txbn id="rcmCrityMatrixService"
 * @spring.property name="rcmCrityMatrixDAO" ref="rcmCrityMatrixDAO"
 */
public class RcmCrityMatrixServiceImpl implements RcmCrityMatrixService
{
	private RcmCrityMatrixDAO rcmCrityMatrixDAO = null;

	public String[][] findCol(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityMatrixDTO rcmCrityMatrixDTO, User user) throws Exception
    {      
        return rcmCrityMatrixDAO.findCol(rcmCrityCommonDTO,rcmCrityMatrixDTO,user);
    }
	public String[][] findVal(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityMatrixDTO rcmCrityMatrixDTO, User user) throws Exception
    {      
        return rcmCrityMatrixDAO.findVal(rcmCrityCommonDTO,rcmCrityMatrixDTO,user);
    }

	public RcmCrityMatrixDAO getRcmCrityMatrixDAO() {
		return rcmCrityMatrixDAO;
	}

	public void setRcmCrityMatrixDAO(RcmCrityMatrixDAO rcmCrityMatrixDAO) {
		this.rcmCrityMatrixDAO = rcmCrityMatrixDAO;
	}
}

