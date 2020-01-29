package dream.mgr.audtrail.service.spring;

import common.bean.User;
import dream.mgr.audtrail.dao.MgrAudTrailDetailDAO;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDetailDTO;
import dream.mgr.audtrail.service.MgrAudTrailDetailService;
import dream.work.pm.list.dao.MaPmMstrListDAO;

/**
 * »ó¼¼ serviceimpl 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrAudTrailDetailServiceTarget"
 * @spring.txbn id="mgrAudTrailDetailService"
 * @spring.property name="mgrAudTrailDetailDAO" ref="mgrAudTrailDetailDAO"
 * @spring.property name="maPmMstrListDAO" ref="maPmMstrListDAO"
 */
public class MgrAudTrailDetailServiceImpl implements MgrAudTrailDetailService
{
    private MgrAudTrailDetailDAO mgrAudTrailDetailDAO = null;
    
    private MaPmMstrListDAO maPmMstrListDAO = null;
    
    
    public MaPmMstrListDAO getMaPmMstrListDAO() {
		return maPmMstrListDAO;
	}

	public void setMaPmMstrListDAO(MaPmMstrListDAO maPmMstrListDAO) {
		this.maPmMstrListDAO = maPmMstrListDAO;
	}

	public MgrAudTrailDetailDAO getMgrAudTrailDetailDAO() {
		return mgrAudTrailDetailDAO;
	}

	public void setMgrAudTrailDetailDAO(MgrAudTrailDetailDAO mgrAudTrailDetailDAO) {
		this.mgrAudTrailDetailDAO = mgrAudTrailDetailDAO;
	}

	public MgrAudTrailDetailDTO findDetail(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user) throws Exception
	{
		MgrAudTrailDetailDTO mgrAudTrailDetailDTO = new MgrAudTrailDetailDTO();
		
		//String prevKeyId = mgrAudTrailDetailDAO.findPrevKeyId(mgrAudTrailCommonDTO, user);
		
		//mgrAudTrailCommonDTO.setPrevTracelogId(prevKeyId);
				
		mgrAudTrailDetailDTO = mgrAudTrailDetailDAO.findDetail(mgrAudTrailCommonDTO, user);
		
		return mgrAudTrailDetailDTO;
	}
}
