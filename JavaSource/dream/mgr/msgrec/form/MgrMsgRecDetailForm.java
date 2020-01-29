package dream.mgr.msgrec.form;

import common.struts.BaseForm;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;
import dream.mgr.msgrec.dto.MgrMsgRecDetailDTO;

/**
 * MgrMsgRec Page - Detail Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="mgrMsgRecDetailForm"
 */
public class MgrMsgRecDetailForm extends BaseForm
{
    private MgrMsgRecCommonDTO mgrMsgRecCommonDTO = new MgrMsgRecCommonDTO();
    private MgrMsgRecDetailDTO mgrMsgRecDetailDTO = new MgrMsgRecDetailDTO();
    
    public MgrMsgRecCommonDTO getMgrMsgRecCommonDTO() {
        return mgrMsgRecCommonDTO;
    }
    public void setMgrMsgRecCommonDTO(MgrMsgRecCommonDTO mgrMsgRecCommonDTO) {
        this.mgrMsgRecCommonDTO = mgrMsgRecCommonDTO;
    }
    public MgrMsgRecDetailDTO getMgrMsgRecDetailDTO() {
        return mgrMsgRecDetailDTO;
    }
    public void setMgrMsgRecDetailDTO(MgrMsgRecDetailDTO mgrMsgRecDetailDTO) {
        this.mgrMsgRecDetailDTO = mgrMsgRecDetailDTO;
    }
}
