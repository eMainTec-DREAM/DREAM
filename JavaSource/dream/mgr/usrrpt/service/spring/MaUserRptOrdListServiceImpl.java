package dream.mgr.usrrpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptOrdListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.service.MaUserRptOrdListService;

/**
 * ¸ñ·Ï
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptOrdListServiceTarget"
 * @spring.txbn id="maUserRptOrdListService"
 * @spring.property name="maUserRptOrdListDAO" ref="maUserRptOrdListDAO"
 */
public class MaUserRptOrdListServiceImpl implements MaUserRptOrdListService
{
    private MaUserRptOrdListDAO maUserRptOrdListDAO = null;

	public MaUserRptOrdListDAO getMaUserRptOrdListDAO() 
	{
		return maUserRptOrdListDAO;
	}

	public void setMaUserRptOrdListDAO(MaUserRptOrdListDAO maUserRptOrdListDAO) 
	{
		this.maUserRptOrdListDAO = maUserRptOrdListDAO;
	}
	
	public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
	{      
	    return maUserRptOrdListDAO.findList(maUserRptCommonDTO, loginUser);
	}
	
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maUserRptOrdListDAO.deleteList(id, loginUser);
            }
        
        return result;
    }
}

