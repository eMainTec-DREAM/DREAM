package dream.mgr.user.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사용자 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MgrUserPlantauthDetailDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 			= "";
	/** 사용자 ID */
	private String userId 			= "";
	/** 권한부여여부 */
	private String isAuth 			= "";
	/** 공장 */
	private String plant 		    = "";
	/** 공장명 */
	private String plantDesc 		= "";
	/** 사용자공장권한Id */
	private String usrplantauthId   = "";
	
	private String remark           = "";
	
	
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getIsAuth()
    {
        return isAuth;
    }
    public void setIsAuth(String isAuth)
    {
        this.isAuth = isAuth;
    }
    public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getUsrplantauthId()
    {
        return usrplantauthId;
    }
    public void setUsrplantauthId(String usrplantauthId)
    {
        super.setAuditKey(usrplantauthId);
        this.usrplantauthId = usrplantauthId;
    }
}
