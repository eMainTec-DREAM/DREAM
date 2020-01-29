package dream.asset.list.dto;

import java.util.List;

import common.bean.BaseDTO;
import common.bean.MwareConfig;
import common.util.CommonUtil;

/**
 * 설비마스터 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqMstrDetailDTO extends BaseDTO
{
	/** 설비ID */
	private String equipId 				= "";
	/** 설비번호 */
	private String itemNo 				= MwareConfig.getEmptyFieldValue();
	/** 설비명 */
	private String equipDesc 			= MwareConfig.getEmptyFieldValue();
	/** 위치ID */
	private String eqLocId 				= "";
	/** 위치No */
	private String eqLocNo 				= "";
	/** 위치명 */
	private String eqLocDesc 			= "";
	/** 종류ID */
	private String eqCtgId 				= "";
	/** 종류명 */
	private String eqCtgDesc 			= "";
	/** 제작사 */
	private String maker 				= MwareConfig.getEmptyFieldValue();
	/** 모델 */
	private String modelNo 				= MwareConfig.getEmptyFieldValue();
	/** 설비상태ID */
	private String eqStatusId 			= "";
	/** 설비상태명 */
	private String eqStatusDesc 		= "";
	/** 설치일자 */
	private String setupDate 			= "";
	/** 용량 */
	private String capacity 			= MwareConfig.getEmptyFieldValue();
	/** 사용Utility */
	private String utilCapa 			= MwareConfig.getEmptyFieldValue();
	/** 내/외자 id */
	private String plfTypeId 			= "";
	/** 내/외자 명 */
	private String plfTypeDesc 			= "";
	/** 구매금액 */
	private String buyAmt 				= "";
	/** 구매일자 */
	private String buyDate 				= "";
	/** 폐기일자 */
    private String disusedDate          = "";
	/** 부서id */
	private String deptId 				= "";
	/** 부서명 */
	private String deptDesc 			= "";
	/** 관리자(정) id */
	private String mainMngId 			= "";
	/** 관리자(정) 명 */
	private String mainMngName 			= "";
	/** 관리자(부) id */
	private String subMngId 			= "";
	/** 관리자(부) 명 */
	private String subMngName 			= "";
	/** 법정설비여부 */
	private String isLawEq 				= "";
	/** 정렬순서 */
	private String ordNo 				= MwareConfig.getEmptyFieldValue();
	/** 특이사항 */
	private String remark 				= MwareConfig.getEmptyFieldValue();
	/** Old EQNO */
	private String oldEqNo 				= MwareConfig.getEmptyFieldValue();
	/** Serial# */
	private String serialNo 			= MwareConfig.getEmptyFieldValue();
	/** AS업체명 */
	private String asVendor 			= MwareConfig.getEmptyFieldValue();
	/** AS담당자 */
	private String asName 				= MwareConfig.getEmptyFieldValue();
	/** AS전화번호 */
	private String asPhone 				= MwareConfig.getEmptyFieldValue();
	/** 설비중요도Id */
	private String eqGradeId 			= "";
	/** 설비중요도명 */
	private String eqGradeDesc 			= "";
	/** 부서 validation 시 조건  */
	private String deptCateg 			= "";
	/** 위치 validation 시 조건  */
	private String eqLocType	 		= "";
	/** Excel No  */
	private String excelNo	 			= MwareConfig.getEmptyFieldValue();
	/** Plant  */
	private String plant	 			= "";
	/** Plant Desc */
	private String plantDesc 			= "";
	/** 설비유형Id  */
	private String eqCtgTypeId	 		= "";
	/** 설비유형명  */
	private String eqCtgTypeDesc		= "";
	/** 점검유형구분  */
	private String pmiActionType   		= "";
	/** 점검유형구분명  */
    private String pmiActionTypeDesc  	= "";
	/** 교정대상 */
    private String calibTarget			= "";
    		
	private List slideFileList          = null;
	
	/** 상위 설비코드 */
	private String parentEquipId 		= "";
	/** 상위 설비번호 */
	private String parentItemNo 		= "";
	/** 상위 설비명 */
	private String parentEquipDesc 		= "";
	/** 개정이력 id */
	private String revisionhistId 		= "";
	/** 개정 상태 */
	private String revisionStatusId 	= "";
	/** 마지막 버전 여부 */
	private String isLastVersion 		= "";
	/** 제형 */
    private String prodShape         	= MwareConfig.getEmptyFieldValue();
    /** 실번호 */
    private String eqStrLocNo     		= "";
    /** 실명 */
    private String eqStrLocDesc        	= "";
    /** 공급업체 */
    private String supplier         	= MwareConfig.getEmptyFieldValue();
    /** 제조국 */
    private String countryMaker         = MwareConfig.getEmptyFieldValue();
	
    /** CP ID */
	private String ctCtrId 				= "";
	/** CP명 */
	private String ctCtrDesc			= "";
	/** 규격(사양) */
	private String eqSpec				= MwareConfig.getEmptyFieldValue();
	/** 사용부서 ID */
	private String usageDeptId			= "";
	/** 사용부서 DESC */
	private String usageDeptDesc		= "";
	/** 사용자 ID */
	private String userId				= "";
	/** 사용자 NAME */
	private String userName				= "";
	
	/** Tag 번호 */
	private String tagNo				= MwareConfig.getEmptyFieldValue();
	
	/** Size */
	private String eqSize				= MwareConfig.getEmptyFieldValue();
	/** 중량 */
	private String weight				= MwareConfig.getEmptyFieldValue();
	
	/** 생성일자 */
    private String creDate       		= "";
    /** 수정일자 */
    private String updDate       		= "";
    
    /** 가동달력 ID */
    private String lnWrkListId			= "";
    /** 가동달력명 */
    private String lnWrkListDesc		= "";
    
    /** 화폐단위 ID */
    private String currencyId			= "";
    /** 화폐단위 명 */
    private String currencyDesc			= "";

    /** OLD 설비 id */
    private String oldEquipId			= "";
    
	/** 설비부위 id */
	private String eqAsmbId				= "";
	/** OLD 설비부위 id */
	private String oldEqAsmbId			= "";
	
	/** 변경전 종류ID */
	private String originEqCtgId		= "";
    /** 작업그룹 ID */
	private String wkctrId				= "";
	/** 작업그룹 Desc */
	private String wkctrDesc			= "";
	
	/** 사용처(보관처) */
	private String storage  			= MwareConfig.getEmptyFieldValue();
	/** 보증기간 */
	private String guarantyDate         = "";

	
	public String getGuarantyDate() {
		return guarantyDate;
	}

	public void setGuarantyDate(String guarantyDate) {
		this.guarantyDate = CommonUtil.convertDate(guarantyDate);
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getWkctrId() {
		return wkctrId;
	}

	public void setWkctrId(String wkctrId) {
		this.wkctrId = wkctrId;
	}

	public String getWkctrDesc() {
		return wkctrDesc;
	}

	public void setWkctrDesc(String wkctrDesc) {
		this.wkctrDesc = wkctrDesc;
	}

	public String getOriginEqCtgId() {
		return originEqCtgId;
	}

	public void setOriginEqCtgId(String originEqCtgId) {
		this.originEqCtgId = originEqCtgId;
	}

	public String getEqAsmbId() {
		return eqAsmbId;
	}

	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
	}

	public String getOldEquipId() {
		return oldEquipId;
	}

	public void setOldEquipId(String oldEquipId) {
		this.oldEquipId = oldEquipId;
	}

	public String getOldEqAsmbId() {
		return oldEqAsmbId;
	}

	public void setOldEqAsmbId(String oldEqAsmbId) {
		this.oldEqAsmbId = oldEqAsmbId;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyDesc() {
		return currencyDesc;
	}

	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}

	public String getLnWrkListId() {
		return lnWrkListId;
	}

	public void setLnWrkListId(String lnWrkListId) {
		this.lnWrkListId = lnWrkListId;
	}

	public String getLnWrkListDesc() {
		return lnWrkListDesc;
	}

	public void setLnWrkListDesc(String lnWrkListDesc) {
		this.lnWrkListDesc = lnWrkListDesc;
	}

	public String getCreDate() {
		return creDate;
	}

	public void setCreDate(String creDate) {
		this.creDate = CommonUtil.convertDateTime(creDate);
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = CommonUtil.convertDateTime(updDate);
	}

	public String getEqSize() {
		return eqSize;
	}

	public void setEqSize(String eqSize) {
		this.eqSize = eqSize;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getTagNo() {
		return tagNo;
	}

	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}

	public String getCtCtrId() {
		return ctCtrId;
	}

	public String getUsageDeptId() {
		return usageDeptId;
	}

	public void setUsageDeptId(String usageDeptId) {
		this.usageDeptId = usageDeptId;
	}

	public String getUsageDeptDesc() {
		return usageDeptDesc;
	}

	public void setUsageDeptDesc(String usageDeptDesc) {
		this.usageDeptDesc = usageDeptDesc;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEqSpec() {
		return eqSpec;
	}

	public void setEqSpec(String eqSpec) {
		this.eqSpec = eqSpec;
	}

	public void setCtCtrId(String ctCtrId) {
		this.ctCtrId = ctCtrId;
	}

	public String getCtCtrDesc() {
		return ctCtrDesc;
	}

	public void setCtCtrDesc(String ctCtrDesc) {
		this.ctCtrDesc = ctCtrDesc;
	}

	public String getEqStrLocNo()
    {
        return eqStrLocNo;
    }

    public void setEqStrLocNo(String eqStrLocNo)
    {
        this.eqStrLocNo = eqStrLocNo;
    }

    public String getEqStrLocDesc()
    {
        return eqStrLocDesc;
    }

    public void setEqStrLocDesc(String eqStrLocDesc)
    {
        this.eqStrLocDesc = eqStrLocDesc;
    }

    public String getSupplier()
    {
        return supplier;
    }

    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }

    public String getCountryMaker()
    {
        return countryMaker;
    }

    public void setCountryMaker(String countryMaker)
    {
        this.countryMaker = countryMaker;
    }

    public String getProdShape()
    {
        return prodShape;
    }

    public void setProdShape(String prodShape)
    {
        this.prodShape = prodShape;
    }

    public String getRevisionhistId() {
		return revisionhistId;
	}

	public void setRevisionhistId(String revisionhistId) {
		this.revisionhistId = revisionhistId;
	}

	public String getRevisionStatusId() {
		return revisionStatusId;
	}

	public void setRevisionStatusId(String revisionStatusId) {
		this.revisionStatusId = revisionStatusId;
	}

	public String getIsLastVersion() {
		return isLastVersion;
	}

	public void setIsLastVersion(String isLastVersion) {
		this.isLastVersion = isLastVersion;
	}

	public String getParentEquipId() {
		return parentEquipId;
	}

	public void setParentEquipId(String parentEquipId) {
		this.parentEquipId = parentEquipId;
	}

	public String getParentItemNo() {
		return parentItemNo;
	}

	public void setParentItemNo(String parentItemNo) {
		this.parentItemNo = parentItemNo;
	}

	public String getParentEquipDesc() {
		return parentEquipDesc;
	}

	public void setParentEquipDesc(String parentEquipDesc) {
		this.parentEquipDesc = parentEquipDesc;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = CommonUtil.convertDate(buyDate);
	}
	
	public String getDisusedDate()
    {
        return disusedDate;
    }

    public void setDisusedDate(String disusedDate)
    {
        this.disusedDate = CommonUtil.convertDate(disusedDate);
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

	public String getExcelNo() {
		return excelNo;
	}

	public void setExcelNo(String excelNo) {
		this.excelNo = excelNo;
	}

	public String getEqLocType() {
		return eqLocType;
	}

	public void setEqLocType(String eqLocType) {
		this.eqLocType = eqLocType;
	}

	public String getDeptCateg() {
		return deptCateg;
	}

	public void setDeptCateg(String deptCateg) {
		this.deptCateg = deptCateg;
	}

	public String getOldEqNo() {
		return oldEqNo;
	}

	public void setOldEqNo(String oldEqNo) {
		this.oldEqNo = oldEqNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getAsVendor() {
		return asVendor;
	}

	public void setAsVendor(String asVendor) {
		this.asVendor = asVendor;
	}

	public String getAsName() {
		return asName;
	}

	public void setAsName(String asName) {
		this.asName = asName;
	}

	public String getAsPhone() {
		return asPhone;
	}

	public void setAsPhone(String asPhone) {
		this.asPhone = asPhone;
	}

	public String getEqGradeId() {
		return eqGradeId;
	}

	public void setEqGradeId(String eqGradeId) {
		this.eqGradeId = eqGradeId;
	}

	public String getEqGradeDesc() {
		return eqGradeDesc;
	}

	public void setEqGradeDesc(String eqGradeDesc) {
		this.eqGradeDesc = eqGradeDesc;
	}

	public List getSlideFileList()
    {
        return slideFileList;
    }

    public void setSlideFileList(List slideFileList)
    {
        this.slideFileList = slideFileList;
    }

    public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
		super.setAuditKey(equipId);
	}

	public String getEquipDesc() {
		return equipDesc;
	}

	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
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

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getEqStatusId() {
		return eqStatusId;
	}

	public void setEqStatusId(String eqStatusId) {
		this.eqStatusId = eqStatusId;
	}

	public String getEqStatusDesc() {
		return eqStatusDesc;
	}

	public void setEqStatusDesc(String eqStatusDesc) {
		this.eqStatusDesc = eqStatusDesc;
	}

	public String getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(String setupDate) {
		this.setupDate = CommonUtil.convertDate(setupDate);
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getUtilCapa() {
		return utilCapa;
	}

	public void setUtilCapa(String utilCapa) {
		this.utilCapa = utilCapa;
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

	public String getBuyAmt() {
		return buyAmt;
	}

	public void setBuyAmt(String buyAmt) {
		this.buyAmt = CommonUtil.convertMoney(buyAmt);
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

	public String getIsLawEq() {
		return isLawEq;
	}

	public void setIsLawEq(String isLawEq) {
		this.isLawEq = isLawEq;
	}

	public String getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

    public String getPmiActionType()
    {
        return pmiActionType;
    }

    public void setPmiActionType(String pmiActionType)
    {
        this.pmiActionType = pmiActionType;
    }

    public String getPmiActionTypeDesc()
    {
        return pmiActionTypeDesc;
    }

    public void setPmiActionTypeDesc(String pmiActionTypeDesc)
    {
        this.pmiActionTypeDesc = pmiActionTypeDesc;
    }

	public String getEqLocNo() {
		return eqLocNo;
	}

	public void setEqLocNo(String eqLocNo) {
		this.eqLocNo = eqLocNo;
	}

	public String getPlantDesc() {
		return plantDesc;
	}

	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}

	public String getCalibTarget() {
		return calibTarget;
	}

	public void setCalibTarget(String calibTarget) {
		this.calibTarget = calibTarget;
	}
	
}
