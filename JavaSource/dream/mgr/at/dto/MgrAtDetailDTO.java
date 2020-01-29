package dream.mgr.at.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public class MgrAtDetailDTO extends BaseDTO
{
	/** tracelog ID */
	private String tracelogId 			= "";
	/** tracelog Detail ID */
	private String tracelogDtlId 		= "";
	/** file_name */
	private String fileName		 		= "";
	/** field_id */
	private String fieldId		 		= "";
    
	/** fileName / 화면명 */
	private String fileNameFieldDesc	= "";
	/** fieldId / 항목명 */
	private String fieldIdItemDesc		= "";
	/** 구분 Desc */
	private String dataCudTypeDesc		= "";
	/** 변경자 Desc */
	private String updByDesc			= "";
	/** 변경일자  */
	private String updTime 				= "";
	/** 변경후 Desc */
	private String afterValue			= "";
	/** object ID */
	private String objectId 			= "";
	
	public String getTracelogId() {
		return tracelogId;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getTracelogDtlId() {
		return tracelogDtlId;
	}
	public void setTracelogDtlId(String tracelogDtlId) {
		this.tracelogDtlId = tracelogDtlId;
	}
	public String getFileNameFieldDesc() {
		return fileNameFieldDesc;
	}
	public void setFileNameFieldDesc(String fileNameFieldDesc) {
		this.fileNameFieldDesc = fileNameFieldDesc;
	}
	public String getFieldIdItemDesc() {
		return fieldIdItemDesc;
	}
	public void setFieldIdItemDesc(String fieldIdItemDesc) {
		this.fieldIdItemDesc = fieldIdItemDesc;
	}
	public String getAfterValue() {
		return afterValue;
	}
	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}
	public void setTracelogId(String tracelogId) {
		this.tracelogId = tracelogId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUpdTime() {
		return updTime;
	}
	public void setUpdTime(String updTime) {
		this.updTime = CommonUtil.convertDateTime(updTime);
	}
	public String getUpdByDesc() {
		return updByDesc;
	}
	public void setUpdByDesc(String updByDesc) {
		this.updByDesc = updByDesc;
	}
	public String getDataCudTypeDesc() {
		return dataCudTypeDesc;
	}
	public void setDataCudTypeDesc(String dataCudTypeDesc) {
		this.dataCudTypeDesc = dataCudTypeDesc;
	}
}
