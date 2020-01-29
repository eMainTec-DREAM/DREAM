package dream.req.rpt.preworeqrate.form;

import common.struts.BaseForm;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqDetailListDTO;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqRateCommonDTO;


/**
 * 작업의뢰 시스템 요청 목록 - List Form
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 * @struts.form name="reqRptPreWoreqDetailListForm"
 * */
public class ReqRptPreWoreqDetailListForm extends BaseForm {
    
    private ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO = new ReqRptPreWoreqRateCommonDTO();
    private ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO = new ReqRptPreWoreqDetailListDTO();
    
    public ReqRptPreWoreqRateCommonDTO getReqRptPreWoreqRateCommonDTO() {
        return reqRptPreWoreqRateCommonDTO;
    }

    public void setReqRptPreWoreqRateCommonDTO(ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO) {
        this.reqRptPreWoreqRateCommonDTO = reqRptPreWoreqRateCommonDTO;
    }

    public ReqRptPreWoreqDetailListDTO getReqRptPreWoreqDetailListDTO()
    {
        return reqRptPreWoreqDetailListDTO;
    }

    public void setReqRptPreWoreqDetailListDTO(
            ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO)
    {
        this.reqRptPreWoreqDetailListDTO = reqRptPreWoreqDetailListDTO;
    }
}