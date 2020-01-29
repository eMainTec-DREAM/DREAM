package dream.consult.program.linkedfunc.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.linkedfunc.dao.ConsultPgmLinkedFuncListDAO;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;
import dream.consult.program.linkedfunc.service.ConsultPgmLinkedFuncListService;

/**
 * PgmLinkedFunc Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="consultPgmLinkedFuncListServiceTarget"
 * @spring.txbn id="consultPgmLinkedFuncListService"
 * @spring.property name="consultPgmLinkedFuncListDAO" ref="consultPgmLinkedFuncListDAO"
 */
public class ConsultPgmLinkedFuncListServiceImpl implements ConsultPgmLinkedFuncListService
{
    private ConsultPgmLinkedFuncListDAO consultPgmLinkedFuncListDAO = null;

    public List findPgmLinkedFuncList(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user) throws Exception
    {      
        return consultPgmLinkedFuncListDAO.findPgmLinkedFuncList(consultPgmLinkedFuncCommonDTO,user);
    }

    public int deletePgmLinkedFuncList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultPgmLinkedFuncListDAO.deletePgmLinkedFuncList(id, user);
            }
        return result;
    }

    public ConsultPgmLinkedFuncListDAO getConsultPgmLinkedFuncListDAO() {
        return consultPgmLinkedFuncListDAO;
    }

    public void setConsultPgmLinkedFuncListDAO(ConsultPgmLinkedFuncListDAO consultPgmLinkedFuncListDAO) {
        this.consultPgmLinkedFuncListDAO = consultPgmLinkedFuncListDAO;
    }    
    
    public String findTotalCount(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO,User user)  throws Exception
    {
        return consultPgmLinkedFuncListDAO.findTotalCount(consultPgmLinkedFuncCommonDTO, user);
    }
}
