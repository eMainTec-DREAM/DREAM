package dream.work.rpt.mawotype.form;

import common.struts.BaseForm;
import dream.work.rpt.mawotype.dto.MaWoTypeChartDTO;

/**
 * 작업유형별현황 FORM
 * @author  kim21017
 * @version $Id: MaWoTypeChartForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoTypeChartForm"
 */
public class MaWoTypeChartForm extends BaseForm
{    
    private MaWoTypeChartDTO maWoTypeChartDTO = new MaWoTypeChartDTO();

	public MaWoTypeChartDTO getMaWoTypeChartDTO() {
		return maWoTypeChartDTO;
	}

	public void setMaWoTypeChartDTO(MaWoTypeChartDTO maWoTypeChartDTO) {
		this.maWoTypeChartDTO = maWoTypeChartDTO;
	}

}
