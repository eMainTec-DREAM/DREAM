package dream.work.list.dept.sched.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 업체별 작업스케줄 dto
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class WorkListDeptSchedCommonDTO extends BaseDTO
{
    /** 필터 - 기간 */
    private String filterStartDate    	= "";
    private String filterEndDate    	= "";
    /** 필터 - 설비종류 ID */
    private String filterEqCtgId		= "";
    /** 필터 - 설비종류명 */
    private String filterEqCtgDesc		= "";
    /** 필터 - 공장 ID */
    private String filterPlantId		= "";
    /** 필터 - 공장명 */
    private String filterPlantDesc		= "";
    /** 부서 ID */
    private String deptId				= "";
    /** 부서명 */
    private String deptDesc				= "";
    /** 조회 기간 */
    private String startDate    		= "";
    private String endDate    			= "";
    /** 설비종류 ID */
    private String eqCtgId				= "";
    /** 설비종류명 */
    private String eqCtgDesc			= "";
    /** 공장 ID */
    private String plantId				= "";
    /** 공장명 */
    private String plantDesc			= "";
    
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
	}
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
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
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
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
    

}