package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.MaWoReqCommonDTO;

/**
 * �۾���û - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoReqListForm"
 */
public class MaWoReqListForm extends BaseForm
{    
    //===============================================================
    /** �۾���û ���� */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    
	public MaWoReqCommonDTO getMaWoReqCommonDTO() {
		return maWoReqCommonDTO;
	}

	public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO) {
		this.maWoReqCommonDTO = maWoReqCommonDTO;
	}
}
