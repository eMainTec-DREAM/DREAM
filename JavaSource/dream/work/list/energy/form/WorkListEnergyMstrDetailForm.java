package dream.work.list.energy.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;


/**
 * ���������� - �������� ��� �� Form
 * @author  sy.yang
 * @version $Id: WorkListEnergyMstrDetailForm.java,v 1.0 2015/12/02 09:13:09 sy.yang Exp $
 * @since   1.0
 *
 * @struts.form name="workListEnergyMstrDetailForm"
 */
public class WorkListEnergyMstrDetailForm extends BaseForm
{
    //========================================================================
    /** �۾���� ���� */ 
    private WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = new WorkListEnergyMstrListCommonDTO();
    //========================================================================
    /** �۾���� �� */
    private WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = new WorkListEnergyMstrDetailDTO();
    
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
//    /** �۾���� ���峻�� �� DTO  */
//    private MaWoResultFailDetailDTO maWoResultFailDetailDTO = new MaWoResultFailDetailDTO();
//    /** ���� ���� �̷� DTO  */
//    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();


	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
    
	public WorkListEnergyMstrListCommonDTO getWorkListEnergyMstrListCommonDTO() {
		return workListEnergyMstrListCommonDTO;
	}

    public void setWorkListEnergyMstrListCommonDTO(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO) {
		this.workListEnergyMstrListCommonDTO = workListEnergyMstrListCommonDTO;
	}

	public WorkListEnergyMstrDetailDTO getWorkListEnergyMstrDetailDTO() {
		return workListEnergyMstrDetailDTO;
	}

	public void setWorkListEnergyMstrDetailDTO(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO) {
		this.workListEnergyMstrDetailDTO = workListEnergyMstrDetailDTO;
	}
}
