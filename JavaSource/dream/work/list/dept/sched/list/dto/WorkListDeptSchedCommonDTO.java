package dream.work.list.dept.sched.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��ü�� �۾������� dto
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class WorkListDeptSchedCommonDTO extends BaseDTO
{
    /** ���� - �Ⱓ */
    private String filterStartDate    	= "";
    private String filterEndDate    	= "";
    /** ���� - �������� ID */
    private String filterEqCtgId		= "";
    /** ���� - ���������� */
    private String filterEqCtgDesc		= "";
    /** ���� - ���� ID */
    private String filterPlantId		= "";
    /** ���� - ����� */
    private String filterPlantDesc		= "";
    /** �μ� ID */
    private String deptId				= "";
    /** �μ��� */
    private String deptDesc				= "";
    /** ��ȸ �Ⱓ */
    private String startDate    		= "";
    private String endDate    			= "";
    /** �������� ID */
    private String eqCtgId				= "";
    /** ���������� */
    private String eqCtgDesc			= "";
    /** ���� ID */
    private String plantId				= "";
    /** ����� */
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