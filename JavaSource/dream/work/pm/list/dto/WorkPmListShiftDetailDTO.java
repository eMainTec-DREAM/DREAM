package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������ �� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class WorkPmListShiftDetailDTO extends BaseDTO
{
	/** ������ID */
	private String pmWrkShiftId			= "";
	/** ������ type*/
	private String shiftType				= "";
	/** ������ type Desc*/
    private String shiftTypeDesc         = "";
	/** ��뿩�� */
	private String isActive             = "";
	/** ��� */
	private String remark               = "";
	
    public String getPmWrkShiftId()
    {
        return pmWrkShiftId;
    }
    public void setPmWrkShiftId(String pmWrkShiftId)
    {
        this.pmWrkShiftId = pmWrkShiftId;
    }
    public String getShiftType()
    {
        return shiftType;
    }
    public void setShiftType(String shiftType)
    {
        this.shiftType = shiftType;
    }
    public String getShiftTypeDesc()
    {
        return shiftTypeDesc;
    }
    public void setShiftTypeDesc(String shiftTypeDesc)
    {
        this.shiftTypeDesc = shiftTypeDesc;
    }
    public String getIsActive()
    {
        return isActive;
    }
    public void setIsActive(String isActive)
    {
        this.isActive = isActive;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	
}