package dream.rcm.crity.form;

import dream.comm.form.MaFinderAcForm;
import dream.rcm.crity.dto.CrityValLovDTO;

/**
 * Criticality Matrix Page - List Form
 * @author hyosung
 * @version $Id: CrityValLovForm.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
 * @since 1.0
 * @struts.form name="crityValLovForm"
 * 
 */
 public class CrityValLovForm extends MaFinderAcForm{
	
	private CrityValLovDTO crityValLovDTO =new CrityValLovDTO();

    public CrityValLovDTO getCrityValLovDTO()
    {
        return crityValLovDTO;
    }

    public void setCrityValLovDTO(CrityValLovDTO crityValLovDTO)
    {
        this.crityValLovDTO = crityValLovDTO;
    }
	
	
	
}
