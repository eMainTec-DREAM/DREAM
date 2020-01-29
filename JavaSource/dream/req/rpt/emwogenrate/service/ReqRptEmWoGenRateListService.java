package dream.req.rpt.emwogenrate.service;

import java.util.List;

import common.bean.User;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;

/**
 * 사후 작업오더 발생률 목록 - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface ReqRptEmWoGenRateListService
{
    /**
     * FIND LIST
     * @param reqRptEmWoGenRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptEmWoGenRateCommonDTO
     * @return
     */
    public String findTotalCount(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User user) throws Exception;
}
