package dream.comm.revision.service.spring;

import java.util.List;

import common.bean.User;
import dream.comm.revision.dao.CommRevHistListDAO;
import dream.comm.revision.dto.CommRevHistCommonDTO;
import dream.comm.revision.service.CommRevHistListService;


/**
 * 제/개정 변경이력  serviceimpl
 * @author kim21017
 * @version $Id: CommRevHistListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="commRevHistListServiceTarget"
 * @spring.txbn id="commRevHistListService"
 * @spring.property name="commRevHistListDAO" ref="commRevHistListDAO"
 */
public class CommRevHistListServiceImpl implements CommRevHistListService
{
    private CommRevHistListDAO commRevHistListDAO = null;

    public CommRevHistListDAO getCommRevHistListDAO() {
		return commRevHistListDAO;
	}

	public void setCommRevHistListDAO(CommRevHistListDAO commRevHistListDAO) {
		this.commRevHistListDAO = commRevHistListDAO;
	}
	
	public List findList(CommRevHistCommonDTO commRevHistCommonDTO, User user)
    {      
        return commRevHistListDAO.findList(commRevHistCommonDTO, user);
    }
}