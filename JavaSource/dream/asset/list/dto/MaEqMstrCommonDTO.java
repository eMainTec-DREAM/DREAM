package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비마스터 공통 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaEqMstrCommonDTO extends BaseDTO
{
	/** 설비ID */
	private String equipId					= "";
	/** 설비명 */
	private String equipDesc				= "";
	/** 필터-계측기번호 */
	private String filterEquipNo			= "";
	/** 필터-설비번호 */
	private String filterPEquipNo			= "";
	/** 필터-계측기명 */
	private String filterEquipDesc			= "";

	/** 필터-위치id */
	private String filterEqLocId			= "";
	/** 필터-위치명 */
	private String filterEqLocDesc			= "";
	/** 필터-기능코드id(종류) */
	private String filterEqCtgId			= "";
	/** 필터-기능코드명(종류) */
	private String filterEqCtgDesc			= "";
	/** 필터-내/외자 */
	private String filterPlfTypeId			= "";
	/** 필터-내/외자 명 */
	private String filterPlfTypeDesc		= "";
	/** 필터-관리부서id */
	private String filterDeptId				= "";
	/** 필터-관리부서명 */
	private String filterDeptDesc			= "";
	/** 필터-법정설비여부 */
	private String filterIsLawEq			= "";
	/** 필터-관리자(정)id */
	private String filterMainMngId			= "";
	/** 필터-관리자(정)명 */
	private String filterMainMngName		= "";
	/** 필터-관리자(부)id */
	private String filterSubMngId			= "";
	/** 필터-관리자(부)명 */
	private String filterSubMngName			= "";
	/** 필터-제작사 */
	private String filterMaker				= "";
	/** 필터-모델번호 */
	private String filterModelNo			= "";
	/** 필터-설비중요도ID */
	private String filterEqGradeId			= "";
	/** 필터-설비중요도명 */
	private String filterEqGradeDesc		= "";
	/** 필터-Old Eq # */
	private String filterOldEqNo			= "";
	/** 설비상태 id */
	private String eqStatusId				= "";
	/** 설비상태 명 */
	private String eqStatusDesc				= "";
	/** Filter-설비유형Id */
	private String filterEqCtgTypeId		= "";
	/** Filter-설비유형 */
	private String filterEqCtgTypeDesc		= "";
	/** select 팝업에서 선택된 설비유형 */
	private String selectedEqCtgTypeId		= "";
	/** 설비 관리부서 */
	private String deptId					= "";
	/** 소재지 */
	private String eqBuildAddr				="";
	/** 필터-상위설비 ID */
    private String filterPEqId				= "";
    /** 필터-상위설비 번호 */
    private String filterPEqNo				= "";
    /** 필터-상위설비 명 */
    private String filterPEqDesc			= "";
    /** 필터-최신 version 여부 */
    private String filterIsLastVersionId	= "";
    /** 필터-최신 version 여부 */
    private String filterIsLastVersionDesc	= "";
    /** 필터-문서번호  */
    private String filterDocNo				= "";
    /** 필터-Revision version No */
    private String filterRevNo				= "";
    /** 필터-제형 */
    private String filterProdShape         = "";
    /** 필터-회계자산 ID */
    private String filterAccAssetId         = "";
    /** 필터-회계자산 DESC */
    private String filterAccAssetDesc         = "";
    /** 필터-공장 ID */
    private String filterPlantId         	= "";
    /** 필터-공장 DESC */
    private String filterPlantDesc         	= "";
    /** 필터-Serial 번호 */
    private String filterSerialNo         	= "";
    /** 필터-구입일자 Start */
    private String filterStartBuyDate       = "";
    /** 필터-구입일자 End */
    private String filterEndBuyDate      	= "";
    /** 필터-사양 */
    private String filterSpecification      = "";
    /** 필터-사용자 ID */
    private String filterUserId      		= "";
    /** 필터-사용자  */
    private String filterUserName      		= "";
    /** 필터-교정대상 ID */
    private String filterCalPoint			= "";
    /** 설비유형 */
    private String eqCtgType				= "";
    /** 필터-Tag번호 */
    private String filterTagNo 				= "";
    /** 필터-사용부서 ID */
    private String filterUsageDeptId		= "";
    /** 필터-사용부서명 */
    private String filterUsageDeptDesc		= "";
    /** 생성일자 from */
    private String filterFromCreDate		= "";
    /** 생성일자 to */
    private String filterToCreDate			= "";
    /** Excel Tab No */
    private String exceltabNo				= "";
    /** 상위설비 사용부서 Id */
    private String filterPUsageDeptId       = "";
    /** 상위설비 사용부서 desc */
    private String filterPUsageDeptDesc     = "";
    /** SIA 등급 ID */
    private String filterPmiActionTypeId	= "";
    /** SIA 등급명 */
    private String filterPmiActionTypeDesc	= "";
    /** 필터-작업그룹 Id */
    private String filterWkctrId 			= "";
    /** 필터-작업그룹 Desc */
    private String filterWkctrDesc 			= "";
    /** 필터-G/W승인번호 */
    private String filterLappNo				= "";
    /** 필터-금형구분 */
    private String filterEqMoldAtype 		= "";
    /** 필터-금형구분 Desc */
    private String filterEqMoldAtypeDesc	= "";
    /** 필터-금형구분 */
    private String filterEqMoldBtype 		= "";
    /** 필터-금형구분 Desc */
    private String filterEqMoldBtypeDesc	= "";
    /** 필터-금형구분 */
    private String filterEqMoldCtype 		= "";
    /** 필터-금형구분 Desc */
    private String filterEqMoldCtypeDesc	= "";
    /** 필터-금형구분 Desc */
    private String filterStartDate          = "";
    /** 필터-금형구분 Desc */
    private String filterEndDate            = "";
    /** 설치일자 from */
    private String filterFromSetupDate		= "";
    /** 설치일자 to */
    private String filterToSetupDate		= "";
    /** 예방점검여부 */
    private String filterIsPmIns            = "";
    /** 보증기간 from */
    private String filterFromGuarDate		= "";
    /** 보증기간 to */
    private String filterToGuarDate			= "";
    /** 설비종류id */
    private String eqCtgId                  = "";
    /** 필터-부위 */
    private String filterEqasmbId           = "";
    /** 필터-부위 DESC */
    private String filterEqasmbDesc         = "";
    
    
	public String getFilterEqasmbId() {
		return filterEqasmbId;
	}
	public void setFilterEqasmbId(String filterEqasmbId) {
		this.filterEqasmbId = filterEqasmbId;
	}
	public String getFilterEqasmbDesc() {
		return filterEqasmbDesc;
	}
	public void setFilterEqasmbDesc(String filterEqasmbDesc) {
		this.filterEqasmbDesc = filterEqasmbDesc;
	}
	public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getFilterFromGuarDate() {
		return filterFromGuarDate;
	}
	public void setFilterFromGuarDate(String filterFromGuarDate) {
		this.filterFromGuarDate = CommonUtil.convertDate(filterFromGuarDate);
	}
	public String getFilterToGuarDate() {
		return filterToGuarDate;
	}
	public void setFilterToGuarDate(String filterToGuarDate) {
		this.filterToGuarDate = CommonUtil.convertDate(filterToGuarDate);
	}
	public String getFilterIsPmIns()
    {
        return filterIsPmIns;
    }
    public void setFilterIsPmIns(String filterIsPmIns)
    {
        this.filterIsPmIns = filterIsPmIns;
    }
    public String getFilterEqMoldBtype()
    {
        return filterEqMoldBtype;
    }
    public void setFilterEqMoldBtype(String filterEqMoldBtype)
    {
        this.filterEqMoldBtype = filterEqMoldBtype;
    }
    public String getFilterEqMoldBtypeDesc()
    {
        return filterEqMoldBtypeDesc;
    }
    public void setFilterEqMoldBtypeDesc(String filterEqMoldBtypeDesc)
    {
        this.filterEqMoldBtypeDesc = filterEqMoldBtypeDesc;
    }
    public String getFilterEqMoldCtype()
    {
        return filterEqMoldCtype;
    }
    public void setFilterEqMoldCtype(String filterEqMoldCtype)
    {
        this.filterEqMoldCtype = filterEqMoldCtype;
    }
    public String getFilterEqMoldCtypeDesc()
    {
        return filterEqMoldCtypeDesc;
    }
    public void setFilterEqMoldCtypeDesc(String filterEqMoldCtypeDesc)
    {
        this.filterEqMoldCtypeDesc = filterEqMoldCtypeDesc;
    }
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public String getFilterFromSetupDate() {
		return filterFromSetupDate;
	}
	public void setFilterFromSetupDate(String filterFromSetupDate) {
		this.filterFromSetupDate = CommonUtil.convertDate(filterFromSetupDate);
	}
	public String getFilterToSetupDate() {
		return filterToSetupDate;
	}
	public void setFilterToSetupDate(String filterToSetupDate) {
		this.filterToSetupDate = CommonUtil.convertDate(filterToSetupDate);
	}
	public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = filterStartDate;
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = filterEndDate;
    }
    public String getFilterEqMoldAtype()
    {
        return filterEqMoldAtype;
    }
    public void setFilterEqMoldAtype(String filterEqMoldAtype)
    {
        this.filterEqMoldAtype = filterEqMoldAtype;
    }
    public String getFilterEqMoldAtypeDesc()
    {
        return filterEqMoldAtypeDesc;
    }
    public void setFilterEqMoldAtypeDesc(String filterEqMoldAtypeDesc)
    {
        this.filterEqMoldAtypeDesc = filterEqMoldAtypeDesc;
    }
    public String getFilterLappNo() {
		return filterLappNo;
	}
	public void setFilterLappNo(String filterLappNo) {
		this.filterLappNo = filterLappNo;
	}
	public String getFilterWkctrId() {
		return filterWkctrId;
	}
	public void setFilterWkctrId(String filterWkctrId) {
		this.filterWkctrId = filterWkctrId;
	}
	public String getFilterWkctrDesc() {
		return filterWkctrDesc;
	}
	public void setFilterWkctrDesc(String filterWkctrDesc) {
		this.filterWkctrDesc = filterWkctrDesc;
	}
	public String getFilterCalPoint() {
		return filterCalPoint;
	}
	public void setFilterCalPoint(String filterCalPoint) {
		this.filterCalPoint = filterCalPoint;
	}
	public String getFilterPmiActionTypeId() {
		return filterPmiActionTypeId;
	}
	public void setFilterPmiActionTypeId(String filterPmiActionTypeId) {
		this.filterPmiActionTypeId = filterPmiActionTypeId;
	}
	public String getFilterPmiActionTypeDesc() {
		return filterPmiActionTypeDesc;
	}
	public void setFilterPmiActionTypeDesc(String filterPmiActionTypeDesc) {
		this.filterPmiActionTypeDesc = filterPmiActionTypeDesc;
	}
	public String getFilterPUsageDeptDesc()
    {
        return filterPUsageDeptDesc;
    }
    public void setFilterPUsageDeptDesc(String filterPUsageDeptDesc)
    {
        this.filterPUsageDeptDesc = filterPUsageDeptDesc;
    }
    public String getFilterPUsageDeptId()
    {
        return filterPUsageDeptId;
    }
    public void setFilterPUsageDeptId(String filterPUsageDeptId)
    {
        this.filterPUsageDeptId = filterPUsageDeptId;
    }
    
	public String getExceltabNo() {
		return exceltabNo;
	}
	public void setExceltabNo(String exceltabNo) {
		this.exceltabNo = exceltabNo;
	}
	public String getFilterFromCreDate() {
		return filterFromCreDate;
	}
	public void setFilterFromCreDate(String filterFromCreDate) {
		this.filterFromCreDate = CommonUtil.convertDate(filterFromCreDate);
	}
	public String getFilterToCreDate() {
		return filterToCreDate;
	}
	public void setFilterToCreDate(String filterToCreDate) {
		this.filterToCreDate = CommonUtil.convertDate(filterToCreDate);
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
	public String getFilterTagNo() {
		return filterTagNo;
	}
	public void setFilterTagNo(String filterTagNo) {
		this.filterTagNo = filterTagNo;
	}
	public String getFilterUserName() {
		return filterUserName;
	}
	public void setFilterUserName(String filterUserName) {
		this.filterUserName = filterUserName;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public String getFilterSpecification() {
		return filterSpecification;
	}
	public void setFilterSpecification(String filterSpecification) {
		this.filterSpecification = filterSpecification;
	}
	public String getFilterStartBuyDate() {
		return filterStartBuyDate;
	}
	public void setFilterStartBuyDate(String filterStartBuyDate) {
		this.filterStartBuyDate = filterStartBuyDate;
	}
	public String getFilterEndBuyDate() {
		return filterEndBuyDate;
	}
	public void setFilterEndBuyDate(String filterEndBuyDate) {
		this.filterEndBuyDate = filterEndBuyDate;
	}
	public String getFilterSerialNo() {
		return filterSerialNo;
	}
	public void setFilterSerialNo(String filterSerialNo) {
		this.filterSerialNo = filterSerialNo;
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
	public String getFilterEquipNo()
    {
        return filterEquipNo;
    }
    public void setFilterEquipNo(String filterEquipNo)
    {
        this.filterEquipNo = filterEquipNo;
    }
	public String getFilterPEquipNo()
    {
        return filterPEquipNo;
    }
    public void setFilterPEquipNo(String filterPEquipNo)
    {
        this.filterPEquipNo = filterPEquipNo;
    }
    public String getFilterAccAssetId()
    {
        return filterAccAssetId;
    }
    public void setFilterAccAssetId(String filterAccAssetId)
    {
        this.filterAccAssetId = filterAccAssetId;
    }
    public String getFilterAccAssetDesc()
    {
        return filterAccAssetDesc;
    }
    public void setFilterAccAssetDesc(String filterAccAssetDesc)
    {
        this.filterAccAssetDesc = filterAccAssetDesc;
    }
    public String getEqCtgType()
    {
        return eqCtgType;
    }
    public void setEqCtgType(String eqCtgType)
    {
        this.eqCtgType = eqCtgType;
    }
    public String getFilterProdShape()
    {
        return filterProdShape;
    }
    public void setFilterProdShape(String filterProdShape)
    {
        this.filterProdShape = filterProdShape;
    }
    public String getFilterDocNo() {
		return filterDocNo;
	}
	public void setFilterDocNo(String filterDocNo) {
		this.filterDocNo = filterDocNo;
	}
	public String getFilterRevNo() {
		return filterRevNo;
	}
	public void setFilterRevNo(String filterRevNo) {
		this.filterRevNo = filterRevNo;
	}
	public String getFilterIsLastVersionId() {
		return filterIsLastVersionId;
	}
	public void setFilterIsLastVersionId(String filterIsLastVersionId) {
		this.filterIsLastVersionId = filterIsLastVersionId;
	}
	public String getFilterIsLastVersionDesc() {
		return filterIsLastVersionDesc;
	}
	public void setFilterIsLastVersionDesc(String filterIsLastVersionDesc) {
		this.filterIsLastVersionDesc = filterIsLastVersionDesc;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getSelectedEqCtgTypeId() {
		return selectedEqCtgTypeId;
	}
	public void setSelectedEqCtgTypeId(String selectedEqCtgTypeId) {
		this.selectedEqCtgTypeId = selectedEqCtgTypeId;
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
	
	public String getFilterEqGradeId() {
		return filterEqGradeId;
	}

	public void setFilterEqGradeId(String filterEqGradeId) {
		this.filterEqGradeId = filterEqGradeId;
	}

	public String getFilterEqGradeDesc() {
		return filterEqGradeDesc;
	}

	public void setFilterEqGradeDesc(String filterEqGradeDesc) {
		this.filterEqGradeDesc = filterEqGradeDesc;
	}

	public String getFilterOldEqNo() {
		return filterOldEqNo;
	}

	public void setFilterOldEqNo(String filterOldEqNo) {
		this.filterOldEqNo = filterOldEqNo;
	}

	public String getEqStatusId()
    {
        return eqStatusId;
    }

    public void setEqStatusId(String eqStatusId)
    {
        this.eqStatusId = eqStatusId;
    }

    public String getEqStatusDesc()
    {
        return eqStatusDesc;
    }

    public void setEqStatusDesc(String eqStatusDesc)
    {
        this.eqStatusDesc = eqStatusDesc;
    }

    public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
		super.setAuditKey(equipId);
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
    public String getEqBuildAddr()
    {
        return eqBuildAddr;
    }
    public void setEqBuildAddr(String eqBuildAddr)
    {
        this.eqBuildAddr = eqBuildAddr;
    }
    public String getFilterPEqId()
    {
        return filterPEqId;
    }
    public void setFilterPEqId(String filterPEqId)
    {
        this.filterPEqId = filterPEqId;
    }
    public String getFilterPEqDesc()
    {
        return filterPEqDesc;
    }
    public void setFilterPEqDesc(String filterPEqDesc)
    {
        this.filterPEqDesc = filterPEqDesc;
    }
    public String getFilterPEqNo()
    {
        return filterPEqNo;
    }
    public void setFilterPEqNo(String filterPEqNo)
    {
        this.filterPEqNo = filterPEqNo;
    }

    
    
	
	
	
}
