package dream.req.rpt.prewoplanrate.service;

import java.util.List;

import common.bean.User;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanDetailListDTO;

/**
 * 작업오더 사전 계획 수립률 목록 - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface ReqRptPreWoPlanDetailListService
{
    /**
     * FIND LIST
     * @param reqRptPreWoPlanDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptPreWoPlanDetailListDTO
     * @return
     */
    public String findTotalCount(ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO, User user) throws Exception;
}
