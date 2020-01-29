package dream.work.rpt.mabmctgchart.dto;

import common.bean.BaseDTO;

/**
 * ��������������м� DTO
 * @author  kim21017
 * @version $Id: MaBmCtgChartDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaBmCtgChartDTO extends BaseDTO
{
	/** ����-�μ����̵� */
	private String filterDeptDesc 		= "";
	/** ����-�μ��� */
	private String filterDeptId 		= "";
	/** ����-������μ����̵� */
	private String filterUsageDeptDesc 	= "";
	/** ����-������μ��� */
	private String filterUsageDeptId 	= "";
	/** ����-�� */
	private String filterYyyy 			= "";
	/** ��ġid - ���� */
	private String filterEqLocId		= "";
	/** ��ġ�� - ���� */
	private String filterEqLocDesc		= "";
	/** ����id - ���� */
	private String filterEqCtgId		= "";
	/** ������ - ���� */
	private String filterEqCtgDesc		= "";
	/** ����-������(��)id */
	private String filterMainMngId		= "";
	/** ����-������(��)�� */
	private String filterMainMngName	= "";
	/** ����-������(��)id */
	private String filterSubMngId		= "";
	/** ����-������(��)�� */
	private String filterSubMngName		= "";
	/** ����-�������񿩺� */
	private String filterIsLawEq		= "";
    /** ����Id */
    private String filterEquipId        = "";
    /** ����� */
    private String filterEquipDesc      = "";
    /** ����-���� ID */
    private String filterPlantId            = "";
    /** ����-���� DESC */
    private String filterPlantDesc          = "";
	/** Ŭ�� �� ��*/
	private String yyyy 				= "";
	/** Ŭ�� �� ����*/
	private String eqlocId 				= "";
	/** Ŭ�� �� ����desc*/
    private String eqlocDesc              = "";
	/** Ŭ�� �� ����*/
	private String eqctgId 				= "";
	/** Ŭ�� �� ����desc*/
    private String eqctgDesc              = "";
	/** Ŭ�� �� �μ�*/
	private String deptId 				= "";
	/** Ŭ�� �� ������μ�*/
	private String usageDeptId 			= "";
	/** Ŭ�� �� ������μ�desc*/
	private String usageDeptDesc        = "";
	
	
	public String getUsageDeptId()
    {
        return usageDeptId;
    }
    public void setUsageDeptId(String usageDeptId)
    {
        this.usageDeptId = usageDeptId;
    }
    public String getUsageDeptDesc()
    {
        return usageDeptDesc;
    }
    public void setUsageDeptDesc(String usageDeptDesc)
    {
        this.usageDeptDesc = usageDeptDesc;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
    }
    public String getFilterUsageDeptId()
    {
        return filterUsageDeptId;
    }
    public void setFilterUsageDeptId(String filterUsageDeptId)
    {
        this.filterUsageDeptId = filterUsageDeptId;
    }
    public String getEqlocDesc()
    {
        return eqlocDesc;
    }
    public void setEqlocDesc(String eqlocDesc)
    {
        this.eqlocDesc = eqlocDesc;
    }
    public String getEqctgDesc()
    {
        return eqctgDesc;
    }
    public void setEqctgDesc(String eqctgDesc)
    {
        this.eqctgDesc = eqctgDesc;
    }
    public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	public String getFilterMainMngId() {
		return filterMainMngId;
	}
	public void setFilterMainMngId(String filterMainMngId) {
		this.filterMainMngId = filterMainMngId;
	}
	public String getFilterMainMngName() {
		return filterMainMngName;
	}
	public void setFilterMainMngName(String filterMainMngName) {
		this.filterMainMngName = filterMainMngName;
	}
	public String getFilterSubMngId() {
		return filterSubMngId;
	}
	public void setFilterSubMngId(String filterSubMngId) {
		this.filterSubMngId = filterSubMngId;
	}
	public String getFilterSubMngName() {
		return filterSubMngName;
	}
	public void setFilterSubMngName(String filterSubMngName) {
		this.filterSubMngName = filterSubMngName;
	}
	public String getFilterIsLawEq() {
		return filterIsLawEq;
	}
	public void setFilterIsLawEq(String filterIsLawEq) {
		this.filterIsLawEq = filterIsLawEq;
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
	public String getEqlocId() {
		return eqlocId;
	}
	public void setEqlocId(String eqlocId) {
		this.eqlocId = eqlocId;
	}
	public String getEqctgId() {
		return eqctgId;
	}
	public void setEqctgId(String eqctgId) {
		this.eqctgId = eqctgId;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterYyyy() {
		return filterYyyy;
	}
	public void setFilterYyyy(String filterYyyy) {
		this.filterYyyy = filterYyyy;
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
