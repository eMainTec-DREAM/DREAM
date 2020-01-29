package dream.work.rpt.pmins.ach.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchDetailDTO;

/**
 * �������� ������(�����) �� ��� dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsAchDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$   
     * @since   1.0
     * 
     * @param workRptPminsAchDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO, User loginUser);
 
    public String findTotalCount(WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO, User user);
}