package dream.mgr.rpt.form;

import common.struts.BaseForm;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;


/**
 * �α��� �α� ����Ʈ Page - List Form
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * @struts.form name="mgrRptLoginLogListForm"
 * */
public class MgrRptLoginLogListForm extends BaseForm {
    
    private MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO = new MgrRptLoginLogCommonDTO();
    
	public MgrRptLoginLogCommonDTO getMgrRptLoginLogCommonDTO() {
        return mgrRptLoginLogCommonDTO;
    }

    public void setMgrRptLoginLogCommonDTO(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO) {
        this.mgrRptLoginLogCommonDTO = mgrRptLoginLogCommonDTO;
    }
}