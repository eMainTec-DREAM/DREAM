package dream.part.rpt.maptcstchart.service.spring;

import java.util.List;

import dream.part.rpt.maptcstchart.dao.MaPtCostChartDAO;
import dream.part.rpt.maptcstchart.dto.MaPtCostChartDTO;
import dream.part.rpt.maptcstchart.service.MaPtCostChartService;

/**
 * 자재비용분석 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maPtCostChartServiceTarget"
 * @spring.txbn id="maPtCostChartService"
 * @spring.property name="maPtCostChartDAO" ref="maPtCostChartDAO"
 */
public class MaPtCostChartServiceImpl implements MaPtCostChartService
{
    private MaPtCostChartDAO maPtCostChartDAO = null;

    public MaPtCostChartDAO getMaPtCostChartDAO() 
    {
		return maPtCostChartDAO;
	}

	public void setMaPtCostChartDAO(MaPtCostChartDAO maPtCostChartDAO) 
	{
		this.maPtCostChartDAO = maPtCostChartDAO;
	}

	public List findList(MaPtCostChartDTO maPtCostChartDTO)
    {      
        return maPtCostChartDAO.findList(maPtCostChartDTO);
    }
	
	public List findRecChart(MaPtCostChartDTO maPtCostChartDTO)
    {      
        return maPtCostChartDAO.findRecChart(maPtCostChartDTO);
    }
	
	public List findRepairChart(MaPtCostChartDTO maPtCostChartDTO)
	{      
	    return maPtCostChartDAO.findRepairChart(maPtCostChartDTO);
	}
	
	public List findMonthTotalChart(MaPtCostChartDTO maPtCostChartDTO)
	{      
	    return maPtCostChartDAO.findMonthTotalChart(maPtCostChartDTO);
	}
}