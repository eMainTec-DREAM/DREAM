package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * 처리사항 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWorkResListForm"
 */
public class ReqWorkResListForm extends BaseForm
{
    //===============================================================
    /** 작업요청 공통 */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
    /** 처리사항 */
    private ReqWorkResListDTO reqWorkResListDTO = new ReqWorkResListDTO();

	public ReqWorkCommonDTO getReqWorkCommonDTO() {
		return reqWorkCommonDTO;
	}

	public void setReqWorkCommonDTO(ReqWorkCommonDTO reqWorkCommonDTO) {
		this.reqWorkCommonDTO = reqWorkCommonDTO;
	}

	public ReqWorkResListDTO getReqWorkResListDTO() {
		return reqWorkResListDTO;
	}

	public void setReqWorkResListDTO(ReqWorkResListDTO reqWorkResListDTO) {
		this.reqWorkResListDTO = reqWorkResListDTO;
	}

}
