package dream.req.rpt.woreqrate.form;

import common.struts.BaseForm;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateCommonDTO;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateDetailDTO;

/**
 * 요청접수율(처리자)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqRptWoReqRateListForm"
 */
public class ReqRptWoReqRateListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO = new ReqRptWoReqRateCommonDTO();
    private ReqRptWoReqRateDetailDTO reqRptWoReqRateDetailDTO = new ReqRptWoReqRateDetailDTO();
    
    public ReqRptWoReqRateCommonDTO getReqRptWoReqRateCommonDTO()
    {
        return reqRptWoReqRateCommonDTO;
    }

    public void setReqRptWoReqRateCommonDTO(
            ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO)
    {
        this.reqRptWoReqRateCommonDTO = reqRptWoReqRateCommonDTO;
    }
    
    public ReqRptWoReqRateDetailDTO getReqRptWoReqRateDetailDTO()
    {
        return reqRptWoReqRateDetailDTO;
    }

    public void setReqRptWoReqRateDetailDTO(
            ReqRptWoReqRateDetailDTO reqRptWoReqRateDetailDTO)
    {
        this.reqRptWoReqRateDetailDTO = reqRptWoReqRateDetailDTO;
    }
	
}
