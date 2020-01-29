package dream.work.rpt.pmins.orgach.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchDetailDTO;

/**
 * 예방점검 이행율(조직) 상세 목록
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsOrgAchDetailService
{     
    /**
     *  grid find
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsOrgAchDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO, User loginUser);

    public String findTotalCount(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO, User user);
    
}
