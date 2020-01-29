package dream.work.rpt.pmins.orgach.form;

import common.struts.BaseForm;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchCommonDTO;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchDetailDTO;

/**
 * 예방점검 이행율(조직) 상세
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPminsOrgAchDetailForm"
 */
public class WorkRptPminsOrgAchDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
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
