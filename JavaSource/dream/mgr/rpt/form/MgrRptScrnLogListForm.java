package dream.mgr.rpt.form;

import common.struts.BaseForm;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;
import dream.mgr.rpt.dto.MgrRptScrnLogCommonDTO;


/**
 * ȭ�����ӷα� ����Ʈ Page - List Form
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * @struts.form name="mgrRptScrnLogListForm"
 * */
public class MgrRptScrnLogListForm extends BaseForm {
    
    private MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO = new MgrRptScrnLogCommonDTO();
    private MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO = new MgrRptLoginLogCommonDTO();
    
	public MgrRptScrnLogCommonDTO getMgrRptScrnLogCommonDTO() {
		return mgrRptScrnLogCommonDTO;
	}
	public void setMgrRptScrnLogCommonDTO(MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO) {
		this.mgrRptScrnLogCommonDTO = mgrRptScrnLogCommonDTO;
	}
	public MgrRptLoginLogCommonDTO getMgrRptLoginLogCommonDTO() {
		return mgrRptLoginLogCommonDTO;
	}
	public void setMgrRptLoginLogCommonDTO(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO) {
		this.mgrRptLoginLogCommonDTO = mgrRptLoginLogCommonDTO;
	}

}