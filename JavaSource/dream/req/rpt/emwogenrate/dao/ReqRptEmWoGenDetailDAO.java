package dream.req.rpt.emwogenrate.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;

/**
 * 작업의뢰 작업발행율 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ReqRptEmWoGenDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptEmWoGenRateCommonDTO
     * @param loginUser
     * @return List
     * @throws Exception 
     */
    public List findDetail(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User loginUser) throws Exception;
    
    public String findTotalCount(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User user)  throws Exception;
    
}