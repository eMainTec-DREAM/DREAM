package dream.consult.program.page.service.spring;

import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.program.page.dao.MaPgMngPageDetailDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageDetailDTO;
import dream.consult.program.page.service.MaPgMngPageDetailService;

/**
 * 화면별 탭페이지 상세
 * @author kim2107
 * @version $Id: MaPgMngPageDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPgMngPageDetailServiceTarget"
 * @spring.txbn id="maPgMngPageDetailService"
 * @spring.property name="maPgMngPageDetailDAO" ref="maPgMngPageDetailDAO"
 */
public class MaPgMngPageDetailServiceImpl implements MaPgMngPageDetailService
{
    private MaPgMngPageDetailDAO maPgMngPageDetailDAO = null;
    
    public MaPgMngPageDetailDAO getMaPgMngPageDetailDAO() {
		return maPgMngPageDetailDAO;
	}

	public void setMaPgMngPageDetailDAO(MaPgMngPageDetailDAO maPgMngPageDetailDAO) {
		this.maPgMngPageDetailDAO = maPgMngPageDetailDAO;
	}

	public MaPgMngPageDetailDTO findDetail(String pageId, String pgPageId, String lang)throws Exception
    {
        return maPgMngPageDetailDAO.findDetail(pageId, pgPageId, lang);
    }
    
	public int updateDetail(MaPgMngPageDetailDTO maPgMngPageDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception
    {        
        int rtn = maPgMngPageDetailDAO.updateDetail(maPgMngPageDetailDTO, maPgMngCommonDTO);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadTabPages();
        return rtn;
    }
	public int insertDetail(MaPgMngPageDetailDTO maPgMngPageDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception
    {        
	    int rtn = maPgMngPageDetailDAO.insertDetail( maPgMngPageDetailDTO, maPgMngCommonDTO);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadTabPages();
        return rtn;
    }
}
