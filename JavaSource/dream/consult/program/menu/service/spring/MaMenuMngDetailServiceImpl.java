package dream.consult.program.menu.service.spring;

import common.bean.User;
import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.program.menu.dao.MaMenuMngDetailDAO;
import dream.consult.program.menu.dto.MaMenuMngDetailDTO;
import dream.consult.program.menu.service.MaMenuMngDetailService;

/**
 * 메뉴 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaMenuMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maMenuMngDetailServiceTarget"
 * @spring.txbn id="maMenuMngDetailService"
 * @spring.property name="maMenuMngDetailDAO" ref="maMenuMngDetailDAO"
 */
public class MaMenuMngDetailServiceImpl implements MaMenuMngDetailService
{
    private MaMenuMngDetailDAO maMenuMngDetailDAO = null;
    
    public MaMenuMngDetailDAO getMaMenuMngDetailDAO() {
		return maMenuMngDetailDAO;
	}

	public void setMaMenuMngDetailDAO(MaMenuMngDetailDAO maMenuMngDetailDAO) {
		this.maMenuMngDetailDAO = maMenuMngDetailDAO;
	}

	public MaMenuMngDetailDTO findDetail(String menuId, String lang)throws Exception
    {
        return maMenuMngDetailDAO.findDetail(menuId, lang);
    }
    
	public int insertDetail(MaMenuMngDetailDTO maMenuMngDetailDTO,User loginUser) throws Exception
    {        
        int rtn = maMenuMngDetailDAO.insertDetail(maMenuMngDetailDTO, loginUser);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadMenuPathTable();
        return rtn;
    }
	
	public int updateDetail(MaMenuMngDetailDTO maMenuMngDetailDTO,User loginUser) throws Exception
    {        
	    int rtn = maMenuMngDetailDAO.updateDetail(maMenuMngDetailDTO, loginUser);
	    ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
	    configService.loadMenuPathTable();
	    return rtn;
    }
}
