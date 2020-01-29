package dream.work.rpt.org.bd.form;

import common.struts.BaseForm;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdDetailDTO;

/**
 * 조직별 고장분석 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptOrgBdDetailForm"
 */
public class WorkRptOrgBdDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkRptOrgBdCommonDTO workRptOrgCommonDTO = new WorkRptOrgBdCommonDTO();
    
    private WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO = new WorkRptOrgBdDetailDTO();
    
    public WorkRptOrgBdCommonDTO getWorkRptOrgCommonDTO()
    {
        return workRptOrgCommonDTO;
    }

    public void setWorkRptOrgCommonDTO(
            WorkRptOrgBdCommonDTO workRptOrgCommonDTO)
    {
        this.workRptOrgCommonDTO = workRptOrgCommonDTO;
    }
    
    public WorkRptOrgBdDetailDTO getWorkRptOrgBdDetailDTO()
    {
        return workRptOrgBdDetailDTO;
    }

    public void setWorkRptOrgBdDetailDTO(
            WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO)
    {
        this.workRptOrgBdDetailDTO = workRptOrgBdDetailDTO;
    }
	
}
