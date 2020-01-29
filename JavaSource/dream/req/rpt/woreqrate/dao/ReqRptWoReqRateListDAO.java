package dream.req.rpt.woreqrate.dao;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateCommonDTO;

/**
 * 夸没立荐啦(贸府磊) 格废 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ReqRptWoReqRateListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptWoReqRateCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO, User loginUser);

    public String findTotalCount(ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO, User user);
    
}