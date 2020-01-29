package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.MaWoReqCommonDTO;

/**
 * 작업요청 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoReqListForm"
 */
public class MaWoReqListForm extends BaseForm
{    
    //===============================================================
    /** 작업요청 공통 */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    
	public MaWoReqCommonDTO getMaWoReqCommonDTO() {
		return maWoReqCommonDTO;
	}

	public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO) {
		this.maWoReqCommonDTO = maWoReqCommonDTO;
	}
}
