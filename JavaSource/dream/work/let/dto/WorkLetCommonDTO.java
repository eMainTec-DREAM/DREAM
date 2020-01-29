package dream.work.let.dto;

import common.bean.BaseDTO;

/**
 * �����۾� ���� DTO
 * @author  syyang
 * @version $Id: WorkLetListCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class WorkLetCommonDTO extends BaseDTO
{
	/** �����۾��㰡�� ID */
	private String woLetId				= "";

	/** �����۾��� - ���� */
	private String filterWoLetDesc		= "";
	/** �����۾���ȣ - ���� */
	private String filterWoLetNo		= "";
	/** ��û�μ�id - ���� */
	private String filterReqDeptId		= "";
	/** ��û�μ��� - ���� */
	private String filterReqDeptDesc	= "";
	/** ��û��id - ���� */
	private String filterReqById		= "";
	/** ��û�ڸ� - ���� */
	private String filterReqByDesc		= "";
	/** �㰡�μ�id - ���� */
	private String filterRecDeptId		= "";
	/** �㰡�μ��� - ���� */
	private String filterRecDeptDesc	= "";
	/** �㰡��id - ���� */
	private String filterRecById		= "";
	/** �㰡�ڸ� - ���� */
	private String filterRecByDesc		= "";
	/** ����/��� - ���� */
	private String filterItemDesc		= "";
	/** �۾���� - ���� */
	private String filterPlace			= "";
	/** ����Id */
    private String filterPlant        	= "";
    /** ����� */
    private String filterPlantDesc      = "";
	/** �۾���ȣ ID - ���� */
	private String filterWkorId			= "";
	/** �۾���ȣ - ���� */
	private String filterWoNo			= "";
	/** �۾��Ⱓ From ������ - ���� */
	private String filterFromStartDate	= "";
	/** �۾��Ⱓ From ������ - ���� */
	private String filterFromEndDate	= "";
    /** �۾��Ⱓ To - ������ */
    private String filterToStartDate	= "";
    /** �۾��Ⱓ To - ������ */
    private String filterToEndDate		= "";
    
    
	public String getWoLetId() {
		return woLetId;
	}
	public void setWoLetId(String woLetId) {
		this.woLetId = woLetId;
		super.setAuditKey(woLetId);
	}
	public String getFilterWoLetDesc() {
		return filterWoLetDesc;
	}
	public void setFilterWoLetDesc(String filterWoLetDesc) {
		this.filterWoLetDesc = filterWoLetDesc;
	}
	public String getFilterWoLetNo() {
		return filterWoLetNo;
	}
	public void setFilterWoLetNo(String filterWoLetNo) {
		this.filterWoLetNo = filterWoLetNo;
	}
	public String getFilterReqDeptId() {
		return filterReqDeptId;
	}
	public void setFilterReqDeptId(String filterReqDeptId) {
		this.filterReqDeptId = filterReqDeptId;
	}
	public String getFilterReqDeptDesc() {
		return filterReqDeptDesc;
	}
	public void setFilterReqDeptDesc(String filterReqDeptDesc) {
		this.filterReqDeptDesc = filterReqDeptDesc;
	}
	public String getFilterReqById() {
		return filterReqById;
	}
	public void setFilterReqById(String filterReqById) {
		this.filterReqById = filterReqById;
	}
	public String getFilterReqByDesc() {
		return filterReqByDesc;
	}
	public void setFilterReqByDesc(String filterReqByDesc) {
		this.filterReqByDesc = filterReqByDesc;
	}
	public String getFilterRecDeptId() {
		return filterRecDeptId;
	}
	public void setFilterRecDeptId(String filterRecDeptId) {
		this.filterRecDeptId = filterRecDeptId;
	}
	public String getFilterRecDeptDesc() {
		return filterRecDeptDesc;
	}
	public void setFilterRecDeptDesc(String filterRecDeptDesc) {
		this.filterRecDeptDesc = filterRecDeptDesc;
	}
	public String getFilterRecById() {
		return filterRecById;
	}
	public void setFilterRecById(String filterRecById) {
		this.filterRecById = filterRecById;
	}
	public String getFilterRecByDesc() {
		return filterRecByDesc;
	}
	public void setFilterRecByDesc(String filterRecByDesc) {
		this.filterRecByDesc = filterRecByDesc;
	}
	public String getFilterItemDesc() {
		return filterItemDesc;
	}
	public void setFilterItemDesc(String filterItemDesc) {
		this.filterItemDesc = filterItemDesc;
	}
	public String getFilterPlace() {
		return filterPlace;
	}
	public void setFilterPlace(String filterPlace) {
		this.filterPlace = filterPlace;
	}
	public String getFilterPlant() {
		return filterPlant;
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
	public String getFilterWkorId() {
		return filterWkorId;
	}
	public void setFilterWkorId(String filterWkorId) {
		this.filterWkorId = filterWkorId;
	}
	public String getFilterWoNo() {
		return filterWoNo;
	}
	public void setFilterWoNo(String filterWoNo) {
		this.filterWoNo = filterWoNo;
	}
	public String getFilterFromStartDate() {
		return filterFromStartDate;
	}
	public void setFilterFromStartDate(String filterFromStartDate) {
		this.filterFromStartDate = filterFromStartDate;
	}
	public String getFilterFromEndDate() {
		return filterFromEndDate;
	}
	public void setFilterFromEndDate(String filterFromEndDate) {
		this.filterFromEndDate = filterFromEndDate;
	}
	public String getFilterToStartDate() {
		return filterToStartDate;
	}
	public void setFilterToStartDate(String filterToStartDate) {
		this.filterToStartDate = filterToStartDate;
	}
	public String getFilterToEndDate() {
		return filterToEndDate;
	}
	public void setFilterToEndDate(String filterToEndDate) {
		this.filterToEndDate = filterToEndDate;
	}
    
}
