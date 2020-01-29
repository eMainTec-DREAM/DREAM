package dream.part.pur.buy.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매신청 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaPtBuyReqHdrDetailDTO extends BaseDTO
{
	/** 요청ID */
	private String ptPrListId 					= "";
	/** 요청No */
	private String ptPrListNo 					= "";
	/** 요청상태id */
	private String ptPrListStatusId 			= "";
	/** 요청상태명 */
	private String ptPrListStatusDesc 			= "";
	/** 제목 */	
	private String ptPrListDesc					= "";
	/** 신청부서id */
	private String deptId 						= "";
	/** 신청부서명 */
	private String deptDesc 					= "";
	/** 신청자id */
	private String userId 						= "";
	/** 신청자명 */
	private String userDesc 					= "";
	/** 거래처Id */
	private String vendorId 					= "";
	/** 거래처명 */
	private String vendorDesc	 				= "";
	/** 신청일자 */
	private String reqDate		 				= "";
	/** 비거 */
	private String remark		 				= "";
	private String userNo		 				= "";
	
	/** 공장  */
	private String plantId						= "";
	/** 공장명  */
	private String plantDesc					= "";
	
	/** 접수자 Id */
	private String recById						= "";
	/** 접수자명 */
	private String recByName 					= "";
	
	/** 발주 Id */
	private String poListId						= "";
	/** 화폐 */
	private String curr							= "";
	/** ERP PR번호 */
	private String erpPrNo         				= "";
	/** 접수공장 ID */
    private String recPlant                     = "";
    /** 접수공장 명*/
    private String recPlantDesc                 = "";
    /** 접수부서 ID*/
    private String recDeptId                    = "";
    /** 접수부서 명*/
    private String recDeptDesc                  = "";
    /** 금액*/
    private String totAmt                       = "";
    /** 창고Id*/
    private String wcodeId                      = "";
	
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getTotAmt()
    {
        return totAmt;
    }
    public void setTotAmt(String totAmt)
    {
        this.totAmt = totAmt;
    }
    public String getRecPlant()
    {
        return recPlant;
    }
    public void setRecPlant(String recPlant)
    {
        this.recPlant = recPlant;
    }
    public String getRecPlantDesc()
    {
        return recPlantDesc;
    }
    public void setRecPlantDesc(String recPlantDesc)
    {
        this.recPlantDesc = recPlantDesc;
    }
    public String getRecDeptId()
    {
        return recDeptId;
    }
    public void setRecDeptId(String recDeptId)
    {
        this.recDeptId = recDeptId;
    }
    public String getRecDeptDesc()
    {
        return recDeptDesc;
    }
    public void setRecDeptDesc(String recDeptDesc)
    {
        this.recDeptDesc = recDeptDesc;
    }
    public String getErpPrNo()
    {
        return erpPrNo;
    }
    public void setErpPrNo(String erpPrNo)
    {
        this.erpPrNo = erpPrNo;
    }
    public String getCurr() {
		return curr;
	}
	public void setCurr(String curr) {
		this.curr = curr;
	}
	public String getPoListId() {
		return poListId;
	}
	public void setPoListId(String poListId) {
		this.poListId = poListId;
	}
	public String getRecById() {
		return recById;
	}
	public void setRecById(String recById) {
		this.recById = recById;
	}
	public String getRecByName() {
		return recByName;
	}
	public void setRecByName(String recByName) {
		this.recByName = recByName;
	}
	public String getPlantId() {
		return plantId;
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
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = CommonUtil.convertDate(reqDate);
	}
	public String getPtPrListId() {
		return ptPrListId;
	}
	public void setPtPrListId(String ptPrListId) {
		this.ptPrListId = ptPrListId;
		super.setAuditKey(ptPrListId);
	}
	public String getPtPrListNo() {
		return ptPrListNo;
	}
	public void setPtPrListNo(String ptPrListNo) {
		this.ptPrListNo = ptPrListNo;
	}
	public String getPtPrListStatusId() {
		return ptPrListStatusId;
	}
	public void setPtPrListStatusId(String ptPrListStatusId) {
		this.ptPrListStatusId = ptPrListStatusId;
	}
	public String getPtPrListStatusDesc() {
		return ptPrListStatusDesc;
	}
	public void setPtPrListStatusDesc(String ptPrListStatusDesc) {
		this.ptPrListStatusDesc = ptPrListStatusDesc;
	}
	public String getPtPrListDesc() {
		return ptPrListDesc;
	}
	public void setPtPrListDesc(String ptPrListDesc) {
		this.ptPrListDesc = ptPrListDesc;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorDesc() {
		return vendorDesc;
	}
	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
	}
	
}
