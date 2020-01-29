package dream.req.work.dao;

import common.bean.User;
import dream.req.work.dto.ReqInvWorkResDetailDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * 작업요청-처리사항 상세(투자) dao
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public interface ReqInvWorkResDetailDAO
{
    /**
     * detail find
     * @author js.lee
     * @version $Id: $
     * @since   1.0
     *
     * @param reqWorkResListDTO
	 * @param user
	 * @return
     */
    public ReqInvWorkResDetailDTO findDetail(ReqWorkResListDTO reqWorkResListDTO, User user);

}