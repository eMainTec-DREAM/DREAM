package dream.part.rpt.maptcstchart.service;

import java.util.List;

import dream.part.rpt.maptcstchart.dto.MaPtCostChartDTO;

/**
 * ������м� service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPtCostChartService
{     
    /**
     *  ������м� grid find
     * @author  
     * @version $Id: $
     * @param maPtCostChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaPtCostChartDTO maPtCostChartDTO);
    
    /**
     *  ������м� chart find
     * @author  
     * @version $Id: $
     * @param maPtCostChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findRecChart(MaPtCostChartDTO maPtCostChartDTO);    
    
    /**
     *  ������м� chart find
     * @author  
     * @version $Id: $
     * @param maPtCostChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findRepairChart(MaPtCostChartDTO maPtCostChartDTO);    
    
    /**
     *  ������м� chart find
     * @author  
     * @version $Id: $
     * @param maPtCostChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findMonthTotalChart(MaPtCostChartDTO maPtCostChartDTO);    
}
