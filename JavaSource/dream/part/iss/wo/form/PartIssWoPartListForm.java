package dream.part.iss.wo.form;

import common.struts.BaseForm;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;
import dream.part.iss.wo.dto.PartIssWoPartListDTO;

/**
 * 자재출고WO - 목록 form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="partIssWoPartListForm"
 */
public class PartIssWoPartListForm extends BaseForm
{    
    private PartIssWoPartListDTO partIssWoPartListDTO = new PartIssWoPartListDTO();
    
    private MaPtIssDetailDTO maPtIssDetailDTO =new MaPtIssDetailDTO();
    
    public MaPtIssDetailDTO getMaPtIssDetailDTO()
    {
        return maPtIssDetailDTO;
    }

    public void setMaPtIssDetailDTO(MaPtIssDetailDTO maPtIssDetailDTO)
    {
        this.maPtIssDetailDTO = maPtIssDetailDTO;
    }

    public PartIssWoPartListDTO getPartIssWoPartListDTO()
    {
        return partIssWoPartListDTO;
    }

    public void setPartIssWoPartListDTO(PartIssWoPartListDTO partIssWoPartListDTO)
    {
        this.partIssWoPartListDTO = partIssWoPartListDTO;
    }
}
