package dream.part.iss.wo.form;

import common.struts.BaseForm;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;

/**
 * 자재출고확정 - 목록 form
 * @author  ssong
 * @version $Id: partIssWoItemListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="partIssWoItemListForm"
 */
public class PartIssWoItemListForm extends BaseForm
{    
    //===============================================================
    /** 자재출고확정 공통 */
    private PartIssWoItemListDTO partIssWoItemListDTO = new PartIssWoItemListDTO();
    
    private MaPtIssDetailDTO maPtIssDetailDTO =new MaPtIssDetailDTO();
    private MaPtIssCommonDTO maPtIssCommonDTO =new MaPtIssCommonDTO();
    
    
    
    public MaPtIssCommonDTO getMaPtIssCommonDTO()
    {
        return maPtIssCommonDTO;
    }

    public void setMaPtIssCommonDTO(MaPtIssCommonDTO maPtIssCommonDTO)
    {
        this.maPtIssCommonDTO = maPtIssCommonDTO;
    }
    
    

    public MaPtIssDetailDTO getMaPtIssDetailDTO()
    {
        return maPtIssDetailDTO;
    }

    public void setMaPtIssDetailDTO(MaPtIssDetailDTO maPtIssDetailDTO)
    {
        this.maPtIssDetailDTO = maPtIssDetailDTO;
    }

    public PartIssWoItemListDTO getPartIssWoItemListDTO()
    {
        return partIssWoItemListDTO;
    }

    public void setPartIssWoItemListDTO(PartIssWoItemListDTO partIssWoItemListDTO)
    {
        this.partIssWoItemListDTO = partIssWoItemListDTO;
    }
    
	
}
