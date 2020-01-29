package dream.mgr.usrdata.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrdata.dao.McDataSelectListDAO;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.service.McDataSelectListService;



/**
 * 메뉴 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: McDataSelectListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="mcDataSelectListServiceTarget"
 * @spring.txbn id="mcDataSelectListService"
 * @spring.property name="mcDataSelectListDAO" ref="mcDataSelectListDAO"
 */
public class McDataSelectListServiceImpl implements McDataSelectListService
{
    private McDataSelectListDAO mcDataSelectListDAO = null;

    public McDataSelectListDAO getMcDataSelectListDAO() {
		return mcDataSelectListDAO;
	}

	public void setMcDataSelectListDAO(McDataSelectListDAO mcDataSelectListDAO) {
		this.mcDataSelectListDAO = mcDataSelectListDAO;
	}

	public List findMenuList(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser)
    {      
        return mcDataSelectListDAO.findMenuList(mcDataSelectCommonDTO, loginUser);
    }
	
	public int deleteMenu(String[] deleteRows) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mcDataSelectListDAO.deleteMenu(id);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception {
		return mcDataSelectListDAO.findTotalCount(mcDataSelectCommonDTO, user);
	}
}

