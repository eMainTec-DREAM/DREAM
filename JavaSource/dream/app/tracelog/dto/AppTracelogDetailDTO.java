package dream.app.tracelog.dto;

import common.bean.BaseDTO;

/**
 * TraceLog - Detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AppTracelogDetailDTO extends BaseDTO
{
	/**Screen Trace ID*/
	private String scrnTraceLogId 		= "";
	/**회사번호*/
    private String compNo       = "";
    /**회사명*/
    private String compDesc       = "";
    /**화면 ID*/
    private String pageId       = "";
    /**화면명*/
    private String pageDesc       = "";
    /**jsp 파일 이름*/
    private String fileName       = "";
    /**Object ID*/
    private String objectId       = "";
    /**수정일자*/
    private String updDate       = "";
    /**수정자 ID*/
    private String updBy       = "";
    /**Trace Log*/
    private String contents       = "";
    
    public String getScrnTraceLogId()
    {
        return scrnTraceLogId;
    }
    public void setScrnTraceLogId(String scrnTraceLogId)
    {
        this.scrnTraceLogId = scrnTraceLogId;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getCompDesc()
    {
        return compDesc;
    }
    public void setCompDesc(String compDesc)
    {
        this.compDesc = compDesc;
    }
    public String getPageId()
    {
        return pageId;
    }
    public void setPageId(String pageId)
    {
        this.pageId = pageId;
    }
    public String getPageDesc()
    {
        return pageDesc;
    }
    public void setPageDesc(String pageDesc)
    {
        this.pageDesc = pageDesc;
    }
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getObjectId()
    {
        return objectId;
    }
    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }
    public String getUpdDate()
    {
        return updDate;
    }
    public void setUpdDate(String updDate)
    {
        this.updDate = updDate;
    }
    public String getUpdBy()
    {
        return updBy;
    }
    public void setUpdBy(String updBy)
    {
        this.updBy = updBy;
    }
    public String getContents()
    {
        return contents;
    }
    public void setContents(String contents)
    {
        this.contents = contents;
    }
    
}
