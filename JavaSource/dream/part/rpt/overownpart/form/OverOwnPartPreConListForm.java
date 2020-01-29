package dream.part.rpt.overownpart.form;

import common.struts.BaseForm;
import dream.part.rpt.overownpart.dto.OverOwnPartPreConCommonDTO;


/**
 * OverOwnPartPreCon Page - List Form
 * @author youngjoo38
 * @version $Id: OverOwnPartPreConListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="overOwnPartPreConListForm"
 * */
public class OverOwnPartPreConListForm extends BaseForm {
    
    private OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO = new OverOwnPartPreConCommonDTO();

    public OverOwnPartPreConCommonDTO getOverOwnPartPreConCommonDTO() {
        return overOwnPartPreConCommonDTO;
    }

    public void setOverOwnPartPreConCommonDTO(OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO) {
        this.overOwnPartPreConCommonDTO = overOwnPartPreConCommonDTO;
    }
}