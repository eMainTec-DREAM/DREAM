package dream.work.rpt.pmwrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwrate.dto.WorkRptPmwDetailDTO;

/**
 * �ֱ����� ���� ���� �� ���
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmwDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmwDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(WorkRptPmwDetailDTO workRptPmwDetailDTO, User loginUser) throws Exception;

    public String findTotalCount(WorkRptPmwDetailDTO workRptPmwDetailDTO, User loginUser) throws Exception;
    
}
