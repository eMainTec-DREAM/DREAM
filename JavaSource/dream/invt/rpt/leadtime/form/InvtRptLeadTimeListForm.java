package dream.invt.rpt.leadtime.form;

import common.struts.BaseForm;
import dream.invt.rpt.leadtime.dto.InvtRptLeadTimeCommonDTO;


/**
 * InvtRptLeadTime Page - List Form
 * @author cjscjs9
 * @version $Id: InvtRptLeadTimeListForm.java,v 1.0 2017/08/22 15:19:40 cjscjs9 Exp $
 * @since 1.0
 * @struts.form name="invtRptLeadTimeListForm"
 * */
public class InvtRptLeadTimeListForm extends BaseForm {
    
    private InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO = new InvtRptLeadTimeCommonDTO();

    public InvtRptLeadTimeCommonDTO getInvtRptLeadTimeCommonDTO() {
        return invtRptLeadTimeCommonDTO;
    }

    public void setInvtRptLeadTimeCommonDTO(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO) {
        this.invtRptLeadTimeCommonDTO = invtRptLeadTimeCommonDTO;
    }
}