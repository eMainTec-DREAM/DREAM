package dream.invt.rpt.invtprecon.form;

import common.struts.BaseForm;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;


/**
 * InvtRptInvtPreCon Page - List Form
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="invtRptInvtPreConListForm"
 * */
public class InvtRptInvtPreConListForm extends BaseForm {
    
    private InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO = new InvtRptInvtPreConCommonDTO();

    public InvtRptInvtPreConCommonDTO getInvtRptInvtPreConCommonDTO() {
        return invtRptInvtPreConCommonDTO;
    }

    public void setInvtRptInvtPreConCommonDTO(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO) {
        this.invtRptInvtPreConCommonDTO = invtRptInvtPreConCommonDTO;
    }
}