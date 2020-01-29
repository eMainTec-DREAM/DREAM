package dream.part.iss.wo.form;

import common.struts.BaseForm;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.PartIssWoItemDetailDTO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;

/**
 * �������Ȯ��- �� Form
 * @author  ssong
 * @version $Id: partIssWoItemDetailForm.java,v 1.0 2015/12/02 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="partIssWoItemDetailForm"
 */
public class PartIssWoItemDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private PartIssWoItemListDTO partIssWoItemListDTO = new PartIssWoItemListDTO();
    //========================================================================
    /** �� */
    private PartIssWoItemDetailDTO partIssWoItemDetailDTO = new PartIssWoItemDetailDTO();
    
    
    public PartIssWoItemListDTO getPartIssWoItemListDTO()
    {
        return partIssWoItemListDTO;
    }
    public void setPartIssWoItemListDTO(PartIssWoItemListDTO partIssWoItemListDTO)
    {
        this.partIssWoItemListDTO = partIssWoItemListDTO;
    }
    public PartIssWoItemDetailDTO getPartIssWoItemDetailDTO()
    {
        return partIssWoItemDetailDTO;
    }
    public void setPartIssWoItemDetailDTO(
            PartIssWoItemDetailDTO partIssWoItemDetailDTO)
    {
        this.partIssWoItemDetailDTO = partIssWoItemDetailDTO;
    }
    
    
    
    
    
    
}
