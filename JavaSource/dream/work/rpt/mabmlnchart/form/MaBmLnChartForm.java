package dream.work.rpt.mabmlnchart.form;

import common.struts.BaseForm;
import dream.work.rpt.mabmlnchart.dto.MaBmLnChartDTO;

/**
 * 라인고장분석 FORM
 * @author  kim21017
 * @version $Id: MaBmLnChartForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maBmLnChartForm"
 */
public class MaBmLnChartForm extends BaseForm
{    
    private MaBmLnChartDTO maBmLnChartDTO = new MaBmLnChartDTO();

	public MaBmLnChartDTO getMaBmLnChartDTO() {
		return maBmLnChartDTO;
	}

	public void setMaBmLnChartDTO(MaBmLnChartDTO maBmLnChartDTO) {
		this.maBmLnChartDTO = maBmLnChartDTO;
	}

}
