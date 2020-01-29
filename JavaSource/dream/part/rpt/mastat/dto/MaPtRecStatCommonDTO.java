package dream.part.rpt.mastat.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재입고내역 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtRecStatCommonDTO extends BaseDTO
{	
	/** 필터-창고Id */
	private String filterDeptId        = "";
	private String filterDeptDesc      = "";
	/** 필터-입고일자 */
	private String filterRecStartDate  = "";
	private String filterRecEndDate    = "";
	/** 필터-공장 ID */
    private String filterPlantId       = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc     = "";
	
    /** 필터-부품 ID */
    private String filterPartId        = "";
    /** 필터-부품 NO */
    private String filterPartNo        = "";
    /** 필터-부품명 */
    private String filterPartDesc      = "";
    
    public String getFilterPartId() {
		return filterPartId;
	}
	public String getFilterPartNo() {
		return filterPartNo;
	}
	public void setFilterPartNo(String filterPartNo) {
		this.filterPartNo = filterPartNo;
	}
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	public void setFilterPartId(String filterPartId) {
		this.filterPartId = filterPartId;
	}
	public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getFilterRecStartDate()
    {
        return filterRecStartDate;
    }
    public void setFilterRecStartDate(String filterRecStartDate)
    {
        this.filterRecStartDate = CommonUtil.convertDate(filterRecStartDate);
    }
    public String getFilterRecEndDate()
    {
        return filterRecEndDate;
    }
    public void setFilterRecEndDate(String filterRecEndDate)
    {
        this.filterRecEndDate = CommonUtil.convertDate(filterRecEndDate);
    }

}
