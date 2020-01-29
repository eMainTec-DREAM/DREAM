package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.bean.MwareConfig;
import common.util.CommonUtil;

/**
 * 설비마스터 - 금형 상세 DTO
 * @author  kim21017
 * @version $Id: MaEqMoldMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqToolMstrDetailDTO extends BaseDTO
{
	/** 설비ID */
	private String equipId 				= "";
	
	/** 게이지종류ID */
	private String guaTypeId 			= "";
	/** 게이지종류명 */
	private String guaTypeDesc 			= "";
	/** 기준측정기ID */
	private String baseEquipId 			= "";
	/** 기준측정기명 */
	private String baseEquipDesc 		= "";
	
	/** 측정단위 */
	private String measureUnit 			= "";
	/** 측정검사장비 */
	private String measureTool 			= MwareConfig.getEmptyFieldValue();
	/** 측정형태 */
	private String measureCateg 		= MwareConfig.getEmptyFieldValue();
	/** 측정최소눈금 */
	private String minUnitVal 			= "";
	/** 측정가능 하한값 */
	private String minVal 				= "";
	/** 측정가능 상한값 */
	private String maxVal 				= "";
	/** 기준측정기여부 */
	private String isStandardEq 		= "";
	
	
	/** 검교정작업타입 id */
	private String pmcTypeId 			= "";
	/** 검교정작업타입  명 */
	private String pmcTypeDesc 			= "";
	/** 교정표준값 id */
	private String pmCalibStdTpId 		= "";
	/** 교정표준값  명 */
	private String pmCalibStdTpRange 	= "";
	/** 전체범위 */
	private String allRange				= MwareConfig.getEmptyFieldValue();
	/** 사용범위 */
	private String useRange				= MwareConfig.getEmptyFieldValue();
	/** 정확도 */
    private String accuracy             = MwareConfig.getEmptyFieldValue();
    /** 허용오차 */
    private String tolerance             = MwareConfig.getEmptyFieldValue();
	/** 교정대상 */
    private String calibTarget			= "";
    
	/** 작업그룹 Id */
	private String wkctrId					= "";
	/** 작업그룹 Desc */
	private String wkctrDesc				= "";
	
	
	public String getWkctrId() {
		return wkctrId;
	}
	public void setWkctrId(String wkctrId) {
		this.wkctrId = wkctrId;
	}
	public String getWkctrDesc() {
		return wkctrDesc;
	}
	public void setWkctrDesc(String wkctrDesc) {
		this.wkctrDesc = wkctrDesc;
	}
	public String getCalibTarget() {
		return calibTarget;
	}
	public void setCalibTarget(String calibTarget) {
		this.calibTarget = calibTarget;
	}
	public String getAccuracy()
    {
        return accuracy;
    }
    public void setAccuracy(String accuracy)
    {
        this.accuracy = accuracy;
    }
    public String getTolerance()
    {
        return tolerance;
    }
    public void setTolerance(String tolerance)
    {
        this.tolerance = tolerance;
    }
    public String getAllRange() {
		return allRange;
	}
	public void setAllRange(String allRange) {
		this.allRange = allRange;
	}
	public String getUseRange() {
		return useRange;
	}
	public void setUseRange(String useRange) {
		this.useRange = useRange;
	}
	public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
	}
	public String getPmCalibStdTpRange() {
		return pmCalibStdTpRange;
	}
	public void setPmCalibStdTpRange(String pmCalibStdTpRange) {
		this.pmCalibStdTpRange = pmCalibStdTpRange;
	}
	public String getPmcTypeId() {
		return pmcTypeId;
	}
	public void setPmcTypeId(String pmcTypeId) {
		this.pmcTypeId = pmcTypeId;
	}
	public String getPmcTypeDesc() {
		return pmcTypeDesc;
	}
	public void setPmcTypeDesc(String pmcTypeDesc) {
		this.pmcTypeDesc = pmcTypeDesc;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getGuaTypeId() {
		return guaTypeId;
	}
	public void setGuaTypeId(String guaTypeId) {
		this.guaTypeId = guaTypeId;
	}
	public String getGuaTypeDesc() {
		return guaTypeDesc;
	}
	public void setGuaTypeDesc(String guaTypeDesc) {
		this.guaTypeDesc = guaTypeDesc;
	}
	public String getBaseEquipId() {
		return baseEquipId;
	}
	public void setBaseEquipId(String baseEquipId) {
		this.baseEquipId = baseEquipId;
	}
	public String getBaseEquipDesc() {
		return baseEquipDesc;
	}
	public void setBaseEquipDesc(String baseEquipDesc) {
		this.baseEquipDesc = baseEquipDesc;
	}
	public String getMeasureUnit() {
		return measureUnit;
	}
	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}
	public String getMeasureTool() {
		return measureTool;
	}
	public void setMeasureTool(String measureTool) {
		this.measureTool = measureTool;
	}
	public String getMeasureCateg() {
		return measureCateg;
	}
	public void setMeasureCateg(String measureCateg) {
		this.measureCateg = measureCateg;
	}
	public String getMinUnitVal() {
		return minUnitVal;
	}
	public void setMinUnitVal(String minUnitVal) {
		this.minUnitVal = minUnitVal;
	}
	public String getMinVal() {
		return minVal;
	}
	public void setMinVal(String minVal) {
		this.minVal = minVal;
	}
	public String getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(String maxVal) {
		this.maxVal = maxVal;
	}
	public String getIsStandardEq() {
		return isStandardEq;
	}
	public void setIsStandardEq(String isStandardEq) {
		this.isStandardEq = isStandardEq;
	}
	
	
	
}
