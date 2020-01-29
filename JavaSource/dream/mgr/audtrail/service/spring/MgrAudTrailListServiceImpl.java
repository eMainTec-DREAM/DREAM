package dream.mgr.audtrail.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.audtrail.dao.MgrAudTrailListDAO;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.service.MgrAudTrailListService;


/**
 * Audit Trail  serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrAudTrailListServiceTarget"
 * @spring.txbn id="mgrAudTrailListService"
 * @spring.property name="mgrAudTrailListDAO" ref="mgrAudTrailListDAO"
 */
public class MgrAudTrailListServiceImpl implements MgrAudTrailListService
{
    private MgrAudTrailListDAO mgrAudTrailListDAO = null;

    public MgrAudTrailListDAO getMgrAudTrailListDAO() {
		return mgrAudTrailListDAO;
	}

	public void setMgrAudTrailListDAO(MgrAudTrailListDAO mgrAudTrailListDAO) {
		this.mgrAudTrailListDAO = mgrAudTrailListDAO;
	}
	
	public List findList(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user)
    {      
        return mgrAudTrailListDAO.findList(mgrAudTrailCommonDTO, user);
    }

	public String findTotalCount(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user) throws Exception 
	{
		return mgrAudTrailListDAO.findTotalCount(mgrAudTrailCommonDTO, user);
	}
}