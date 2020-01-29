package dream.req.rpt.emwogenrate.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;

/**
 * �۾��Ƿ� �۾������� �� ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ReqRptEmWoGenDetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptEmWoGenRateCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User loginUser) throws Exception;
    
    public String findTotalCount(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User user)  throws Exception;
	
    
}
