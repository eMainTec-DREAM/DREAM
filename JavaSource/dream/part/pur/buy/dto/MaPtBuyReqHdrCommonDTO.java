package dream.part.pur.buy.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매신청 공통 DTO
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaPtBuyReqHdrCommonDTO extends BaseDTO
{
	/** 자재요청 id */
	private String ptPrListId 				= "";
	/** 필터 신청부서id  */
	private String filterDeptId				= "";
	/** 필터 신청부서명 */
	private String filterDeptDesc			= "";
	/** 필터 시작 요청일자 */
	private String filterStartReqDate		= "";
	/** 필터 끝 요청일자 */
	private String filterEndReqDate			= "";
	/** 필터 제목 */
	private String filterPtPrListDesc		= "";
	/** 필터 자재id  */
	private String filterPartId				= "";
	/** 필터 자재No */
	private String filterPartNo				= "";
	/** 필터 자재명 */
	private String filterPartDesc			= "";
	/** 필터 작성상태id  */
	private String filterPtPrListStatusId	= "";
	/** 필터 작성상태명 */
	private String filterPtPrListStatusDesc	= "";
	/** 필터 품명/규격 */
	private String filterPtDescSize			= "";
	/** 필터 요청번호 */
	private String filterPtPrListNo			= "";
	/** 필터 신청자id  */
	private String filterUserId				= "";
	/** 필터 신청자명 */
	private String filterUserDesc			= "";

	/** 필터-공장 ID */
    private String filterPlantId         	= "";
    /** 필터-공장 DESC */
    private String filterPlantDesc        	= "";
    
    /** 필터 현장요청# */
    private String filterPtPnListNo			= "";
    /** 필터 ERP PR번호 */
    private String filterErpPrNo			= "";
    
    /** 필터 - 접수자 ID */
    private String filterRecById            = "";
    /** 필터 - 접수자 DESC */
    private String filterRecByDesc          = "";

    /** 필터 - 접수공장 ID */
    private String filterRecPlantId         = "";
    /** 필터 - 접수공장 DESC */
    private String filterRecPlantDesc       = "";
    
	public String getFilterRecById()
    {
        return filterRecById;
    }
    public void setFilterRecById(String filterRecById)
    {
        this.filterRecById = filterRecById;
    }
    public String getFilterRecByDesc()
    {
        return filterRecByDesc;
    }
    public void setFilterRecByDesc(String filterRecByDesc)
    {
        this.filterRecByDesc = filterRecByDesc;
    }
    public String getFilterRecPlantId()
    {
        return filterRecPlantId;
    }
    public void setFilterRecPlantId(String filterRecPlantId)
    {
        this.filterRecPlantId = filterRecPlantId;
    }
    public String getFilterRecPlantDesc()
    {
        return filterRecPlantDesc;
    }
    public void setFilterRecPlantDesc(String filterRecPlantDesc)
    {
        this.filterRecPlantDesc = filterRecPlantDesc;
    }
    public String getFilterErpPrNo()
    {
        return filterErpPrNo;
    }
    public void setFilterErpPrNo(String filterErpPrNo)
    {
        this.filterErpPrNo = filterErpPrNo;
    }
    public String getFilterPtPnListNo() {
		return filterPtPnListNo;
	}
	public void setFilterPtPnListNo(String filterPtPnListNo) {
		this.filterPtPnListNo = filterPtPnListNo;
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
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	public String getPtPrListId() {
		return ptPrListId;
	}
	public void setPtPrListId(String ptPrListId) {
		this.ptPrListId = ptPrListId;
		super.setAuditKey(ptPrListId);
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
	public String getFilterStartReqDate() {
		return filterStartReqDate;
	}
	public void setFilterStartReqDate(String filterStartReqDate) {
		this.filterStartReqDate = CommonUtil.convertDate(filterStartReqDate);
	}
	public String getFilterEndReqDate() {
		return filterEndReqDate;
	}
	public void setFilterEndReqDate(String filterEndReqDate) {
		this.filterEndReqDate = CommonUtil.convertDate(filterEndReqDate);
	}
	public String getFilterPtPrListDesc() {
		return filterPtPrListDesc;
	}
	public void setFilterPtPrListDesc(String filterPtPrListDesc) {
		this.filterPtPrListDesc = filterPtPrListDesc;
	}
	public String getFilterPartId() {
		return filterPartId;
	}
	public void setFilterPartId(String filterPartId) {
		this.filterPartId = filterPartId;
	}
	public String getFilterPartNo() {
		return filterPartNo;
	}
	public void setFilterPartNo(String filterPartNo) {
		this.filterPartNo = filterPartNo;
	}
	public String getFilterPtPrListStatusId() {
		return filterPtPrListStatusId;
	}
	public void setFilterPtPrListStatusId(String filterPtPrListStatusId) {
		this.filterPtPrListStatusId = filterPtPrListStatusId;
	}
	public String getFilterPtPrListStatusDesc() {
		return filterPtPrListStatusDesc;
	}
	public void setFilterPtPrListStatusDesc(String filterPtPrListStatusDesc) {
		this.filterPtPrListStatusDesc = filterPtPrListStatusDesc;
	}
	public String getFilterPtDescSize() {
		return filterPtDescSize;
	}
	public void setFilterPtDescSize(String filterPtDescSize) {
		this.filterPtDescSize = filterPtDescSize;
	}
	public String getFilterPtPrListNo() {
		return filterPtPrListNo;
	}
	public void setFilterPtPrListNo(String filterPtPrListNo) {
		this.filterPtPrListNo = filterPtPrListNo;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public String getFilterUserDesc() {
		return filterUserDesc;
	}
	public void setFilterUserDesc(String filterUserDesc) {
		this.filterUserDesc = filterUserDesc;
	}
	
}
