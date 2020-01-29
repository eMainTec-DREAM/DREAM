package dream.req.rpt.woplancmptrate.service;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptDetailListDTO;

/**
 * 작업의뢰 초기계획 요청 목록 - List Service
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public interface ReqRptWoPlanCmptDetailListService
{
    /**
     * FIND LIST
     * @param reqRptWoPlanCmptDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptWoPlanCmptDetailListDTO
     * @return
     */
    public String findTotalCount(ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO, User user) throws Exception;
}
