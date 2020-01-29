package dream.req.work.dao;

import common.bean.User;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResDetailDTO;

/**
 * 작업요청-처리사항 상세 dao
 * @author  kim21017
 * @version $Id: ReqWorkResDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface ReqWorkResDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ReqWorkResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param woDayListId
     * @param woDayActId
     * @param compNo
     * @return
     */
    public ReqWorkResDetailDTO findDetail(String woReqId, String woReqResId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: ReqWorkResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param reqWorkResDetailDTO
     * @param reqWorkCommonDTO
     * @return
     */
    public int updateDetail(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: ReqWorkResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param reqWorkResDetailDTO
     * @param reqWorkCommonDTO
     * @return
     */
    public int insertDetail(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO);

    public int changeHdrStatus(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO);
}