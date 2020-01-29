package dream.part.rpt.maptcstchart.service;

import java.util.List;

import dream.part.rpt.maptcstchart.dto.MaPtCostChartDTO;

/**
 * 자재비용분석 service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPtCostChartService
{     
    /**
     *  자재비용분석 grid find
     * @author  
     * @version $Id: $
     * @param maPtCostChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPtCostChartDTO maPtCostChartDTO);
    
    /**
     *  자재비용분석 chart find
     * @author  
     * @version $Id: $
     * @param maPtCostChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findRecChart(MaPtCostChartDTO maPtCostChartDTO);    
    
    /**
     *  자재비용분석 chart find
     * @author  
     * @version $Id: $
     * @param maPtCostChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findRepairChart(MaPtCostChartDTO maPtCostChartDTO);    
    
    /**
     *  자재비용분석 chart find
     * @author  
     * @version $Id: $
     * @param maPtCostChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findMonthTotalChart(MaPtCostChartDTO maPtCostChartDTO);    
}
