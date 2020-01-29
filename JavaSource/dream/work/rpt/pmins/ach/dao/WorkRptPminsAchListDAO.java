package dream.work.rpt.pmins.ach.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchCommonDTO;

/**
 * 예방점검 이행율(담당자) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsAchListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsAchCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO, User loginUser);

    public String findTotalCount(WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO, User user);
    
}