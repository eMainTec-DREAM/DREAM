package dream.rcm.fmea.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.fmea.dao.RcmFmeaListDAO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.service.RcmFmeaListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: RcmFmeaListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmFmeaListServiceTarget"
 * @spring.txbn id="rcmFmeaListService"
 * @spring.property name="rcmFmeaListDAO" ref="rcmFmeaListDAO"
 */
public class RcmFmeaListServiceImpl implements RcmFmeaListService
{
    private RcmFmeaListDAO rcmFmeaListDAO = null;

    public RcmFmeaListDAO getRcmFmeaListDAO() {
		return rcmFmeaListDAO;
	}

	public void setRcmFmeaListDAO(RcmFmeaListDAO rcmFmeaListDAO) {
		this.rcmFmeaListDAO = rcmFmeaListDAO;
	}

	public List findList(RcmFmeaCommonDTO rcmFmeaCommonDTO, User user) {
		// TODO Auto-generated method stub
		return rcmFmeaListDAO.findList(rcmFmeaCommonDTO, user);
	}

	@Override
	public String findTotalCount(RcmFmeaCommonDTO rcmFmeaCommonDTO, User user) {
	   return rcmFmeaListDAO.findTotalCount(rcmFmeaCommonDTO, user);
	}

}

