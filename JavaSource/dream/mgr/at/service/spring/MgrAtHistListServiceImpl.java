package dream.mgr.at.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.at.dao.MgrAtHistListDAO;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtHistListDTO;
import dream.mgr.at.service.MgrAtHistListService;

/**
 * Audit Trail Hist Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrAtHistListServiceTarget"
 * @spring.txbn id="mgrAtHistListService"
 * @spring.property name="mgrAtHistListDAO" ref="mgrAtHistListDAO"
 */
public class MgrAtHistListServiceImpl implements MgrAtHistListService
{
    private MgrAtHistListDAO mgrAtHistListDAO = null;

	public List findList(MgrAtCommonDTO mgrAtCommonDTO, MgrAtHistListDTO mgrAtHistListDTO, User user) throws Exception
    {      
        return mgrAtHistListDAO.findList(mgrAtCommonDTO,mgrAtHistListDTO,user);
    }

    public MgrAtHistListDAO getMgrAtHistListDAO() {
        return mgrAtHistListDAO;
    }

    public void setMgrAtHistListDAO(MgrAtHistListDAO mgrAtHistListDAO) {
        this.mgrAtHistListDAO = mgrAtHistListDAO;
    }    
    
    public String findTotalCount(MgrAtCommonDTO mgrAtCommonDTO, MgrAtHistListDTO mgrAtHistListDTO, User user)  throws Exception
    {
        return mgrAtHistListDAO.findTotalCount(mgrAtCommonDTO, mgrAtHistListDTO, user);
    }
}
