package dream.work.rpt.wopmwcmptrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptDetailDTO;

/**
 * �����۾� ��ȹ��� ���� ���� �� ���
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptWoPmwCmptDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptWoPmwCmptDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO, User loginUser) throws Exception;

    public String findTotalCount(WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO, User loginUser) throws Exception;
    
}
