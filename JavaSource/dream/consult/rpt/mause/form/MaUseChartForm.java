package dream.consult.rpt.mause.form;

import common.struts.BaseForm;
import dream.consult.rpt.mause.dto.MaUseChartDTO;

/**
 * 사용현황 FORM
 * @author  kim21017
 * @version $Id: MaUseChartForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maUseChartForm"
 */
public class MaUseChartForm extends BaseForm
{    
    private MaUseChartDTO maUseChartDTO = new MaUseChartDTO();

	public MaUseChartDTO getMaUseChartDTO() {
		return maUseChartDTO;
	}

	public void setMaUseChartDTO(MaUseChartDTO maUseChartDTO) {
		this.maUseChartDTO = maUseChartDTO;
	}

}
