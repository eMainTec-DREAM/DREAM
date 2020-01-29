package dream.mgr.usrrpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptColListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.service.MaUserRptColListService;

/**
 * ¸ñ·Ï
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptColListServiceTarget"
 * @spring.txbn id="maUserRptColListService"
 * @spring.property name="maUserRptColListDAO" ref="maUserRptColListDAO"
 */
public class MaUserRptColListServiceImpl implements MaUserRptColListService
{
    private MaUserRptColListDAO maUserRptColListDAO = null;

	public MaUserRptColListDAO getMaUserRptColListDAO() 
	{
		return maUserRptColListDAO;
	}

	public void setMaUserRptColListDAO(MaUserRptColListDAO maUserRptColListDAO) 
	{
		this.maUserRptColListDAO = maUserRptColListDAO;
	}
	
	public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
	{      
	    return maUserRptColListDAO.findList(maUserRptCommonDTO, loginUser);
	}
	
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maUserRptColListDAO.deleteList(id, loginUser);
            }
        
        return result;
    }
}

