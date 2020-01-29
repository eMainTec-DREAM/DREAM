package dream.req.rpt.woreqrate.service;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateDetailDTO;

/**
 * ��û������(ó����) �� ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ReqRptWoReqRateDetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptWoReqRateDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(ReqRptWoReqRateDetailDTO reqRptWoReqRateDetailDTO, User loginUser);
}
