package dream.app.tracelog.form;

import common.struts.BaseForm;
import dream.app.tracelog.dto.AppTracelogCommonDTO;
import dream.app.tracelog.dto.AppTracelogDetailDTO;

/**
 * TraceLog - Detail Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="appTracelogDetailForm"
 */
public class AppTracelogDetailForm extends BaseForm
{
	private AppTracelogCommonDTO appTracelogCommonDTO = new AppTracelogCommonDTO();
	private AppTracelogDetailDTO appTracelogDetailDTO = new AppTracelogDetailDTO();
    
	public AppTracelogCommonDTO getAppTracelogCommonDTO() {
		return appTracelogCommonDTO;
	}
	public void setAppTracelogCommonDTO(AppTracelogCommonDTO appTracelogCommonDTO) {
		this.appTracelogCommonDTO = appTracelogCommonDTO;
	}
	public AppTracelogDetailDTO getAppTracelogDetailDTO() {
		return appTracelogDetailDTO;
	}
	public void setAppTracelogDetailDTO(AppTracelogDetailDTO appTracelogDetailDTO) {
		this.appTracelogDetailDTO = appTracelogDetailDTO;
	}
}
