package dream.req.rpt.woreqrate.dao;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateDetailDTO;

/**
 * ��û������(ó����) �� ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ReqRptWoReqRateDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$   
     * @since   1.0
     * 
     * @param reqRptWoReqRateDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(ReqRptWoReqRateDetailDTO reqRptWoReqRateDetailDTO, User loginUser);
}