package dream.consult.program.linkedfunc.service.spring;

import common.bean.User;
import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.program.linkedfunc.dao.ConsultPgmLinkedFuncDetailDAO;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncDetailDTO;
import dream.consult.program.linkedfunc.service.ConsultPgmLinkedFuncDetailService;

/**
 * PgmLinkedFunc Page - Detail Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="consultPgmLinkedFuncDetailServiceTarget"
 * @spring.txbn id="consultPgmLinkedFuncDetailService"
 * @spring.property name="consultPgmLinkedFuncDetailDAO" ref="consultPgmLinkedFuncDetailDAO"
 */
public class ConsultPgmLinkedFuncDetailServiceImpl implements ConsultPgmLinkedFuncDetailService
{
    private ConsultPgmLinkedFuncDetailDAO consultPgmLinkedFuncDetailDAO = null;
    
    public ConsultPgmLinkedFuncDetailDTO findPgmLinkedFuncDetail(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user) throws Exception
    {
        return consultPgmLinkedFuncDetailDAO.findPgmLinkedFuncDetail(consultPgmLinkedFuncCommonDTO, user);
    }
    
    public int insertPgmLinkedFuncDetail(ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO, User user) throws Exception
    {
        int rtn = consultPgmLinkedFuncDetailDAO.insertPgmLinkedFuncDetail(consultPgmLinkedFuncDetailDTO, user);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadPageLinkes();
        return rtn;
    }
    
    public int updatePgmLinkedFuncDetail(ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO, User user) throws Exception
    {
        int rtn = consultPgmLinkedFuncDetailDAO.updatePgmLinkedFuncDetail(consultPgmLinkedFuncDetailDTO, user);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadPageLinkes();
        return rtn;
    }

    public ConsultPgmLinkedFuncDetailDAO getConsultPgmLinkedFuncDetailDAO() {
        return consultPgmLinkedFuncDetailDAO;
    }

    public void setConsultPgmLinkedFuncDetailDAO(ConsultPgmLinkedFuncDetailDAO consultPgmLinkedFuncDetailDAO) {
        this.consultPgmLinkedFuncDetailDAO = consultPgmLinkedFuncDetailDAO;
    }
}
