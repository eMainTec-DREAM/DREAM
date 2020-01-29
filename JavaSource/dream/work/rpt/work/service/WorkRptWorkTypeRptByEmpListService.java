package dream.work.rpt.work.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpCommonDTO;

/**
 * 담당자별작업현황 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptWorkTypeRptByEmpListService
{     
	/**
	 * grid find
	 * @author js.lee
	 * @since   1.0
	 *
	 * @param workRptWorkTypeRptByEmpCommonDTO
	 * @param user
	 * @return
	 */
    public List findList(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO, User user);
    
    public String findTotalCount(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO, User user);
    
}
