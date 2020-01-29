package dream.work.rpt.org.mtbfmttr.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrDetailDTO;

/**
 * ������MTBF,MTTR �� ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptOrgMtbfmttrDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgMtbfmttrDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO, User loginUser);
    
    public String findTotalCount(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO, User user);
}