package dream.mgr.audtrail.form;

import common.struts.BaseForm;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDetailDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDtlListDTO;

/**
 * 상세 Form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrAudTrailDetailForm"
 */
public class MgrAudTrailDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private MgrAudTrailCommonDTO mgrAudTrailCommonDTO = new MgrAudTrailCommonDTO();
    //========================================================================
    /** 상세 DTO */ 
    private MgrAudTrailDetailDTO mgrAudTrailDetailDTO = new MgrAudTrailDetailDTO();
    
    private MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO = new MgrAudTrailDtlListDTO();

	public MgrAudTrailCommonDTO getMgrAudTrailCommonDTO() {
		return mgrAudTrailCommonDTO;
	}
	public MgrAudTrailDtlListDTO getMgrAudTrailDtlListDTO() {
		return mgrAudTrailDtlListDTO;
	}
	public void setMgrAudTrailDtlListDTO(MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO) {
		this.mgrAudTrailDtlListDTO = mgrAudTrailDtlListDTO;
	}
	public void setMgrAudTrailCommonDTO(MgrAudTrailCommonDTO mgrAudTrailCommonDTO) {
		this.mgrAudTrailCommonDTO = mgrAudTrailCommonDTO;
	}
	public MgrAudTrailDetailDTO getMgrAudTrailDetailDTO() {
		return mgrAudTrailDetailDTO;
	}
	public void setMgrAudTrailDetailDTO(MgrAudTrailDetailDTO mgrAudTrailDetailDTO) {
		this.mgrAudTrailDetailDTO = mgrAudTrailDetailDTO;
	}
}
