package dream.part.rec.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매입고 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtRecDetailDTO extends BaseDTO
{
	/** 구매입고ID */
	private String prRecListId     = "";
	/** 입고번호 */
	private String prRecListNo     = "";
	/** 입고상태Id */
	private String prRecListStatus = "";
	/** 입고상태명 */
	private String prRecListStatusDesc = "";
	/** 담당부서Id */
	private String deptId          = "";
	/** 담당부서명 */
	private String deptDesc        = "";
	/** 입고창고Id */
	private String wcodeId         = "";
	/** 입고창고명 */
	private String wname           = "";
	/** 거래처 Id */
	private String vendorId        = "";
	/** 거래처명 */
	private String vendorDesc      = "";
	/** 입고일자 */
	private String recDate         = "";
	/** 입고품목 자재Id */
	private String partId          = "";
	/** 입고품목 자재번호 */
	private String partNo          = "";
	/** 입고품목 자재명/규격 */
	private String partNameSize    = "";
	/** 입고수량 */
	private String recQty          = "";
	/** 입고단가 */
	private String unitPrice       = "";
	/** 입고금액 */
	private String totPrice        = "";
	/** 검수자Id */
	private String inspector       = "";
	/** 검수자명 */
	private String inspectorName   = "";
	/** 비고 */
	private String remark          = "";
	/** 자재등급 */
	private String partGrade       = "";
	/** 자재등급명 */
	private String partGradeDesc   = "";
	
	private String isSerial        = "";
	
	private String countWo         = "";
	
	/** 입고품목 자재명 */
	private String partDesc        = "";
	/** 입고품목 자재규격 */
	private String partSize    	   = "";
	/** 입고품목 model */
	private String model    	   = "";
	/** 입고품목 단위 */
	private String uom    		   = "";
	/** 품번 생성 여부 ID */
	private String isMakePartNoId  = "";
	/** 품번 생성 여부 */
	private String isMakePartNo    = "";

	/** 공장  */
	private String plantId		   = "";
	/** 공장명  */
	private String plantDesc	   = "";
	
	private String invtlistId      = "";
	
	private String invtlistDesc    = "";
	
	private String polistId        = "";
	
	private String polistDesc      = "";
	
	private String ptpritemId      = "";
	
	private String poitemId        = "";
	/** 화폐단위 ID */
	private String currencyId		= "";
	/** 화폐단위 DESC */
	private String currencyDesc		= "";
	
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
	public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public String getPoitemId()
    {
        return poitemId;
    }
    public void setPoitemId(String poitemId)
    {
        this.poitemId = poitemId;
    }
    public String getPtpritemId()
    {
        return ptpritemId;
    }
    public void setPtpritemId(String ptpritemId)
    {
        this.ptpritemId = ptpritemId;
    }
    public String getPolistId()
    {
        return polistId;
    }
    public void setPolistId(String polistId)
    {
        this.polistId = polistId;
    }
    public String getPolistDesc()
    {
        return polistDesc;
    }
    public void setPolistDesc(String polistDesc)
    {
        this.polistDesc = polistDesc;
    }
    public String getInvtlistId()
    {
        return invtlistId;
    }
    public void setInvtlistId(String invtlistId)
    {
        this.invtlistId = invtlistId;
    }
    public String getInvtlistDesc()
    {
        return invtlistDesc;
    }
    public void setInvtlistDesc(String invtlistDesc)
    {
        this.invtlistDesc = invtlistDesc;
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
	public String getIsMakePartNoId() {
		return isMakePartNoId;
	}
	public void setIsMakePartNoId(String isMakePartNoId) {
		this.isMakePartNoId = isMakePartNoId;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public String getIsMakePartNo() {
		return isMakePartNo;
	}
	public void setIsMakePartNo(String isMakePartNo) {
		this.isMakePartNo = isMakePartNo;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getPartSize() {
		return partSize;
	}
	public void setPartSize(String partSize) {
		this.partSize = partSize;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getCountWo() {
		return countWo;
	}
	public void setCountWo(String countWo) {
		this.countWo = countWo;
	}
	public String getIsSerial() {
		return isSerial;
	}
	public void setIsSerial(String isSerial) {
		this.isSerial = isSerial;
	}
	public String getPartGrade()
    {
        return partGrade;
    }
    public void setPartGrade(String partGrade)
    {
        this.partGrade = partGrade;
    }
    public String getPartGradeDesc() {
		return partGradeDesc;
	}
	public void setPartGradeDesc(String partGradeDesc) {
		this.partGradeDesc = partGradeDesc;
	}
	/** 입고이력 - C:입고완료, R:입고취소 */
	private String ptRecMode   = "";

    public String getPtRecMode()
    {
        return ptRecMode;
    }
    public void setPtRecMode(String ptRecMode)
    {
        this.ptRecMode = ptRecMode;
    }
    public String getWname()
    {
        return wname;
    }
    public void setWname(String wname)
    {
        this.wname = wname;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getPrRecListId()
    {
        return prRecListId;
    }
    public void setPrRecListId(String prRecListId)
    {
        this.prRecListId = prRecListId;
        super.setAuditKey(prRecListId);
    }
    public String getPrRecListNo()
    {
        return prRecListNo;
    }
    public void setPrRecListNo(String prRecListNo)
    {
        this.prRecListNo = prRecListNo;
    }
    public String getPrRecListStatus()
    {
        return prRecListStatus;
    }
    public void setPrRecListStatus(String prRecListStatus)
    {
        this.prRecListStatus = prRecListStatus;
    }
    public String getPrRecListStatusDesc()
    {
        return prRecListStatusDesc;
    }
    public void setPrRecListStatusDesc(String prRecListStatusDesc)
    {
        this.prRecListStatusDesc = prRecListStatusDesc;
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
    public String getRecDate()
    {
        return recDate;
    }
    public void setRecDate(String recDate)
    {
        this.recDate = CommonUtil.convertDate(recDate);
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
    public String getRecQty()
    {
        return recQty;
    }
    public void setRecQty(String recQty)
    {
        this.recQty = CommonUtil.convertMoney(recQty);
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
    
	
}
