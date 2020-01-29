package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� �����ڻ� �� dto
 * @author  kim21017
 * @version $Id: MaEqMstrAssetDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrAssetDetailDTO extends BaseDTO
{
	/** �����ڻ� id */
	private String eqAssetId		= "";
	/** �ڻ�ID */
	private String assetId			= "";
	/** �ڻ��ȣ */
	private String assetNo			= "";
	/** �ڻ�� */
	private String assetDesc		= "";
	/** ������� */
	private String acqDate			= "";
	/** ��氡�� */
	private String buyerAmt			= "";
	/** �����󰢾� */
	private String depAmt			= "";
	/** �������� */
	private String resAmt			= "";
	/** OLD �����ڻ� id */
	private String oldEqAssetId		= "";
	
	
	public String getEqAssetId() {
		return eqAssetId;
	}
	public void setEqAssetId(String eqAssetId) {
		this.eqAssetId = eqAssetId;
		super.setAuditKey(eqAssetId);
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	public String getAssetDesc() {
		return assetDesc;
	}
	public void setAssetDesc(String assetDesc) {
		this.assetDesc = assetDesc;
	}
	public String getAcqDate() {
		return acqDate;
	}
	public void setAcqDate(String acqDate) {
		this.acqDate = CommonUtil.convertDate(acqDate);
	}
	public String getBuyerAmt() {
		return buyerAmt;
	}
	public void setBuyerAmt(String buyerAmt) {
		this.buyerAmt = buyerAmt;
	}
	public String getDepAmt() {
		return depAmt;
	}
	public void setDepAmt(String depAmt) {
		this.depAmt = depAmt;
	}
	public String getResAmt() {
		return resAmt;
	}
	public void setResAmt(String resAmt) {
		this.resAmt = resAmt;
	}
	public String getOldEqAssetId() {
		return oldEqAssetId;
	}
	public void setOldEqAssetId(String oldEqAssetId) {
		this.oldEqAssetId = oldEqAssetId;
	}
}