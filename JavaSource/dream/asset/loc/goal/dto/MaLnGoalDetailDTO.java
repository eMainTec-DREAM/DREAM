package dream.asset.loc.goal.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaLnGoalDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo  			= "";
	/** �Ϻ����ΰ���ID */
	private String mtLnPointId      = "";
	/** ���屸�� */
    private String plant            = "";
    /** ���屸�и� */
    private String plantDesc        = "";
    /** �� */
    private String yyyymm			= "";
    /** �����ڵ� */
    private String eqlocId          = "";
    /** �����ڵ�� */
    private String eqlocIdDesc      = "";
    /** ������ǥ�׸� */
    private String mtpoint          = "";
    /** ������ǥ�׸�� */
    private String mtpointDesc      = "";
    /** ������ */
    private String avalue           = "";
    /** ��ǥ�� */
    private String pvalue           = "";
    /** ��� */
    private String remark			= "";
    /** ��� */
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
