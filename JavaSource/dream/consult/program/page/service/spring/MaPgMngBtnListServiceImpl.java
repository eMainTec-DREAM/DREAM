package dream.consult.program.page.service.spring;

import java.util.List;

import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.program.page.dao.MaPgMngBtnListDAO;
import dream.consult.program.page.dto.MaPgMngBtnListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.service.MaPgMngBtnListService;

/**
 * 화면별 버튼 목록
 * @author kim21017
 * @version $Id: MaPgMngBtnListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPgMngBtnListServiceTarget"
 * @spring.txbn id="maPgMngBtnListService"
 * @spring.property name="maPgMngBtnListDAO" ref="maPgMngBtnListDAO"
 */
public class MaPgMngBtnListServiceImpl implements MaPgMngBtnListService
{
    private MaPgMngBtnListDAO maPgMngBtnListDAO = null;


	public List findBtnList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngBtnListDTO maPgMngBtnListDTO)
    {      
        return maPgMngBtnListDAO.findBtnList(maPgMngCommonDTO, maPgMngBtnListDTO);
    }

	public MaPgMngBtnListDAO getMaPgMngBtnListDAO() {
		return maPgMngBtnListDAO;
	}

	public void setMaPgMngBtnListDAO(MaPgMngBtnListDAO maPgMngBtnListDAO) {
		this.maPgMngBtnListDAO = maPgMngBtnListDAO;
	}
	
	public int deleteBtnList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPgMngBtnListDAO.deleteBtnList(id);
            }
        
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadPageButtons();
        
        return result;
    }
}

