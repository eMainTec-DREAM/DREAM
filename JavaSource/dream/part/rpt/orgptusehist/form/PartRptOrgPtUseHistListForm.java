package dream.part.rpt.orgptusehist.form;

import common.struts.BaseForm;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistCommonDTO;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistDetailDTO;


/**
 * PartRptOrgPtUseHist Page - List Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="partRptOrgPtUseHistListForm"
 * */
public class PartRptOrgPtUseHistListForm extends BaseForm {
    
    private PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO = new PartRptOrgPtUseHistCommonDTO();
    private PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO = new PartRptOrgPtUseHistDetailDTO();

    public PartRptOrgPtUseHistCommonDTO getPartRptOrgPtUseHistCommonDTO() {
        return partRptOrgPtUseHistCommonDTO;
    }

    public void setPartRptOrgPtUseHistCommonDTO(PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO) {
        this.partRptOrgPtUseHistCommonDTO = partRptOrgPtUseHistCommonDTO;
    }

    public PartRptOrgPtUseHistDetailDTO getPartRptOrgPtUseHistDetailDTO()
    {
        return partRptOrgPtUseHistDetailDTO;
    }

    public void setPartRptOrgPtUseHistDetailDTO(
            PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO)
    {
        this.partRptOrgPtUseHistDetailDTO = partRptOrgPtUseHistDetailDTO;
    }
    
}