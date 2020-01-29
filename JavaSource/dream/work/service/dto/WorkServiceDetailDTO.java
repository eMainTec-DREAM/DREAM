package dream.work.service.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� ������ ��ȭ�� DTO
 * @author cjscjs9
 * @version $Id: WorkServiceDetailDTO.java,v 1.0 2018/07/27 09:12:40 cjscjs9 Exp $
 * @since 1.0
 *
 */
public class WorkServiceDetailDTO extends BaseDTO
{
	/**���� ID*/
	private String serviceId 	   = "";
	/**���� No*/
	private String serviceNo 	   = "";
	/**���� ��*/
	private String serviceName	   = "";
	/**���� ID*/
	private String serviceUomId    = "";
	/**���� ��*/
	private String serviceUomDesc  = "";
	/**ȯ�갪*/
	private String fitValue 	   = "";
	/**��뿩��ID*/
	private String isUseId 		   = "";
	/**��뿩�� ��*/
	private String isUseDesc 	   = "";
	/**������� Id*/
	private String regDate 		   = "";
	/**��Ϻμ� Id*/
	private String deptId 		   = "";
	/**��Ϻμ� ��*/
	private String deptDesc 	   = "";
	/**����� Id*/
	private String empId 		   = "";
	/**����� ��*/
	private String empDesc 		   = "";
	/**����*/
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
