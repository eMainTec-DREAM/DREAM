package dream.mgr.audtrail.dto;

import common.bean.BaseDTO;

/**
 * Audit Trail DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class MgrAudTrailCommonDTO extends BaseDTO
{
	/** tracelog ID */
	private String tracelogId 		= "";
	/** object ID */
	private String objectId 		= "";
	/** file_name */
	private String fileName		 	= "";
	
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
