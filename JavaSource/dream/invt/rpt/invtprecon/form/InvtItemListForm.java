package dream.invt.rpt.invtprecon.form;

import common.struts.BaseForm;
import dream.invt.rpt.invtprecon.dto.InvtItemListDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;

/**
 * InvtItem Page - List Form
 * @author youngjoo38
 * @version $Id: InvtItemListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="invtItemListForm"
 * */
public class InvtItemListForm extends BaseForm {
    
    private InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO = new InvtRptInvtPreConCommonDTO();
    private InvtItemListDTO invtItemListDTO = new InvtItemListDTO();
    
    public InvtRptInvtPreConCommonDTO getInvtRptInvtPreConCommonDTO()
    {
        return invtRptInvtPreConCommonDTO;
    }

    public void setInvtRptInvtPreConCommonDTO(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO)
    {
        this.invtRptInvtPreConCommonDTO = invtRptInvtPreConCommonDTO;
    }

    public InvtItemListDTO getInvtItemListDTO() {
        return invtItemListDTO;
    }

    public void setInvtItemListDTO(InvtItemListDTO invtItemListDTO) {
        this.invtItemListDTO = invtItemListDTO;
    }
}