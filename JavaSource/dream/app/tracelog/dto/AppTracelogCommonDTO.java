package dream.app.tracelog.dto;

import common.bean.BaseDTO;
/**
 * TraceLog - 공통 DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AppTracelogCommonDTO extends BaseDTO
{
	/**Screen Trace ID*/
	private String scrnTraceLogId 			= "";
	/**회사번호*/
    private String filterCompNo           = "";
    /**jsp 파일 이름*/
    private String filterFileName           = "";
    /**Object ID*/
    private String filterObjectId           = "";
    
    public String getScrnTraceLogId()
    {
        return scrnTraceLogId;
    }
    public void setScrnTraceLogId(String scrnTraceLogId)
    {
        this.scrnTraceLogId = scrnTraceLogId;
    }
    public String getFilterCompNo()
    {
        return filterCompNo;
    }
    public void setFilterCompNo(String filterCompNo)
    {
        this.filterCompNo = filterCompNo;
    }
    public String getFilterFileName()
    {
        return filterFileName;
    }
    public void setFilterFileName(String filterFileName)
    {
        this.filterFileName = filterFileName;
    }
    public String getFilterObjectId()
    {
        return filterObjectId;
    }
    public void setFilterObjectId(String filterObjectId)
    {
        this.filterObjectId = filterObjectId;
    }
    
}
