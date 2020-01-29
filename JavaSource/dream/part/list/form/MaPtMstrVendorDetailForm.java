package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrVendorDetailDTO;
import dream.part.list.dto.MaPtMstrVendorListDTO;

/**
 * ��ǰ�ŷ�ó
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrVendorDetailForm"
 */
public class MaPtMstrVendorDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	/** ��ǰ�ŷ�ó ��� DTO  */
    private MaPtMstrVendorListDTO maPtMstrVendorListDTO = new MaPtMstrVendorListDTO();
	/** ��ǰ�ŷ�ó �� DTO  */
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
