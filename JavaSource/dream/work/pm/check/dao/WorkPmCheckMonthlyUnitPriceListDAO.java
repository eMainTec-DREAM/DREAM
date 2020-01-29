package dream.work.pm.check.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;

/**
 * WorkPmCheckMonthlyUnitPrice Page - List DAO
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmCheckMonthlyUnitPriceListDAO
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
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * DELETE LIST
     * @param workPmCheckMonthlyUnitPriceListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkPmCheckCommonDTO workPmCheckCommonDTO, WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user) throws Exception;
    
}
