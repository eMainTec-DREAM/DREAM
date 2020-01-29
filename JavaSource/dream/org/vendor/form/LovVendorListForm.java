package dream.org.vendor.form;

import common.struts.BaseForm;
import dream.comm.form.MaFinderAcForm;
import dream.org.vendor.dto.LovVendorListDTO;

/**
 * °Å·¡Ã³ÆË¾÷ Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovVendorListForm"
 */
public class LovVendorListForm extends MaFinderAcForm
{
    /** °Å·¡Ã³ÆË¾÷ DTO */
    private LovVendorListDTO lovVendorListDTO = new LovVendorListDTO();

	public LovVendorListDTO getLovVendorListDTO() 
	{
		return lovVendorListDTO;
	}

	public void setLovVendorListDTO(LovVendorListDTO lovVendorListDTO) 
	{
		this.lovVendorListDTO = lovVendorListDTO;
	}
}
