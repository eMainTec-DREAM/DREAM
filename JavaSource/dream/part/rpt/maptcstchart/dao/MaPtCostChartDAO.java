package dream.part.rpt.maptcstchart.dao;

import java.util.List;

import common.util.QueryBuffer;
import dream.part.rpt.maptcstchart.dto.MaPtCostChartDTO;

/**
 * 자재비용분석 DAO
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPtCostChartDAO
{
    /**
     * 과별종합분석 find grid
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findList(MaPtCostChartDTO maPtCostChartDTO);
    
    /**
     * 입고금액 find chart
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findRecChart(MaPtCostChartDTO maPtCostChartDTO);
    
    /**
     * 수리금액 find chart
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findRepairChart(MaPtCostChartDTO maPtCostChartDTO);
    /**
     * 월별 합계 find chart
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findMonthTotalChart(MaPtCostChartDTO maPtCostChartDTO);
}