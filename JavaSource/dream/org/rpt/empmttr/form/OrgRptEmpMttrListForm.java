package dream.org.rpt.empmttr.form;

import common.struts.BaseForm;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrCommonDTO;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrDetailDTO;

/**
 * MTTR(담당자)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="orgRptEmpMttrListForm"
 */
public class OrgRptEmpMttrListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO = new OrgRptEmpMttrCommonDTO();
    
    private OrgRptEmpMttrDetailDTO orgRptEmpMttrDetailDTO = new OrgRptEmpMttrDetailDTO();
    
    public OrgRptEmpMttrCommonDTO getOrgRptEmpMttrCommonDTO()
    {
        return orgRptEmpMttrCommonDTO;
    }

    public void setOrgRptEmpMttrCommonDTO(
            OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO)
    {
        this.orgRptEmpMttrCommonDTO = orgRptEmpMttrCommonDTO;
    }
    
    public OrgRptEmpMttrDetailDTO getOrgRptEmpMttrDetailDTO()
    {
        return orgRptEmpMttrDetailDTO;
    }

    public void setOrgRptEmpMttrDetailDTO(
            OrgRptEmpMttrDetailDTO orgRptEmpMttrDetailDTO)
    {
        this.orgRptEmpMttrDetailDTO = orgRptEmpMttrDetailDTO;
    }
	
}
