package dream.invt.rpt.invtcateg.form;

import common.struts.BaseForm;
import dream.invt.rpt.invtcateg.dto.InvtRptInvtCategCommonDTO;
import dream.invt.rpt.invtcateg.dto.InvtRptInvtCategDetailListDTO;

/**
 * 투자구분분석 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="invtRptInvtCategDetailListForm"
 */
public class InvtRptInvtCategDetailListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private InvtRptInvtCategCommonDTO invtRptInvtCategCommonDTO = new InvtRptInvtCategCommonDTO();
    
    private InvtRptInvtCategDetailListDTO invtRptInvtCategDetailListDTO = new InvtRptInvtCategDetailListDTO();
    
    public InvtRptInvtCategCommonDTO getInvtRptInvtCategCommonDTO()
    {
        return invtRptInvtCategCommonDTO;
    }

    public void setInvtRptInvtCategCommonDTO(
            InvtRptInvtCategCommonDTO invtRptInvtCategCommonDTO)
    {
        this.invtRptInvtCategCommonDTO = invtRptInvtCategCommonDTO;
    }
    
    public InvtRptInvtCategDetailListDTO getInvtRptInvtCategDetailListDTO()
    {
        return invtRptInvtCategDetailListDTO;
    }

    public void setInvtRptInvtCategDetailListDTO(
            InvtRptInvtCategDetailListDTO invtRptInvtCategDetailListDTO)
    {
        this.invtRptInvtCategDetailListDTO = invtRptInvtCategDetailListDTO;
    }
	
}
