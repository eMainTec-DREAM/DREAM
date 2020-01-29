package dream.consult.program.page.service.spring;

import common.bean.User;
import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.program.page.dao.MaPgMngDetailDAO;
import dream.consult.program.page.dto.MaPgMngDetailDTO;
import dream.consult.program.page.service.MaPgMngDetailService;

/**
 * 화면 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaPgMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngDetailServiceTarget"
 * @spring.txbn id="maPgMngDetailService"
 * @spring.property name="maPgMngDetailDAO" ref="maPgMngDetailDAO"
 */
public class MaPgMngDetailServiceImpl implements MaPgMngDetailService
{
    private MaPgMngDetailDAO maPgMngDetailDAO = null;
    
    public MaPgMngDetailDAO getMaPgMngDetailDAO() {
		return maPgMngDetailDAO;
	}

	public void setMaPgMngDetailDAO(MaPgMngDetailDAO maPgMngDetailDAO) {
		this.maPgMngDetailDAO = maPgMngDetailDAO;
	}

	public MaPgMngDetailDTO findDetail(String pageId, User user)throws Exception
    {
        return maPgMngDetailDAO.findDetail(pageId, user);
    }
	
	public int insertDetail(MaPgMngDetailDTO maPgMngDetailDTO) throws Exception
    {        
        int rtn = maPgMngDetailDAO.insertDetail(maPgMngDetailDTO);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadSecurityTable();
        configService.loadPageButtons();
        configService.loadTabPages();
        configService.loadPages();
        return rtn;
    }
	
	public int updateDetail(MaPgMngDetailDTO maPgMngDetailDTO) throws Exception
    {        
	    int rtn = maPgMngDetailDAO.updateDetail(maPgMngDetailDTO);
	    ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
	    configService.loadSecurityTable();
	    configService.loadPageButtons();
	    configService.loadTabPages();
	    configService.loadPages();
	    return rtn;
    }
}
