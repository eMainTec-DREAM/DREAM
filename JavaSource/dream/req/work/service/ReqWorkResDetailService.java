package dream.req.work.service;

import common.bean.User;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResDetailDTO;

/**
 * 작업요청 - 처리사항
 * @author  kim210117
 * @version $Id: ReqWorkResDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface ReqWorkResDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ReqWorkResDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     * @throws Exception
     */
    public ReqWorkResDetailDTO findDetail(String woReqId, String woReqResId,User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: ReqWorkResDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param reqWorkResDetailDTO
     * @param reqWorkCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ReqWorkResDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param reqWorkResDetailDTO
     * @param reqWorkCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO) throws Exception;
}
