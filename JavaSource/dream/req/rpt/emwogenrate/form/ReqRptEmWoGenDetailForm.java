package dream.req.rpt.emwogenrate.form;

import common.struts.BaseForm;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;

/**
 * 작업의뢰 작업발행율 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqRptEmWoGenDetailForm"
 */
public class ReqRptEmWoGenDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO = new ReqRptEmWoGenRateCommonDTO();
    
    public ReqRptEmWoGenRateCommonDTO getReqRptEmWoGenRateCommonDTO() {
		return reqRptEmWoGenRateCommonDTO;
	}

	public void setReqRptEmWoGenRateCommonDTO(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO) {
		this.reqRptEmWoGenRateCommonDTO = reqRptEmWoGenRateCommonDTO;
	}
}
