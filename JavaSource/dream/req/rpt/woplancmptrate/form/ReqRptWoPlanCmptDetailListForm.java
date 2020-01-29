package dream.req.rpt.woplancmptrate.form;

import common.struts.BaseForm;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptDetailListDTO;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptRateCommonDTO;


/**
 * 작업의뢰 초기계획 요청 목록 - List Form
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * @struts.form name="reqRptWoPlanCmptDetailListForm"
 * */
public class ReqRptWoPlanCmptDetailListForm extends BaseForm {
    
    private ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO = new ReqRptWoPlanCmptRateCommonDTO();
    private ReqRptWoPlanCmptDetailListDTO ReqRptWoPlanCmptDetailListDTO = new ReqRptWoPlanCmptDetailListDTO();
    
    public ReqRptWoPlanCmptRateCommonDTO getReqRptWoPlanCmptRateCommonDTO()
    {
        return reqRptWoPlanCmptRateCommonDTO;
    }

    public void setReqRptWoPlanCmptRateCommonDTO(
            ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO)
    {
        this.reqRptWoPlanCmptRateCommonDTO = reqRptWoPlanCmptRateCommonDTO;
    }

    public ReqRptWoPlanCmptDetailListDTO getReqRptWoPlanCmptDetailListDTO()
    {
        return ReqRptWoPlanCmptDetailListDTO;
    }

    public void setReqRptWoPlanCmptDetailListDTO(
            ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO)
    {
        ReqRptWoPlanCmptDetailListDTO = reqRptWoPlanCmptDetailListDTO;
    }
    
}