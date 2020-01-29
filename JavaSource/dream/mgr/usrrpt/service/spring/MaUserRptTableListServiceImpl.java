package dream.mgr.usrrpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptTableListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.service.MaUserRptTableListService;

/**
 * ¸ñ·Ï
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptTableListServiceTarget"
 * @spring.txbn id="maUserRptTableListService"
 * @spring.property name="maUserRptTableListDAO" ref="maUserRptTableListDAO"
 */
public class MaUserRptTableListServiceImpl implements MaUserRptTableListService
{
    private MaUserRptTableListDAO maUserRptTableListDAO = null;

	public MaUserRptTableListDAO getMaUserRptTableListDAO() 
	{
		return maUserRptTableListDAO;
	}

	public void setMaUserRptTableListDAO(MaUserRptTableListDAO maUserRptTableListDAO) 
	{
		this.maUserRptTableListDAO = maUserRptTableListDAO;
	}
	
	public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
	{      
	    return maUserRptTableListDAO.findList(maUserRptCommonDTO, loginUser);
	}
	
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maUserRptTableListDAO.deleteList(id, loginUser);
                result = result + maUserRptTableListDAO.deleteJoinList(id, loginUser);
                result = result + maUserRptTableListDAO.deleteColList(id, loginUser);
                result = result + maUserRptTableListDAO.deleteParamList(id, loginUser);
                result = result + maUserRptTableListDAO.deleteOrdList(id, loginUser);
                
            }
        
        return result;
    }
}

