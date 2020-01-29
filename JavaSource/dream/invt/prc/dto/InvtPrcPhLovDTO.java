package dream.invt.prc.dto;

import common.bean.BaseDTO;

/**
 * 구매절차 소분류 팝업 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class InvtPrcPhLovDTO extends BaseDTO
{
    /** Search Description */
    private String description 	= "";
    /** id */
    private String id 	= "";

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    
}
