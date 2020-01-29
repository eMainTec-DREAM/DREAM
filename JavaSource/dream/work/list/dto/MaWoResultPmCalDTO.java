package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���� ���� DTO
 * @author  kim21017
 * @version $Id: MaWoResultMstrCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaWoResultPmCalDTO extends BaseDTO
{			 
	/** �۾����ID */
	private String wkOrId				= "";
	
	/** �˻���ID*/
	private String calCorpId			= "";
	/** �˻����� */
	private String calCorpDesc			= "";
	/** �˻籸��ID*/
	private String calTypeId			= "";
	/** �˻籸�и� */
	private String calTypeDesc			= "";
	/** ��������ID*/
	private String calRsltStatId		= "";
	/** ���������� */
	private String calRsltStatDesc		= "";
	/** �˻�ȯ�� */
	private String calEnv				= "";

	/** �˱��� ����SOP���� */
	private String calibSopdocNo 		= "";
	/** �˱��� ����SOP���� */
	private String calibSopdocNoDesc 	= "";
	/** �������� */
	private String calibResultStatus 	= "";
	/** �˻���� */
	private String calibDevice 			= "";
	/** �˻籸�� */
	private String calibType 			= "";
	/** �˻��� */
	private String calibCorp 			= "";
	/** �˻�ȯ�� */
	private String calibEnv 			= "";
	/** ���������� */
	private String calibCertNo			= "";
	/** ���� ������ */
	private String nextCalibDate		= "";
	/** �������� */
	private String wkorDate				= "";
	/** �����ֱ� */
	private String calibCycle           = "";
	/** �ֱ�Ÿ�� ID */
    private String periodTypeId         = "";
    /** �ֱ�Ÿ�� DESC */
    private String periodTypeDesc       = "";
	
	public String getCalibCycle()
    {
        return calibCycle;
    }
    public void setCalibCycle(String calibCycle)
    {
        this.calibCycle = CommonUtil.blankToNull(calibCycle);
    }
    public String getPeriodTypeId()
    {
        return periodTypeId;
    }
    public void setPeriodTypeId(String periodTypeId)
    {
        this.periodTypeId = periodTypeId;
    }
    public String getPeriodTypeDesc()
    {
        return periodTypeDesc;
    }
    public void setPeriodTypeDesc(String periodTypeDesc)
    {
        this.periodTypeDesc = periodTypeDesc;
    }
    public String getWkorDate() {
		return wkorDate;
	}
	public void setWkorDate(String wkorDate) {
		this.wkorDate = CommonUtil.convertDate(wkorDate);
	}
	public String getCalibCertNo() {
		return calibCertNo;
	}
	public void setCalibCertNo(String calibCertNo) {
		this.calibCertNo = calibCertNo;
	}
	public String getNextCalibDate() {
		return nextCalibDate;
	}
	public void setNextCalibDate(String nextCalibDate) {
		this.nextCalibDate = CommonUtil.convertDate(nextCalibDate);
	}
	public String getCalibSopdocNo() {
		return calibSopdocNo;
	}
	public void setCalibSopdocNo(String calibSopdocNo) {
		this.calibSopdocNo = calibSopdocNo;
	}
	public String getCalibSopdocNoDesc() {
		return calibSopdocNoDesc;
	}
	public void setCalibSopdocNoDesc(String calibSopdocNoDesc) {
		this.calibSopdocNoDesc = calibSopdocNoDesc;
	}
	public String getCalibResultStatus() {
		return calibResultStatus;
	}
	public void setCalibResultStatus(String calibResultStatus) {
		this.calibResultStatus = calibResultStatus;
	}
	public String getCalibDevice() {
		return calibDevice;
	}
	public void setCalibDevice(String calibDevice) {
		this.calibDevice = calibDevice;
	}
	public String getCalibType() {
		return calibType;
	}
	public void setCalibType(String calibType) {
		this.calibType = calibType;
	}
	public String getCalibCorp() {
		return calibCorp;
	}
	public void setCalibCorp(String calibCorp) {
		this.calibCorp = calibCorp;
	}
	public String getCalibEnv() {
		return calibEnv;
	}
	public void setCalibEnv(String calibEnv) {
		this.calibEnv = calibEnv;
	}
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
	public String getCalCorpId() {
		return calCorpId;
	}
	public void setCalCorpId(String calCorpId) {
		this.calCorpId = calCorpId;
	}
	public String getCalCorpDesc() {
		return calCorpDesc;
	}
	public void setCalCorpDesc(String calCorpDesc) {
		this.calCorpDesc = calCorpDesc;
	}
	public String getCalTypeId() {
		return calTypeId;
	}
	public void setCalTypeId(String calTypeId) {
		this.calTypeId = calTypeId;
	}
	public String getCalTypeDesc() {
		return calTypeDesc;
	}
	public void setCalTypeDesc(String calTypeDesc) {
		this.calTypeDesc = calTypeDesc;
	}
	public String getCalRsltStatId() {
		return calRsltStatId;
	}
	public void setCalRsltStatId(String calRsltStatId) {
		this.calRsltStatId = calRsltStatId;
	}
	public String getCalRsltStatDesc() {
		return calRsltStatDesc;
	}
	public void setCalRsltStatDesc(String calRsltStatDesc) {
		this.calRsltStatDesc = calRsltStatDesc;
	}
	public String getCalEnv() {
		return calEnv;
	}
	public void setCalEnv(String calEnv) {
		this.calEnv = calEnv;
	}
	
	
}
