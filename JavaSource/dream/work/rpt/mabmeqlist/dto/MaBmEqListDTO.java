package dream.work.rpt.mabmeqlist.dto;

import common.bean.BaseDTO;

/**
 * ���� ����м�DTO
 * @author  kim21017
 * @version $Id: MaBmEqListDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaBmEqListDTO extends BaseDTO
{
	/** ����-�μ����̵� */
	private String filterDeptDesc 		= "";
	/** ����-�μ��� */
	private String filterDeptId 		= "";
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
}
