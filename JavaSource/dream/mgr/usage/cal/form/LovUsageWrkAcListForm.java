package dream.mgr.usage.cal.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.usage.cal.dto.LovUsageWrkAcListDTO;
/**
 * 사용달력 LOV - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="lovUsageWrkAcListForm"
 * */

public class LovUsageWrkAcListForm extends MaFinderAcForm{
	
	private LovUsageWrkAcListDTO lovUsageWrkAcListDTO = new LovUsageWrkAcListDTO();

	public LovUsageWrkAcListDTO getLovUsageWrkAcListDTO() {
		return lovUsageWrkAcListDTO;
	}

	public void setLovUsageWrkAcListDTO(LovUsageWrkAcListDTO lovUsageWrkAcListDTO) {
		this.lovUsageWrkAcListDTO = lovUsageWrkAcListDTO;
	}

	
}
