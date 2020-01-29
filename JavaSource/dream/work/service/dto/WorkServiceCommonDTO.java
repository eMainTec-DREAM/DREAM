package dream.work.service.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * ���񽺸����� ���� DTO
 * @author cjscjs9
 * @version $Id: WorkServiceCommonDTO.java,v 1.0 2018/07/27 09:12:40 cjscjs9 Exp $
 * @since 1.0
 *
 */
public class WorkServiceCommonDTO extends BaseDTO
{
	/**���� ID*/
	private String serviceId 				= "";
	
	/**Filter ����#*/
	private String filterServiceNo 	        = "";
	/**Filter ���񽺸�*/
	private String filterDescription 	    = "";
	/**Filter ���� �������*/
	private String filterFromRegDate 	    = "";
	/**Filter ���� �������*/
	private String filterToRegDate 	        = "";
	/**Filter ����� key*/
	private String filterEmpId      	    = "";
	/**Filter ����� ��*/
	private String filterEmpDesc      	    = "";
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
		super.setAuditKey(serviceId);
	}
	public String getFilterServiceNo() {
		return filterServiceNo;
	}
	public void setFilterServiceNo(String filterServiceNo) {
		this.filterServiceNo = filterServiceNo;
	}
	public String getFilterDescription() {
		return filterDescription;
	}
	public void setFilterDescription(String filterDescription) {
		this.filterDescription = filterDescription;
	}
	public String getFilterFromRegDate() {
		return filterFromRegDate;
	}
	public void setFilterFromRegDate(String filterFromRegDate) {
		this.filterFromRegDate = CommonUtil.convertDate(filterFromRegDate);
	}
	public String getFilterToRegDate() {
		return filterToRegDate;
	}
	public void setFilterToRegDate(String filterToRegDate) {
		this.filterToRegDate = CommonUtil.convertDate(filterToRegDate);
	}
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpDesc() {
		return filterEmpDesc;
	}
	public void setFilterEmpDesc(String filterEmpDesc) {
		this.filterEmpDesc = filterEmpDesc;
	}
	
	
}
