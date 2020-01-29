package dream.org.emp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaEmpDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 			= "";
	/** ���Id */
	private String empId 			= "";
	/** �����ȣ */
	private String empNo 			= "";
	/** �μ�Id */
	private String deptId 			= "";
	/** �μ��� */
	private String deptDesc 		= "";
	/** ����� */
	private String empName          = "";
	/** ���޸�  */
	private String grade			= "";
	/** ��������, ����=Y, ���»�=N, Default=Y */
	private String isDirect         = "";
	/** ��������, ����=Y, ���»�=N, Default=Y */
	private String isDirectDesc     = "";
	/** �ڵ��� */
	private String mphone           = "";
	/** �̸��� */
	private String email            = "";
	/** �Ի��� */
	private String joinDate	        = "";
	/** ����� */
	private String retireDate		= "";
	/** �ٹ����� */
	private String isJoin 			= "";
	private String isJoinDesc		= "";
	/** ��� */
	private String remark 			= "";
	/** ����Id */
	private String plantId			= "";
	/** ����� */
	private String plantDesc		= "";
	/** �۾��׷� Id */
	private String wkCtrId			= "";
	/** �۾��׷� �� */
	private String wkCtrDesc		= "";
	/** ���ϼ��ſ��� */
    private String isMailRec        = "";
    private String isMailRecDesc    = "";
	
	/** �ŷ�ó */
	private String vendorId				= "";
	private String vendorDesc			= "";
	
	
    public String getIsDirectDesc()
    {
        return isDirectDesc;
    }
    public void setIsDirectDesc(String isDirectDesc)
    {
        this.isDirectDesc = isDirectDesc;
    }
    public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorDesc() {
		return vendorDesc;
	}
	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
	}
	public String getIsMailRec()
    {
        return isMailRec;
    }
    public void setIsMailRec(String isMailRec)
    {
        this.isMailRec = isMailRec;
    }
    public String getIsMailRecDesc()
    {
        return isMailRecDesc;
    }
    public void setIsMailRecDesc(String isMailRecDesc)
    {
        this.isMailRecDesc = isMailRecDesc;
    }
    public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getIsJoinDesc()
    {
        return isJoinDesc;
    }
    public void setIsJoinDesc(String isJoinDesc)
    {
        this.isJoinDesc = isJoinDesc;
    }
    public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getEmpId() 
	{
		return empId;
	}
	public void setEmpId(String empId) 
	{
		this.empId = empId;
		super.setAuditKey(empId);
	}
	public String getEmpName() 
	{
		return empName;
	}
	public void setEmpName(String empName) 
	{
		this.empName = empName;
	}
	public String getDeptDesc() 
	{
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) 
	{
		this.deptDesc = deptDesc;
	}
	public String getGrade() 
	{
		return grade;
	}
	public void setGrade(String grade) 
	{
		this.grade = grade;
	}
	public String getIsDirect() 
	{
		return isDirect;
	}
	public void setIsDirect(String isDirect) 
	{
		this.isDirect = isDirect;
	}
	public String getMphone() 
	{
		return mphone;
	}
	public void setMphone(String mphone) 
	{
		this.mphone = mphone;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getJoinDate() 
	{
		return joinDate;
	}
	public void setJoinDate(String joinDate) 
	{
		this.joinDate = CommonUtil.convertDate(joinDate);
	}
	public String getRetireDate() 
	{
		return retireDate;
	}
	public void setRetireDate(String retireDate) 
	{
		this.retireDate = CommonUtil.convertDate(retireDate);
	}
	public String getIsJoin() 
	{
		return isJoin;
	}
	public void setIsJoin(String isJoin) 
	{
		this.isJoin = isJoin;
	}
	public String getRemark() 
	{
		return remark;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
    public String getEmpNo()
    {
        return empNo;
    }
    public void setEmpNo(String empNo)
    {
        this.empNo = empNo;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
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
    
   
}
