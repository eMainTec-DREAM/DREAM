package dream.asset.list.dto;

import java.util.List;

import common.bean.BaseDTO;
import common.bean.MwareConfig;
import common.util.CommonUtil;

/**
 * ���񸶽��� - �� DTO
 * @author  kim21017
 * @version $Id: MaEqMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqMstrDetailDTO extends BaseDTO
{
	/** ����ID */
	private String equipId 				= "";
	/** �����ȣ */
	private String itemNo 				= MwareConfig.getEmptyFieldValue();
	/** ����� */
	private String equipDesc 			= MwareConfig.getEmptyFieldValue();
	/** ��ġID */
	private String eqLocId 				= "";
	/** ��ġNo */
	private String eqLocNo 				= "";
	/** ��ġ�� */
	private String eqLocDesc 			= "";
	/** ����ID */
	private String eqCtgId 				= "";
	/** ������ */
	private String eqCtgDesc 			= "";
	/** ���ۻ� */
	private String maker 				= MwareConfig.getEmptyFieldValue();
	/** �� */
	private String modelNo 				= MwareConfig.getEmptyFieldValue();
	/** �������ID */
	private String eqStatusId 			= "";
	/** ������¸� */
	private String eqStatusDesc 		= "";
	/** ��ġ���� */
	private String setupDate 			= "";
	/** �뷮 */
	private String capacity 			= MwareConfig.getEmptyFieldValue();
	/** ���Utility */
	private String utilCapa 			= MwareConfig.getEmptyFieldValue();
	/** ��/���� id */
	private String plfTypeId 			= "";
	/** ��/���� �� */
	private String plfTypeDesc 			= "";
	/** ���űݾ� */
	private String buyAmt 				= "";
	/** �������� */
	private String buyDate 				= "";
	/** ������� */
    private String disusedDate          = "";
	/** �μ�id */
	private String deptId 				= "";
	/** �μ��� */
	private String deptDesc 			= "";
	/** ������(��) id */
	private String mainMngId 			= "";
	/** ������(��) �� */
	private String mainMngName 			= "";
	/** ������(��) id */
	private String subMngId 			= "";
	/** ������(��) �� */
	private String subMngName 			= "";
	/** �������񿩺� */
	private String isLawEq 				= "";
	/** ���ļ��� */
	private String ordNo 				= MwareConfig.getEmptyFieldValue();
	/** Ư�̻��� */
	private String remark 				= MwareConfig.getEmptyFieldValue();
	/** Old EQNO */
	private String oldEqNo 				= MwareConfig.getEmptyFieldValue();
	/** Serial# */
	private String serialNo 			= MwareConfig.getEmptyFieldValue();
	/** AS��ü�� */
	private String asVendor 			= MwareConfig.getEmptyFieldValue();
	/** AS����� */
	private String asName 				= MwareConfig.getEmptyFieldValue();
	/** AS��ȭ��ȣ */
	private String asPhone 				= MwareConfig.getEmptyFieldValue();
	/** �����߿䵵Id */
	private String eqGradeId 			= "";
	/** �����߿䵵�� */
	private String eqGradeDesc 			= "";
	/** �μ� validation �� ����  */
	private String deptCateg 			= "";
	/** ��ġ validation �� ����  */
	private String eqLocType	 		= "";
	/** Excel No  */
	private String excelNo	 			= MwareConfig.getEmptyFieldValue();
	/** Plant  */
	private String plant	 			= "";
	/** Plant Desc */
	private String plantDesc 			= "";
	/** ��������Id  */
	private String eqCtgTypeId	 		= "";
	/** ����������  */
	private String eqCtgTypeDesc		= "";
	/** ������������  */
	private String pmiActionType   		= "";
	/** �����������и�  */
    private String pmiActionTypeDesc  	= "";
	/** ������� */
    private String calibTarget			= "";
    		
	private List slideFileList          = null;
	
	/** ���� �����ڵ� */
	private String parentEquipId 		= "";
	/** ���� �����ȣ */
	private String parentItemNo 		= "";
	/** ���� ����� */
	private String parentEquipDesc 		= "";
	/** �����̷� id */
	private String revisionhistId 		= "";
	/** ���� ���� */
	private String revisionStatusId 	= "";
	/** ������ ���� ���� */
	private String isLastVersion 		= "";
	/** ���� */
    private String prodShape         	= MwareConfig.getEmptyFieldValue();
    /** �ǹ�ȣ */
    private String eqStrLocNo     		= "";
    /** �Ǹ� */
    private String eqStrLocDesc        	= "";
    /** ���޾�ü */
    private String supplier         	= MwareConfig.getEmptyFieldValue();
    /** ������ */
    private String countryMaker         = MwareConfig.getEmptyFieldValue();
	
    /** CP ID */
	private String ctCtrId 				= "";
	/** CP�� */
	private String ctCtrDesc			= "";
	/** �԰�(���) */
	private String eqSpec				= MwareConfig.getEmptyFieldValue();
	/** ���μ� ID */
	private String usageDeptId			= "";
	/** ���μ� DESC */
	private String usageDeptDesc		= "";
	/** ����� ID */
	private String userId				= "";
	/** ����� NAME */
	private String userName				= "";
	
	/** Tag ��ȣ */
	private String tagNo				= MwareConfig.getEmptyFieldValue();
	
	/** Size */
	private String eqSize				= MwareConfig.getEmptyFieldValue();
	/** �߷� */
	private String weight				= MwareConfig.getEmptyFieldValue();
	
	/** �������� */
    private String creDate       		= "";
    /** �������� */
    private String updDate       		= "";
    
    /** �����޷� ID */
    private String lnWrkListId			= "";
    /** �����޷¸� */
    private String lnWrkListDesc		= "";
    
    /** ȭ����� ID */
    private String currencyId			= "";
    /** ȭ����� �� */
    private String currencyDesc			= "";

    /** OLD ���� id */
    private String oldEquipId			= "";
    
	/** ������� id */
	private String eqAsmbId				= "";
	/** OLD ������� id */
	private String oldEqAsmbId			= "";
	
	/** ������ ����ID */
	private String originEqCtgId		= "";
    /** �۾��׷� ID */
	private String wkctrId				= "";
	/** �۾��׷� Desc */
	private String wkctrDesc			= "";
	
	/** ���ó(����ó) */
	private String storage  			= MwareConfig.getEmptyFieldValue();
	/** �����Ⱓ */
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
