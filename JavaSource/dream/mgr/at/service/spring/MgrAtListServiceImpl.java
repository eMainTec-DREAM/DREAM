package dream.mgr.at.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.at.dao.MgrAtListDAO;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.service.MgrAtListService;


/**
 * Audit Trail  serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrAtListServiceTarget"
 * @spring.txbn id="mgrAtListService"
 * @spring.property name="mgrAtListDAO" ref="mgrAtListDAO"
 */
public class MgrAtListServiceImpl implements MgrAtListService
{
    private MgrAtListDAO mgrAtListDAO = null;

    public MgrAtListDAO getMgrAtListDAO() {
		return mgrAtListDAO;
	}

	public void setMgrAtListDAO(MgrAtListDAO mgrAtListDAO) {
		this.mgrAtListDAO = mgrAtListDAO;
	}
	
	public List findList(MgrAtCommonDTO mgrAtCommonDTO, User user)
    {      
        return mgrAtListDAO.findList(mgrAtCommonDTO, user);
    }

	public String findTotalCount(MgrAtCommonDTO mgrAtCommonDTO, User user) throws Exception 
	{
		return mgrAtListDAO.findTotalCount(mgrAtCommonDTO, user);
	}
}