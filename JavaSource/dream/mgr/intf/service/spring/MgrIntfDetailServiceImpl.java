package dream.mgr.intf.service.spring;

import common.bean.User;
import dream.mgr.intf.dao.MgrIntfDetailDAO;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfDetailDTO;
import dream.mgr.intf.service.MgrIntfDetailService;

/**
 * Interface Page - Detail Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrIntfDetailServiceTarget"
 * @spring.txbn id="mgrIntfDetailService"
 * @spring.property name="mgrIntfDetailDAO" ref="mgrIntfDetailDAO"
 */
public class MgrIntfDetailServiceImpl implements MgrIntfDetailService
{
    private MgrIntfDetailDAO mgrIntfDetailDAO = null;
    
    public MgrIntfDetailDTO findDetail(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception
    {
    	return mgrIntfDetailDAO.findDetail(mgrIntfCommonDTO, user);
    }
    
    public int insertDetail(MgrIntfDetailDTO mgrIntfDetailDTO, User user) throws Exception
    {
    	return mgrIntfDetailDAO.insertDetail(mgrIntfDetailDTO, user);
    }
    
    public int updateDetail(MgrIntfDetailDTO mgrIntfDetailDTO, User user) throws Exception
    {
    	 return mgrIntfDetailDAO.updateDetail(mgrIntfDetailDTO, user);
    }

	public MgrIntfDetailDAO getMgrIntfDetailDAO() {
		return mgrIntfDetailDAO;
	}

	public void setMgrIntfDetailDAO(MgrIntfDetailDAO mgrIntfDetailDAO) {
		this.mgrIntfDetailDAO = mgrIntfDetailDAO;
	}
}
