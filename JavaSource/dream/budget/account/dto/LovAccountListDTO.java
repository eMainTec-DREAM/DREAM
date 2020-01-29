package dream.budget.account.dto;

import common.bean.BaseDTO;

/**
 * 예산계정 LOV DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class LovAccountListDTO extends BaseDTO
{
    /** Search No */
    private String accntNo 		= "";
    /** Search description */
    private String description 	= "";
    
    public String getAccntNo()
    {
        return accntNo;
    }
    public void setAccntNo(String accntNo)
    {
        this.accntNo = accntNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    
}
