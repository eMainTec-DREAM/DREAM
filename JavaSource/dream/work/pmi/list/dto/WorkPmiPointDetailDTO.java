package dream.work.pmi.list.dto;

import java.util.List;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 점검작업 점검 상세 DTO
 * @author  kim21017
 * @version $Id: WorkPmiPointDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkPmiPointDetailDTO extends BaseDTO
{
	/** 점검작업 점검 id */
	private String pmInsPointId				= "";
	/** 점검항목ID */
	private String pmPointId				= "";
	/** 점검순서 */
	private String stepNum 					= "";
	/** 점검부위 */
	private String eqAsmbDesc 				= "";
	/** 점검항목 */
	private String checkPoint 				= "";
	/** 점검방법 */
	private String checkMethod 				= "";
	/** 적정기준 */
	private String fitBasis 				= "";
	/** 수치/판정 구분 */
	private String checkTypeDesc 			= "";
	/** 설정값/단위 */
	private String checkValUom 				= "";
	/** 비고 */
	private String remark 					= "";
	/** 검사결과code */
	private String pmPointRsltStatus 		= "";
	/** 검사결과desc */
	private String pmPointRsltStatusDesc 	= "";
	/** 검사값 */
	private String resultValue 				= "";
	/** 검사세부내용 */
	private String resultRemark 			= "";
	
	/** 표준점검항목 ID */
    private String stwrkPointId     = "";    
    /** 표준점검항목 DESC */
    private String stwrkPointDesc   = "";    
    
    /** equip_id */
    private String equipId   = "";    
    /** pmequip_id */
    private String pmEquipId   = "";    

	private List slideFileList      = null;
	
	/** 수치/판정 구분 ID */
	private String checkTypeId 				= "";
	
	
	public String getCheckTypeId() {
		return checkTypeId;
	}
	public void setCheckTypeId(String checkTypeId) {
		this.checkTypeId = checkTypeId;
	}
	public List getSlideFileList() {
		return slideFileList;
	}
	public void setSlideFileList(List slideFileList) {
		this.slideFileList = slideFileList;
	}
	public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getPmEquipId()
    {
        return pmEquipId;
    }
    public void setPmEquipId(String pmEquipId)
    {
        this.pmEquipId = pmEquipId;
    }
    public String getStwrkPointId()
    {
        return stwrkPointId;
    }
    public void setStwrkPointId(String stwrkPointId)
    {
        this.stwrkPointId = stwrkPointId;
    }
    public String getStwrkPointDesc()
    {
        return stwrkPointDesc;
    }
    public void setStwrkPointDesc(String stwrkPointDesc)
    {
        this.stwrkPointDesc = stwrkPointDesc;
    }
    public String getPmInsPointId() {
		return pmInsPointId;
	}
	public void setPmInsPointId(String pmInsPointId) {
		this.pmInsPointId = pmInsPointId;
		super.setAuditKey(pmInsPointId);
	}
	public String getPmPointId() {
		return pmPointId;
	}
	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
	}	
	public String getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	public String getFitBasis() {
		return fitBasis;
	}
	public void setFitBasis(String fitBasis) {
		this.fitBasis = fitBasis;
	}
	public String getCheckTypeDesc() {
		return checkTypeDesc;
	}
	public void setCheckTypeDesc(String checkTypeDesc) {
		this.checkTypeDesc = checkTypeDesc;
	}
	public String getCheckValUom() {
		return checkValUom;
	}
	public void setCheckValUom(String checkValUom) {
		this.checkValUom = checkValUom;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPmPointRsltStatus() {
		return pmPointRsltStatus;
	}
	public void setPmPointRsltStatus(String pmPointRsltStatus) {
		this.pmPointRsltStatus = pmPointRsltStatus;
	}
	public String getPmPointRsltStatusDesc() {
		return pmPointRsltStatusDesc;
	}
	public void setPmPointRsltStatusDesc(String pmPointRsltStatusDesc) {
		this.pmPointRsltStatusDesc = pmPointRsltStatusDesc;
	}
	public String getResultValue() {
		return resultValue;
	}
	public void setResultValue(String resultValue) {
		this.resultValue = CommonUtil.convertMoney(resultValue);
	}
	public String getResultRemark() {
		return resultRemark;
	}
	public void setResultRemark(String resultRemark) {
		this.resultRemark = resultRemark;
	}
}