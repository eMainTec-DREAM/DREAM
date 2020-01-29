package dream.work.close.check.form;

import common.struts.BaseForm;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;


/**
 * MgrWorkCloseCheck Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="mgrWorkCloseCheckListForm"
 * */
public class MgrWorkCloseCheckListForm extends BaseForm {
    
    private MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = new MgrWorkCloseCheckCommonDTO();

    public MgrWorkCloseCheckCommonDTO getMgrWorkCloseCheckCommonDTO() {
        return mgrWorkCloseCheckCommonDTO;
    }

    public void setMgrWorkCloseCheckCommonDTO(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO) {
        this.mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckCommonDTO;
    }
}