package dream.work.rpt.pm.monthnew.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pm.monthnew.dto.WorkRptPmMonthNewListDTO;

/**
 * 신규점검등록현황 DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WorkRptPmMonthNewListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workRptPmMonthNewListDTO
     * @return List
     */
    public List findConnList(WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO, User loginUser);
    
}