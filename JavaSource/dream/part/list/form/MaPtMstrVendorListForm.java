package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrVendorListDTO;

/**
 * ��ǰ�ŷ�ó ���
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrVendorListForm"
 */
public class MaPtMstrVendorListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
    /**  ��ǰ�ŷ�ó ���  */
    private MaPtMstrVendorListDTO maPtMstrVendorListDTO = new MaPtMstrVendorListDTO();
	

	public MaPtMstrCommonDTO getMaPtMstrCommonDTO() {
		return maPtMstrCommonDTO;
	}

	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) {
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}

	public MaPtMstrVendorListDTO getMaPtMstrVendorListDTO() {
		return maPtMstrVendorListDTO;
	}

	public void setMaPtMstrVendorListDTO(MaPtMstrVendorListDTO maPtMstrVendorListDTO) {
		this.maPtMstrVendorListDTO = maPtMstrVendorListDTO;
	}
}
