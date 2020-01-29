package dream.mgr.intf.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.intf.dao.MgrIntfLogListDAO;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;
import dream.mgr.intf.service.MgrIntfLogListService;

/**
 * Interface Log Page - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrIntfLogListServiceTarget"
 * @spring.txbn id="mgrIntfLogListService"
 * @spring.property name="mgrIntfLogListDAO" ref="mgrIntfLogListDAO"
 */
public class MgrIntfLogListServiceImpl implements MgrIntfLogListService
{
	private MgrIntfLogListDAO mgrIntfLogListDAO = null;

	public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, MgrIntfLogListDTO mgrIntfLogListDTO, User user) throws Exception
    {      
        return mgrIntfLogListDAO.findList(mgrIntfCommonDTO, mgrIntfLogListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrIntfLogListDAO.deleteList(id, user);
            }
        return result;
    }

	public MgrIntfLogListDAO getMgrIntfLogListDAO() {
		return mgrIntfLogListDAO;
	}

	public void setMgrIntfLogListDAO(MgrIntfLogListDAO mgrIntfLogListDAO) {
		this.mgrIntfLogListDAO = mgrIntfLogListDAO;
	}
	public String findTotalCount(MgrIntfCommonDTO mgrIntfCommonDTO, MgrIntfLogListDTO mgrIntfLogListDTO,User user) throws Exception
    {
        return mgrIntfLogListDAO.findTotalCount(mgrIntfCommonDTO, mgrIntfLogListDTO, user);
    }
}

