package dream.consult.comp.eqmgr.dto;

import common.bean.BaseDTO;

/**
 * �������� ������� DTO
 * @author  jung7126
 * @version $Id: MaEqMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaEqMngCommonDTO extends BaseDTO
{
	/** ����ID */
	private String equipId					= "";
	/** ����-����� */
	private String filterEquipDesc			= "";
	/** ����-��ġid */
	private String filterEqLocId			= "";
	/** ����-��ġ�� */
	private String filterEqLocDesc			= "";
	/** ����-����ڵ�id(����) */
	private String filterEqCtgId			= "";
	/** ����-����ڵ��(����) */
	private String filterEqCtgDesc			= "";
	/** ����-��/���� */
	private String filterPlfTypeId			= "";
	/** ����-��/���� �� */
	private String filterPlfTypeDesc		= "";
	/** ����-�����μ�id */
	private String filterDeptId				= "";
	/** ����-�����μ��� */
	private String filterDeptDesc			= "";
	/** ����-�������񿩺� */
	private String filterIsLawEq			= "";
	/** ����-������(��)id */
	private String filterMainMngId			= "";
	/** ����-������(��)�� */
	private String filterMainMngName		= "";
	/** ����-������(��)id */
	private String filterSubMngId			= "";
	/** ����-������(��)�� */
	private String filterSubMngName			= "";
	/** ����-���ۻ� */
	private String filterMaker				= "";
	/** ����-�𵨹�ȣ */
	private String filterModelNo			= "";
	/** ����-�����ȣ */
	private String filterItemNo				= "";
	
	public String getFilterItemNo() {
		return filterItemNo;
	}

	public void setFilterItemNo(String filterItemNo) {
		this.filterItemNo = filterItemNo;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}

	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
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

	public String getFilterPlfTypeId() {
		return filterPlfTypeId;
	}

	public void setFilterPlfTypeId(String filterPlfTypeId) {
		this.filterPlfTypeId = filterPlfTypeId;
	}

	public String getFilterPlfTypeDesc() {
		return filterPlfTypeDesc;
	}

	public void setFilterPlfTypeDesc(String filterPlfTypeDesc) {
		this.filterPlfTypeDesc = filterPlfTypeDesc;
	}

	public String getFilterDeptId() {
		return filterDeptId;
	}

	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}

	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}

	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}

	public String getFilterIsLawEq() {
		return filterIsLawEq;
	}

	public void setFilterIsLawEq(String filterIsLawEq) {
		this.filterIsLawEq = filterIsLawEq;
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

	public String getFilterMaker() {
		return filterMaker;
	}

	public void setFilterMaker(String filterMaker) {
		this.filterMaker = filterMaker;
	}

	public String getFilterModelNo() {
		return filterModelNo;
	}

	public void setFilterModelNo(String filterModelNo) {
		this.filterModelNo = filterModelNo;
	}
	
}
