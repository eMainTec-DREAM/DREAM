package dream.req.rpt.preworeqrate.dao;

import java.util.List;

import common.bean.User;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqRateCommonDTO;

/**
 * 작업의뢰 사전 시스템 요청율 목록 - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ReqRptPreWoreqRateListDAO
{
    /**
     * FIND LIST
     * @param reqRptPreWoreqRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param reqRptPreWoreqRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO, User user) throws Exception;
    
}
