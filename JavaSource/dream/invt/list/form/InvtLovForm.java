package dream.invt.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.invt.list.dto.InvtLovDTO;

/**
 * 구매절차 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="invtLovForm"
 */
public class InvtLovForm extends MaFinderAcForm
{
    /** Task Map DTO */
    private InvtLovDTO invtLovDTO = new InvtLovDTO();

    public InvtLovDTO getInvtLovDTO()
    {
        return invtLovDTO;
    }

    public void setInvtLovDTO(InvtLovDTO invtLovDTO)
    {
        this.invtLovDTO = invtLovDTO;
    }

}
