package dream.mgr.usrrpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptParamListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.service.MaUserRptParamListService;

/**
 * ¸ñ·Ï
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptParamListServiceTarget"
 * @spring.txbn id="maUserRptParamListService"
 * @spring.property name="maUserRptParamListDAO" ref="maUserRptParamListDAO"
 */
public class MaUserRptParamListServiceImpl implements MaUserRptParamListService
{
    private MaUserRptParamListDAO maUserRptParamListDAO = null;

	public MaUserRptParamListDAO getMaUserRptParamListDAO() 
	{
		return maUserRptParamListDAO;
	}

	public void setMaUserRptParamListDAO(MaUserRptParamListDAO maUserRptParamListDAO) 
	{
		this.maUserRptParamListDAO = maUserRptParamListDAO;
	}
	
	public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
	{      
	    return maUserRptParamListDAO.findList(maUserRptCommonDTO, loginUser);
	}
	
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maUserRptParamListDAO.deleteList(id, loginUser);
            }
        
        return result;
    }
}

