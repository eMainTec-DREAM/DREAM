package dream.work.rpt.work.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpMonthDTO;

/**
 * ����ں��۾���Ȳ - ����� �����۾���Ȳ �� ��� dao 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptWorkTypeRptByEmpMonthDAO
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