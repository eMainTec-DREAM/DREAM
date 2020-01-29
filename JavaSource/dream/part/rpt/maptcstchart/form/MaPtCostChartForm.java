package dream.part.rpt.maptcstchart.form;

import common.struts.BaseForm;
import dream.part.rpt.maptcstchart.dto.MaPtCostChartDTO;

/**
 * 자재비용분석 FORM
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPtCostChartForm"
 */
public class MaPtCostChartForm extends BaseForm
{    
    private MaPtCostChartDTO maPtCostChartDTO = new MaPtCostChartDTO();

	public MaPtCostChartDTO getMaPtCostChartDTO() {
		return maPtCostChartDTO;
	}

	public void setMaPtCostChartDTO(MaPtCostChartDTO maPtCostChartDTO) {
		this.maPtCostChartDTO = maPtCostChartDTO;
	}

}
