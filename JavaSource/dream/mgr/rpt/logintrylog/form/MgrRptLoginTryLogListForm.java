package dream.mgr.rpt.logintrylog.form;

import common.struts.BaseForm;
import dream.mgr.rpt.logintrylog.dto.MgrRptLoginTryLogCommonDTO;


/**
 * 로그인 시도 로그 리스트 Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="mgrRptLoginTryLogListForm"
 * */
public class MgrRptLoginTryLogListForm extends BaseForm {
    
    private MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO = new MgrRptLoginTryLogCommonDTO();
    
	public MgrRptLoginTryLogCommonDTO getMgrRptLoginTryLogCommonDTO() {
        return mgrRptLoginTryLogCommonDTO;
    }

    public void setMgrRptLoginTryLogCommonDTO(MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO) {
        this.mgrRptLoginTryLogCommonDTO = mgrRptLoginTryLogCommonDTO;
    }
}