package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.ReqWorkCommonDTO;

/**
 * �۾���û - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWorkListForm"
 */
public class ReqWorkListForm extends BaseForm
{
    //===============================================================
    /** �۾���û ���� */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();

	public ReqWorkCommonDTO getReqWorkCommonDTO() {
		return reqWorkCommonDTO;
	}

	public void setReqWorkCommonDTO(ReqWorkCommonDTO reqWorkCommonDTO) {
		this.reqWorkCommonDTO = reqWorkCommonDTO;
	}
}
