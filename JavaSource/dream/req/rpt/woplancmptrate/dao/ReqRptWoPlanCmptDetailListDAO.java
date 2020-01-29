package dream.req.rpt.woplancmptrate.dao;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptDetailListDTO;

/**
 * �۾��Ƿ� �ʱ��ȹ ��û ��� - List DAO
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 *
 */
public interface ReqRptWoPlanCmptDetailListDAO
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
     * FIND TOTAL LIST
     * @param reqRptWoPlanCmptDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO, User user) throws Exception;
    
}
