package dream.mgr.audtrail.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public class MgrAudTrailDetailDTO extends BaseDTO
{
	/** tracelog ID */
	private String tracelogId 		= "";
	/** object ID */
	private String objectId 		= "";
	/** file_name */
	private String fileName		 	= "";
    
	/** 변경일자  */
	private String updTime 			= "";
	/** 변경자 Desc */
	private String updByDesc		= "";
	/** 구분 Desc */
	private String dataCudTypeDesc	= "";
	
	public String getTracelogId() {
		return tracelogId;
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
