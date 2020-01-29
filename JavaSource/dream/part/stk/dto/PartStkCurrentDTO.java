package dream.part.stk.dto;

import common.bean.BaseDTO;

/**
 * �� dto
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
	
	/** â��� */
	private String wname           = "";
	/** �����ȣ */
	private String partNo          	= "";
	/** ��ǰ�� */
	private String partDesc        	= "";
	/** �԰� */
	private String ptSize          	= "";
	/** �� */
	private String model           	= "";
	/** ���� ǰ��/�԰� */
	private String partNameSize    	= "";
	/** ������¸� */
	private String partGradeDesc   		= "";
	/** a������ */
	private String astockQty        	= "";
	/** b������ */
	private String bstockQty        	= "";
	/** ������� Max*/
	private String maxSaftyQty      	= "";
	/** ������� Min*/
	private String minSaftyQty      	= "";
	/** a������ġ */
	private String abinNo            	= "";
	/** b������ġ */
	private String bbinNo            	= "";
	/** â��з�  */
	private String whType           	= "";
	/** �ø���  */
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