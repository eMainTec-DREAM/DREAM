package dream.part.iss.emg.list.form;

import common.struts.BaseForm;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;

/**
 * 긴급출고 - 목록 form
 * @author  ssong
 * @version $Id: MaPtIssEmgListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPtIssEmgListForm"
 */
public class MaPtIssEmgListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPtIssEmgCommonDTO maPtIssEmgCommonDTO = new MaPtIssEmgCommonDTO();
    /** 상세 */
    private MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = new MaPtIssEmgDetailDTO();
    
    public MaPtIssEmgDetailDTO getMaPtIssEmgDetailDTO()
    {
        return maPtIssEmgDetailDTO;
    }

    public void setMaPtIssEmgDetailDTO(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO)
    {
        this.maPtIssEmgDetailDTO = maPtIssEmgDetailDTO;
    }

    private PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = new PartIssEmgReqCommonDTO();
    
	public MaPtIssEmgCommonDTO getMaPtIssEmgCommonDTO() {
		return maPtIssEmgCommonDTO;
	}

	public void setMaPtIssEmgCommonDTO(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO) {
		this.maPtIssEmgCommonDTO = maPtIssEmgCommonDTO;
	}

    public PartIssEmgReqCommonDTO getPartIssEmgReqCommonDTO()
    {
        return partIssEmgReqCommonDTO;
    }

    public void setPartIssEmgReqCommonDTO(
            PartIssEmgReqCommonDTO partIssEmgReqCommonDTO)
    {
        this.partIssEmgReqCommonDTO = partIssEmgReqCommonDTO;
    }
	
}
