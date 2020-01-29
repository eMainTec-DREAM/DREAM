package dream.req.work.service;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;

/**
 * »ó¼¼ service
 *
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface ReqWorkDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param compNo
     * @param ptRecListId
     * @return
     * @throws Exception
     */
    public ReqWorkDetailDTO findDetail(ReqWorkCommonDTO reqWorkCommonDTO, User loginUser)throws Exception;


    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user) throws Exception;
    public int insertDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user) throws Exception;


    public int updateStatus(ReqWorkDetailDTO reqWorkDetailDTO, User user) throws Exception;


    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user);


}
