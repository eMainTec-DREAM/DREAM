package dream.work.rpt.org.mtbfmttr.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrCommonDTO;

/**
 * 조직별MTBF,MTTR 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptOrgMtbfmttrListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgMtbfmttrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO, User loginUser);

    public String findTotalCount(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO, User user);
    
}