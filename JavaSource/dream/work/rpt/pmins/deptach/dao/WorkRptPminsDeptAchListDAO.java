package dream.work.rpt.pmins.deptach.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchCommonDTO;

/**
 * �������� ������(�μ�) ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsDeptAchListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsDeptAchCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO, User loginUser);

    public String findTotalCount(WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO, User user);
    
}