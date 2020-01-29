package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���� ���԰��ⱸ �� DTO
 * @author  kim21017
 * @version $Id: MaWoResultToolDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultToolDetailDTO extends BaseDTO
{
	/** �۾���� �������� id */
	private String woToolId			= "";
	/** ��������id*/
	private String partId			= "";
	/** ��������No*/
	private String partNo			= "";
	/** ��������� */
	private String partDesc			= "";
	/** ������ */
	private String useQty 			= "";
	/** ȸ������ */
	private String disUseRtnQty 	= "";
	/** ���â��id */
	private String wcodeId 			= "";
	/** ���â��� */
	private String wcodeDesc		= "";
	/** ��� */
	private String remark 			= "";
	/** ��ǰ����ڵ� */
	private String partGradeId		= "";
	/** ��ǰ��޸� */
	private String partGradeDesc 	= "";
	/** ����� */
	private String stockQty			= "";
    /** ��ǰ �԰� */
    private String partSize			= "";
    /** MultiSelect Part ID */
    private String multiPartKey     = "";
    /** MultiSelect Part DESC */
    private String multiPartDesc    = "";
    /** WO ID */
    private String wkOrId           = "";
    
	public String getWoToolId() {
		return woToolId;
	}
	public void setWoToolId(String woToolId) {
		this.woToolId = woToolId;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
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
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = CommonUtil.convertMoney(useQty);
	}
	public String getDisUseRtnQty() {
		return disUseRtnQty;
	}
	public void setDisUseRtnQty(String disUseRtnQty) {
		this.disUseRtnQty = CommonUtil.convertMoney(disUseRtnQty);
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getWcodeDesc() {
		return wcodeDesc;
	}
	public void setWcodeDesc(String wcodeDesc) {
		this.wcodeDesc = wcodeDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPartGradeId() {
		return partGradeId;
	}
	public void setPartGradeId(String partGradeId) {
		this.partGradeId = partGradeId;
	}
	public String getPartGradeDesc() {
		return partGradeDesc;
	}
	public void setPartGradeDesc(String partGradeDesc) {
		this.partGradeDesc = partGradeDesc;
	}
	public String getStockQty() {
		return stockQty;
	}
	public void setStockQty(String stockQty) {
		this.stockQty = CommonUtil.convertMoney(stockQty);
	}
	public String getPartSize() {
		return partSize;
	}
	public void setPartSize(String partSize) {
		this.partSize = partSize;
	}
	public String getMultiPartKey() {
		return multiPartKey;
	}
	public void setMultiPartKey(String multiPartKey) {
		this.multiPartKey = multiPartKey;
	}
	public String getMultiPartDesc() {
		return multiPartDesc;
	}
	public void setMultiPartDesc(String multiPartDesc) {
		this.multiPartDesc = multiPartDesc;
	}
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
    
    
}