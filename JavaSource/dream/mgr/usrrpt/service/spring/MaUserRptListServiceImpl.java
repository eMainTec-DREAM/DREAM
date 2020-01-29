package dream.mgr.usrrpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.service.MaUserRptListService;



/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: MaUserRptListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maUserRptListServiceTarget"
 * @spring.txbn id="maUserRptListService"
 * @spring.property name="maUserRptListDAO" ref="maUserRptListDAO"
 */
public class MaUserRptListServiceImpl implements MaUserRptListService
{
    private MaUserRptListDAO maUserRptListDAO = null;

    public MaUserRptListDAO getMaUserRptListDAO() {
		return maUserRptListDAO;
	}

	public void setMaUserRptListDAO(MaUserRptListDAO maUserRptListDAO) {
		this.maUserRptListDAO = maUserRptListDAO;
	}

	public List findMenuList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {      
        return maUserRptListDAO.findMenuList(maUserRptCommonDTO, loginUser);
    }
	
	public int deleteMenu(String[] deleteRows) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maUserRptListDAO.deleteRpt(id);
                result = result + maUserRptListDAO.deleteTable(id);
                result = result + maUserRptListDAO.deleteJoin(id);
                result = result + maUserRptListDAO.deleteCol(id);
                result = result + maUserRptListDAO.deleteParam(id);
                result = result + maUserRptListDAO.deleteOrd(id);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaUserRptCommonDTO maUserRptCommonDTO, User user) throws Exception {
		return maUserRptListDAO.findTotalCount(maUserRptCommonDTO, user);
	}
}

