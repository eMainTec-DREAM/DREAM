package dream.org.vendor.form;

import common.struts.BaseForm;
import dream.org.vendor.dto.MaVendorCommonDTO;
import dream.org.vendor.dto.MaVendorDetailDTO;

/**
 * ���þ�ü- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maVendorDetailForm"
 */
public class MaVendorDetailForm extends BaseForm
{
    //========================================================================
    /** ���þ�ü ���� */ 
    private MaVendorCommonDTO maVendorCommonDTO = new MaVendorCommonDTO();
    //========================================================================
    /** ���þ�ü �� */
    private MaVendorDetailDTO maVendorDetailDTO = new MaVendorDetailDTO();
    
	public MaVendorCommonDTO getMaVendorCommonDTO() {
		return maVendorCommonDTO;
	}

	public void setMaVendorCommonDTO(MaVendorCommonDTO maVendorCommonDTO) {
		this.maVendorCommonDTO = maVendorCommonDTO;
	}

	public MaVendorDetailDTO getMaVendorDetailDTO() {
		return maVendorDetailDTO;
	}

	public void setMaVendorDetailDTO(MaVendorDetailDTO maVendorDetailDTO) {
		this.maVendorDetailDTO = maVendorDetailDTO;
	}

}
