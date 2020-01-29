package dream.part.iss.emg.req.form;

import common.struts.BaseForm;

import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;

/**
 * 긴급출고- 상세 Form
 * @author  ssong
 * @version $Id: MaPtIssEmgDetailForm.java,v 1.0 2015/12/02 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="partIssEmgReqPartsDetailForm"
 */
public class PartIssEmgReqPartsDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
	private PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = new PartIssEmgReqCommonDTO();
    //========================================================================
    /** 상세 */
    private PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO = new PartIssEmgReqPartsDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }

	public PartIssEmgReqCommonDTO getPartIssEmgReqCommonDTO() {
		return partIssEmgReqCommonDTO;
	}

	public void setPartIssEmgReqCommonDTO(
			PartIssEmgReqCommonDTO partIssEmgReqCommonDTO) {
		this.partIssEmgReqCommonDTO = partIssEmgReqCommonDTO;
	}

	public PartIssEmgReqPartsDetailDTO getPartIssEmgReqPartsDetailDTO() {
		return partIssEmgReqPartsDetailDTO;
	}

	public void setPartIssEmgReqPartsDetailDTO(
			PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO) {
		this.partIssEmgReqPartsDetailDTO = partIssEmgReqPartsDetailDTO;
	}
	
	

    
    
}
