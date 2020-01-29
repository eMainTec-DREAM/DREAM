package dream.mgr.intf.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.intf.dao.MgrIntfListDAO;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.service.MgrIntfListService;

/**
 * Interface Page - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrIntfListServiceTarget"
 * @spring.txbn id="mgrIntfListService"
 * @spring.property name="mgrIntfListDAO" ref="mgrIntfListDAO"
 */
public class MgrIntfListServiceImpl implements MgrIntfListService
{
	private MgrIntfListDAO mgrIntfListDAO = null;

	public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception
    {      
        return mgrIntfListDAO.findList(mgrIntfCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrIntfListDAO.deleteList(id, user);
            }
        return result;
    }

	public MgrIntfListDAO getMgrIntfListDAO() {
		return mgrIntfListDAO;
	}

	public void setMgrIntfListDAO(MgrIntfListDAO mgrIntfListDAO) {
		this.mgrIntfListDAO = mgrIntfListDAO;
	}
	public String findTotalCount(MgrIntfCommonDTO mgrIntfCommonDTO,User user) throws Exception
    {
        return mgrIntfListDAO.findTotalCount(mgrIntfCommonDTO, user);
    }
}

