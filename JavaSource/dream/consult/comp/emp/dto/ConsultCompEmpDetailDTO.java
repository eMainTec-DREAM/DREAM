package dream.consult.comp.emp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사원 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class ConsultCompEmpDetailDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 			= "";
	/** 회사명 */
	private String compDesc       = "";
	/** 사원Id */
	private String empId 			= "";
	/** 사원번호 */
	private String empNo 			= "";
	/** 부서Id */
	private String deptId 			= "";
	/** 부서명 */
	private String deptDesc 		= "";
	/** 사원명 */
	private String empName          = "";
	/** 직급명  */
	private String grade			= "";
	/** 직영여부, 직영=Y, 협력사=N, Default=Y */
	private String isDirect         = "";
	/** 핸드폰 */
	private String mphone           = "";
	/** 이메일 */
	private String email            = "";
	/** 입사일 */
	private String joinDate	        = "";
	/** 퇴사일 */
	private String retireDate		= "";
	/** 근무여부 */
	private String isJoin 			= "";
	private String isJoinDesc		= "";
	/** 비고 */
	private String remark 			= "";
	/** 공장Id */
	private String plantId			= "";
	/** 고장명 */
	private String plantDesc		= "";
	/** 작업그룹 Id */
	private String wkCtrId			= "";
	/** 작업그룹 명 */
	private String wkCtrDesc		= "";
	
    public String getCompDesc()
    {
        return compDesc;
    }
    public void setCompDesc(String compDesc)
    {
        this.compDesc = compDesc;
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
