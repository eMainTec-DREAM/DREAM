package dream.asset.loc.goal.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaLnGoalDetailDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo  			= "";
	/** 일별라인가동ID */
	private String mtLnPointId      = "";
	/** 공장구분 */
    private String plant            = "";
    /** 공장구분명 */
    private String plantDesc        = "";
    /** 년 */
    private String yyyymm			= "";
    /** 라인코드 */
    private String eqlocId          = "";
    /** 라인코드명 */
    private String eqlocIdDesc      = "";
    /** 보전목표항목 */
    private String mtpoint          = "";
    /** 보전목표항목명 */
    private String mtpointDesc      = "";
    /** 실적값 */
    private String avalue           = "";
    /** 목표값 */
    private String pvalue           = "";
    /** 비고 */
    private String remark			= "";
    /** 비고 */
    private String eqlocType		= "";
    
    
	public String getEqlocType() {
		return eqlocType;
	}
	public void setEqlocType(String eqlocType) {
		this.eqlocType = eqlocType;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getMtLnPointId() {
		return mtLnPointId;
	}
	public void setMtLnPointId(String mtLnPointId) {
		this.mtLnPointId = mtLnPointId;
		super.setAuditKey(mtLnPointId);
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getYyyymm() {
		return yyyymm;
	}
	public void setYyyymm(String yyyymm) {
		this.yyyymm = CommonUtil.convertDate(yyyymm);
		
	}
	public String getEqlocId() {
		return eqlocId;
	}
	public void setEqlocId(String eqlocId) {
		this.eqlocId = eqlocId;
	}
	public String getEqlocIdDesc() {
		return eqlocIdDesc;
	}
	public void setEqlocIdDesc(String eqlocIdDesc) {
		this.eqlocIdDesc = eqlocIdDesc;
	}
	public String getMtpoint() {
		return mtpoint;
	}
	public void setMtpoint(String mtpoint) {
		this.mtpoint = mtpoint;
	}
	public String getMtpointDesc() {
		return mtpointDesc;
	}
	public void setMtpointDesc(String mtpointDesc) {
		this.mtpointDesc = mtpointDesc;
	}
	public String getAvalue() {
		return avalue;
	}
	public void setAvalue(String avalue) {
		this.avalue = CommonUtil.convertMoney(avalue);
	}
	public String getPvalue() {
		return pvalue;
	}
	public void setPvalue(String pvalue) {
		this.pvalue = CommonUtil.convertMoney(pvalue);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

    
}
