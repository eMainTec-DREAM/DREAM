package dream.work.rpt.pmins.orgach.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchCommonDTO;

/**
 * 예방점검 이행율(조직) 목록 dao
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsOrgAchListDAO
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsOrgAchCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO, User loginUser);

    public String findTotalCount(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO, User user);
    
}