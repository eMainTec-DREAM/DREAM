package dream.work.rpt.maeqwochart.form;

import common.struts.BaseForm;
import dream.work.rpt.maeqwochart.dto.MaEqWoChartDTO;

/**
 * 설비작업현황 FORM
 * @author  kim21017
 * @version $Id: MaEqWoChartForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqWoChartForm"
 */
public class MaEqWoChartForm extends BaseForm
{    
    private MaEqWoChartDTO maEqWoChartDTO = new MaEqWoChartDTO();

	public MaEqWoChartDTO getMaEqWoChartDTO() {
		return maEqWoChartDTO;
	}

	public void setMaEqWoChartDTO(MaEqWoChartDTO maEqWoChartDTO) {
		this.maEqWoChartDTO = maEqWoChartDTO;
	}

}
