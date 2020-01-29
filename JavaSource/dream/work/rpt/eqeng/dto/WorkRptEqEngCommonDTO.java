package dream.work.rpt.eqeng.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.BaseDTO;

/**
 * ��������뷮(����) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptEqEngCommonDTO extends BaseDTO
{
    /** ����(��) */
    private String filterYear    		= "";
    /** ���� */
    private String filterPlant    		= "";
    /** ���� desc */
    private String filterPlantDesc    	= "";
    /** ��ġ ID */
    private String filterEqLocId    	= "";
    /** ��ġ DESC */
    private String filterEqLocDesc    	= "";
    /** ���μ� ID */
    private String filterUsageDeptId    = "";
    /** ���μ� DESC */
    private String filterUsageDeptDesc  = "";
    /** ���� ID */
    private String filterEqCtgId    	= "";
    /** ���� DESC */
    private String filterEqCtgDesc    	= "";
    /** ���� ID */
    private String filterEquipId    	= "";
    /** ���� DESC */
    private String filterEquipDesc    	= "";
	
    private List totalUsageList = new ArrayList<Map>();
    
    // ��������
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