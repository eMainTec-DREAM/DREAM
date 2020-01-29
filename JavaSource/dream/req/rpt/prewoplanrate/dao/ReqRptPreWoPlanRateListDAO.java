package dream.req.rpt.prewoplanrate.dao;

import java.util.List;

import common.bean.User;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanRateCommonDTO;

/**
 * �۾����� ���� ��ȹ ������ ��� - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ReqRptPreWoPlanRateListDAO
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
     * FIND TOTAL LIST
     * @param reqRptPreWoPlanRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO, User user) throws Exception;
    
}
