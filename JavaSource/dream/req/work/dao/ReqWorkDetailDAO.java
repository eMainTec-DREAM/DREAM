package dream.req.work.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;

/**
 * 작업요청 - 상세 dao
 *
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 */
public interface ReqWorkDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkCommonDTO
     * @param user
     * @return
     */
    public ReqWorkDetailDTO findDetail(ReqWorkCommonDTO reqWorkCommonDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user);

    public int insertDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user);

    public int updateStatus(ReqWorkDetailDTO reqWorkDetailDTO, User user);

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);
}