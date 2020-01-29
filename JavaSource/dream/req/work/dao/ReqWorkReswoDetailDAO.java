package dream.req.work.dao;

import common.bean.User;
import dream.req.work.dto.ReqWorkReswoDetailDTO;

/**
 * 작업요청-처리사항 상세 dao
 * @author  kim21017
 * @version $Id: ReqWorkReswoDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface ReqWorkReswoDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ReqWorkReswoDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param woDayListId
     * @param woDayActId
     * @param compNo
     * @return
     */
    public ReqWorkReswoDetailDTO findDetail(String woReqId, String woReqResId, User user);

}