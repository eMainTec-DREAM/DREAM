package dream.part.iss.emg.req.form;

import common.struts.BaseForm;

import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;

/**
 * 긴급출고 - 목록 form
 * @author  ssong
 * @version $Id: MaPtIssEmgListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="partIssEmgReqPartsListForm"
 */
public class PartIssEmgReqPartsListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = new PartIssEmgReqCommonDTO();
    /** 상세 */
    private PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO = new PartIssEmgReqPartsDetailDTO();

	public PartIssEmgReqPartsDetailDTO getPartIssEmgReqPartsDetailDTO()
    {
        return partIssEmgReqPartsDetailDTO;
    }

    public void setPartIssEmgReqPartsDetailDTO(
            PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO)
    {
        this.partIssEmgReqPartsDetailDTO = partIssEmgReqPartsDetailDTO;
    }

    public PartIssEmgReqCommonDTO getPartIssEmgReqCommonDTO() {
		return partIssEmgReqCommonDTO;
	}

	public void setPartIssEmgReqCommonDTO(
			PartIssEmgReqCommonDTO partIssEmgReqCommonDTO) {
		this.partIssEmgReqCommonDTO = partIssEmgReqCommonDTO;
	}
    
	
	
}
