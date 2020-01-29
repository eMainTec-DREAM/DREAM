package dream.org.dept.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.org.dept.dto.MaDeptCommonDTO;
import dream.org.dept.dto.MaDeptDetailDTO;

/**
 * 보전부서- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maDeptDetailForm"
 */
public class MaDeptDetailForm extends BaseForm
{
    //========================================================================
    /** 보전부서 공통 */ 
    private MaDeptCommonDTO maDeptCommonDTO = new MaDeptCommonDTO();
    //========================================================================
    /** 보전부서 상세 */
    private MaDeptDetailDTO maDeptDetailDTO = new MaDeptDetailDTO();
    
    /** 사용여부 Options */
    private Collection isUseOptions = null;

	public MaDeptCommonDTO getMaDeptCommonDTO() {
		return maDeptCommonDTO;
	}

	public void setMaDeptCommonDTO(MaDeptCommonDTO maDeptCommonDTO) {
		this.maDeptCommonDTO = maDeptCommonDTO;
	}

	public MaDeptDetailDTO getMaDeptDetailDTO() {
		return maDeptDetailDTO;
	}

	public void setMaDeptDetailDTO(MaDeptDetailDTO maDeptDetailDTO) {
		this.maDeptDetailDTO = maDeptDetailDTO;
	}

	public Collection getIsUseOptions() {
		return isUseOptions;
	}

	public void setIsUseOptions(Collection isUseOptions) {
		this.isUseOptions = isUseOptions;
	}
}
