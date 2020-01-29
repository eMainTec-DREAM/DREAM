package dream.work.rpt.mapmtrend.form;

import common.struts.BaseForm;
import dream.work.rpt.mapmtrend.dto.MaPmTrendChartDTO;

/**
 * 예방점검경향분석 FORM
 * @author  kim21017
 * @version $Id: MaPmTrendChartForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmTrendChartForm"
 */
public class MaPmTrendChartForm extends BaseForm
{    
    private MaPmTrendChartDTO maPmTrendChartDTO = new MaPmTrendChartDTO();

	public MaPmTrendChartDTO getMaPmTrendChartDTO() {
		return maPmTrendChartDTO;
	}

	public void setMaPmTrendChartDTO(MaPmTrendChartDTO maPmTrendChartDTO) {
		this.maPmTrendChartDTO = maPmTrendChartDTO;
	}
 
}
