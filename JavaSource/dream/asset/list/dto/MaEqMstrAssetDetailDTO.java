package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비 관련자산 상세 dto
 * @author  kim21017
 * @version $Id: MaEqMstrAssetDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrAssetDetailDTO extends BaseDTO
{
	/** 관련자산 id */
	private String eqAssetId		= "";
	/** 자산ID */
	private String assetId			= "";
	/** 자산번호 */
	private String assetNo			= "";
	/** 자산명 */
	private String assetDesc		= "";
	/** 취득일자 */
	private String acqDate			= "";
	/** 취득가액 */
	private String buyerAmt			= "";
	/** 감가상각액 */
	private String depAmt			= "";
	/** 잔존가액 */
	private String resAmt			= "";
	/** OLD 관련자산 id */
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