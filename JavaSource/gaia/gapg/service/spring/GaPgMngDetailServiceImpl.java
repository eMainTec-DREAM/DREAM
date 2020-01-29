package gaia.gapg.service.spring;

import common.bean.User;
import common.config.service.ConfigService;
import common.util.CommonUtil;
import gaia.gapg.dao.GaPgMngDetailDAO;
import gaia.gapg.dto.GaPgMngDetailDTO;
import gaia.gapg.service.GaPgMngDetailService;

/**
 * 화면 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: GaPgMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="gaPgMngDetailServiceTarget"
 * @spring.txbn id="gaPgMngDetailService"
 * @spring.property name="gaPgMngDetailDAO" ref="gaPgMngDetailDAO"
 */
public class GaPgMngDetailServiceImpl implements GaPgMngDetailService
{
    private GaPgMngDetailDAO gaPgMngDetailDAO = null;
    
    public GaPgMngDetailDAO getGaPgMngDetailDAO() {
		return gaPgMngDetailDAO;
	}

	public void setGaPgMngDetailDAO(GaPgMngDetailDAO gaPgMngDetailDAO) {
		this.gaPgMngDetailDAO = gaPgMngDetailDAO;
	}

	public GaPgMngDetailDTO findDetail(String pageId, User user)throws Exception
    {
        return gaPgMngDetailDAO.findDetail(pageId, user);
    }
	
	public int insertDetail(GaPgMngDetailDTO gaPgMngDetailDTO) throws Exception
    {        
        int rtn = gaPgMngDetailDAO.insertDetail(gaPgMngDetailDTO);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadSecurityTable();
        configService.loadPageButtons();
        configService.loadTabPages();
        configService.loadPages();
        return rtn;
    }
	
	public int updateDetail(GaPgMngDetailDTO gaPgMngDetailDTO) throws Exception
    {        
	    int rtn = gaPgMngDetailDAO.updateDetail(gaPgMngDetailDTO);
	    ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadSecurityTable();
        configService.loadPageButtons();
        configService.loadTabPages();
        configService.loadPages();
	    return rtn;
    }
}
