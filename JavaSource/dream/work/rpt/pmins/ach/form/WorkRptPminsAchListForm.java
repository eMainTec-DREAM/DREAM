package dream.work.rpt.pmins.ach.form;

import common.struts.BaseForm;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchCommonDTO;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchDetailDTO;

/**
 * �������� ������(�����)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPminsAchListForm"
 */
public class WorkRptPminsAchListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO = new WorkRptPminsAchCommonDTO();
    
    private WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO = new WorkRptPminsAchDetailDTO();
    
    public WorkRptPminsAchCommonDTO getWorkRptPminsAchCommonDTO()
    {
        return workRptPminsAchCommonDTO;
    }

    public void setWorkRptPminsAchCommonDTO(
            WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO)
    {
        this.workRptPminsAchCommonDTO = workRptPminsAchCommonDTO;
    }
    
    public WorkRptPminsAchDetailDTO getWorkRptPminsAchDetailDTO()
    {
        return workRptPminsAchDetailDTO;
    }

    public void setWorkRptPminsAchDetailDTO(
            WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO)
    {
        this.workRptPminsAchDetailDTO = workRptPminsAchDetailDTO;
    }
	
}
