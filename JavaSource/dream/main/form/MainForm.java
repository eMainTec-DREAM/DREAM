package dream.main.form;

import java.util.List;

import common.struts.BaseForm;
import dream.main.dto.AppCntDTO;
import dream.main.dto.InspChartDTO;
import dream.main.dto.MyWorkCntDTO;
import dream.main.dto.PartChartDTO;
import dream.main.dto.WorkNoticeCntDTO;

/**
 * Main Action Form
 * @author  javaworker
 * @version $Id: MainForm.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="mainForm"
 */
public class MainForm extends BaseForm
{
    /** 공지사항 */
    private List noticeList = null;
    /** 게시판 */
    private List boardList = null;
    
    /** Approval 건수 DTO */
    private AppCntDTO appCntDTO = null;
    /** My Work 건수 DTO */
    private MyWorkCntDTO myWorkCntDTO = null;
    /** Work Notice 건수 DTO */
    private WorkNoticeCntDTO workNoticeCntDTO = null;
    /** Inspection Chart DTO */
    private InspChartDTO inspChartDTO = null;
    /** 자재 입고/출고 Chart DTO */
    private PartChartDTO partChartDTO = null;
    /** 위치정보 */
    private String vsPosition = "";  
    
    public PartChartDTO getPartChartDTO()
    {
        return partChartDTO;
    }
    public void setPartChartDTO(PartChartDTO partChartDTO)
    {
        this.partChartDTO = partChartDTO;
    }
    public InspChartDTO getInspChartDTO()
    {
        return inspChartDTO;
    }
    public void setInspChartDTO(InspChartDTO inspChartDTO)
    {
        this.inspChartDTO = inspChartDTO;
    }
    public List getNoticeList()
    {
        return noticeList;
    }
    public void setNoticeList(List noticeList)
    {
        this.noticeList = noticeList;
    }
    public AppCntDTO getAppCntDTO()
    {
        return appCntDTO;
    }
    public void setAppCntDTO(AppCntDTO appCntDTO)
    {
        this.appCntDTO = appCntDTO;
    }
    public MyWorkCntDTO getMyWorkCntDTO()
    {
        return myWorkCntDTO;
    }
    public void setMyWorkCntDTO(MyWorkCntDTO myWorkCntDTO)
    {
        this.myWorkCntDTO = myWorkCntDTO;
    }
    public WorkNoticeCntDTO getWorkNoticeCntDTO()
    {
        return workNoticeCntDTO;
    }
    public void setWorkNoticeCntDTO(WorkNoticeCntDTO workNoticeCntDTO)
    {
        this.workNoticeCntDTO = workNoticeCntDTO;
    }
    public String getVsPosition()
    {
        return vsPosition;
    }
    public void setVsPosition(String vsPosition)
    {
        this.vsPosition = vsPosition;
    }
    public List getBoardList()
    {
        return boardList;
    }
    public void setBoardList(List boardList)
    {
        this.boardList = boardList;
    }
}