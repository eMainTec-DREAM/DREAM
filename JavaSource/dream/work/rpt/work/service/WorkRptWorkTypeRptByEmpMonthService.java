package dream.work.rpt.work.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpMonthDTO;

/**
 * ����ں��۾���Ȳ - ����� �����۾���Ȳ �� ��� 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptWorkTypeRptByEmpMonthService
{     
	/**
	 * ����� �����۾���Ȳ grid find
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
