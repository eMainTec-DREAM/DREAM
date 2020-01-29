package dream.part.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재재고 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtStckCommonDTO extends BaseDTO
{
	/** ID */
	private String wcodeId             = "";
	private String partId              = "";
	private String partGrade           = "";
    /** 구매신청 생성 시 시퀀스  */
    private String reqIdx = "";
    
	public String getReqIdx() {
		return reqIdx;
	}

	public void setReqIdx(String reqIdx) {
		this.reqIdx = reqIdx;
	}
	
	/** 필터-창고Id */
	private String filterWcodeId       = "";
	/** 필터-창고명 */
	private String filterWname         = "";
	/** 필터-부품번호 */
	private String filterPartNo        = "";
	/** 필터-부품번명 */
	private String filterPartDesc      = "";
	/** 필터-제작사 */
	private String filterMaker         = "";
	/** 필터-모델 */
	private String filterModel         = "";
	/** 필터-안전재고미만 */
	private String filterSaftyYN       = "";
	/** 필터-총재고 이상수량 */
	private String filterQtyCnt        = "";
    /** 자재그룹 */
    private String filterPartGroup     = "";
    /** 자재그룹명 */
    private String filterPartGroupDesc = "";
    /** vendor code */
    private String filterVendorPtCode  = "";
    /** 자재중요도 */
    private String filterPtAbcClass    = "";
    /** 자재중요도 */
    private String filterPtAbcClassDesc= "";
    /** 필터 사용설비 Id */
    private String filterEquipId                 = "";
    /** 필터 사용설비 명 */
    private String filterEquipDesc              = "";
    /** 계약여부  */
    private String isCont               = "";
    /** 필터-공장 ID */
    private String filterPlantId         	= "";
    /** 필터-공장 DESC */
    private String filterPlantDesc         	= "";
    
    

    
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

	public String getIsCont()
    {
        return isCont;
    }

    public void setIsCont(String isCont)
    {
        this.isCont = isCont;
    }

    public String getFilterEquipId()
    {
        return filterEquipId;
    }

    public void setFilterEquipId(String filterEquipId)
    {
        this.filterEquipId = filterEquipId;
    }

    public String getFilterEquipDesc()
    {
        return filterEquipDesc;
    }

    public void setFilterEquipDesc(String filterEquipDesc)
    {
        this.filterEquipDesc = filterEquipDesc;
    }

    public String getFilterPtAbcClass() {
		return filterPtAbcClass;
	}

	public void setFilterPtAbcClass(String filterPtAbcClass) {
		this.filterPtAbcClass = filterPtAbcClass;
	}

	public String getFilterPtAbcClassDesc() {
		return filterPtAbcClassDesc;
	}

	public void setFilterPtAbcClassDesc(String filterPtAbcClassDesc) {
		this.filterPtAbcClassDesc = filterPtAbcClassDesc;
	}

	public String getFilterVendorPtCode() {
		return filterVendorPtCode;
	}

	public void setFilterVendorPtCode(String filterVendorPtCode) {
		this.filterVendorPtCode = filterVendorPtCode;
	}

	public String getFilterPartGroup() {
		return filterPartGroup;
	}

	public void setFilterPartGroup(String filterPartGroup) {
		this.filterPartGroup = filterPartGroup;
	}

	public String getFilterPartGroupDesc() {
		return filterPartGroupDesc;
	}

	public void setFilterPartGroupDesc(String filterPartGroupDesc) {
		this.filterPartGroupDesc = filterPartGroupDesc;
	}

	public String getFilterQtyCnt() {
		return filterQtyCnt;
	}

	public void setFilterQtyCnt(String filterQtyCnt) {
		this.filterQtyCnt = CommonUtil.convertMoney(filterQtyCnt);
	}

	public String getFilterSaftyYN() {
		return filterSaftyYN;
	}
	public void setFilterSaftyYN(String filterSaftyYN) {
		this.filterSaftyYN = filterSaftyYN;
	}
	public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
        super.setAuditKey(wcodeId);
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
        super.setAuditKey(partId);
    }
    public String getPartGrade()
    {
        return partGrade;
    }
    public void setPartGrade(String partGrade)
    {
        this.partGrade = partGrade;
    }
    public String getFilterWcodeId()
    {
        return filterWcodeId;
    }
    public void setFilterWcodeId(String filterWcodeId)
    {
        this.filterWcodeId = filterWcodeId;
    }

    public String getFilterWname()
    {
        return filterWname;
    }
    public void setFilterWname(String filterWname)
    {
        this.filterWname = filterWname;
    }
    public String getFilterPartNo()
    {
        return filterPartNo;
    }
    public void setFilterPartNo(String filterPartNo)
    {
        this.filterPartNo = filterPartNo;
    }
    public String getFilterPartDesc()
    {
        return filterPartDesc;
    }
    public void setFilterPartDesc(String filterPartDesc)
    {
        this.filterPartDesc = filterPartDesc;
    }
    public String getFilterMaker()
    {
        return filterMaker;
    }
    public void setFilterMaker(String filterMaker)
    {
        this.filterMaker = filterMaker;
    }
    public String getFilterModel()
    {
        return filterModel;
    }
    public void setFilterModel(String filterModel)
    {
        this.filterModel = filterModel;
    }

}
