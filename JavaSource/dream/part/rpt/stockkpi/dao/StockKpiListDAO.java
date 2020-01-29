package dream.part.rpt.stockkpi.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.stockkpi.dto.StockKpiCommonDTO;

/**
 * 재고지표 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface StockKpiListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param stockKpiCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(StockKpiCommonDTO stockKpiCommonDTO, User loginUser);

    public String findTotalCount(StockKpiCommonDTO stockKpiCommonDTO, User user);
    
}