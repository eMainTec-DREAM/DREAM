package dream.work.rpt.mabmctgchart.form;

import common.struts.BaseForm;
import dream.work.rpt.mabmctgchart.dto.MaBmCtgChartDTO;

/**
 * 설비종류별고장분석 FORM
 * @author  kim21017
 * @version $Id: MaBmCtgChartForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maBmCtgChartForm"
 */
public class MaBmCtgChartForm extends BaseForm
{    
    private MaBmCtgChartDTO maBmCtgChartDTO = new MaBmCtgChartDTO();

	public MaBmCtgChartDTO getMaBmCtgChartDTO() {
		return maBmCtgChartDTO;
	}

	public void setMaBmCtgChartDTO(MaBmCtgChartDTO maBmCtgChartDTO) {
		this.maBmCtgChartDTO = maBmCtgChartDTO;
	}

}
