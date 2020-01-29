package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * ó������ - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWorkResListForm"
 */
public class ReqWorkResListForm extends BaseForm
{
    //===============================================================
    /** �۾���û ���� */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
    /** ó������ */
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
