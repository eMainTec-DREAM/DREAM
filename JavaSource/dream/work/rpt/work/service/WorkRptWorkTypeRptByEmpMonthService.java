package dream.work.rpt.work.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpMonthDTO;

/**
 * 담당자별작업현황 - 담당자 월별작업현황 탭 목록 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptWorkTypeRptByEmpMonthService
{     
	/**
	 * 담당자 월별작업현황 grid find
	 * @author js.lee
	 * @since   1.0
	 *
	 * @param workRptWorkTypeRptByEmpMonthDTO
	 * @param user
	 * @return
	 */
    public List findMonthList(WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO, User user);
    
    public String findTotalCount(WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO, User user);
}
