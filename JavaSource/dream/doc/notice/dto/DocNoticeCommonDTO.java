package dream.doc.notice.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * DocNotice Page - ���� DTO
 * @author youngjoo38
 * @version $Id: DocNoticeCommonDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class DocNoticeCommonDTO extends BaseDTO
{
    /**Key ���� ID */ 
    private String noticeId          	= "";
    
    /**Filter �������� FROM */
    private String filterNoticeFromDate = "";    
    /**Filter �������� TO */
    private String filterNoticeToDate   = "";    
    /**Filter �������� FROM */
    private String filterNoticePeriodFromDate = "";    
    /**Filter �������� TO */
    private String filterNoticePeriodToDate   = "";    
    /**Filter �ۼ��� ID*/
    private String filterWriteById      = "";    
    /**Filter �ۼ��� DESC*/
    private String filterWriteByDesc    = "";    
    /**Filter ���� */
    private String filterTitle          = "";
    /**Filter �������� ���� */
    private String filterReadYn          = "";
    /**Filter emp id */
    private String filterEmpId          = "";
    
	public String getFilterNoticePeriodFromDate()
    {
        return filterNoticePeriodFromDate;
    }
    public void setFilterNoticePeriodFromDate(String filterNoticePeriodFromDate)
    {
        this.filterNoticePeriodFromDate = CommonUtil.convertDate(filterNoticePeriodFromDate);
    }
    public String getFilterNoticePeriodToDate()
    {
        return filterNoticePeriodToDate;
    }
    public void setFilterNoticePeriodToDate(String filterNoticePeriodToDate)
    {
        this.filterNoticePeriodToDate = CommonUtil.convertDate(filterNoticePeriodToDate);
    }
    public String getFilterReadYn()
    {
        return filterReadYn;
    }
    public void setFilterReadYn(String filterReadYn)
    {
        this.filterReadYn = filterReadYn;
    }
    public String getFilterEmpId()
    {
        return filterEmpId;
    }
    public void setFilterEmpId(String filterEmpId)
    {
        this.filterEmpId = filterEmpId;
    }
    public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
		super.setAuditKey(noticeId);
	}
	public String getFilterNoticeFromDate() {
		return filterNoticeFromDate;
	}
	public void setFilterNoticeFromDate(String filterNoticeFromDate) {
		this.filterNoticeFromDate = CommonUtil.convertDate(filterNoticeFromDate);
	}
	public String getFilterNoticeToDate() {
		return filterNoticeToDate;
	}
	public void setFilterNoticeToDate(String filterNoticeToDate) {
		this.filterNoticeToDate = CommonUtil.convertDate(filterNoticeToDate);
	}
	public String getFilterWriteById() {
		return filterWriteById;
	}
	public void setFilterWriteById(String filterWriteById) {
		this.filterWriteById = filterWriteById;
	}
	public String getFilterWriteByDesc() {
		return filterWriteByDesc;
	}
	public void setFilterWriteByDesc(String filterWriteByDesc) {
		this.filterWriteByDesc = filterWriteByDesc;
	}
	public String getFilterTitle() {
		return filterTitle;
	}
	public void setFilterTitle(String filterTitle) {
		this.filterTitle = filterTitle;
	}    
}
