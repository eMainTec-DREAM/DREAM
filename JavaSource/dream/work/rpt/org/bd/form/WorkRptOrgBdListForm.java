package dream.work.rpt.org.bd.form;

import common.struts.BaseForm;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdDetailDTO;

/**
 * ������ ����м�
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptOrgBdListForm"
 */
public class WorkRptOrgBdListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
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
