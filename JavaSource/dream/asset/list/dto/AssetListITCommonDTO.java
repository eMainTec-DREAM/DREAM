package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * IT����� - ���� DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetListITCommonDTO extends BaseDTO
{
	/** IT��� ID */
	private String itEqId 					= "";
	/** ���� - IT��� NO */
	private String filterItEqNo 		    = "";
	/** ���� - IT��� DESC */                     
	private String filterItEqDesc 		    = "";
	/** ���� - IT������ ID */                   
	private String filterItEqStatusId 	    = "";
	/** ���� - IT������ DESC */                 
	private String filterItEqStatusDesc     = "";
	/** ���� - IT�������μ� ID */                 
	private String filterDeptId 		    = "";
	/** ���� - IT�������μ� DESC */               
	private String filterDeptDesc 		    = "";
	/** ���� - �� */                          
	private String filterModel 			    = "";
	/** ���� - ������(��) ID */                   
	private String filterMainMngId 			= "";
	/** ���� - ������(��) NAME */                 
	private String filterMainMngName 		= "";
	/** ���� - ����� ID */                      
	private String filterUserId 		    = "";
	/** ���� - ����� NAME */                    
	private String filterUserName 		    = "";
	/** ���� - �ø����ȣ */                       
	private String filterSerialNo 		    = "";
	/** ���� - �������� FROM */                   
	private String filterStartBuyDate 	    = "";
	/** ���� - �������� TO */                     
	private String filterEndBuyDate 	    = "";
	/** ���� - ��� */                          
	private String filterSpecification 	    = "";
	/** ����-�ֽ� version ���� */
    private String filterIsLastVersionId	= "";
    /** ����-�ֽ� version ���� */
    private String filterIsLastVersionDesc	= "";
    /** ����-Revision version No */
    private String filterRevNo				= "";
    
    /** ���� - ����� ID */                      
    private String filterPlantId 		    = "";
    /** ���� - ����� NAME */                    
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
