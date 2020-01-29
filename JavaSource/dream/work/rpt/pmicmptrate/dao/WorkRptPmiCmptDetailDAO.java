package dream.work.rpt.pmicmptrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptDetailDTO;

/**
 * 고장TOP(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmiCmptDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiCmptDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO, User loginUser) throws Exception;

    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiCmptDetailDTO
     * @return
     */
    public String findTotalCount(WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO, User user) throws Exception;  
}