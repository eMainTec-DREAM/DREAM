package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업결과 고장내역 상세 DTO
 * @author  kim21017
 * @version $Id: MaWoResultFailDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultFailDetailDTO extends BaseDTO
{
	/** 설비부위ID */
	private String eqAsmbId 		= "";
	/** 설비부위명 */
	private String eqAsmbDesc 		= "";
	/** 계통코드 */
	private String fsCd 			= "";
	/** 계통코드명 */
	private String fsCdDesc 		= "";
	/** 계통설명 */
	private String fsDesc 			= "";
	/** 현상코드 */
	private String moCd 			= "";
	/** 현상코드명 */
	private String moCdDesc 		= "";
	/** 현상설명 */
	private String moDesc 		= "";
	/** 원인코드 */
	private String caCd 			= "";
	/** 원인코드명 */
	private String caCdDesc 		= "";
	/** 원인설명 */
	private String caDesc 			= "";
	/** 조치코드 */
	private String reCd 			= "";
	/** 조치코드명 */
	private String reCdDesc 		= "";
	/** 조치설명 */
	private String reDesc 			= "";
	/** 설비고장 FROM 작업일자 */
	private String eqDnStartDate 	= "";
	/** 설비고장 FROM 작업시간 */
	private String eqDnStartTime 	= "";
	/** 설비고장 TO 작업일자 */
	private String eqDnEndDate 		= "";
	/** 설비고장 TO 작업시간 */
	private String eqDnEndTime 		= "";
	/** 설비고장 작업시간(분) */
	private String eqDnWorkTime 	= "";
	/** 라인정지 FROM 작업일자 */
	private String lnDnStartDate 	= "";
	/** 라인정지 FROM 작업시간 */
	private String lnDnStartTime 	= "";
	/** 라인정지 TO 작업일자 */
	private String lnDnEndDate 		= "";
	/** 라인정지 TO 작업시간 */
	private String lnDnEndTime 		= "";
	/** 라인정지 작업시간(분) */
	private String lnDnWorkTime 	= "";
	/** 비고 */
	private String remark 			= "";
	/** 설비번호 */
	private String equipId 			= "";
	
	public String getFsDesc() {
		return fsDesc;
	}
	public void setFsDesc(String fsDesc) {
		this.fsDesc = fsDesc;
	}
	public String getFsCd() {
		return fsCd;
	}
	public void setFsCd(String fsCd) {
		this.fsCd = fsCd;
	}
	public String getFsCdDesc() {
		return fsCdDesc;
	}
	public void setFsCdDesc(String fsCdDesc) {
		this.fsCdDesc = fsCdDesc;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
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
	public String getMoCd() {
		return moCd;
	}
	public void setMoCd(String moCd) {
		this.moCd = moCd;
	}
	public String getMoCdDesc() {
		return moCdDesc;
	}
	public void setMoCdDesc(String moCdDesc) {
		this.moCdDesc = moCdDesc;
	}
	public String getCaCd() {
		return caCd;
	}
	public void setCaCd(String caCd) {
		this.caCd = caCd;
	}
	public String getCaCdDesc() {
		return caCdDesc;
	}
	public void setCaCdDesc(String caCdDesc) {
		this.caCdDesc = caCdDesc;
	}
	public String getCaDesc() {
		return caDesc;
	}
	public void setCaDesc(String caDesc) {
		this.caDesc = caDesc;
	}
	public String getReCd() {
		return reCd;
	}
	public void setReCd(String reCd) {
		this.reCd = reCd;
	}
	public String getReCdDesc() {
		return reCdDesc;
	}
	public void setReCdDesc(String reCdDesc) {
		this.reCdDesc = reCdDesc;
	}
	public String getReDesc() {
		return reDesc;
	}
	public void setReDesc(String reDesc) {
		this.reDesc = reDesc;
	}
	public String getEqDnStartDate() {
		return eqDnStartDate;
	}
	public void setEqDnStartDate(String eqDnStartDate) {
		this.eqDnStartDate = CommonUtil.convertDate(eqDnStartDate);
	}
	public String getEqDnStartTime() {
		return eqDnStartTime;
	}
	public void setEqDnStartTime(String eqDnStartTime) {
		this.eqDnStartTime = CommonUtil.convertTime(eqDnStartTime);
	}
	public String getEqDnEndDate() {
		return eqDnEndDate;
	}
	public void setEqDnEndDate(String eqDnEndDate) {
		this.eqDnEndDate = CommonUtil.convertDate(eqDnEndDate);
	}
	public String getEqDnEndTime() {
		return eqDnEndTime;
	}
	public void setEqDnEndTime(String eqDnEndTime) {
		this.eqDnEndTime = CommonUtil.convertTime(eqDnEndTime);
	}
	public String getEqDnWorkTime() {
		return eqDnWorkTime;
	}
	public void setEqDnWorkTime(String eqDnWorkTime) {
		this.eqDnWorkTime = CommonUtil.convertMoney(eqDnWorkTime);
	}
	public String getLnDnStartDate() {
		return lnDnStartDate;
	}
	public void setLnDnStartDate(String lnDnStartDate) {
		this.lnDnStartDate = CommonUtil.convertDate(lnDnStartDate);
	}
	public String getLnDnStartTime() {
		return lnDnStartTime;
	}
	public void setLnDnStartTime(String lnDnStartTime) {
		this.lnDnStartTime = CommonUtil.convertTime(lnDnStartTime);
	}
	public String getLnDnEndDate() {
		return lnDnEndDate;
	}
	public void setLnDnEndDate(String lnDnEndDate) {
		this.lnDnEndDate = CommonUtil.convertDate(lnDnEndDate);
	}
	public String getLnDnEndTime() {
		return lnDnEndTime;
	}
	public void setLnDnEndTime(String lnDnEndTime) {
		this.lnDnEndTime = CommonUtil.convertTime(lnDnEndTime);
	}
	public String getLnDnWorkTime() {
		return lnDnWorkTime;
	}
	public void setLnDnWorkTime(String lnDnWorkTime) {
		this.lnDnWorkTime = CommonUtil.convertMoney(lnDnWorkTime);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMoDesc() {
		return moDesc;
	}
	public void setMoDesc(String moDesc) {
		this.moDesc = moDesc;
	}
}