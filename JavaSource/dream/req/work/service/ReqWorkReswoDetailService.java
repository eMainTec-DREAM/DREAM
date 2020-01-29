package dream.req.work.service;

import common.bean.User;
import dream.req.work.dto.ReqWorkReswoDetailDTO;

/**
 * 작업요청 - 처리사항
 * @author  kim210117
 * @version $Id: ReqWorkReswoDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface ReqWorkReswoDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: ReqWorkReswoDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     * @throws Exception
     */
    public ReqWorkReswoDetailDTO findDetail(String woReqId, String woReqResId,User user)throws Exception;
}
