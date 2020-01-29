package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.bean.MwareConfig;
import common.util.CommonUtil;

/**
 * ���񸶽��� - ���� �� DTO
 * @author  kim21017
 * @version $Id: MaEqMoldMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqToolMstrDetailDTO extends BaseDTO
{
	/** ����ID */
	private String equipId 				= "";
	
	/** ����������ID */
	private String guaTypeId 			= "";
	/** ������������ */
	private String guaTypeDesc 			= "";
	/** ����������ID */
	private String baseEquipId 			= "";
	/** ����������� */
	private String baseEquipDesc 		= "";
	
	/** �������� */
	private String measureUnit 			= "";
	/** �����˻���� */
	private String measureTool 			= MwareConfig.getEmptyFieldValue();
	/** �������� */
	private String measureCateg 		= MwareConfig.getEmptyFieldValue();
	/** �����ּҴ��� */
	private String minUnitVal 			= "";
	/** �������� ���Ѱ� */
	private String minVal 				= "";
	/** �������� ���Ѱ� */
	private String maxVal 				= "";
	/** ���������⿩�� */
	private String isStandardEq 		= "";
	
	
	/** �˱����۾�Ÿ�� id */
	private String pmcTypeId 			= "";
	/** �˱����۾�Ÿ��  �� */
	private String pmcTypeDesc 			= "";
	/** ����ǥ�ذ� id */
	private String pmCalibStdTpId 		= "";
	/** ����ǥ�ذ�  �� */
	private String pmCalibStdTpRange 	= "";
	/** ��ü���� */
	private String allRange				= MwareConfig.getEmptyFieldValue();
	/** ������ */
	private String useRange				= MwareConfig.getEmptyFieldValue();
	/** ��Ȯ�� */
    private String accuracy             = MwareConfig.getEmptyFieldValue();
    /** ������ */
    private String tolerance             = MwareConfig.getEmptyFieldValue();
	/** ������� */
    private String calibTarget			= "";
    
	/** �۾��׷� Id */
	private String wkctrId					= "";
	/** �۾��׷� Desc */
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
