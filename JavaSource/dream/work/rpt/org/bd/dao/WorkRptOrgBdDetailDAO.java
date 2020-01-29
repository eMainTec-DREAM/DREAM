package dream.work.rpt.org.bd.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdDetailDTO;

/**
 * ������ ����м� �� ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptOrgBdDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgBdDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO, User loginUser);
    
    public String findTotalCount(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO, User user);
}