package dream.mgr.at.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Audit Trail DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class MgrAtCommonDTO extends BaseDTO
{
	/** tracelog ID */
	private String tracelogId 			= "";
	/** tracelog Detail ID */
	private String tracelogDtlId 		= "";
	/** object ID */
	private String objectId 			= "";
	/** file_name */
	private String fileName		 		= "";
	
	/** FILTER ȭ�� ID */
	private String filterPageId	 		= "";
	/** FILTER ȭ�� DESC */
	private String filterPageDesc 		= "";
	/** FILTER ���汸�� ID */
	private String filterCudTypeId	 	= "";
	/** FILTER ���汸�� DESC */
	private String filterCudTypeDesc 	= "";
	/** FILTER �������� from */
	private String filterFromDate	 	= "";
	/** FILTER �������� to */
	private String filterToDate 		= "";
	/** FILTER ������ ID */
	private String filterUpdById	 	= "";
	/** FILTER ������ DESC */
	private String filterUpdByDesc 		= "";
	/** FILTER �׸� TYPE */
	private String filterKeyType	 	= "";
	/** FILTER �׸� NO */
	private String filterKeyNo 			= "";
	/** FILTER �׸� DESC */
	private String filterKeyName 		= "";

	public String getFilterPageId() {
		return filterPageId;
	}
	public String getTracelogDtlId() {
		return tracelogDtlId;
	}
	public void setTracelogDtlId(String tracelogDtlId) {
		this.tracelogDtlId = tracelogDtlId;
	}
	public void setFilterPageId(String filterPageId) {
		this.filterPageId = filterPageId;
	}
	public String getFilterPageDesc() {
		return filterPageDesc;
	}
	public void setFilterPageDesc(String filterPageDesc) {
		this.filterPageDesc = filterPageDesc;
	}
	public String getFilterCudTypeId() {
		return filterCudTypeId;
	}
	public void setFilterCudTypeId(String filterCudTypeId) {
		this.filterCudTypeId = filterCudTypeId;
	}
	public String getFilterCudTypeDesc() {
		return filterCudTypeDesc;
	}
	public void setFilterCudTypeDesc(String filterCudTypeDesc) {
		this.filterCudTypeDesc = filterCudTypeDesc;
	}
	public String getFilterFromDate() {
		return filterFromDate;
	}
	public void setFilterFromDate(String filterFromDate) {
		this.filterFromDate = CommonUtil.convertDate(filterFromDate);
	}
	public String getFilterToDate() {
		return filterToDate;
	}
	public void setFilterToDate(String filterToDate) {
		this.filterToDate = CommonUtil.convertDate(filterToDate);
	}
	public String getFilterUpdById() {
		return filterUpdById;
	}
	public void setFilterUpdById(String filterUpdById) {
		this.filterUpdById = filterUpdById;
	}
	public String getFilterUpdByDesc() {
		return filterUpdByDesc;
	}
	public void setFilterUpdByDesc(String filterUpdByDesc) {
		this.filterUpdByDesc = filterUpdByDesc;
	}
	public String getFilterKeyType() {
		return filterKeyType;
	}
	public void setFilterKeyType(String filterKeyType) {
		this.filterKeyType = filterKeyType;
	}
	public String getFilterKeyNo() {
		return filterKeyNo;
	}
	public void setFilterKeyNo(String filterKeyNo) {
		this.filterKeyNo = filterKeyNo;
	}
	public String getFilterKeyName() {
		return filterKeyName;
	}
	public void setFilterKeyName(String filterKeyName) {
		this.filterKeyName = filterKeyName;
	}
	public String getObjectId() {
		return objectId;
	}
	public String getTracelogId() {
		return tracelogId;
	}
	public void setTracelogId(String tracelogId) {
		this.tracelogId = tracelogId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
}
