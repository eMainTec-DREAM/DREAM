package dream.work.rpt.pmicmptrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptDetailDTO;

/**
 * ����TOP(��ġ) �� ���
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmiCmptDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiCmptDetailDTO
     * @param loginUser
     * @throws Exception
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
