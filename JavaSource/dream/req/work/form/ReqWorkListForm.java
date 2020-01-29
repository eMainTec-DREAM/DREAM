package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.ReqWorkCommonDTO;

/**
 * 작업요청 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWorkListForm"
 */
public class ReqWorkListForm extends BaseForm
{
    //===============================================================
    /** 작업요청 공통 */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();

	public ReqWorkCommonDTO getReqWorkCommonDTO() {
		return reqWorkCommonDTO;
	}

	public void setReqWorkCommonDTO(ReqWorkCommonDTO reqWorkCommonDTO) {
		this.reqWorkCommonDTO = reqWorkCommonDTO;
	}
}
