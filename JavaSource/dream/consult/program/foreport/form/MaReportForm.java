package dream.consult.program.foreport.form;

import common.struts.BaseForm;
import dream.consult.program.foreport.dto.MaReportCommonDTO;

/**
 * 레포트변환 form
 * @author  kim21017
 * @version $Id: MaReportForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maReportForm"
 */
public class MaReportForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaReportCommonDTO maReportCommonDTO = new MaReportCommonDTO();

	public MaReportCommonDTO getMaReportCommonDTO() {
		return maReportCommonDTO;
	}

	public void setMaReportCommonDTO(MaReportCommonDTO maReportCommonDTO) {
		this.maReportCommonDTO = maReportCommonDTO;
	}
}
