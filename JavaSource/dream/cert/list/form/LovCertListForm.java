package dream.cert.list.form;

import dream.cert.list.dto.LovCertListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 자격증LOV  Form
 * @author  hyosung
 * @version $Id: LovCertListForm.java,v 1.0 2016/01/18 09:12:46 hyosung Exp $
 * @since   1.0
 *
 * @struts.form name="lovCertListForm"
 */
public class LovCertListForm extends MaFinderAcForm
{
    /** 자산팝업 DTO */
    private LovCertListDTO lovCertListDTO = new LovCertListDTO();

	public LovCertListDTO getLovCertListDTO() {
		return lovCertListDTO;
	}

	public void setLovCertListDTO(LovCertListDTO lovCertListDTO) {
		this.lovCertListDTO = lovCertListDTO;
	}
}
