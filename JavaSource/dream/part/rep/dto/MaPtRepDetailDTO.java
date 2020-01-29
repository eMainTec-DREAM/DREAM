package dream.part.rep.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 부품수리 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtRepDetailDTO extends BaseDTO
{
	/** 부품수리ID */
	private String ptRepairListId     = "";
	/** 수리번호 */
	private String ptRepairListNo     = "";
	/** 수리상태 */
	private String ptRepairListStatus = "";
	private String ptRepairListStatusDesc = "";
	/** 작업 Id */
	private String wkorId          = "";
	/** 작업 명 */
	private String woName          = "";
	private String equipName       = "";
	/** 작업자재Id */
	private String woPartId        = "";
	/** 담당부서 */
	private String deptId          = "";
	private String deptDesc        = "";
	/** 수리창고Id */
	private String wcodeId         = "";
    /** 수리창고명 */
    private String wname           = "";
	/** 거래처 Id */
	private String vendorId        = "";
	private String vendorDesc      = "";
	/** 수리일자 */
	private String repairDate      = "";
	
	/** 작성일자 */
	private String regDate         = "";
	/** 수리품목 자재Id */
	private String partId          = "";
	/** 수리품목 자재번호 */
	private String partNo          = "";
	/** 수리품목 자재 품명/규격 */
	private String partNameSize    = "";
	/** 수리수량 */
	private String repairQty       = "";
	/** 수리단가 */
	private String unitPrice       = "";
	/** 수리금액 */
	private String totPrice        = "";
	/** 검수자 Id */
	private String inspector       = "";
	/** 검수자명 */
	private String inspectorName   = "";
	/** 비고 */
	private String remark          = "";
	/** 재고등급*/
	private String partGrade       = "";
	/** 재고등급명*/
	private String partGradeDesc   = "";

	/** 입고이력 : 입고(C)/입고취소(R) */
	private String ptRecMode       = "";
	/** 입고이력 : 입고이력ID */
	private String ptRecHistId     = "";
	private String requestDate     = "";
	private String requestName     = "";
	private String requestBy       = "";
	
	private String isSerial        = "";
	private String serialNo        = "";
	private String equipId         = "";
	
	/** 설비번호 */
	private String itemNo		   = "";

	/** 공장  */
	private String plantId		   = "";
	/** 공장명  */
	private String plantDesc	   = "";
	
	/** 부위 ID */
	private String eqAsmbId		   = "";
	/** 부위명 */
	private String eqAsmbDesc	   = "";
	
	
    public String getPartGradeDesc()
    {
        return partGradeDesc;
    }
    public void setPartGradeDesc(String partGradeDesc)
    {
        this.partGradeDesc = partGradeDesc;
    }
    public String getEqAsmbId() {
		return eqAsmbId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
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
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getPartGrade() {
		return partGrade;
	}
	public void setPartGrade(String partGrade) {
		this.partGrade = partGrade;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getIsSerial() {
		return isSerial;
	}
	public void setIsSerial(String isSerial) {
		this.isSerial = isSerial;
	}
	public String getPtRecHistId()
    {
        return ptRecHistId;
    }
    public void setPtRecHistId(String ptRecHistId)
    {
        this.ptRecHistId = ptRecHistId;
    }
    public String getWname()
    {
        return wname;
    }
    public void setWname(String wname)
    {
        this.wname = wname;
    }
    public String getPtRecMode()
    {
        return ptRecMode;
    }
    public void setPtRecMode(String ptRecMode)
    {
        this.ptRecMode = ptRecMode;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getPtRepairListId()
    {
        return ptRepairListId;
    }
    public void setPtRepairListId(String ptRepairListId)
    {
        this.ptRepairListId = ptRepairListId;
        super.setAuditKey(ptRepairListId);
    }
    public String getPtRepairListNo()
    {
        return ptRepairListNo;
    }
    public void setPtRepairListNo(String ptRepairListNo)
    {
        this.ptRepairListNo = ptRepairListNo;
    }
    public String getPtRepairListStatus()
    {
        return ptRepairListStatus;
    }
    public void setPtRepairListStatus(String ptRepairListStatus)
    {
        this.ptRepairListStatus = ptRepairListStatus;
    }
    public String getPtRepairListStatusDesc()
    {
        return ptRepairListStatusDesc;
    }
    public void setPtRepairListStatusDesc(String ptRepairListStatusDesc)
    {
        this.ptRepairListStatusDesc = ptRepairListStatusDesc;
    }
    public String getWkorId()
    {
        return wkorId;
    }
    public void setWkorId(String wkorId)
    {
        this.wkorId = wkorId;
    }
    public String getWoPartId()
    {
        return woPartId;
    }
    public void setWoPartId(String woPartId)
    {
        this.woPartId = woPartId;
    }
    public String getRepairDate()
    {
        return repairDate;
    }
    public void setRepairDate(String repairDate)
    {
        this.repairDate = CommonUtil.convertDate(repairDate);
    }
    public String getRegDate()
    {
        return regDate;
    }
    public void setRegDate(String regDate)
    {
        this.regDate = CommonUtil.convertDate(regDate);
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
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
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartNameSize()
    {
        return partNameSize;
    }
    public void setPartNameSize(String partNameSize)
    {
        this.partNameSize = partNameSize;
    }
    public String getRepairQty()
    {
        return repairQty;
    }
    public void setRepairQty(String repairQty)
    {
        this.repairQty = CommonUtil.convertMoney(repairQty);
    }
    public String getUnitPrice()
    {
        return unitPrice;
    }
    public void setUnitPrice(String unitPrice)
    {
        this.unitPrice = CommonUtil.convertMoney(unitPrice);
    }
    public String getTotPrice()
    {
        return totPrice;
    }
    public void setTotPrice(String totPrice)
    {
        this.totPrice = CommonUtil.convertMoney(totPrice);
    }
    public String getInspector()
    {
        return inspector;
    }
    public void setInspector(String inspector)
    {
        this.inspector = inspector;
    }
    public String getInspectorName()
    {
        return inspectorName;
    }
    public void setInspectorName(String inspectorName)
    {
        this.inspectorName = inspectorName;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getWoName() {
		return woName;
	}
	public void setWoName(String woName) {
		this.woName = woName;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = CommonUtil.convertDate(requestDate);
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}
	
}


