package dream.mgr.at.dto;

import common.bean.BaseDTO;

/**
 * MgrAtHist Page - DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrAtHistListDTO extends BaseDTO
{
	/** tracelog ID */
	private String tracelogId 		= "";
	/** tracelog Detail ID */
	private String tracelogDtlId 	= "";
	
	/** file_name */
	private String fileName		 	= "";
	/** field_id */
	private String fieldId		 	= "";
	/** object ID */
	private String objectId 		= "";
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getTracelogId() {
		return tracelogId;
	}
	public void setTracelogId(String tracelogId) {
		this.tracelogId = tracelogId;
	}
	public String getTracelogDtlId() {
		return tracelogDtlId;
	}
	public void setTracelogDtlId(String tracelogDtlId) {
		this.tracelogDtlId = tracelogDtlId;
	}
}
