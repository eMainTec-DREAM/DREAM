package dream.work.rpt.work.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpWoTypeDTO;

/**
 * ����ں��۾���Ȳ - ����� �۾���������Ȳ �� ��� 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptWorkTypeRptByEmpWoTypeService
{     
    /**
     * ����� �۾���������Ȳ grid find
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
