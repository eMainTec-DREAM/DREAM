package dream.invt.prc.dto;

import common.bean.BaseDTO;

/**
 * 구매절차 팝업 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class InvtPrcTpLovDTO extends BaseDTO
{
    /** Search Description */
    private String description 	= "";
    /** id */
    private String id 	= "";
    /** no */
    private String no 	= "";
    
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
    public String getNo()
    {
        return no;
    }
    public void setNo(String no)
    {
        this.no = no;
    }
    
}
