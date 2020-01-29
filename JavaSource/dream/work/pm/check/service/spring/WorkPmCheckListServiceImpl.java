package dream.work.pm.check.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.check.dao.WorkPmCheckListDAO;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.service.WorkPmCheckListService;

/**
 * WorkPmCheck Page - List Service implements
 * @author youngjoo38
 * @version $Id: WorkPmCheckListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmCheckListServiceTarget"
 * @spring.txbn id="workPmCheckListService"
 * @spring.property name="workPmCheckListDAO" ref="workPmCheckListDAO"
 */
public class WorkPmCheckListServiceImpl implements WorkPmCheckListService
{
    private WorkPmCheckListDAO workPmCheckListDAO = null;

    public List findList(WorkPmCheckCommonDTO workPmCheckCommonDTO, User user) throws Exception
    {      
        return workPmCheckListDAO.findList(workPmCheckCommonDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmCheckListDAO.deleteList(id, user);
            }
        return result;
    }

    public WorkPmCheckListDAO getWorkPmCheckListDAO() {
        return workPmCheckListDAO;
    }

    public void setWorkPmCheckListDAO(WorkPmCheckListDAO workPmCheckListDAO) {
        this.workPmCheckListDAO = workPmCheckListDAO;
    }    
    
    public String findTotalCount(WorkPmCheckCommonDTO workPmCheckCommonDTO,User user)  throws Exception
    {
        return workPmCheckListDAO.findTotalCount(workPmCheckCommonDTO, user);
    }
}
