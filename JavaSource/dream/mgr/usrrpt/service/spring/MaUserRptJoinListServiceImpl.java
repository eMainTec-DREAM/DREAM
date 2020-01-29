package dream.mgr.usrrpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptJoinListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.service.MaUserRptJoinListService;

/**
 * ¸ñ·Ï
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptJoinListServiceTarget"
 * @spring.txbn id="maUserRptJoinListService"
 * @spring.property name="maUserRptJoinListDAO" ref="maUserRptJoinListDAO"
 */
public class MaUserRptJoinListServiceImpl implements MaUserRptJoinListService
{
    private MaUserRptJoinListDAO maUserRptJoinListDAO = null;

	public MaUserRptJoinListDAO getMaUserRptJoinListDAO() 
	{
		return maUserRptJoinListDAO;
	}

	public void setMaUserRptJoinListDAO(MaUserRptJoinListDAO maUserRptJoinListDAO) 
	{
		this.maUserRptJoinListDAO = maUserRptJoinListDAO;
	}
	
	public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
	{      
	    return maUserRptJoinListDAO.findList(maUserRptCommonDTO, loginUser);
	}
	
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maUserRptJoinListDAO.deleteList(id, loginUser);
            }
        
        return result;
    }
}

