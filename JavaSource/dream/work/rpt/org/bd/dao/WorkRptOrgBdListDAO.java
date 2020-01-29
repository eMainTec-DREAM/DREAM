package dream.work.rpt.org.bd.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;

/**
 * 조직별 고장분석 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptOrgBdListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgBdCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO, User loginUser);

    public String findTotalCount(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO, User user);
    
}