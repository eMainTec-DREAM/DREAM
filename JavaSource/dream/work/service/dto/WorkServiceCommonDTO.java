package dream.work.service.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * 서비스마스터 공통 DTO
 * @author cjscjs9
 * @version $Id: WorkServiceCommonDTO.java,v 1.0 2018/07/27 09:12:40 cjscjs9 Exp $
 * @since 1.0
 *
 */
public class WorkServiceCommonDTO extends BaseDTO
{
	/**서비스 ID*/
	private String serviceId 				= "";
	
	/**Filter 서비스#*/
	private String filterServiceNo 	        = "";
	/**Filter 서비스명*/
	private String filterDescription 	    = "";
	/**Filter 시작 등록일자*/
	private String filterFromRegDate 	    = "";
	/**Filter 종료 등록일자*/
	private String filterToRegDate 	        = "";
	/**Filter 등록자 key*/
	private String filterEmpId      	    = "";
	/**Filter 등록자 명*/
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
