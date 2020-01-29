package dream.consult.program.menu.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.menu.dao.MaMenuMngListDAO;
import dream.consult.program.menu.dto.MaMenuMngCommonDTO;
import dream.consult.program.menu.service.MaMenuMngListService;

/**
 * 메뉴 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaMenuMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maMenuMngListServiceTarget"
 * @spring.txbn id="maMenuMngListService"
 * @spring.property name="maMenuMngListDAO" ref="maMenuMngListDAO"
 */
public class MaMenuMngListServiceImpl implements MaMenuMngListService
{
    private MaMenuMngListDAO maMenuMngListDAO = null;

    public MaMenuMngListDAO getMaMenuMngListDAO() {
		return maMenuMngListDAO;
	}

	public void setMaMenuMngListDAO(MaMenuMngListDAO maMenuMngListDAO) {
		this.maMenuMngListDAO = maMenuMngListDAO;
	}

	public List findMenuList(MaMenuMngCommonDTO maMenuMngCommonDTO, User loginUser)
    {      
        return maMenuMngListDAO.findMenuList(maMenuMngCommonDTO, loginUser);
    }
	
	public int deleteMenu(String[] deleteRows) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maMenuMngListDAO.deleteMenu(id);
            }
        
        return result;
    }
}

