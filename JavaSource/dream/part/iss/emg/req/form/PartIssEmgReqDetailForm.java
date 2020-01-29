package dream.part.iss.emg.req.form;

import common.struts.BaseForm;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqDetailDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * 부품출고 - Detail Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="partIssEmgReqDetailForm"
 */
public class PartIssEmgReqDetailForm extends BaseForm
{
	private PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = new PartIssEmgReqCommonDTO();
	private PartIssEmgReqDetailDTO partIssEmgReqDetailDTO = new PartIssEmgReqDetailDTO();
	/** 결재 진행 이력 DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    
	public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }
    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }
    public PartIssEmgReqCommonDTO getPartIssEmgReqCommonDTO() {
		return partIssEmgReqCommonDTO;
	}
	public void setPartIssEmgReqCommonDTO(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO) {
		this.partIssEmgReqCommonDTO = partIssEmgReqCommonDTO;
	}
	public PartIssEmgReqDetailDTO getPartIssEmgReqDetailDTO() {
		return partIssEmgReqDetailDTO;
	}
	public void setPartIssEmgReqDetailDTO(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO) {
		this.partIssEmgReqDetailDTO = partIssEmgReqDetailDTO;
	}
}
