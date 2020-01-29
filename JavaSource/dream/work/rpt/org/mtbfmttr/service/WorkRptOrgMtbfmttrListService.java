package dream.work.rpt.org.mtbfmttr.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrCommonDTO;

/**
 * 조직별MTBF,MTTR 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptOrgMtbfmttrListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgMtbfmttrCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO, User loginUser);

    public String findTotalCount(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO, User user);
    
}
