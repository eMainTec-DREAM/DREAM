package dream.consult.program.menu.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.program.menu.dto.LovConsultMenuAcListDTO;

/**
 * ����Ʈ �޴� Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovConsultMenuAcListForm"
 */
public class LovConsultMenuAcListForm extends MaFinderAcForm
{
    /** �޴� DTO */
    private LovConsultMenuAcListDTO lovConsultMenuAcListDTO = new LovConsultMenuAcListDTO();

	public LovConsultMenuAcListDTO getLovConsultMenuAcListDTO() 
	{
		return lovConsultMenuAcListDTO;
	}

	public void setLovConsultMenuAcListDTO(LovConsultMenuAcListDTO lovConsultMenuAcListDTO) 
	{
		this.lovConsultMenuAcListDTO = lovConsultMenuAcListDTO;
	}
}
