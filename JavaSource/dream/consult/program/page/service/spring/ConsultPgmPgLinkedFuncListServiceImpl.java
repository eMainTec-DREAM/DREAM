package dream.consult.program.page.service.spring;

import java.util.List;

import common.bean.User;
import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.program.page.dao.ConsultPgmPgLinkedFuncListDAO;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.service.ConsultPgmPgLinkedFuncListService;

/**
 * 화면별 연결기능 목록    
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="consultPgmPgLinkedFuncListServiceTarget"
 * @spring.txbn id="consultPgmPgLinkedFuncListService"
 * @spring.property name="consultPgmPgLinkedFuncListDAO" ref="consultPgmPgLinkedFuncListDAO"
 */
public class ConsultPgmPgLinkedFuncListServiceImpl implements ConsultPgmPgLinkedFuncListService
{
    private ConsultPgmPgLinkedFuncListDAO consultPgmPgLinkedFuncListDAO = null;


	public List findFieldList(MaPgMngCommonDTO maPgMngCommonDTO, ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO, User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {      	    
        return consultPgmPgLinkedFuncListDAO.findFieldList(maPgMngCommonDTO, consultPgmPgLinkedFuncListDTO, user);
    }
	public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO, User user) throws Exception
    {      
        return consultPgmPgLinkedFuncListDAO.findTotalCount(maPgMngCommonDTO, consultPgmPgLinkedFuncListDTO, user);
    }
	public ConsultPgmPgLinkedFuncListDAO getConsultPgmPgLinkedFuncListDAO() {
		return consultPgmPgLinkedFuncListDAO;
	}

	public void setConsultPgmPgLinkedFuncListDAO(ConsultPgmPgLinkedFuncListDAO consultPgmPgLinkedFuncListDAO) {
		this.consultPgmPgLinkedFuncListDAO = consultPgmPgLinkedFuncListDAO;
	}
	
	public int deleteFieldList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultPgmPgLinkedFuncListDAO.deleteFieldList(id);
            }
        
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadPageLinkes();
        
        return result;
    }
}

