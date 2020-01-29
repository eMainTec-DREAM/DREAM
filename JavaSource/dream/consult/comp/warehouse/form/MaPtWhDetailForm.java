package dream.consult.comp.warehouse.form;

import common.struts.BaseForm;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;
import dream.consult.comp.warehouse.dto.MaPtWhDetailDTO;

/**
 * 부품창고- 상세 Form
 * @author  ssong
 * @version $Id: MaPtWhDetailForm.java,v 1.0 2015/12/02 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPtWhDetailForm"
 */
public class MaPtWhDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private MaPtWhCommonDTO maPtWhCommonDTO = new MaPtWhCommonDTO();
    //========================================================================
    /** 상세 */
    private MaPtWhDetailDTO maPtWhDetailDTO = new MaPtWhDetailDTO();


    public MaPtWhDetailDTO getMaPtWhDetailDTO() {
		return maPtWhDetailDTO;
	}

	public void setMaPtWhDetailDTO(MaPtWhDetailDTO maPtWhDetailDTO) {
		this.maPtWhDetailDTO = maPtWhDetailDTO;
	}

	public MaPtWhCommonDTO getMaPtWhCommonDTO() {
		return maPtWhCommonDTO;
	}

    public void setMaPtWhCommonDTO(MaPtWhCommonDTO maPtWhCommonDTO) {
		this.maPtWhCommonDTO = maPtWhCommonDTO;
	}
}
