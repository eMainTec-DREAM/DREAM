package dream.doc.notice.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * DocNotice Page - 공통 DTO
 * @author youngjoo38
 * @version $Id: DocNoticeCommonDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class DocNoticeCommonDTO extends BaseDTO
{
    /**Key 공지 ID */ 
    private String noticeId          	= "";
    
    /**Filter 공지일자 FROM */
    private String filterNoticeFromDate = "";    
    /**Filter 공지일자 TO */
    private String filterNoticeToDate   = "";    
    /**Filter 공지기한 FROM */
    private String filterNoticePeriodFromDate = "";    
    /**Filter 공지기한 TO */
    private String filterNoticePeriodToDate   = "";    
    /**Filter 작성자 ID*/
    private String filterWriteById      = "";    
    /**Filter 작성자 DESC*/
    private String filterWriteByDesc    = "";    
    /**Filter 제목 */
    private String filterTitle          = "";
    /**Filter 읽은공지 여부 */
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
