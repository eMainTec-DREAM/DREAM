package dream.mgr.msgrec.form;

import common.struts.BaseForm;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;


/**
 * 메시지 수신설정 리스트 Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="mgrMsgRecListForm"
 * */
public class MgrMsgRecListForm extends BaseForm {
    
    private MgrMsgRecCommonDTO mgrMsgRecCommonDTO = new MgrMsgRecCommonDTO();
    
	public MgrMsgRecCommonDTO getMgrMsgRecCommonDTO() {
        return mgrMsgRecCommonDTO;
    }

    public void setMgrMsgRecCommonDTO(MgrMsgRecCommonDTO mgrMsgRecCommonDTO) {
        this.mgrMsgRecCommonDTO = mgrMsgRecCommonDTO;
    }
}