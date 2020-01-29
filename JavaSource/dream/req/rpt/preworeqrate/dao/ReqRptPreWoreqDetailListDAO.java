package dream.req.rpt.preworeqrate.dao;

import java.util.List;

import common.bean.User;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqDetailListDTO;

/**
 * 작업의뢰 시스템 요청 목록 - List DAO
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ReqRptPreWoreqDetailListDAO
{
    /**
     * FIND LIST
     * @param reqRptPreWoreqDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param reqRptPreWoreqDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO, User user) throws Exception;
    
}
