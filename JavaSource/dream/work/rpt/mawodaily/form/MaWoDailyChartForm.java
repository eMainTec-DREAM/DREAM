package dream.work.rpt.mawodaily.form;

import common.struts.BaseForm;
import dream.work.rpt.mawodaily.dto.MaWoDailyChartDTO;

/**
 * 일별작업실행율 FORM
 * @author  kim21017
 * @version $Id: MaWoDailyChartForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoDailyChartForm"
 */
public class MaWoDailyChartForm extends BaseForm
{    
    private MaWoDailyChartDTO maWoDailyChartDTO = new MaWoDailyChartDTO();

	public MaWoDailyChartDTO getMaWoDailyChartDTO() {
		return maWoDailyChartDTO;
	}

	public void setMaWoDailyChartDTO(MaWoDailyChartDTO maWoDailyChartDTO) {
		this.maWoDailyChartDTO = maWoDailyChartDTO;
	}

}
