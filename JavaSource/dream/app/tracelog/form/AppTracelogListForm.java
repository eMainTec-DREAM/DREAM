package dream.app.tracelog.form;

import common.struts.BaseForm;
import dream.app.tracelog.dto.AppTracelogCommonDTO;
/**
 * TraceLog - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="appTracelogListForm"
 * */

public class AppTracelogListForm extends BaseForm{
	
	private AppTracelogCommonDTO appTracelogCommonDTO = new AppTracelogCommonDTO();

	public AppTracelogCommonDTO getAppTracelogCommonDTO() {
		return appTracelogCommonDTO;
	}

	public void setAppTracelogCommonDTO(AppTracelogCommonDTO appTracelogCommonDTO) {
		this.appTracelogCommonDTO = appTracelogCommonDTO;
	}
	
}
