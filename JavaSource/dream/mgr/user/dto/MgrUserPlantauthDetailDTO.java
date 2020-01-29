package dream.mgr.user.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MgrUserPlantauthDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 			= "";
	/** ����� ID */
	private String userId 			= "";
	/** ���Ѻο����� */
	private String isAuth 			= "";
	/** ���� */
	private String plant 		    = "";
	/** ����� */
	private String plantDesc 		= "";
	/** ����ڰ������Id */
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
