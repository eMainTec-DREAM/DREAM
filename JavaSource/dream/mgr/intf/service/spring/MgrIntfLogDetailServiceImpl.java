package dream.mgr.intf.service.spring;

import common.bean.User;
import dream.mgr.intf.dao.MgrIntfLogDetailDAO;
import dream.mgr.intf.dto.MgrIntfLogDetailDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;
import dream.mgr.intf.service.MgrIntfLogDetailService;

/**
 * Interface Log Page - Detail Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrIntfLogDetailServiceTarget"
 * @spring.txbn id="mgrIntfLogDetailService"
 * @spring.property name="mgrIntfLogDetailDAO" ref="mgrIntfLogDetailDAO"
 */
public class MgrIntfLogDetailServiceImpl implements MgrIntfLogDetailService
{
    private MgrIntfLogDetailDAO mgrIntfLogDetailDAO = null;
    
    public MgrIntfLogDetailDTO findDetail(MgrIntfLogListDTO mgrIntfLogListDTO, User user) throws Exception
    {
    	return mgrIntfLogDetailDAO.findDetail(mgrIntfLogListDTO, user);
    }

	public MgrIntfLogDetailDAO getMgrIntfLogDetailDAO() {
		return mgrIntfLogDetailDAO;
	}

	public void setMgrIntfLogDetailDAO(MgrIntfLogDetailDAO mgrIntfLogDetailDAO) {
		this.mgrIntfLogDetailDAO = mgrIntfLogDetailDAO;
	}
}
