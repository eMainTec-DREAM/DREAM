package dream.work.cal.pmyear.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����۾����� ���� DTO
 * @author  kim21017
 * @version $Id: MaWoYearSchedCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaWoYearSchedCommonDTO extends BaseDTO
{
	/** ������ ID */
	private String pmSchedId 				= "";
	/** ���� �� */
	private String filterYear	 			= "";
	/** ���� ��ȹ�μ�id */
	private String filterDeptId 			= "";
	/** ���� ��ȹ�μ��� */
	private String filterDeptDesc 			= "";
	/** ���� ����μ�id */
	private String filterExeDeptId 			= "";
	/** ���� ����μ��� */
	private String filterExeDeptDesc 		= "";
	/** ���� ����id */
	private String filterEquipId 			= "";
	/** ���� �����ȣ */
	private String filterEquipNo 			= "";
	/** ���� ����� */
	private String filterEquipDesc 			= "";
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
	/** ����-��ġid */
	private String filterEqLocId			= "";
	/** ����-��ġ�� */
	private String filterEqLocDesc			= "";
	/** ����-����id */
	private String filterEqCtgId			= "";
	/** ����-���� */
	private String filterEqCtgDesc			= "";
	/** ����-��/���� */
	private String filterPlfTypeId			= "";
	/** ����-��/���� �� */
	private String filterPlfTypeDesc		= "";
	/** ����-�۾����� */
	private String filterWoTypeId			= "";
	/** ����-�۾����� �� */
	private String filterWoTypeDesc			= "";
	/** ����-�۾����� */
	private String filterPmTypeId			= "";
	/** ����-�۾����� �� */
	private String filterPmTypeDesc			= "";
	/** ����-�۾��׷� */
	private String filterWkCtrId			= "";
	/** ����-�۾��׷� �� */
	private String filterWkCtrDesc			= "";
	/** ����-pmNo */
	private String filterPmNo				= "";
	/** Filter-��������Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-�������� */
	private String filterEqCtgTypeDesc 	   = "";
	/** Filter-��ȹ�����Id */
    private String filterPlanEmpId          = "";
    /** Filter-��ȹ����� */
    private String filterPlanEmpDesc        = "";
    /** Filter-��������Id */
    private String filterEmpId              = "";
    /** Filter-�������� */
    private String filterEmpDesc            = "";
	/** ��� */
	private String yyyymm 					= "";
	/** schedType */
	private String schedType 				= "";
	/** ��ȹ�μ�id(�˻��� �μ�id ����)*/
	private String deptId 					= "";
	/** ��ȹ�μ���(�˻��� �μ��� ����) */
	private String deptDesc 				= "";
	/** ����μ�id(�˻��� �μ�id ����)*/
	private String exeDeptId 				= "";
	/** ����μ���(�˻��� �μ��� ����) */
	private String exeDeptDesc 				= "";
	/** �������񿩺� */
	private String isLawEq					= "";
	/** ������(��)id */
	private String mainMngId				= "";
	/** ������(��)�� */
	private String mainMngName				= "";
	/** ������(��)id */
	private String subMngId					= "";
	/** ������(��)�� */
	private String subMngName				= "";
	/** ��ġid */
	private String eqLocId					= "";
	/** ��ġ�� */
	private String eqLocDesc				= "";
	/** ����id */
	private String eqCtgId					= "";
	/** ���� */
	private String eqCtgDesc				= "";
	/** ��/���� */
	private String plfTypeId				= "";
	/** ��/���� �� */
	private String plfTypeDesc				= "";
	/** �۾����� */
	private String woTypeId					= "";
	/** �۾����� �� */
	private String woTypeDesc				= "";
	/** �۾����� */
	private String pmTypeId					= "";
	/** �۾����� �� */
	private String pmTypeDesc				= "";
	/** �۾��׷� */
	private String wkCtrId					= "";
	/** �۾��׷� �� */
	private String wkCtrDesc				= "";
	/** ����id */
	private String equipId					= "";
	/** �����ȣ */
	private String equipNo					= "";
	/** ���� �� */
	private String equipDesc				= "";
	/** pm��ȣ */
	private String pmNo						= "";
	/** ��������Id */
	private String eqCtgTypeId 	   			= "";
	/** �������� */
	private String eqCtgTypeDesc 	   		= "";
	/** ��ȹ�����Id */
    private String planEmpId                = "";
    /** ��ȹ����� */
    private String planEmpDesc              = "";
    /** ��������Id */
    private String empId                    = "";
    /** �������� */
    private String empDesc                  = "";
    /** ����-���� ID */
    private String filterPlantId         	= "";
    /** ����-���� DESC */
    private String filterPlantDesc         	= "";
    /** ���� ID */
    private String plantId         			= "";
    /** ���� DESC */
    private String plantDesc         		= "";
    

    /** ���� - �������� ID */
    private String filterPequipId			= "";
    /** ���� - �������� DESC */
    private String filterPequipDesc			= "";
    /** ���� - ������μ� ID */
    private String filterEqUsaDeptId		= "";
    /** ���� - ������μ� DESC */
    private String filterEqUsaDeptDesc		= "";
    /** ���� - ����������μ� ID */
    private String filterPEqUsaDeptId		= "";
    /** ���� - ����������μ� DESC */
    private String filterPEqUsaDeptDesc		= "";
    /** ���� - Room No. */
    private String filterRoomNumber         = "";
    /** ���� - �������� ID */
    private String filterCalTypeId          = "";
    /** ���� - �������� DESC */
    private String filterCalTypeDesc        = "";
    /** �������� ID */
    private String pequipId					= "";
    /** �������� DESC */
    private String pequipDesc				= "";
    /** ������μ� ID */
    private String eqUsaDeptId				= "";
    /** ������μ� DESC */
    private String eqUsaDeptDesc			= "";
    /** ����������μ� ID */
    private String peqUsaDeptId				= "";
    /** ����������μ� DESC */
    private String peqUsaDeptDesc			= "";
    /** Room No. */
    private String roomNo                   = "";
    /** �������� ID */
    private String calTypeId                = "";
    /** �������� DESC */
    private String calTypeDesc              = "";
    
    private String wkOrId                   = "";
    
	public String getFilterExeDeptId()
    {
        return filterExeDeptId;
    }
    public void setFilterExeDeptId(String filterExeDeptId)
    {
        this.filterExeDeptId = filterExeDeptId;
    }
    public String getFilterExeDeptDesc()
    {
        return filterExeDeptDesc;
    }
    public void setFilterExeDeptDesc(String filterExeDeptDesc)
    {
        this.filterExeDeptDesc = filterExeDeptDesc;
    }
    public String getFilterPlanEmpId()
    {
        return filterPlanEmpId;
    }
    public void setFilterPlanEmpId(String filterPlanEmpId)
    {
        this.filterPlanEmpId = filterPlanEmpId;
    }
    public String getFilterPlanEmpDesc()
    {
        return filterPlanEmpDesc;
    }
    public void setFilterPlanEmpDesc(String filterPlanEmpDesc)
    {
        this.filterPlanEmpDesc = filterPlanEmpDesc;
    }
    public String getExeDeptId()
    {
        return exeDeptId;
    }
    public void setExeDeptId(String exeDeptId)
    {
        this.exeDeptId = exeDeptId;
    }
    public String getExeDeptDesc()
    {
        return exeDeptDesc;
    }
    public void setExeDeptDesc(String exeDeptDesc)
    {
        this.exeDeptDesc = exeDeptDesc;
    }
    public String getPlanEmpId()
    {
        return planEmpId;
    }
    public void setPlanEmpId(String planEmpId)
    {
        this.planEmpId = planEmpId;
    }
    public String getPlanEmpDesc()
    {
        return planEmpDesc;
    }
    public void setPlanEmpDesc(String planEmpDesc)
    {
        this.planEmpDesc = planEmpDesc;
    }
    public String getWkOrId()
    {
        return wkOrId;
    }
    public void setWkOrId(String wkOrId)
    {
        this.wkOrId = wkOrId;
    }
    public String getRoomNo()
    {
        return roomNo;
    }
    public void setRoomNo(String roomNo)
    {
        this.roomNo = roomNo;
    }
    public String getCalTypeId()
    {
        return calTypeId;
    }
    public void setCalTypeId(String calTypeId)
    {
        this.calTypeId = calTypeId;
    }
    public String getCalTypeDesc()
    {
        return calTypeDesc;
    }
    public void setCalTypeDesc(String calTypeDesc)
    {
        this.calTypeDesc = calTypeDesc;
    }
    public String getFilterCalTypeId()
    {
        return filterCalTypeId;
    }
    public void setFilterCalTypeId(String filterCalTypeId)
    {
        this.filterCalTypeId = filterCalTypeId;
    }
    public String getFilterRoomNumber()
    {
        return filterRoomNumber;
    }
    public void setFilterRoomNumber(String filterRoomNumber)
    {
        this.filterRoomNumber = filterRoomNumber;
    }
    public String getFilterCalTypeDesc()
    {
        return filterCalTypeDesc;
    }
    public void setFilterCalTypeDesc(String filterCalTypeDesc)
    {
        this.filterCalTypeDesc = filterCalTypeDesc;
    }
    public String getPlantId() {
		return plantId;
	}
	public String getFilterPequipId() {
		return filterPequipId;
	}
	public void setFilterPequipId(String filterPequipId) {
		this.filterPequipId = filterPequipId;
	}
	public String getFilterPequipDesc() {
		return filterPequipDesc;
	}
	public void setFilterPequipDesc(String filterPequipDesc) {
		this.filterPequipDesc = filterPequipDesc;
	}
	public String getPequipId() {
		return pequipId;
	}
	public void setPequipId(String pequipId) {
		this.pequipId = pequipId;
	}
	public String getPequipDesc() {
		return pequipDesc;
	}
	public void setPequipDesc(String pequipDesc) {
		this.pequipDesc = pequipDesc;
	}
	public String getEquipNo() {
		return equipNo;
	}
	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}
	public String getEqUsaDeptId() {
		return eqUsaDeptId;
	}
	public void setEqUsaDeptId(String eqUsaDeptId) {
		this.eqUsaDeptId = eqUsaDeptId;
	}
	public String getEqUsaDeptDesc() {
		return eqUsaDeptDesc;
	}
	public void setEqUsaDeptDesc(String eqUsaDeptDesc) {
		this.eqUsaDeptDesc = eqUsaDeptDesc;
	}
	public String getPeqUsaDeptId() {
		return peqUsaDeptId;
	}
	public void setPeqUsaDeptId(String peqUsaDeptId) {
		this.peqUsaDeptId = peqUsaDeptId;
	}
	public String getPeqUsaDeptDesc() {
		return peqUsaDeptDesc;
	}
	public void setPeqUsaDeptDesc(String peqUsaDeptDesc) {
		this.peqUsaDeptDesc = peqUsaDeptDesc;
	}
	public String getFilterEquipNo() {
		return filterEquipNo;
	}
	public void setFilterEquipNo(String filterEquipNo) {
		this.filterEquipNo = filterEquipNo;
	}
	public String getFilterEqUsaDeptId() {
		return filterEqUsaDeptId;
	}
	public void setFilterEqUsaDeptId(String filterEqUsaDeptId) {
		this.filterEqUsaDeptId = filterEqUsaDeptId;
	}
	public String getFilterEqUsaDeptDesc() {
		return filterEqUsaDeptDesc;
	}
	public void setFilterEqUsaDeptDesc(String filterEqUsaDeptDesc) {
		this.filterEqUsaDeptDesc = filterEqUsaDeptDesc;
	}
	public String getFilterPEqUsaDeptId() {
		return filterPEqUsaDeptId;
	}
	public void setFilterPEqUsaDeptId(String filterPEqUsaDeptId) {
		this.filterPEqUsaDeptId = filterPEqUsaDeptId;
	}
	public String getFilterPEqUsaDeptDesc() {
		return filterPEqUsaDeptDesc;
	}
	public void setFilterPEqUsaDeptDesc(String filterPEqUsaDeptDesc) {
		this.filterPEqUsaDeptDesc = filterPEqUsaDeptDesc;
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
	public String getEmpId()
    {
        return empId;
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
	public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpDesc()
    {
        return empDesc;
    }
    public void setEmpDesc(String empDesc)
    {
        this.empDesc = empDesc;
    }
    public String getFilterEmpId()
    {
        return filterEmpId;
    }
    public void setFilterEmpId(String filterEmpId)
    {
        this.filterEmpId = filterEmpId;
    }
    public String getFilterEmpDesc()
    {
        return filterEmpDesc;
    }
    public void setFilterEmpDesc(String filterEmpDesc)
    {
        this.filterEmpDesc = filterEmpDesc;
    }
    public String getFilterEqCtgTypeId() {
		return filterEqCtgTypeId;
	}
	public void setFilterEqCtgTypeId(String filterEqCtgTypeId) {
		this.filterEqCtgTypeId = filterEqCtgTypeId;
	}
	public String getFilterEqCtgTypeDesc() {
		return filterEqCtgTypeDesc;
	}
	public void setFilterEqCtgTypeDesc(String filterEqCtgTypeDesc) {
		this.filterEqCtgTypeDesc = filterEqCtgTypeDesc;
	}
	public String getEqCtgTypeId() {
		return eqCtgTypeId;
	}
	public void setEqCtgTypeId(String eqCtgTypeId) {
		this.eqCtgTypeId = eqCtgTypeId;
	}
	public String getEqCtgTypeDesc() {
		return eqCtgTypeDesc;
	}
	public void setEqCtgTypeDesc(String eqCtgTypeDesc) {
		this.eqCtgTypeDesc = eqCtgTypeDesc;
	}
	public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
	public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getFilterPmNo() {
		return filterPmNo;
	}
	public void setFilterPmNo(String filterPmNo) {
		this.filterPmNo = filterPmNo;
	}
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
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
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getFilterWoTypeId() {
		return filterWoTypeId;
	}
	public void setFilterWoTypeId(String filterWoTypeId) {
		this.filterWoTypeId = filterWoTypeId;
	}
	public String getFilterWoTypeDesc() {
		return filterWoTypeDesc;
	}
	public void setFilterWoTypeDesc(String filterWoTypeDesc) {
		this.filterWoTypeDesc = filterWoTypeDesc;
	}
	public String getFilterPmTypeId() {
		return filterPmTypeId;
	}
	public void setFilterPmTypeId(String filterPmTypeId) {
		this.filterPmTypeId = filterPmTypeId;
	}
	public String getFilterPmTypeDesc() {
		return filterPmTypeDesc;
	}
	public void setFilterPmTypeDesc(String filterPmTypeDesc) {
		this.filterPmTypeDesc = filterPmTypeDesc;
	}
	public String getWoTypeId() {
		return woTypeId;
	}
	public void setWoTypeId(String woTypeId) {
		this.woTypeId = woTypeId;
	}
	public String getWoTypeDesc() {
		return woTypeDesc;
	}
	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
	}
	public String getPmTypeId() {
		return pmTypeId;
	}
	public void setPmTypeId(String pmTypeId) {
		this.pmTypeId = pmTypeId;
	}
	public String getPmTypeDesc() {
		return pmTypeDesc;
	}
	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}
	public String getIsLawEq() {
		return isLawEq;
	}
	public void setIsLawEq(String isLawEq) {
		this.isLawEq = isLawEq;
	}
	public String getMainMngId() {
		return mainMngId;
	}
	public void setMainMngId(String mainMngId) {
		this.mainMngId = mainMngId;
	}
	public String getMainMngName() {
		return mainMngName;
	}
	public void setMainMngName(String mainMngName) {
		this.mainMngName = mainMngName;
	}
	public String getSubMngId() {
		return subMngId;
	}
	public void setSubMngId(String subMngId) {
		this.subMngId = subMngId;
	}
	public String getSubMngName() {
		return subMngName;
	}
	public void setSubMngName(String subMngName) {
		this.subMngName = subMngName;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
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
	public String getPlfTypeId() {
		return plfTypeId;
	}
	public void setPlfTypeId(String plfTypeId) {
		this.plfTypeId = plfTypeId;
	}
	public String getPlfTypeDesc() {
		return plfTypeDesc;
	}
	public void setPlfTypeDesc(String plfTypeDesc) {
		this.plfTypeDesc = plfTypeDesc;
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getYyyymm() {
		return yyyymm;
	}
	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}
	public String getSchedType() {
		return schedType;
	}
	public void setSchedType(String schedType) {
		this.schedType = schedType;
	}
	public String getPmSchedId() {
		return pmSchedId;
	}
	public void setPmSchedId(String pmSchedId) {
		this.pmSchedId = pmSchedId;
	}
	public String getFilterYear() {
		return filterYear;
	}
	public void setFilterYear(String filterYear) {
		this.filterYear = CommonUtil.convertDate(filterYear);
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
}
