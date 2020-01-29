package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.ReqInvWorkResDetailDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * 작업요청(투자)-처리사항
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="reqInvWorkResDetailForm"
 */
public class ReqInvWorkResDetailForm extends BaseForm
{
	/** 작업요청-처리사항 목록 DTO  */
    private ReqWorkResListDTO reqWorkResListDTO = new ReqWorkResListDTO();
	/** 작업요청-처리사항 상세(투자) DTO  */
    private ReqInvWorkResDetailDTO reqInvWorkResDetailDTO = new ReqInvWorkResDetailDTO();
    
    
	public ReqWorkResListDTO getReqWorkResListDTO() {
		return reqWorkResListDTO;
	}
	public void setReqWorkResListDTO(ReqWorkResListDTO reqWorkResListDTO) {
		this.reqWorkResListDTO = reqWorkResListDTO;
	}
	public ReqInvWorkResDetailDTO getReqInvWorkResDetailDTO() {
		return reqInvWorkResDetailDTO;
	}
	public void setReqInvWorkResDetailDTO(ReqInvWorkResDetailDTO reqInvWorkResDetailDTO) {
		this.reqInvWorkResDetailDTO = reqInvWorkResDetailDTO;
	}

    
}
