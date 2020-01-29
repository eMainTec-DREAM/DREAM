package dream.mgr.user.dto;

import common.bean.BaseDTO;

/**
 * »ç¿ëÀÚ ÆË¾÷ DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovUserListDTO extends BaseDTO
{
    /** Search Code */
    private String userNo     = "";
    /** Search Description */
    private String userName   = "";
    
    public String getUserNo()
    {
        return userNo;
    }
    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
   
}
