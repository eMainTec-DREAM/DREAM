package dream.rcm.pmtask.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.pmtask.dao.RcmPmtaskListDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.service.RcmPmtaskListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: RcmPmtaskListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmPmtaskListServiceTarget"
 * @spring.txbn id="rcmPmtaskListService"
 * @spring.property name="rcmPmtaskListDAO" ref="rcmPmtaskListDAO"
 */
public class RcmPmtaskListServiceImpl implements RcmPmtaskListService
{
    private RcmPmtaskListDAO rcmPmtaskListDAO = null;

    public RcmPmtaskListDAO getRcmPmtaskListDAO() {
		return rcmPmtaskListDAO;
	}

	public void setRcmPmtaskListDAO(RcmPmtaskListDAO rcmPmtaskListDAO) {
		this.rcmPmtaskListDAO = rcmPmtaskListDAO;
	}

	public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, User user)
    {      
        return rcmPmtaskListDAO.findList(rcmPmtaskCommonDTO, user);
    }

	@Override
	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, User user) {
		return rcmPmtaskListDAO.findTotalCount(rcmPmtaskCommonDTO, user);
	}

}

