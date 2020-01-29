package dream.work.rpt.org.mtbfmttr.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrDetailDTO;

/**
 * ������MTBF,MTTR �� ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptOrgMtbfmttrDetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgMtbfmttrDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO, User loginUser);
    
    public String findTotalCount(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO, User user);
}
