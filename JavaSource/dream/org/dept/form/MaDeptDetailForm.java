package dream.org.dept.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.org.dept.dto.MaDeptCommonDTO;
import dream.org.dept.dto.MaDeptDetailDTO;

/**
 * �����μ�- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maDeptDetailForm"
 */
public class MaDeptDetailForm extends BaseForm
{
    //========================================================================
    /** �����μ� ���� */ 
    private MaDeptCommonDTO maDeptCommonDTO = new MaDeptCommonDTO();
    //========================================================================
    /** �����μ� �� */
    private MaDeptDetailDTO maDeptDetailDTO = new MaDeptDetailDTO();
    
    /** ��뿩�� Options */
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
