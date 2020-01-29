package dream.part.rpt.stockkpi.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.stockkpi.dto.StockKpiCommonDTO;

/**
 * 재고지표 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface StockKpiListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param stockKpiCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(StockKpiCommonDTO stockKpiCommonDTO, User loginUser);

    public String findTotalCount(StockKpiCommonDTO stockKpiCommonDTO, User user);
    
}
