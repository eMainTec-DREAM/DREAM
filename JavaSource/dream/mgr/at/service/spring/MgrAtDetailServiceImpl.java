package dream.mgr.at.service.spring;

import common.bean.User;
import dream.mgr.at.dao.MgrAtDetailDAO;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtDetailDTO;
import dream.mgr.at.service.MgrAtDetailService;
import dream.work.pm.list.dao.MaPmMstrListDAO;

/**
 * »ó¼¼ serviceimpl 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrAtDetailServiceTarget"
 * @spring.txbn id="mgrAtDetailService"
 * @spring.property name="mgrAtDetailDAO" ref="mgrAtDetailDAO"
 * @spring.property name="maPmMstrListDAO" ref="maPmMstrListDAO"
 */
public class MgrAtDetailServiceImpl implements MgrAtDetailService
{
    private MgrAtDetailDAO mgrAtDetailDAO = null;
    
    private MaPmMstrListDAO maPmMstrListDAO = null;
    
    
    public MaPmMstrListDAO getMaPmMstrListDAO() {
		return maPmMstrListDAO;
	}

	public void setMaPmMstrListDAO(MaPmMstrListDAO maPmMstrListDAO) {
		this.maPmMstrListDAO = maPmMstrListDAO;
	}

	public MgrAtDetailDAO getMgrAtDetailDAO() {
		return mgrAtDetailDAO;
	}

	public void setMgrAtDetailDAO(MgrAtDetailDAO mgrAtDetailDAO) {
		this.mgrAtDetailDAO = mgrAtDetailDAO;
	}

	public MgrAtDetailDTO findDetail(MgrAtCommonDTO mgrAtCommonDTO, User user) throws Exception
	{
		MgrAtDetailDTO mgrAtDetailDTO = new MgrAtDetailDTO();
		
		mgrAtDetailDTO = mgrAtDetailDAO.findDetail(mgrAtCommonDTO, user);
		
		return mgrAtDetailDTO;
	}
}
