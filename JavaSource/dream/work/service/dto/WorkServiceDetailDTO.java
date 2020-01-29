package dream.work.service.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 서비스 마스터 상세화면 DTO
 * @author cjscjs9
 * @version $Id: WorkServiceDetailDTO.java,v 1.0 2018/07/27 09:12:40 cjscjs9 Exp $
 * @since 1.0
 *
 */
public class WorkServiceDetailDTO extends BaseDTO
{
	/**서비스 ID*/
	private String serviceId 	   = "";
	/**서비스 No*/
	private String serviceNo 	   = "";
	/**서비스 명*/
	private String serviceName	   = "";
	/**단위 ID*/
	private String serviceUomId    = "";
	/**단위 명*/
	private String serviceUomDesc  = "";
	/**환산값*/
	private String fitValue 	   = "";
	/**사용여부ID*/
	private String isUseId 		   = "";
	/**사용여부 명*/
	private String isUseDesc 	   = "";
	/**등록일자 Id*/
	private String regDate 		   = "";
	/**등록부서 Id*/
	private String deptId 		   = "";
	/**등록부서 명*/
	private String deptDesc 	   = "";
	/**등록자 Id*/
	private String empId 		   = "";
	/**등록자 명*/
	private String empDesc 		   = "";
	/**내용*/
	private String remark 		   = "";
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
		super.setAuditKey(serviceId);
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String description) {
		this.serviceName = description;
	}
	public String getServiceUomId() {
		return serviceUomId;
	}
	public void setServiceUomId(String serviceUomId) {
		this.serviceUomId = serviceUomId;
	}
	public String getServiceUomDesc() {
		return serviceUomDesc;
	}
	public void setServiceUomDesc(String serviceUomDesc) {
		this.serviceUomDesc = serviceUomDesc;
	}
	public String getFitValue() {
		return fitValue;
	}
	public void setFitValue(String fitValue) {
		this.fitValue = CommonUtil.convertMoney(fitValue);
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
}
