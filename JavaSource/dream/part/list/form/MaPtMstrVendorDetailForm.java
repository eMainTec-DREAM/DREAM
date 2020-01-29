package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrVendorDetailDTO;
import dream.part.list.dto.MaPtMstrVendorListDTO;

/**
 * 何前芭贰贸
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrVendorDetailForm"
 */
public class MaPtMstrVendorDetailForm extends BaseForm
{    
    //===============================================================
    /** 傍烹 DTO */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	/** 何前芭贰贸 格废 DTO  */
    private MaPtMstrVendorListDTO maPtMstrVendorListDTO = new MaPtMstrVendorListDTO();
	/** 何前芭贰贸 惑技 DTO  */
    private MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO = new MaPtMstrVendorDetailDTO();
    
	public MaPtMstrVendorListDTO getMaPtMstrVendorListDTO() {
		return maPtMstrVendorListDTO;
	}
	public void setMaPtMstrVendorListDTO(MaPtMstrVendorListDTO maPtMstrVendorListDTO) {
		this.maPtMstrVendorListDTO = maPtMstrVendorListDTO;
	}
	public MaPtMstrVendorDetailDTO getMaPtMstrVendorDetailDTO() {
		return maPtMstrVendorDetailDTO;
	}
	public void setMaPtMstrVendorDetailDTO(MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO) {
		this.maPtMstrVendorDetailDTO = maPtMstrVendorDetailDTO;
	}
	public MaPtMstrCommonDTO getMaPtMstrCommonDTO() {
		return maPtMstrCommonDTO;
	}
	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) {
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}
}
