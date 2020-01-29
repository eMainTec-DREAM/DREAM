package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.ReqInvWorkResDetailDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * �۾���û(����)-ó������
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="reqInvWorkResDetailForm"
 */
public class ReqInvWorkResDetailForm extends BaseForm
{
	/** �۾���û-ó������ ��� DTO  */
    private ReqWorkResListDTO reqWorkResListDTO = new ReqWorkResListDTO();
	/** �۾���û-ó������ ��(����) DTO  */
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
