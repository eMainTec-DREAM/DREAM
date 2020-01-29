package dream.part.pur.po.form;

import common.struts.BaseForm;
import dream.part.pur.po.dto.MaPtPoCommonDTO;
import dream.part.pur.po.dto.MaPtPoDetailDTO;

/**
 * 발주이력- 상세 Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtPoDetailForm"
 */
public class MaPtPoDetailForm extends BaseForm
{
    //========================================================================
    /** 발주이력 공통 */ 
    private MaPtPoCommonDTO maPtPoCommonDTO = new MaPtPoCommonDTO();
    //========================================================================
    /** 발주이력 상세 */
    private MaPtPoDetailDTO maPtPoDetailDTO = new MaPtPoDetailDTO();
    
	public MaPtPoCommonDTO getMaPtPoCommonDTO() {
		return maPtPoCommonDTO;
	}
	public void setMaPtPoCommonDTO(MaPtPoCommonDTO maPtPoCommonDTO) {
		this.maPtPoCommonDTO = maPtPoCommonDTO;
	}
	public MaPtPoDetailDTO getMaPtPoDetailDTO() {
		return maPtPoDetailDTO;
	}
	public void setMaPtPoDetailDTO(MaPtPoDetailDTO maPtPoDetailDTO) {
		this.maPtPoDetailDTO = maPtPoDetailDTO;
	}

}
