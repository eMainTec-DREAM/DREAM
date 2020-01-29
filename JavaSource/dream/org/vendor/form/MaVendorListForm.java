package dream.org.vendor.form;

import common.struts.BaseForm;
import dream.org.vendor.dto.MaVendorCommonDTO;

/**
 * 관련업체 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maVendorListForm"
 */
public class MaVendorListForm extends BaseForm
{    
    //===============================================================
    /** 관련업체 공통 */
    private MaVendorCommonDTO maVendorCommonDTO = new MaVendorCommonDTO();;

	public MaVendorCommonDTO getMaVendorCommonDTO() 
	{
		return maVendorCommonDTO;
	}

	public void setMaVendorCommonDTO(MaVendorCommonDTO maVendorCommonDTO) 
	{
		this.maVendorCommonDTO = maVendorCommonDTO;
	}

}
