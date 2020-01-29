package dream.work.rpt.pmwcmptrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptDetailDTO;

/**
 * �ֱ����� ��ȹ��� ���� ���� �� ���
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
