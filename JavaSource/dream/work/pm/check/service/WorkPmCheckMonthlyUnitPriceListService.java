package dream.work.pm.check.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;

/**
 * WorkPmCheckMonthlyUnitPrice Page - List Service
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmCheckMonthlyUnitPriceListService
{
    /**
     * FIND LIST
     * @param workPmCheckMonthlyUnitPriceListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkPmCheckCommonDTO workPmCheckCommonDTO, WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user) throws Exception;
    /**
     * DELETE LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmCheckMonthlyUnitPriceListDTO
     * @return
     */
    public String findTotalCount(WorkPmCheckCommonDTO workPmCheckCommonDTO, WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user) throws Exception;
}
