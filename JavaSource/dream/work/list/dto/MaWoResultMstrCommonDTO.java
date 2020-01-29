package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���� ���� DTO
 * @author  kim21017
 * @version $Id: MaWoResultMstrCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaWoResultMstrCommonDTO extends BaseDTO
{
	/** �۾����ID */
	private String wkOrId				= "";
	/** �۾���û ID */
	private String woReqId				= "";
	/** ������ ID */
	private String pmSchedId			= "";
	/** �������� ID */
	private String invtlistId			= "";
	/** �۾��� - ���� */
	private String filterWkOrDesc		= "";
	/** �۾���ȣ - ���� */
	private String filterWoNo			= "";
	/** �۾����� ������ - ���� */
	private String filterStartDate		= "";
	/** �۾����� ������ - ���� */
	private String filterEndDate		= "";
	/** �μ�id - ���� */
	private String filterDeptId			= "";
	/** �μ��� - ���� */
	private String filterDeptDesc		= "";
	/** ����id - ���� */
	private String filterEquipId		= "";
	/** ����id - ���� */
	private String filterEquipNo		= "";
	/** ����� - ���� */
	private String filterEquipDesc		= "";
	/** �����id - ���� */
	private String filterEmpId			= "";
	/** ����ڸ� - ���� */
	private String filterEmpDesc		= "";
	/** ��ġid - ���� */
	private String filterEqLocId		= "";
	/** ��ġ�� - ���� */
	private String filterEqLocDesc		= "";
	/** Room No. - ���� */
    private String filterRoomNumber     = "";
	/** ����id - ���� */
	private String filterEqCtgId		= "";
	/** ������ - ���� */
	private String filterEqCtgDesc		= "";
	/** ����-��/���� */
	private String filterPlfTypeId		= "";
	/** ����-��/���� �� */
	private String filterPlfTypeDesc	= "";
	/** ����-�۾����� */
	private String filterWoTypeId		= "";
	/** ����-�۾����� �� */
	private String filterWoTypeDesc		= "";
	/** ����-�۾����� */
	private String filterPmTypeId		= "";
	/** ����-�۾����� �� */
	private String filterPmTypeDesc		= "";
	/** ����-�������񿩺� */
	private String filterIsLawEq		= "";
	/** ����-������(��)id */
	private String filterMainMngId		= "";
	/** ����-������(��)�� */
	private String filterMainMngName	= "";
	/** ����-������(��)id */
	private String filterSubMngId		= "";
	/** ����-������(��)�� */
	private String filterSubMngName		= "";
	/** ���� - �����ڵ� */
	private String filterWoStatus		= "";
	/** ���� - �����ڵ�� */
	private String filterWoStatusDesc	= "";
	/** ���� - ����Ʈ */
	private String filterShiftId		= "";
	/** ���� - ����Ʈ */
	private String filterShiftDesc		= "";
	/** ���� - �۾��׷�Id */
	private String filterWkCtrId		= "";
	/** ���� - �۾��׷�� */
	private String filterWkCtrDesc		= "";
	/** ���� - �����۾�# */
	private String filterPmNo			= "";
	/** ���õ� wkorId */
	private String selectedWkorId		= "";
	/** ���� �м����� �Ѿ���� �����ڵ� */
	private String caDesc				= "";
	/** ���� �м����� �Ѿ���� ��ġ�ڵ� */
	private String reDesc				= "";
	/** ���� �м����� �Ѿ���� �����ڵ尡 ���� �� ���� */
	private String notCaCd				= "";
	/** ���� �м����� �Ѿ���� ��ġ�ڵ尡 ���� �� ���� */
	private String notReCd				= "";
	/** �ڰ�/���ֱ��� �ڵ� */
	private String selfVendorType       = "";
	/** �ڰ�/���ֱ��и� */
	private String selfVendorTypeDesc   = "";
	/** �ŷ�óID */
	private String vendorId             = "";
	/** �ŷ�ó */
	private String vendorDesc           = "";
	/** ���� - �ŷ�ó��(text value) */
    private String filterVendorName     = "";
	/** ���õ� �۾����� */
	private String selectedPmType       = "";
	/** ���õ� �۾����� */
	private String selectedWoType       = "";
	/** Dashboard ���� �ƴ� �۾� */
	private String notPmTypeId          = "";
	/** Dashboard ���� �ƴ� �۾� */
    private String notWoTypeId          = "";
	/** Filter-��������Id */
	private String filterEqCtgTypeId 	= "";
	/** Filter-�������� */
	private String filterEqCtgTypeDesc 	= "";
	
	private String woNo					= "";
	/** �����۾� �Ϲ�������� ID */
	private String wocalibgnlvalueId 	= "";
	/** Filter-������� */
	private String filterCaCd 	   		= "";
	/** Filter-������� */
	private String filterCaCdDesc 	    = "";
	
	/** Filter-����*/
	private String filterReCd 	   		= "";
	/** Filter-������� */
	private String filterReCdDesc 	    = "";
	/** �������ذ� ID */
	private String pmCalibStdTpId		= "";

	/** �˻����� */
	private String filterCalStartDate	= "";
	private String filterCalEndDate		= "";
	/** �˻����� */
	private String filterCalCorpDesc	= "";
	/** �˻���ID*/
	private String filterCalCorId		= "";
	/** �˻� ȯ��*/
	private String filterCalEnv			= "";
	/** ��������*/
	private String filterCalRsltStatDesc= "";
	/** ��������*/
	private String filterCalRsltStatId	= "";
	/** �˻籸��*/
	private String filterCalTypeDesc	= "";
	/** �˻籸��*/
	private String filterCalTypeId		= "";
	/** ����Id */
    private String filterPlantId        = "";
    /** ����� */
    private String filterPlantDesc      = "";
    /** ����Id */
    private String filterEqAsmbId       = "";
    /** ������ */
    private String filterEqAsmbDesc     = "";
    
    /** ǰ�ǿ��� */
    private String filterIsDraft		= "";
    
    /** ��û�켱������ */
    private String filterReqPriorityDesc                = "";
    /** ��û�켱���� */
    private String filterReqPriority                    = "";
    /** �����޸� */
    private String filterEqGradeDesc                    = "";
    /** ������ */
    private String filterEqGrade                        = "";
    /** ���������ð� */
    private String filterEqDnWorkTime                   = "";
    /** �����ڵ� */
    private String conOper                              = "";
    /** ���ǻ��¸� */
    private String conOperDesc                          = "";
    /** ó������ ID */
    private String woReqResId                           = "";
    
    /** �۾���û���� - ������ */
    private String filterResStartDate					= "";
    /** �۾���û���� - ������ */
    private String filterResEndDate						= "";
    
    /** Excel Tab No */
    private String exceltabNo							= "";
    
    /** ������ pmType */
    private String pagePmType							= "";

	/** ������μ�id - ���� */
	private String filterUsageDeptId					= "";
	/** ������μ��� - ���� */
	private String filterUsageDeptDesc					= "";

	/** �������� id - ���� */
	private String filterParEquipId						= "";
	/** ��������� - ���� */
	private String filterParEquipDesc					= "";
	/** ����ǰ id - ���� */
    private String filterPartId                         = "";
    /** ������ �۾����� */
    private String hiddenWoType                         = "";
    /** �����ȣ */
    private String equipId                              = "";
	/** �������� ������ - ���� */
	private String filterUpdStartDate					= "";
	/** �������� ������ - ���� */
	private String filterUpdEndDate						= "";

	/** linked ������μ�id */
	private String linkedUsageDeptId					= "";
	/** linked ������μ��� */
	private String linkedUsageDeptDesc					= "";
    
	/** �۾��󼼳��� - ���� */
	private String filterWoPerform						= "";
	
	/** �����̻� ID */
	private String woNgPointId      					= "";
	/** �̻��������� ID */
	private String woNgPointResId						= "";
	
	/** �۾��� ID */
	private String filterWorkerId						= "";
	/** �۾��� Desc */
	private String filterWorkerDesc						= "";
	
	public String getInvtlistId()
    {
        return invtlistId;
    }
    public void setInvtlistId(String invtlistId)
    {
        this.invtlistId = invtlistId;
    }
	public String getFilterWorkerId() {
		return filterWorkerId;
	}
	public void setFilterWorkerId(String filterWorkerId) {
		this.filterWorkerId = filterWorkerId;
	}
	public String getFilterWorkerDesc() {
		return filterWorkerDesc;
	}
	public void setFilterWorkerDesc(String filterWorkerDesc) {
		this.filterWorkerDesc = filterWorkerDesc;
	}
	public String getWoNgPointId() {
		return woNgPointId;
	}
	public void setWoNgPointId(String woNgPointId) {
		this.woNgPointId = woNgPointId;
	}
	public String getWoNgPointResId() {
		return woNgPointResId;
	}
	public void setWoNgPointResId(String woNgPointResId) {
		this.woNgPointResId = woNgPointResId;
	}
	public String getPmSchedId()
    {
        return pmSchedId;
    }
    public void setPmSchedId(String pmSchedId)
    {
        this.pmSchedId = pmSchedId;
    }
    public String getLinkedUsageDeptId()
    {
        return linkedUsageDeptId;
    }
    public void setLinkedUsageDeptId(String linkedUsageDeptId)
    {
        this.linkedUsageDeptId = linkedUsageDeptId;
    }
    public String getLinkedUsageDeptDesc()
    {
        return linkedUsageDeptDesc;
    }
    public void setLinkedUsageDeptDesc(String linkedUsageDeptDesc)
    {
        this.linkedUsageDeptDesc = linkedUsageDeptDesc;
    }
    public String getFilterWoPerform() {
		return filterWoPerform;
	}
	public void setFilterWoPerform(String filterWoPerform) {
		this.filterWoPerform = filterWoPerform;
	}
	public String getEquipId()
    {
        return equipId;
    }
    public String getFilterUpdStartDate() {
		return filterUpdStartDate;
	}
	public void setFilterUpdStartDate(String filterUpdStartDate) {
		this.filterUpdStartDate = CommonUtil.convertDate(filterUpdStartDate);
	}
	public String getFilterUpdEndDate() {
		return filterUpdEndDate;
	}
	public void setFilterUpdEndDate(String filterUpdEndDate) {
		this.filterUpdEndDate = CommonUtil.convertDate(filterUpdEndDate);
	}
	public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getHiddenWoType()
    {
        return hiddenWoType;
    }
    public void setHiddenWoType(String hiddenWoType)
    {
        this.hiddenWoType = hiddenWoType;
    }
    public String getFilterPartId()
    {
        return filterPartId;
    }
    public void setFilterPartId(String filterPartId)
    {
        this.filterPartId = filterPartId;
    }
    public String getFilterParEquipId() {
		return filterParEquipId;
	}
	public String getFilterRoomNumber()
    {
        return filterRoomNumber;
    }
    public void setFilterRoomNumber(String filterRoomNumber)
    {
        this.filterRoomNumber = filterRoomNumber;
    }
    public void setFilterParEquipId(String filterParEquipId) {
		this.filterParEquipId = filterParEquipId;
	}
	public String getFilterParEquipDesc() {
		return filterParEquipDesc;
	}
	public void setFilterParEquipDesc(String filterParEquipDesc) {
		this.filterParEquipDesc = filterParEquipDesc;
	}
	public String getPagePmType() {
		return pagePmType;
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
	public void setPagePmType(String pagePmType) {
		this.pagePmType = pagePmType;
	}
	public String getExceltabNo() {
		return exceltabNo;
	}
	public void setExceltabNo(String exceltabNo) {
		this.exceltabNo = exceltabNo;
	}
    
	public String getFilterResStartDate() {
		return filterResStartDate;
	}
	public void setFilterResStartDate(String filterResStartDate) {
		this.filterResStartDate = filterResStartDate;
	}
	public String getFilterResEndDate() {
		return filterResEndDate;
	}
	public void setFilterResEndDate(String filterResEndDate) {
		this.filterResEndDate = filterResEndDate;
	}
	
	public String getFilterIsDraft() {
		return filterIsDraft;
	}
	public String getWoReqResId() {
		return woReqResId;
	}
	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
	}
	public void setFilterIsDraft(String filterIsDraft) {
		this.filterIsDraft = filterIsDraft;
	}
	public String getConOper()
    {
        return conOper;
    }
    public void setConOper(String conOper)
    {
        this.conOper = conOper;
    }
    public String getConOperDesc()
    {
        return conOperDesc;
    }
    public void setConOperDesc(String conOperDesc)
    {
        this.conOperDesc = conOperDesc;
    }
    public String getFilterReqPriorityDesc()
    {
        return filterReqPriorityDesc;
    }
    public void setFilterReqPriorityDesc(String filterReqPriorityDesc)
    {
        this.filterReqPriorityDesc = filterReqPriorityDesc;
    }
    public String getFilterReqPriority()
    {
        return filterReqPriority;
    }
    public void setFilterReqPriority(String filterReqPriority)
    {
        this.filterReqPriority = filterReqPriority;
    }
    public String getFilterEqGradeDesc()
    {
        return filterEqGradeDesc;
    }
    public void setFilterEqGradeDesc(String filterEqGradeDesc)
    {
        this.filterEqGradeDesc = filterEqGradeDesc;
    }
    public String getFilterEqGrade()
    {
        return filterEqGrade;
    }
    public void setFilterEqGrade(String filterEqGrade)
    {
        this.filterEqGrade = filterEqGrade;
    }
    public String getFilterEqDnWorkTime()
    {
        return filterEqDnWorkTime;
    }
    public void setFilterEqDnWorkTime(String filterEqDnWorkTime)
    {
        this.filterEqDnWorkTime = filterEqDnWorkTime;
    }
    public String getFilterEqAsmbId()
    {
        return filterEqAsmbId;
    }
    public void setFilterEqAsmbId(String filterEqAsmbId)
    {
        this.filterEqAsmbId = filterEqAsmbId;
    }
    public String getFilterEqAsmbDesc()
    {
        return filterEqAsmbDesc;
    }
    public void setFilterEqAsmbDesc(String filterEqAsmbDesc)
    {
        this.filterEqAsmbDesc = filterEqAsmbDesc;
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
    public String getNotWoTypeId()
    {
        return notWoTypeId;
    }
    public void setNotWoTypeId(String notWoTypeId)
    {
        this.notWoTypeId = notWoTypeId;
    }
    public String getFilterVendorName()
    {
        return filterVendorName;
    }
    public void setFilterVendorName(String filterVendorName)
    {
        this.filterVendorName = filterVendorName;
    }
    public String getFilterCalStartDate() {
		return filterCalStartDate;
	}
	public void setFilterCalStartDate(String filterCalStartDate) {
		this.filterCalStartDate = filterCalStartDate;
	}
	public String getFilterCalEndDate() {
		return filterCalEndDate;
	}
	public void setFilterCalEndDate(String filterCalEndDate) {
		this.filterCalEndDate = filterCalEndDate;
	}
	public String getFilterCalCorpDesc() {
		return filterCalCorpDesc;
	}
	public void setFilterCalCorpDesc(String filterCalCorpDesc) {
		this.filterCalCorpDesc = filterCalCorpDesc;
	}
	public String getFilterCalCorId() {
		return filterCalCorId;
	}
	public void setFilterCalCorId(String filterCalCorId) {
		this.filterCalCorId = filterCalCorId;
	}
	public String getFilterCalEnv() {
		return filterCalEnv;
	}
	public void setFilterCalEnv(String filterCalEnv) {
		this.filterCalEnv = filterCalEnv;
	}
	public String getFilterCalRsltStatDesc() {
		return filterCalRsltStatDesc;
	}
	public void setFilterCalRsltStatDesc(String filterCalRsltStatDesc) {
		this.filterCalRsltStatDesc = filterCalRsltStatDesc;
	}
	public String getFilterCalRsltStatId() {
		return filterCalRsltStatId;
	}
	public void setFilterCalRsltStatId(String filterCalRsltStatId) {
		this.filterCalRsltStatId = filterCalRsltStatId;
	}
	public String getFilterCalTypeDesc() {
		return filterCalTypeDesc;
	}
	public void setFilterCalTypeDesc(String filterCalTypeDesc) {
		this.filterCalTypeDesc = filterCalTypeDesc;
	}
	public String getFilterCalTypeId() {
		return filterCalTypeId;
	}
	public void setFilterCalTypeId(String filterCalTypeId) {
		this.filterCalTypeId = filterCalTypeId;
	}
	public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
	}
	public String getFilterReCd() {
		return filterReCd;
	}
	public void setFilterReCd(String filterReCd) {
		this.filterReCd = filterReCd;
	}
	public String getFilterReCdDesc() {
		return filterReCdDesc;
	}
	public void setFilterReCdDesc(String filterReCdDesc) {
		this.filterReCdDesc = filterReCdDesc;
	}
	public String getFilterCaCd() {
		return filterCaCd;
	}
	public void setFilterCaCd(String filterCaCd) {
		this.filterCaCd = filterCaCd;
	}
	public String getFilterCaCdDesc() {
		return filterCaCdDesc;
	}
	public void setFilterCaCdDesc(String filterCaCdDesc) {
		this.filterCaCdDesc = filterCaCdDesc;
	}
	public String getWocalibgnlvalueId() {
		return wocalibgnlvalueId;
	}
	public void setWocalibgnlvalueId(String wocalibgnlvalueId) {
		this.wocalibgnlvalueId = wocalibgnlvalueId;
		super.setAuditKey(wocalibgnlvalueId);
	}
	public String getWoNo() {
		return woNo;
	}
	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	/** �˱����۾�ǥ�ر����� ID */
	private String wocalibstdeqId		= "";
	
	
	public String getWocalibstdeqId() {
		return wocalibstdeqId;
	}
	public void setWocalibstdeqId(String wocalibstdeqId) {
		this.wocalibstdeqId = wocalibstdeqId;
		super.setAuditKey(wocalibstdeqId);
	}
	public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
	}
	public String getFilterEquipNo() {
		return filterEquipNo;
	}
	public void setFilterEquipNo(String filterEquipNo) {
		this.filterEquipNo = filterEquipNo;
	}
	public String getFilterShiftId() {
		return filterShiftId;
	}
	public void setFilterShiftId(String filterShiftId) {
		this.filterShiftId = filterShiftId;
	}
	public String getFilterShiftDesc() {
		return filterShiftDesc;
	}
	public void setFilterShiftDesc(String filterShiftDesc) {
		this.filterShiftDesc = filterShiftDesc;
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
	public String getNotPmTypeId()
    {
        return notPmTypeId;
    }
    public void setNotPmTypeId(String notPmTypeId)
    {
        this.notPmTypeId = notPmTypeId;
    }
    public String getSelectedPmType() {
		return selectedPmType;
	}
	public void setSelectedPmType(String selectedPmType) {
		this.selectedPmType = selectedPmType;
	}
	public String getSelectedWoType() {
		return selectedWoType;
	}
	public void setSelectedWoType(String selectedWoType) {
		this.selectedWoType = selectedWoType;
	}
	public String getFilterPmNo() {
		return filterPmNo;
	}
	public void setFilterPmNo(String filterPmNo) {
		this.filterPmNo = filterPmNo;
	}
	public String getSelfVendorType()
    {
        return selfVendorType;
    }
    public void setSelfVendorType(String selfVendorType)
    {
        this.selfVendorType = selfVendorType;
    }
    public String getSelfVendorTypeDesc()
    {
        return selfVendorTypeDesc;
    }
    public void setSelfVendorTypeDesc(String selfVendorTypeDesc)
    {
        this.selfVendorTypeDesc = selfVendorTypeDesc;
    }
    public String getVendorId()
    {
        return vendorId;
    }
    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
    }
    public String getVendorDesc()
    {
        return vendorDesc;
    }
    public void setVendorDesc(String vendorDesc)
    {
        this.vendorDesc = vendorDesc;
    }
    public String getNotCaCd() {
		return notCaCd;
	}
	public void setNotCaCd(String notCaCd) {
		this.notCaCd = notCaCd;
	}
	public String getNotReCd() {
		return notReCd;
	}
	public void setNotReCd(String notReCd) {
		this.notReCd = notReCd;
	}
	public String getCaDesc() {
		return caDesc;
	}
	public void setCaDesc(String caDesc) {
		this.caDesc = caDesc;
	}
	public String getReDesc() {
		return reDesc;
	}
	public void setReDesc(String reDesc) {
		this.reDesc = reDesc;
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
	public String getFilterWoNo() {
		return filterWoNo;
	}
	public void setFilterWoNo(String filterWoNo) {
		this.filterWoNo = filterWoNo;
	}
	public String getSelectedWkorId() {
		return selectedWkorId;
	}
	public void setSelectedWkorId(String selectedWkorId) {
		this.selectedWkorId = selectedWkorId;
	}
	public String getFilterWoStatusDesc() {
		return filterWoStatusDesc;
	}
	public void setFilterWoStatusDesc(String filterWoStatusDesc) {
		this.filterWoStatusDesc = filterWoStatusDesc;
	}
	public String getFilterWoStatus() {
		return filterWoStatus;
	}
	public void setFilterWoStatus(String filterWoStatus) {
		this.filterWoStatus = filterWoStatus;
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
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
		super.setAuditKey(wkOrId);
	}
	public String getFilterWkOrDesc() {
		return filterWkOrDesc;
	}
	public void setFilterWkOrDesc(String filterWkOrDesc) {
		this.filterWkOrDesc = filterWkOrDesc;
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
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpDesc() {
		return filterEmpDesc;
	}
	public void setFilterEmpDesc(String filterEmpDesc) {
		this.filterEmpDesc = filterEmpDesc;
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
	
}
