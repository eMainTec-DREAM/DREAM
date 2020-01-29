package dream.work.rpt.org.bd.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;

/**
 * ������ ����м� ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptOrgBdListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgBdCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO, User loginUser);

    public String findTotalCount(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO, User user);
    
}
