package dream.rcm.taskmap.dto;

import common.bean.BaseDTO;

/**
 * Task Map ÆË¾÷ DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class RcmTaskMapListLovDTO extends BaseDTO
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
