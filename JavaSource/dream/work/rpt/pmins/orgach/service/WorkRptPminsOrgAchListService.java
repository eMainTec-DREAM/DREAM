package dream.work.rpt.pmins.orgach.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchCommonDTO;

/**
 * �������� ������(����) ���
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsOrgAchListService
{     
    /**
     *  grid find
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsOrgAchCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO, User loginUser);

    public String findTotalCount(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO, User user);
}
