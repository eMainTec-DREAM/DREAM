package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 교대조 상세 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class WorkPmListShiftDetailDTO extends BaseDTO
{
	/** 교대조ID */
	private String pmWrkShiftId			= "";
	/** 교대조 type*/
	private String shiftType				= "";
	/** 교대조 type Desc*/
    private String shiftTypeDesc         = "";
	/** 사용여부 */
	private String isActive             = "";
	/** 비고 */
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