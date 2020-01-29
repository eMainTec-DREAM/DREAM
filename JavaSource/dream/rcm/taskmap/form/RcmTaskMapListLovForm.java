package dream.rcm.taskmap.form;

import dream.comm.form.MaFinderAcForm;
import dream.rcm.taskmap.dto.RcmTaskMapListLovDTO;

/**
 * Task Map Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="rcmTaskMapListLovForm"
 */
public class RcmTaskMapListLovForm extends MaFinderAcForm
{
    /** Task Map DTO */
    private RcmTaskMapListLovDTO rcmTaskMapListLovDTO = new RcmTaskMapListLovDTO();

    public RcmTaskMapListLovDTO getRcmTaskMapListLovDTO()
    {
        return rcmTaskMapListLovDTO;
    }

    public void setRcmTaskMapListLovDTO(RcmTaskMapListLovDTO rcmTaskMapListLovDTO)
    {
        this.rcmTaskMapListLovDTO = rcmTaskMapListLovDTO;
    }

}
