package dream.work.rpt.selfvendor.form;

import common.struts.BaseForm;
import dream.work.rpt.selfvendor.dto.WorkRptSelfVendorCommonDTO;


/**
 * �系, ���� �۾� ��Ȳ Report - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptSelfVendorListForm"
 * */
public class WorkRptSelfVendorListForm extends BaseForm {
    
    private WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO = new WorkRptSelfVendorCommonDTO();

    public WorkRptSelfVendorCommonDTO getWorkRptSelfVendorCommonDTO() {
        return workRptSelfVendorCommonDTO;
    }

    public void setWorkRptSelfVendorCommonDTO(WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO) {
        this.workRptSelfVendorCommonDTO = workRptSelfVendorCommonDTO;
    }

}