package dream.part.stk.dto;

import common.bean.BaseDTO;

/**
 * 상세 dto
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 */
public class PartStkCurrentDTO extends BaseDTO
{
	/** ID */
	private String wcodeId             = "";
	private String partId              = "";
	private String partGrade           = "";
	
	private String filterWcode			= "";
	private String filterPartDesc		= "";
	private String filterPartNo			= "";
	private String filterMaker			= "";
	private String filterModel			= "";
	private String filterPartGroup		= "";
	private String filterPartGroupDesc	= "";
	private String filterVendorPtCode	= "";
	private String filterBinNo			= "";
	private String filterPlant			= "";
	private String filterCompNo			= "";
	
	/** 창고명 */
	private String wname           = "";
	/** 자재번호 */
	private String partNo          	= "";
	/** 부품명 */
	private String partDesc        	= "";
	/** 규격 */
	private String ptSize          	= "";
	/** 모델 */
	private String model           	= "";
	/** 자재 품명/규격 */
	private String partNameSize    	= "";
	/** 자재상태명 */
	private String partGradeDesc   		= "";
	/** a재고수량 */
	private String astockQty        	= "";
	/** b재고수량 */
	private String bstockQty        	= "";
	/** 안전재고 Max*/
	private String maxSaftyQty      	= "";
	/** 안전재고 Min*/
	private String minSaftyQty      	= "";
	/** a보관위치 */
	private String abinNo            	= "";
	/** b보관위치 */
	private String bbinNo            	= "";
	/** 창고분류  */
	private String whType           	= "";
	/** 시리얼  */
	private String isSerial           	= "";
	
	public String getFilterPartGroup() {
		return filterPartGroup;
	}
	public void setFilterPartGroup(String filterPartGroup) {
		this.filterPartGroup = filterPartGroup;
	}
	public String getFilterWcode() {
		return filterWcode;
	}
	public void setFilterWcode(String filterWcode) {
		this.filterWcode = filterWcode;
	}
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	public String getFilterPartNo() {
		return filterPartNo;
	}
	public void setFilterPartNo(String filterPartNo) {
		this.filterPartNo = filterPartNo;
	}
	public String getFilterMaker() {
		return filterMaker;
	}
	public void setFilterMaker(String filterMaker) {
		this.filterMaker = filterMaker;
	}
	public String getFilterModel() {
		return filterModel;
	}
	public void setFilterModel(String filterModel) {
		this.filterModel = filterModel;
	}
	public String getFilterPartGroupDesc() {
		return filterPartGroupDesc;
	}
	public void setFilterPartGroupDesc(String filterPartGroupDesc) {
		this.filterPartGroupDesc = filterPartGroupDesc;
	}
	public String getFilterVendorPtCode() {
		return filterVendorPtCode;
	}
	public void setFilterVendorPtCode(String filterVendorPtCode) {
		this.filterVendorPtCode = filterVendorPtCode;
	}
	public String getFilterBinNo() {
		return filterBinNo;
	}
	public void setFilterBinNo(String filterBinNo) {
		this.filterBinNo = filterBinNo;
	}
	public String getFilterPlant() {
		return filterPlant;
	}
	public void setFilterPlant(String filterPlant) {
		this.filterPlant = filterPlant;
	}
	public String getFilterCompNo() {
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) {
		this.filterCompNo = filterCompNo;
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
        super.setAuditKey(partId);
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getPtSize() {
		return ptSize;
	}
	public void setPtSize(String ptSize) {
		this.ptSize = ptSize;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPartNameSize() {
		return partNameSize;
	}
	public void setPartNameSize(String partNameSize) {
		this.partNameSize = partNameSize;
	}
	public String getPartGrade() {
		return partGrade;
	}
	public void setPartGrade(String partGrade) {
		this.partGrade = partGrade;
	}
	public String getPartGradeDesc() {
		return partGradeDesc;
	}
	public void setPartGradeDesc(String partGradeDesc) {
		this.partGradeDesc = partGradeDesc;
	}
	public String getAstockQty() {
		return astockQty;
	}
	public void setAstockQty(String astockQty) {
		this.astockQty = astockQty;
	}
	public String getBstockQty() {
		return bstockQty;
	}
	public void setBstockQty(String bstockQty) {
		this.bstockQty = bstockQty;
	}
	public String getMaxSaftyQty() {
		return maxSaftyQty;
	}
	public void setMaxSaftyQty(String maxSaftyQty) {
		this.maxSaftyQty = maxSaftyQty;
	}
	public String getMinSaftyQty() {
		return minSaftyQty;
	}
	public void setMinSaftyQty(String minSaftyQty) {
		this.minSaftyQty = minSaftyQty;
	}
	public String getAbinNo() {
		return abinNo;
	}
	public void setAbinNo(String abinNo) {
		this.abinNo = abinNo;
	}
	public String getBbinNo() {
		return bbinNo;
	}
	public void setBbinNo(String bbinNo) {
		this.bbinNo = bbinNo;
	}
	public String getWhType() {
		return whType;
	}
	public void setWhType(String whType) {
		this.whType = whType;
	}
	public String getIsSerial() {
		return isSerial;
	}
	public void setIsSerial(String isSerial) {
		this.isSerial = isSerial;
	}
	
	
}