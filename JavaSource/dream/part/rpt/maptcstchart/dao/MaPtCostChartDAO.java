package dream.part.rpt.maptcstchart.dao;

import java.util.List;

import common.util.QueryBuffer;
import dream.part.rpt.maptcstchart.dto.MaPtCostChartDTO;

/**
 * ������м� DAO
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPtCostChartDAO
{
    /**
     * �������պм� find grid
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findList(MaPtCostChartDTO maPtCostChartDTO);
    
    /**
     * �԰�ݾ� find chart
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findRecChart(MaPtCostChartDTO maPtCostChartDTO);
    
    /**
     * �����ݾ� find chart
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findRepairChart(MaPtCostChartDTO maPtCostChartDTO);
    /**
     * ���� �հ� find chart
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findMonthTotalChart(MaPtCostChartDTO maPtCostChartDTO);
}