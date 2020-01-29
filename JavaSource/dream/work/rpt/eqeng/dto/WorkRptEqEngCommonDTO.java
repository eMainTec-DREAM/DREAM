package dream.work.rpt.eqeng.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.BaseDTO;

/**
 * 에너지사용량(설비별) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptEqEngCommonDTO extends BaseDTO
{
    /** 일자(년) */
    private String filterYear    		= "";
    /** 공장 */
    private String filterPlant    		= "";
    /** 공장 desc */
    private String filterPlantDesc    	= "";
    /** 위치 ID */
    private String filterEqLocId    	= "";
    /** 위치 DESC */
    private String filterEqLocDesc    	= "";
    /** 사용부서 ID */
    private String filterUsageDeptId    = "";
    /** 사용부서 DESC */
    private String filterUsageDeptDesc  = "";
    /** 종류 ID */
    private String filterEqCtgId    	= "";
    /** 종류 DESC */
    private String filterEqCtgDesc    	= "";
    /** 설비 ID */
    private String filterEquipId    	= "";
    /** 설비 DESC */
    private String filterEquipDesc    	= "";
	
    private List totalUsageList = new ArrayList<Map>();
    
    // 지워야함
    private String totalCnt		    	= "";
	
	public String getFilterPlant() {
		return filterPlant;
	}
	public List getTotalUsageList() {
		return totalUsageList;
	}
	public void setTotalUsageList(List totalUsageList) {
		this.totalUsageList = totalUsageList;
	}
	public String getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
	}
	public void setFilterPlant(String filterPlant) {
		this.filterPlant = filterPlant;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getFilterYear() {
		return filterYear;
	}
	public void setFilterYear(String filterYear) {
		this.filterYear = filterYear;
	}
	public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterUsageDeptId() {
		return filterUsageDeptId;
	}
	public void setFilterUsageDeptId(String filterUsageDeptId) {
		this.filterUsageDeptId = filterUsageDeptId;
	}
	public String getFilterUsageDeptDesc() {
		return filterUsageDeptDesc;
	}
	public void setFilterUsageDeptDesc(String filterUsageDeptDesc) {
		this.filterUsageDeptDesc = filterUsageDeptDesc;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	public String getFilterEquipId() {
		return filterEquipId;
	}
	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}
}