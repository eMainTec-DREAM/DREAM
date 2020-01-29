package dream.work.rpt.wopmwcmptrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptRateCommonDTO;

/**
 * 예방작업 계획대비 실행 비율 목록 - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptWoPmwCmptRateListDAO
{
    /**
     * FIND LIST
     * @param workRptWoPmwCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param workRptWoPmwCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO, User user) throws Exception;
    
}
