package dream.work.rpt.pmins.orgach.form;

import common.struts.BaseForm;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchCommonDTO;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchDetailDTO;

/**
 * �������� ������(����) ��
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPminsOrgAchDetailForm"
 */
public class WorkRptPminsOrgAchDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO = new WorkRptPminsOrgAchCommonDTO();
    
    private WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO = new WorkRptPminsOrgAchDetailDTO();
    
    public WorkRptPminsOrgAchCommonDTO getWorkRptPminsOrgAchCommonDTO()
    {
        return workRptPminsOrgAchCommonDTO;
    }

    public void setWorkRptPminsOrgAchCommonDTO(
            WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO)
    {
        this.workRptPminsOrgAchCommonDTO = workRptPminsOrgAchCommonDTO;
    }
    
    public WorkRptPminsOrgAchDetailDTO getWorkRptPminsOrgAchDetailDTO()
    {
        return workRptPminsOrgAchDetailDTO;
    }

    public void setWorkRptPminsOrgAchDetailDTO(
            WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO)
    {
        this.workRptPminsOrgAchDetailDTO = workRptPminsOrgAchDetailDTO;
    }
	
}
