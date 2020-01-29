package dream.req.rpt.prewoplanrate.form;

import common.struts.BaseForm;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanDetailListDTO;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanRateCommonDTO;


/**
 * 작업오더 사전 계획 수립률 목록 - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="reqRptPreWoPlanRateListForm"
 * */
public class ReqRptPreWoPlanRateListForm extends BaseForm {
    
    private ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO = new ReqRptPreWoPlanRateCommonDTO();
    private ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO = new ReqRptPreWoPlanDetailListDTO();

    public ReqRptPreWoPlanDetailListDTO getReqRptPreWoPlanDetailListDTO() {
		return reqRptPreWoPlanDetailListDTO;
	}

	public void setReqRptPreWoPlanDetailListDTO(ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO) {
		this.reqRptPreWoPlanDetailListDTO = reqRptPreWoPlanDetailListDTO;
	}

	public ReqRptPreWoPlanRateCommonDTO getReqRptPreWoPlanRateCommonDTO() {
        return reqRptPreWoPlanRateCommonDTO;
    }

    public void setReqRptPreWoPlanRateCommonDTO(ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO) {
        this.reqRptPreWoPlanRateCommonDTO = reqRptPreWoPlanRateCommonDTO;
    }

}