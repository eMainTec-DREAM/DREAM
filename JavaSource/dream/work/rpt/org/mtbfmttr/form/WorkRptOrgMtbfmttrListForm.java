package dream.work.rpt.org.mtbfmttr.form;

import common.struts.BaseForm;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrCommonDTO;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrDetailDTO;

/**
 * ������MTBF,MTTR
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptOrgMtbfmttrListForm"
 */
public class WorkRptOrgMtbfmttrListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO = new WorkRptOrgMtbfmttrCommonDTO();
    
    private WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO = new WorkRptOrgMtbfmttrDetailDTO();
    
    public WorkRptOrgMtbfmttrCommonDTO getWorkRptOrgMtbfmttrCommonDTO()
    {
        return workRptOrgMtbfmttrCommonDTO;
    }

    public void setWorkRptOrgMtbfmttrCommonDTO(
            WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO)
    {
        this.workRptOrgMtbfmttrCommonDTO = workRptOrgMtbfmttrCommonDTO;
    }
    
    public WorkRptOrgMtbfmttrDetailDTO getWorkRptOrgMtbfmttrDetailDTO()
    {
        return workRptOrgMtbfmttrDetailDTO;
    }

    public void setWorkRptOrgMtbfmttrDetailDTO(
            WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO)
    {
        this.workRptOrgMtbfmttrDetailDTO = workRptOrgMtbfmttrDetailDTO;
    }
	
}
