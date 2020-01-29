package dream.org.dept.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����μ� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaDeptDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 			= "";
	/** �μ�ID */
	private String deptId 			= "";
	/** �μ��ڵ� */
	private String deptNo 			= "";
	/** �μ��� */
	private String description 		= "";
	/** �����μ�Id */
	private String pdeptId			= "";
	/** �����μ��� */
	private String pdeptDesc		= "";
	/** �μ������ڵ� */
	private String deptCateg		= "";
	/** �μ����и� */
	private String deptCategDesc	= "";
	/** ��뿩�� */
	private String isUse 			= "";
	private String isUseDesc       	= "";
	/** ��ȸ���� */
	private String ordNo 			= "";
	/** â��Id */
	private String wcodeId			= "";
	/** â��� */
	private String wcodeDesc		= "";
	/** ����Id */
	private String plantId			= "";
	/** ����� */
	private String plantDesc		= "";
	/** ���ⱸ â��Id */
	private String twcodeId			= "";
	/** ���ⱸ â��� */
	private String twcodeDesc		= "";
	/** �����μ����� */
	private String isMaint          = "";
	private String isMaintDesc      = "";
	/** ����͸����� */
    private String isMonitoring     = "";
    private String isMonitoringDesc = "";
	
    /** From ��ǰ�̵�â�� ID */
    private String fromWcodeId      = "";
    /** From ��ǰ�̵�â�� DESC */
    private String fromWcodeDesc    = "";
    /** To ��ǰ�̵�â�� ID */
    private String toWcodeId        = "";
    /** To ��ǰ�̵�â�� DESC */
    private String toWcodeDesc      = "";
	
	/** ���������� */
	private String eqCnt			= "";
    
	
    public String getEqCnt() {
		return eqCnt;
	}
	public void setEqCnt(String eqCnt) {
		this.eqCnt = eqCnt;
	}
	public String getIsMonitoring()
    {
        return isMonitoring;
    }
    public String getFromWcodeId() {
		return fromWcodeId;
	}
	public void setFromWcodeId(String fromWcodeId) {
		this.fromWcodeId = fromWcodeId;
	}
	public String getFromWcodeDesc() {
		return fromWcodeDesc;
	}
	public void setFromWcodeDesc(String fromWcodeDesc) {
		this.fromWcodeDesc = fromWcodeDesc;
	}
	public String getToWcodeId() {
		return toWcodeId;
	}
	public void setToWcodeId(String toWcodeId) {
		this.toWcodeId = toWcodeId;
	}
	public String getToWcodeDesc() {
		return toWcodeDesc;
	}
	public void setToWcodeDesc(String toWcodeDesc) {
		this.toWcodeDesc = toWcodeDesc;
	}
	public void setIsMonitoring(String isMonitoring)
    {
        this.isMonitoring = isMonitoring;
    }
    public String getIsMonitoringDesc()
    {
        return isMonitoringDesc;
    }
    public void setIsMonitoringDesc(String isMonitoringDesc)
    {
        this.isMonitoringDesc = isMonitoringDesc;
    }
    public String getTwcodeId() {
		return twcodeId;
	}
	public void setTwcodeId(String twcodeId) {
		this.twcodeId = twcodeId;
	}
	public String getTwcodeDesc() {
		return twcodeDesc;
	}
	public void setTwcodeDesc(String twcodeDesc) {
		this.twcodeDesc = twcodeDesc;
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
	public String getDeptCateg()
    {
        return deptCateg;
    }
    public void setDeptCateg(String deptCateg)
    {
        this.deptCateg = deptCateg;
    }
    public String getDeptCategDesc()
    {
        return deptCategDesc;
    }
    public void setDeptCategDesc(String deptCategDesc)
    {
        this.deptCategDesc = deptCategDesc;
    }
    public String getIsUseDesc()
    {
        return isUseDesc;
    }
    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }
    public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getDeptNo() 
	{
		return deptNo;
	}
	public void setDeptNo(String deptNo) 
	{
		this.deptNo = deptNo;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
        super.setAuditKey(deptId);
    }
    public String getPdeptId()
    {
        return pdeptId;
    }
    public void setPdeptId(String pdeptId)
    {
        this.pdeptId = pdeptId;
    }
    public String getPdeptDesc() 
	{
		return pdeptDesc;
	}
	public void setPdeptDesc(String pdeptDesc) 
	{
		this.pdeptDesc = pdeptDesc;
	}
	public String getIsUse() 
	{
		return isUse;
	}
	public void setIsUse(String isUse) 
	{
		this.isUse = isUse;
	}
	public String getOrdNo() 
	{
		return ordNo;
	}
	public void setOrdNo(String ordNo) 
	{
		this.ordNo = CommonUtil.convertMoney(ordNo);
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
    public String getIsMaint()
    {
        return isMaint;
    }
    public void setIsMaint(String isMaint)
    {
        this.isMaint = isMaint;
    }
    public String getIsMaintDesc()
    {
        return isMaintDesc;
    }
    public void setIsMaintDesc(String isMaintDesc)
    {
        this.isMaintDesc = isMaintDesc;
    }
	
}
