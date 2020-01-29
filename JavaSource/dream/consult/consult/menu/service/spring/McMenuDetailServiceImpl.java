package dream.consult.consult.menu.service.spring;

import common.bean.User;
import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.consult.menu.dao.McMenuDetailDAO;
import dream.consult.consult.menu.dto.McMenuDetailDTO;
import dream.consult.consult.menu.service.McMenuDetailService;

/**
 * 메뉴 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: McMenuDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="mcMenuDetailServiceTarget"
 * @spring.txbn id="mcMenuDetailService"
 * @spring.property name="mcMenuDetailDAO" ref="mcMenuDetailDAO"
 */
public class McMenuDetailServiceImpl implements McMenuDetailService
{
    private McMenuDetailDAO mcMenuDetailDAO = null;
    
    public McMenuDetailDAO getMcMenuDetailDAO() {
		return mcMenuDetailDAO;
	}

	public void setMcMenuDetailDAO(McMenuDetailDAO mcMenuDetailDAO) {
		this.mcMenuDetailDAO = mcMenuDetailDAO;
	}

	public McMenuDetailDTO findDetail(String menuId, String lang)throws Exception
    {
        return mcMenuDetailDAO.findDetail(menuId, lang);
    }
    
	public int insertDetail(McMenuDetailDTO mcMenuDetailDTO,User loginUser) throws Exception
    {        
        int rtn = mcMenuDetailDAO.insertDetail(mcMenuDetailDTO, loginUser);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadMenuPathTable();
        return rtn;
    }
	
	public int updateDetail(McMenuDetailDTO mcMenuDetailDTO,User loginUser) throws Exception
    {        
	    int rtn = mcMenuDetailDAO.updateDetail(mcMenuDetailDTO, loginUser);
	    ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadMenuPathTable();
	    return rtn;
    }
}
