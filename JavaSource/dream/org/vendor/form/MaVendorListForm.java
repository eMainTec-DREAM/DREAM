package dream.org.vendor.form;

import common.struts.BaseForm;
import dream.org.vendor.dto.MaVendorCommonDTO;

/**
 * ���þ�ü - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maVendorListForm"
 */
public class MaVendorListForm extends BaseForm
{    
    //===============================================================
    /** ���þ�ü ���� */
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
