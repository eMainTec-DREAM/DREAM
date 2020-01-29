package dream.consult.program.page.service.spring;

import common.bean.User;
import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.program.page.dao.ConsultPgmPgLinkedFuncDetailDAO;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.service.ConsultPgmPgLinkedFuncDetailService;

/**
 * 화면별 연결기능 상세
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="consultPgmPgLinkedFuncDetailServiceTarget"
 * @spring.txbn id="consultPgmPgLinkedFuncDetailService"
 * @spring.property name="consultPgmPgLinkedFuncDetailDAO" ref="consultPgmPgLinkedFuncDetailDAO"
 */
public class ConsultPgmPgLinkedFuncDetailServiceImpl implements ConsultPgmPgLinkedFuncDetailService
{
    private ConsultPgmPgLinkedFuncDetailDAO consultPgmPgLinkedFuncDetailDAO = null;
    
    public ConsultPgmPgLinkedFuncDetailDAO getConsultPgmPgLinkedFuncDetailDAO() {
		return consultPgmPgLinkedFuncDetailDAO;
	}

	public void setConsultPgmPgLinkedFuncDetailDAO(ConsultPgmPgLinkedFuncDetailDAO consultPgmPgLinkedFuncDetailDAO) {
		this.consultPgmPgLinkedFuncDetailDAO = consultPgmPgLinkedFuncDetailDAO;
	}

	public ConsultPgmPgLinkedFuncDetailDTO findDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, User user)
    {
        return consultPgmPgLinkedFuncDetailDAO.findDetail(consultPgmPgLinkedFuncDetailDTO, user);
    }
    
	public int updateDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception
    {        
		int rtn = consultPgmPgLinkedFuncDetailDAO.updateDetail(consultPgmPgLinkedFuncDetailDTO, maPgMngCommonDTO);
		ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
		configService.loadPageLinkes();
		return rtn;
    }
	public int insertDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception
    {        
	    int rtn = consultPgmPgLinkedFuncDetailDAO.insertDetail( consultPgmPgLinkedFuncDetailDTO, maPgMngCommonDTO);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadPageLinkes();
        return rtn;
    }
}
