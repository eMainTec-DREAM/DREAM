package dream.work.rpt.org.bd.form;

import common.struts.BaseForm;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdDetailDTO;

/**
 * 조직별 고장분석
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptOrgBdListForm"
 */
public class WorkRptOrgBdListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO = new WorkRptOrgBdCommonDTO();
    
    private WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO = new WorkRptOrgBdDetailDTO();
    
    public WorkRptOrgBdCommonDTO getWorkRptOrgBdCommonDTO()
    {
        return workRptOrgBdCommonDTO;
    }

    public void setWorkRptOrgBdCommonDTO(
            WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO)
    {
        this.workRptOrgBdCommonDTO = workRptOrgBdCommonDTO;
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
