package dream.invt.rpt.invtprecon.form;

import common.struts.BaseForm;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConDetailDTO;

/**
 * InvtRptInvtPreCon Page - Detail Form
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConDetailForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="invtRptInvtPreConDetailForm"
 */
public class InvtRptInvtPreConDetailForm extends BaseForm
{
    private InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO = new InvtRptInvtPreConCommonDTO();
    private InvtRptInvtPreConDetailDTO invtRptInvtPreConDetailDTO = new InvtRptInvtPreConDetailDTO();
    
    public InvtRptInvtPreConCommonDTO getInvtRptInvtPreConCommonDTO() {
        return invtRptInvtPreConCommonDTO;
    }
    public void setInvtRptInvtPreConCommonDTO(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO) {
        this.invtRptInvtPreConCommonDTO = invtRptInvtPreConCommonDTO;
    }
    public InvtRptInvtPreConDetailDTO getInvtRptInvtPreConDetailDTO() {
        return invtRptInvtPreConDetailDTO;
    }
    public void setInvtRptInvtPreConDetailDTO(InvtRptInvtPreConDetailDTO invtRptInvtPreConDetailDTO) {
        this.invtRptInvtPreConDetailDTO = invtRptInvtPreConDetailDTO;
    }
}
