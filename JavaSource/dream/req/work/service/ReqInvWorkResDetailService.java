package dream.req.work.service;

import common.bean.User;
import dream.req.work.dto.ReqInvWorkResDetailDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * 작업요청(투자) - 처리사항
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public interface ReqInvWorkResDetailService
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
     * @throws Exception
     */
    public ReqInvWorkResDetailDTO findDetail(ReqWorkResListDTO reqWorkResListDTO,User user)throws Exception;
}
