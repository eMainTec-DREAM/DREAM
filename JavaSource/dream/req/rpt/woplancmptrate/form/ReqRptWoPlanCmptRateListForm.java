package dream.req.rpt.woplancmptrate.form;

import common.struts.BaseForm;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptDetailListDTO;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptRateCommonDTO;


/**
 * 작업의뢰 계획대비 실행 비율 목록 - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="reqRptWoPlanCmptRateListForm"
 * */
public class ReqRptWoPlanCmptRateListForm extends BaseForm {
    
    private ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO = new ReqRptWoPlanCmptRateCommonDTO();
    private ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO = new ReqRptWoPlanCmptDetailListDTO();

    public ReqRptWoPlanCmptRateCommonDTO getReqRptWoPlanCmptRateCommonDTO() {
        return reqRptWoPlanCmptRateCommonDTO;
    }

    public void setReqRptWoPlanCmptRateCommonDTO(ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO) {
        this.reqRptWoPlanCmptRateCommonDTO = reqRptWoPlanCmptRateCommonDTO;
    }

    public ReqRptWoPlanCmptDetailListDTO getReqRptWoPlanCmptDetailListDTO() {
        return reqRptWoPlanCmptDetailListDTO;
    }

    public void setReqRptWoPlanCmptDetailListDTO(ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO) {
        this.reqRptWoPlanCmptDetailListDTO = reqRptWoPlanCmptDetailListDTO;
    }

}