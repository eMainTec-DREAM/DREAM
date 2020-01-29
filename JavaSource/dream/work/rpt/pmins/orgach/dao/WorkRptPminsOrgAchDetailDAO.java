package dream.work.rpt.pmins.orgach.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchDetailDTO;

/**
 * 예방점검 이행율(조직) 상세 목록 dao
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsOrgAchDetailDAO
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id:$   
     * @since   1.0
     * 
     * @param workRptPminsOrgAchDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO, User loginUser);

    public String findTotalCount(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO, User loginUser);
    
}