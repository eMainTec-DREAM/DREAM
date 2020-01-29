package dream.work.rpt.org.bd.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdDetailDTO;

/**
 * 조직별 고장분석 상세 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptOrgBdDetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgBdDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO, User loginUser);
    
    public String findTotalCount(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO, User user);
}
