package dream.work.rpt.pmwrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwrate.dto.WorkRptPmwDetailDTO;

/**
 * �ֱ����� ���� ���� �� ��� dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmwDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmwDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPmwDetailDTO workRptPmwDetailDTO, User loginUser) throws Exception;

    public String findTotalCount(WorkRptPmwDetailDTO workRptPmwDetailDTO, User loginUser) throws Exception;
    
}