package dream.req.rpt.preworeqrate.service;

import java.util.List;

import common.bean.User;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqRateCommonDTO;

/**
 * �۾��Ƿ� ���� �ý��� ��û�� ��� - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface ReqRptPreWoreqRateListService
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
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptPreWoreqRateCommonDTO
     * @return
     */
    public String findTotalCount(ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO, User user) throws Exception;
}
