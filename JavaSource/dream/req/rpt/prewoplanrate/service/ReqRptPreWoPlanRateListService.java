package dream.req.rpt.prewoplanrate.service;

import java.util.List;

import common.bean.User;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanRateCommonDTO;

/**
 * 작업오더 사전 계획 수립률 목록 - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface ReqRptPreWoPlanRateListService
{
    /**
     * FIND LIST
     * @param reqRptPreWoPlanRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptPreWoPlanRateCommonDTO
     * @return
     */
    public String findTotalCount(ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO, User user) throws Exception;
}
