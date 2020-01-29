package dream.part.iss.wo.form;

import common.struts.BaseForm;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;

/**
 * 자재출고확정 - 목록 form
 * @author  ssong
 * @version $Id: MaPtIssListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPtIssListForm"
 */
public class MaPtIssListForm extends BaseForm
{    
    //===============================================================
    /** 자재출고확정 공통 */
    private MaPtIssCommonDTO maPtIssCommonDTO = new MaPtIssCommonDTO();
    /** 상세 */
    private MaPtIssDetailDTO maPtIssDetailDTO = new MaPtIssDetailDTO();
    
	public MaPtIssDetailDTO getMaPtIssDetailDTO()
    {
        return maPtIssDetailDTO;
    }

    public void setMaPtIssDetailDTO(MaPtIssDetailDTO maPtIssDetailDTO)
    {
        this.maPtIssDetailDTO = maPtIssDetailDTO;
    }

    public MaPtIssCommonDTO getMaPtIssCommonDTO() {
		return maPtIssCommonDTO;
	}

	public void setMaPtIssCommonDTO(MaPtIssCommonDTO maPtIssCommonDTO) {
		this.maPtIssCommonDTO = maPtIssCommonDTO;
	}
}
