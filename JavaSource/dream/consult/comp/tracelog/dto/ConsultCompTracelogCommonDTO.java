package dream.consult.comp.tracelog.dto;

import common.bean.BaseDTO;
/**
 * Screen Trace - ���� DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class ConsultCompTracelogCommonDTO extends BaseDTO
{
	/**Screen Trace ID*/
	private String scrnTraceLogId 			= "";
	/**ȸ���ȣ*/
    private String filterCompNo           = "";
    /**ȸ���*/
    private String filterCompDesc           = "";
    /**������ ID*/
    private String filterPageId           = "";
    /**��������*/
    private String filterPageDesc           = "";
    /**jsp ���� �̸�*/
    private String filterFileName           = "";
    /**Object ID*/
    private String filterObjectId           = "";
    /**�޴� ID*/
    private String filterMenuId           = "";
    /**�޴���*/
    private String filterMenuDesc           = "";
    
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
    public String getFilterCompDesc()
    {
        return filterCompDesc;
    }
    public void setFilterCompDesc(String filterCompDesc)
    {
        this.filterCompDesc = filterCompDesc;
    }
    public String getFilterPageId()
    {
        return filterPageId;
    }
    public void setFilterPageId(String filterPageId)
    {
        this.filterPageId = filterPageId;
    }
    public String getFilterPageDesc()
    {
        return filterPageDesc;
    }
    public void setFilterPageDesc(String filterPageDesc)
    {
        this.filterPageDesc = filterPageDesc;
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
    public String getFilterMenuId()
    {
        return filterMenuId;
    }
    public void setFilterMenuId(String filterMenuId)
    {
        this.filterMenuId = filterMenuId;
    }
    public String getFilterMenuDesc()
    {
        return filterMenuDesc;
    }
    public void setFilterMenuDesc(String filterMenuDesc)
    {
        this.filterMenuDesc = filterMenuDesc;
    }
	
}
