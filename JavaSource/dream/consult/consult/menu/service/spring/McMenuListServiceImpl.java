package dream.consult.consult.menu.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.consult.menu.dao.McMenuListDAO;
import dream.consult.consult.menu.dto.McMenuCommonDTO;
import dream.consult.consult.menu.service.McMenuListService;


/**
 * 메뉴 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: McMenuListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="mcMenuListServiceTarget"
 * @spring.txbn id="mcMenuListService"
 * @spring.property name="mcMenuListDAO" ref="mcMenuListDAO"
 */
public class McMenuListServiceImpl implements McMenuListService
{
    private McMenuListDAO mcMenuListDAO = null;

    public McMenuListDAO getMcMenuListDAO() {
		return mcMenuListDAO;
	}

	public void setMcMenuListDAO(McMenuListDAO mcMenuListDAO) {
		this.mcMenuListDAO = mcMenuListDAO;
	}

	public List findMenuList(McMenuCommonDTO mcMenuCommonDTO, User loginUser)
    {      
        return mcMenuListDAO.findMenuList(mcMenuCommonDTO, loginUser);
    }
	
	public int deleteMenu(String[] deleteRows) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mcMenuListDAO.deleteMenu(id);
            }
        
        return result;
    }
}

