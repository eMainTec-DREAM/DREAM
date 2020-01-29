package dream.work.rpt.pm.monthnew.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pm.monthnew.dto.WorkRptPmMonthNewListDTO;

/**
 * 신규점검등록현황 service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WorkRptPmMonthNewListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param workRptPmMonthNewListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findConnList(WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO, User loginUser);    
}
