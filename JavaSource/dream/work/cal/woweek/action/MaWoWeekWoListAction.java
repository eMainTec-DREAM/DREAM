package dream.work.cal.woweek.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.report.service.ReportService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.cal.woweek.dto.MaWoWeekWoCommonDTO;
import dream.work.cal.woweek.form.MaWoWeekWoListForm;
import dream.work.cal.woweek.service.MaWoWeekWoListService;

/**
 * 주간작업일정 - 목록 action
 * @author  kim21017
 * @version $Id: MaWoWeekWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoWeekWoList" name="maWoWeekWoListForm"
 *                input="/dream/work/cal/woweek/maWoWeekWoList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoWeekWoList" path="/dream/work/cal/woweek/maWoWeekWoList.jsp"
 *                        redirect="false"
 */
public class MaWoWeekWoListAction extends AuthAction
{
    /** 조회 */
    public static final int WEEK_WO_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WEEK_WO_LIST_DELETE 		= 7002;
    /** 스케쥴조회 */
    public static final int WEEK_WO_FIND				= 1003;
    public static final int WO_TYPE_FIND				= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoWeekWoListForm maWoWeekWoListForm = (MaWoWeekWoListForm) form;
        
        switch (maWoWeekWoListForm.getStrutsAction())
        {
            case MaWoWeekWoListAction.WEEK_WO_LIST_FIND:
                findSchedList(request, maWoWeekWoListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoWeekWoListAction.WEEK_WO_FIND:
            	findSchedule(request,maWoWeekWoListForm,response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoWeekWoListAction.WO_TYPE_FIND:
            	findWoType(request,maWoWeekWoListForm,response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoWeekWoListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoWeekWoListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoWeekWoListAction.WEEK_WO_LIST_DELETE:
            	deleteSched(request, maWoWeekWoListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoWeekWoListAction.BASE_GRID_EXPORT:
            	findSchedList(request, maWoWeekWoListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaWoWeekWoListAction.BASE_ACTION_REPORT:
            	findReport(request, maWoWeekWoListForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            default:
                returnActionForward = mapping.findForward("maWoWeekWoList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoWeekWoListForm maWoWeekWoListForm) throws IOException
    {
        super.setHeader(request, response, maWoWeekWoListForm.getListId(),maWoWeekWoListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoWeekWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoWeekWoListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, MaWoWeekWoListForm maWoWeekWoListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoWeekWoListService maWoWeekWoListService = (MaWoWeekWoListService) getBean("maWoWeekWoListService");        

    	MaWoWeekWoCommonDTO maWoWeekWoCommonDTO = maWoWeekWoListForm.getMaWoWeekWoCommonDTO();
    	
    	//Paging
    	maWoWeekWoCommonDTO.setIsLoadMaxCount("Y".equals(maWoWeekWoListForm.getIsLoadMaxCount())?true:false);
    	maWoWeekWoCommonDTO.setFirstRow(maWoWeekWoListForm.getFirstRow());
    	maWoWeekWoCommonDTO.setOrderBy(maWoWeekWoListForm.getOrderBy());
    	maWoWeekWoCommonDTO.setDirection(maWoWeekWoListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maWoWeekWoListService.findSchedList(maWoWeekWoCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maWoWeekWoListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoWeekWoListService.findTotalCount(maWoWeekWoCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoWeekWoListForm.getListId(),maWoWeekWoListForm.getCurrentPageId(), maWoWeekWoListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
	}
    
    /**
     * schedule find
     * @author  kim2107
     * @version $Id: MaWoWeekWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoWeekWoListForm
     * @param response
     * @throws Exception
     */
    private void findSchedule(HttpServletRequest request, MaWoWeekWoListForm maWoWeekWoListForm, HttpServletResponse response) throws IOException
    {
    	MaWoWeekWoListService maWoWeekWoListService = (MaWoWeekWoListService) getBean("maWoWeekWoListService");        

    	MaWoWeekWoCommonDTO maWoWeekWoCommonDTO = maWoWeekWoListForm.getMaWoWeekWoCommonDTO();
    	maWoWeekWoCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        String[][] resultList = maWoWeekWoListService.findSchedule(maWoWeekWoCommonDTO,getUser(request));

        setAjaxData(request, resultList);
	}
    
    
    private void findWoType(HttpServletRequest request, MaWoWeekWoListForm maWoWeekWoListForm, HttpServletResponse response) throws IOException
    {
    	MaWoWeekWoListService maWoWeekWoListService = (MaWoWeekWoListService) getBean("maWoWeekWoListService");        
    	
    	MaWoWeekWoCommonDTO maWoWeekWoCommonDTO = maWoWeekWoListForm.getMaWoWeekWoCommonDTO();
    	maWoWeekWoCommonDTO.setCompNo(getUser(request).getCompNo());
    	//리스트를 조회한다.
    	String[][] resultList = maWoWeekWoListService.findWoType(maWoWeekWoCommonDTO,getUser(request));
    	
    	setAjaxData(request, resultList);
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaWoWeekWoListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoWeekWoListForm
     */
    private void deleteSched(HttpServletRequest request, MaWoWeekWoListForm maWoWeekWoListForm) throws Exception
    {
    	MaWoWeekWoListService maWoWeekWoListService = (MaWoWeekWoListService) getBean("maWoWeekWoListService");        

    	String[] deleteRows = maWoWeekWoListForm.getDeleteRows();
    	
    	maWoWeekWoListService.updateDeleteTagSched(deleteRows, getUser(request));
    	
        setAjaxStatus(request);
    }
    /**
     * Report 를 조회한다.
     * @param request
     * @param maWoResultMstrDetailForm
     * @throws IOException 
     * @throws DRException 
     * @throws JRException 
     */
    private void findReport(HttpServletRequest request, MaWoWeekWoListForm maWoWeekWoListForm) throws JRException, DRException, IOException
    {
    	MaWoWeekWoListService maWoWeekWoListService = (MaWoWeekWoListService) getBean("maWoWeekWoListService");        
    	MaWoWeekWoCommonDTO maWoWeekWoCommonDTO = maWoWeekWoListForm.getMaWoWeekWoCommonDTO();
    	maWoWeekWoCommonDTO.setEnterBy(getUser(request).getUserId());
    	maWoWeekWoCommonDTO.setCompNo(getUser(request).getCompNo());
    	maWoWeekWoCommonDTO.setUserLang(getUser(request).getLangId());
        
        List reportList = maWoWeekWoListService.getReportView(maWoWeekWoCommonDTO);
        
        Map map = (Map)reportList.get(0);
        Gson gson = new Gson();
        String strJson = gson.toJson(map);
        
        ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
        String destFileName = rs.viewReport("maWoResultMstrList",strJson, getUser(request));

        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);

        // 조회한 List 를 form에 셋팅한다.
//        request.setAttribute(REPORT_NAME_ATTRIBUTE, "WOLIST");
//        REQUEST.SETATTRIBUTE(REPORT_MAP_ATTRIBUTE, reportList);
    }
}
