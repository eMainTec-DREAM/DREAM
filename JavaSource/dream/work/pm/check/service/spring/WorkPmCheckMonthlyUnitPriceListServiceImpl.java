package dream.work.pm.check.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.check.dao.WorkPmCheckMonthlyUnitPriceListDAO;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;
import dream.work.pm.check.service.WorkPmCheckMonthlyUnitPriceListService;

/**
 * WorkPmCheckMonthlyUnitPrice Page - List Service implements
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmCheckMonthlyUnitPriceListServiceTarget"
 * @spring.txbn id="workPmCheckMonthlyUnitPriceListService"
 * @spring.property name="workPmCheckMonthlyUnitPriceListDAO" ref="workPmCheckMonthlyUnitPriceListDAO"
 */
public class WorkPmCheckMonthlyUnitPriceListServiceImpl implements WorkPmCheckMonthlyUnitPriceListService
{
    private WorkPmCheckMonthlyUnitPriceListDAO workPmCheckMonthlyUnitPriceListDAO = null;

    public List findList(WorkPmCheckCommonDTO workPmCheckCommonDTO, WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user) throws Exception
    {      
        return workPmCheckMonthlyUnitPriceListDAO.findList(workPmCheckCommonDTO,workPmCheckMonthlyUnitPriceListDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmCheckMonthlyUnitPriceListDAO.deleteList(id, user);
            }
        return result;
    }

    public WorkPmCheckMonthlyUnitPriceListDAO getWorkPmCheckMonthlyUnitPriceListDAO() {
        return workPmCheckMonthlyUnitPriceListDAO;
    }

    public void setWorkPmCheckMonthlyUnitPriceListDAO(WorkPmCheckMonthlyUnitPriceListDAO workPmCheckMonthlyUnitPriceListDAO) {
        this.workPmCheckMonthlyUnitPriceListDAO = workPmCheckMonthlyUnitPriceListDAO;
    }    
    
    public String findTotalCount(WorkPmCheckCommonDTO workPmCheckCommonDTO, WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user)  throws Exception
    {
        return workPmCheckMonthlyUnitPriceListDAO.findTotalCount(workPmCheckCommonDTO, workPmCheckMonthlyUnitPriceListDTO, user);
    }
}
