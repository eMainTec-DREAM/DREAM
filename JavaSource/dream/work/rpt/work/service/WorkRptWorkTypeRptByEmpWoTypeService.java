package dream.work.rpt.work.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpWoTypeDTO;

/**
 * 담당자별작업현황 - 담당자 작업종류별현황 탭 목록 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptWorkTypeRptByEmpWoTypeService
{     
    /**
     * 담당자 작업종류별현황 grid find
     * @author js.lee
     * @since   1.0
     *
     * @param workRptWorkTypeRptByEmpWoTypeDTO
     * @param user
     * @return
     */
    public List findWoTypeList(WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO, User user);
    
    public String findTotalCount(WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO, User user);
    
}
