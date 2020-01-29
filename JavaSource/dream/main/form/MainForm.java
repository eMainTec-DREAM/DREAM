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
    /** �������� */
    private List noticeList = null;
    /** �Խ��� */
    private List boardList = null;
    
    /** Approval �Ǽ� DTO */
    private AppCntDTO appCntDTO = null;
    /** My Work �Ǽ� DTO */
    private MyWorkCntDTO myWorkCntDTO = null;
    /** Work Notice �Ǽ� DTO */
    private WorkNoticeCntDTO workNoticeCntDTO = null;
    /** Inspection Chart DTO */
    private InspChartDTO inspChartDTO = null;
    /** ���� �԰�/��� Chart DTO */
    private PartChartDTO partChartDTO = null;
    /** ��ġ���� */
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