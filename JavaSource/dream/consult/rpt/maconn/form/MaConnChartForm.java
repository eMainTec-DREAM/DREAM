package dream.consult.rpt.maconn.form;

import common.struts.BaseForm;
import dream.consult.rpt.maconn.dto.MaConnChartDTO;

/**
 * 접속현황 FORM
 * @author  kim21017
 * @version $Id: MaConnChartForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maConnChartForm"
 */
public class MaConnChartForm extends BaseForm
{    
    private MaConnChartDTO maConnChartDTO = new MaConnChartDTO();

	public MaConnChartDTO getMaConnChartDTO() {
		return maConnChartDTO;
	}

	public void setMaConnChartDTO(MaConnChartDTO maConnChartDTO) {
		this.maConnChartDTO = maConnChartDTO;
	}

}
