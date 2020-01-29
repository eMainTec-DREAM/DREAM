package dream.work.rpt.wopmwcmptrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptDetailDTO;

/**
 * 예방작업 계획대비 실행 비율 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptWoPmwCmptDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptWoPmwCmptDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO, User loginUser) throws Exception;

    public String findTotalCount(WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO, User loginUser) throws Exception;
    
}