package dream.req.rpt.emwogenrate.form;

import common.struts.BaseForm;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;


/**
 * ���� �۾����� �߻��� ��� - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="reqRptEmWoGenRateListForm"
 * */
public class ReqRptEmWoGenRateListForm extends BaseForm {
    
    private ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO = new ReqRptEmWoGenRateCommonDTO();

    public ReqRptEmWoGenRateCommonDTO getReqRptEmWoGenRateCommonDTO() {
        return reqRptEmWoGenRateCommonDTO;
    }

    public void setReqRptEmWoGenRateCommonDTO(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO) {
        this.reqRptEmWoGenRateCommonDTO = reqRptEmWoGenRateCommonDTO;
    }

}