package dream.mgr.audtrail.dto;

import common.bean.BaseDTO;

/**
 * MgrAudTrailDtl Page - DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrAudTrailDtlListDTO extends BaseDTO
{
	/** tracelog ID */
	private String tracelogId 		= "";
	/** tracelog Detail ID */
	private String tracelogDtlId 	= "";
	/** Prev Tracelog ID */
	private String prevTracelogId 	= "";
	
	public String getTracelogId() {
		return tracelogId;
	}
	public String getPrevTracelogId() {
		return prevTracelogId;
	}
	public void setPrevTracelogId(String prevTracelogId) {
		this.prevTracelogId = prevTracelogId;
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
