package mobile.dream.mapm.mains.form;

import common.struts.BaseForm;
import mobile.dream.mapm.mains.dto.MaPmInsCommonDTO;
import mobile.dream.mapm.mains.dto.MaPmInsDetailDTO;

/**
 * 상세 Form
 * @author  jung7126
 * @version $Id: MaPmInsDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmInsDetailForm"
 */
public class MaPmInsDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private MaPmInsCommonDTO maPmInsCommonDTO = new MaPmInsCommonDTO();
    //========================================================================
    /** 상세 */
    private MaPmInsDetailDTO maPmInsDetailDTO = new MaPmInsDetailDTO();
    

	public MaPmInsCommonDTO getMaPmInsCommonDTO() {
		return maPmInsCommonDTO;
	}

    public void setMaPmInsCommonDTO(MaPmInsCommonDTO maPmInsCommonDTO) {
		this.maPmInsCommonDTO = maPmInsCommonDTO;
	}

	public MaPmInsDetailDTO getMaPmInsDetailDTO() {
		return maPmInsDetailDTO;
	}

	public void setMaPmInsDetailDTO(MaPmInsDetailDTO maPmInsDetailDTO) {
		this.maPmInsDetailDTO = maPmInsDetailDTO;
	}
}
