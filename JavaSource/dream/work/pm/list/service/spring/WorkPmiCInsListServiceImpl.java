package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmiCInsListDAO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.service.WorkPmiCInsListService;

/**
 * WorkPmiCIns Page - List Service implements
 * @author youngjoo38
 * @version $Id: WorkPmiCInsListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmiCInsListServiceTarget"
 * @spring.txbn id="workPmiCInsListService"
 * @spring.property name="workPmiCInsListDAO" ref="workPmiCInsListDAO"
 */
public class WorkPmiCInsListServiceImpl implements WorkPmiCInsListService
{
    private WorkPmiCInsListDAO workPmiCInsListDAO = null;

    public List findList(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception
    {      
        return workPmiCInsListDAO.findList(workPmiCInsCommonDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmiCInsListDAO.deleteList(id, user);
            }
        return result;
    }

    public WorkPmiCInsListDAO getWorkPmiCInsListDAO() {
        return workPmiCInsListDAO;
    }

    public void setWorkPmiCInsListDAO(WorkPmiCInsListDAO workPmiCInsListDAO) {
        this.workPmiCInsListDAO = workPmiCInsListDAO;
    }    
    
    public String findTotalCount(WorkPmiCInsCommonDTO workPmiCInsCommonDTO,User user)  throws Exception
    {
        return workPmiCInsListDAO.findTotalCount(workPmiCInsCommonDTO, user);
    }
}
