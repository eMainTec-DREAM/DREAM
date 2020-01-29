package dream.consult.program.page.service.spring;

import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.program.page.dao.MaPgMngBtnDetailDAO;
import dream.consult.program.page.dto.MaPgMngBtnDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.service.MaPgMngBtnDetailService;

/**
 * 화면별 버튼 상세
 * @author kim2107
 * @version $Id: MaPgMngBtnDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPgMngBtnDetailServiceTarget"
 * @spring.txbn id="maPgMngBtnDetailService"
 * @spring.property name="maPgMngBtnDetailDAO" ref="maPgMngBtnDetailDAO"
 */
public class MaPgMngBtnDetailServiceImpl implements MaPgMngBtnDetailService
{
    private MaPgMngBtnDetailDAO maPgMngBtnDetailDAO = null;
    
    public MaPgMngBtnDetailDAO getMaPgMngBtnDetailDAO() {
		return maPgMngBtnDetailDAO;
	}

	public void setMaPgMngBtnDetailDAO(MaPgMngBtnDetailDAO maPgMngBtnDetailDAO) {
		this.maPgMngBtnDetailDAO = maPgMngBtnDetailDAO;
	}

	public MaPgMngBtnDetailDTO findDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO)
    {
        return maPgMngBtnDetailDAO.findDetail(maPgMngBtnDetailDTO);
    }
    
	public int updateDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception
    {        
		int rtn = maPgMngBtnDetailDAO.updateDetail(maPgMngBtnDetailDTO, maPgMngCommonDTO);
		ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
		configService.loadPageButtons();
		return rtn;
    }
	public int insertDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception
    {        
	    int rtn = maPgMngBtnDetailDAO.insertDetail( maPgMngBtnDetailDTO, maPgMngCommonDTO);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadPageButtons();
        return rtn;
    }
}
