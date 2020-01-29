package dream.asset.rpt.lcc.equip.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 고장TOP(설비) 상세 dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptLccEquipDetailDTO extends BaseDTO
{
    /** 설비 no */
    private String itemNo    = "";
    /** 위치 */
    private String eqLocId    = "";
    /** 위치 desc */
    private String eqLocDesc    = "";
    /** 부서 */
    private String deptId    = "";
    /** 부서 desc */
    private String deptDesc    = "";
    /** 종류 */
    private String eqCtgId    = "";
    /** 종류 desc */
    private String eqCtgDesc    = "";
    /** 설비 desc */
    private String itemDesc    = "";
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
    private String endDate    = "";
    /** 선택된 차트 value */
    private String chartValue   = "";
    /** 공장 ID */
    private String plantId        	= "";
    /** 공장 DESC */
    private String plantDesc      	= "";
    
    
    public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getChartValue()
    {
        return chartValue;
    }
    public void setChartValue(String chartValue)
    {
        this.chartValue = chartValue;
    }
    public String getItemNo()
    {
        return itemNo;
    }
    public void setItemNo(String itemNo)
    {
        this.itemNo = itemNo;
    }
    public String getEqLocId()
    {
        return eqLocId;
    }
    public void setEqLocId(String eqLocId)
    {
        this.eqLocId = eqLocId;
    }
    public String getEqLocDesc()
    {
        return eqLocDesc;
    }
    public void setEqLocDesc(String eqLocDesc)
    {
        this.eqLocDesc = eqLocDesc;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getEqCtgDesc()
    {
        return eqCtgDesc;
    }
    public void setEqCtgDesc(String eqCtgDesc)
    {
        this.eqCtgDesc = eqCtgDesc;
    }
    public String getItemDesc()
    {
        return itemDesc;
    }
    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = CommonUtil.convertDate(startDate);
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = CommonUtil.convertDate(endDate);
    }
    
}