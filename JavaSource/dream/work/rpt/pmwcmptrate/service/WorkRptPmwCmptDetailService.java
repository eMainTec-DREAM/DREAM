package dream.work.rpt.pmwcmptrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptDetailDTO;

/**
 * 주기정비 계획대비 실행 비율 상세 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmwCmptDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmwCmptDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO, User loginUser) throws Exception;

    public String findTotalCount( WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO, User loginUser) throws Exception;
    
}
