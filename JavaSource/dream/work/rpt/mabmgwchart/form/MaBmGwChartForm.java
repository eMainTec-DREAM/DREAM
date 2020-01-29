package dream.work.rpt.mabmgwchart.form;

import common.struts.BaseForm;
import dream.work.rpt.mabmgwchart.dto.MaBmGwChartDTO;

/**
 * 과별고장분석 FORM
 * @author  kim21017
 * @version $Id: MaBmGwChartForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maBmGwChartForm"
 */
public class MaBmGwChartForm extends BaseForm
{    
    private MaBmGwChartDTO maBmGwChartDTO = new MaBmGwChartDTO();

	public MaBmGwChartDTO getMaBmGwChartDTO() {
		return maBmGwChartDTO;
	}

	public void setMaBmGwChartDTO(MaBmGwChartDTO maBmGwChartDTO) {
		this.maBmGwChartDTO = maBmGwChartDTO;
	}

}
