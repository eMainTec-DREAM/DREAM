package dream.mgr.audtrail.form;

import common.struts.BaseForm;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDtlListDTO;

/**
 * MgrAudTrailDtl Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="mgrAudTrailDtlListForm"
 * */
public class MgrAudTrailDtlListForm extends BaseForm {
    
    private MgrAudTrailCommonDTO mgrAudTrailCommonDTO = new MgrAudTrailCommonDTO();
    private MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO = new MgrAudTrailDtlListDTO();

    
    public MgrAudTrailCommonDTO getMgrAudTrailCommonDTO()
    {
        return mgrAudTrailCommonDTO;
    }

    public void setMgrAudTrailCommonDTO(MgrAudTrailCommonDTO mgrAudTrailCommonDTO)
    {
        this.mgrAudTrailCommonDTO = mgrAudTrailCommonDTO;
    }

    public MgrAudTrailDtlListDTO getMgrAudTrailDtlListDTO() {
        return mgrAudTrailDtlListDTO;
    }

    public void setMgrAudTrailDtlListDTO(MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO) {
        this.mgrAudTrailDtlListDTO = mgrAudTrailDtlListDTO;
    }
}