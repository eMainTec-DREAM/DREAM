package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 부품마스터 List DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPtMstrListDTO extends BaseDTO
{
    
    /** 자재Id */
    private String partId = "";

    public String getPartId()
    {
        return partId;
    }

    public void setPartId(String partId)
    {
        this.partId = partId;
    }
   
    
}
