package dream.mgr.ptwh.service.spring;

import common.bean.User;
import dream.mgr.ptwh.dao.MgrPtWhDetailDAO;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhDetailDTO;
import dream.mgr.ptwh.service.MgrPtWhDetailService;

/**
 * 부품창고 - Detail Service implements
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="mgrPtWhDetailServiceTarget"
 * @spring.txbn id="mgrPtWhDetailService"
 * @spring.property name="mgrPtWhDetailDAO" ref="mgrPtWhDetailDAO"
 */
public class MgrPtWhDetailServiceImpl implements MgrPtWhDetailService
{
    private MgrPtWhDetailDAO mgrPtWhDetailDAO = null;
    
    public MgrPtWhDetailDTO findDetail(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user) throws Exception
    {
    	return mgrPtWhDetailDAO.findDetail(mgrPtWhCommonDTO, user);
    }
    
    public int updatePtWhDetail(MgrPtWhDetailDTO mgrPtWhDetailDTO, User user) throws Exception
    {
    	 return mgrPtWhDetailDAO.updatePtWhDetail(mgrPtWhDetailDTO, user);
    }

	public MgrPtWhDetailDAO getMgrPtWhDetailDAO() {
		return mgrPtWhDetailDAO;
	}

	public void setMgrPtWhDetailDAO(MgrPtWhDetailDAO mgrPtWhDetailDAO) {
		this.mgrPtWhDetailDAO = mgrPtWhDetailDAO;
	}
}
