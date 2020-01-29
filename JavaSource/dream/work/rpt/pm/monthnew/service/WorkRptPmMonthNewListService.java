package dream.work.rpt.pm.monthnew.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pm.monthnew.dto.WorkRptPmMonthNewListDTO;

/**
 * �ű����˵����Ȳ service
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
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findConnList(WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO, User loginUser);    
}
