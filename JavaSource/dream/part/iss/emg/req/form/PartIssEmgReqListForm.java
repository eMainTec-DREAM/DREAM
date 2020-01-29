package dream.part.iss.emg.req.form;

import common.struts.BaseForm;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
/**
 * 부품출고 - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="partIssEmgReqListForm"
 * */

public class PartIssEmgReqListForm extends BaseForm{
	
	private PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = new PartIssEmgReqCommonDTO();

	public PartIssEmgReqCommonDTO getPartIssEmgReqCommonDTO() {
		return partIssEmgReqCommonDTO;
	}

	public void setPartIssEmgReqCommonDTO(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO) {
		this.partIssEmgReqCommonDTO = partIssEmgReqCommonDTO;
	}
	
}
