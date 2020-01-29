package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * IT장비목록 - 공통 DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetListITCommonDTO extends BaseDTO
{
	/** IT장비 ID */
	private String itEqId 					= "";
	/** 필터 - IT장비 NO */
	private String filterItEqNo 		    = "";
	/** 필터 - IT장비 DESC */                     
	private String filterItEqDesc 		    = "";
	/** 필터 - IT장비상태 ID */                   
	private String filterItEqStatusId 	    = "";
	/** 필터 - IT장비상태 DESC */                 
	private String filterItEqStatusDesc     = "";
	/** 필터 - IT장비관리부서 ID */                 
	private String filterDeptId 		    = "";
	/** 필터 - IT장비관리부서 DESC */               
	private String filterDeptDesc 		    = "";
	/** 필터 - 모델 */                          
	private String filterModel 			    = "";
	/** 필터 - 관리자(정) ID */                   
	private String filterMainMngId 			= "";
	/** 필터 - 관리자(정) NAME */                 
	private String filterMainMngName 		= "";
	/** 필터 - 사용자 ID */                      
	private String filterUserId 		    = "";
	/** 필터 - 사용자 NAME */                    
	private String filterUserName 		    = "";
	/** 필터 - 시리얼번호 */                       
	private String filterSerialNo 		    = "";
	/** 필터 - 구입일자 FROM */                   
	private String filterStartBuyDate 	    = "";
	/** 필터 - 구입일자 TO */                     
	private String filterEndBuyDate 	    = "";
	/** 필터 - 사양 */                          
	private String filterSpecification 	    = "";
	/** 필터-최신 version 여부 */
    private String filterIsLastVersionId	= "";
    /** 필터-최신 version 여부 */
    private String filterIsLastVersionDesc	= "";
    /** 필터-Revision version No */
    private String filterRevNo				= "";
    
    /** 필터 - 사용자 ID */                      
    private String filterPlantId 		    = "";
    /** 필터 - 사용자 NAME */                    
    private String filterPlantDesc 		    = "";
    
    private String selectedEqCtgTypeId 		= "";
    
    
	public String getSelectedEqCtgTypeId() {
		return selectedEqCtgTypeId;
	}
	public void setSelectedEqCtgTypeId(String selectedEqCtgTypeId) {
		this.selectedEqCtgTypeId = selectedEqCtgTypeId;
	}
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getItEqId() {
		return itEqId;
	}
	public void setItEqId(String itEqId) {
		this.itEqId = itEqId;
	}
	public String getFilterItEqNo() {
		return filterItEqNo;
	}
	public void setFilterItEqNo(String filterItEqNo) {
		this.filterItEqNo = filterItEqNo;
	}
	public String getFilterItEqDesc() {
		return filterItEqDesc;
	}
	public void setFilterItEqDesc(String filterItEqDesc) {
		this.filterItEqDesc = filterItEqDesc;
	}
	public String getFilterItEqStatusId() {
		return filterItEqStatusId;
	}
	public void setFilterItEqStatusId(String filterItEqStatusId) {
		this.filterItEqStatusId = filterItEqStatusId;
	}
	public String getFilterItEqStatusDesc() {
		return filterItEqStatusDesc;
	}
	public void setFilterItEqStatusDesc(String filterItEqStatusDesc) {
		this.filterItEqStatusDesc = filterItEqStatusDesc;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterModel() {
		return filterModel;
	}
	public void setFilterModel(String filterModel) {
		this.filterModel = filterModel;
	}
	public String getFilterMainMngId() {
		return filterMainMngId;
	}
	public void setFilterMainMngId(String filterMainMngId) {
		this.filterMainMngId = filterMainMngId;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public String getFilterMainMngName() {
		return filterMainMngName;
	}
	public void setFilterMainMngName(String filterMainMngName) {
		this.filterMainMngName = filterMainMngName;
	}
	public String getFilterUserName() {
		return filterUserName;
	}
	public void setFilterUserName(String filterUserName) {
		this.filterUserName = filterUserName;
	}
	public String getFilterSerialNo() {
		return filterSerialNo;
	}
	public void setFilterSerialNo(String filterSerialNo) {
		this.filterSerialNo = filterSerialNo;
	}
	public String getFilterStartBuyDate() {
		return filterStartBuyDate;
	}
	public void setFilterStartBuyDate(String filterStartBuyDate) {
		this.filterStartBuyDate = CommonUtil.convertDate(filterStartBuyDate);
	}
	public String getFilterEndBuyDate() {
		return filterEndBuyDate;
	}
	public void setFilterEndBuyDate(String filterEndBuyDate) {
		this.filterEndBuyDate = CommonUtil.convertDate(filterEndBuyDate);
	}
	public String getFilterSpecification() {
		return filterSpecification;
	}
	public void setFilterSpecification(String filterSpecification) {
		this.filterSpecification = filterSpecification;
	}
	public String getFilterIsLastVersionId() {
		return filterIsLastVersionId;
	}
	public void setFilterIsLastVersionId(String filterIsLastVersionId) {
		this.filterIsLastVersionId = filterIsLastVersionId;
	}
	public String getFilterIsLastVersionDesc() {
		return filterIsLastVersionDesc;
	}
	public void setFilterIsLastVersionDesc(String filterIsLastVersionDesc) {
		this.filterIsLastVersionDesc = filterIsLastVersionDesc;
	}
	public String getFilterRevNo() {
		return filterRevNo;
	}
	public void setFilterRevNo(String filterRevNo) {
		this.filterRevNo = filterRevNo;
	}
    
    
}
