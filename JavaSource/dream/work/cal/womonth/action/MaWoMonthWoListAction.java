package dream.work.cal.womonth.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.bean.User;
import common.report.service.ReportService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.cal.womonth.dto.MaWoMonthWoCommonDTO;
import dream.work.cal.womonth.form.MaWoMonthWoListForm;
import dream.work.cal.womonth.service.MaWoMonthWoListService;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;

/**
 * 월간작업일정 - 목록 action
 * @author  kim21017
 * @version $Id: MaWoMonthWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoMonthWoList" name="maWoMonthWoListForm"
 *                input="/dream/work/cal/womonth/maWoMonthWoList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoMonthWoList" path="/dream/work/cal/womonth/maWoMonthWoList.jsp"
 *                        redirect="false"
 */
public class MaWoMonthWoListAction extends AuthAction
{
    /** 조회 */
    public static final int MONTH_WO_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int MONTH_WO_LIST_DELETE 		= 7002;
    /** 스케쥴조회 */
    public static final int MONTH_WO_FIND				= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoMonthWoListForm maWoMonthWoListForm = (MaWoMonthWoListForm) form;
        
        super.updateAudit(maWoMonthWoListForm.getMaWoMonthWoCommonDTO().getAuditKey()==""?maWoMonthWoListForm.getMaWoMonthWoCommonDTO().getAuditKey():maWoMonthWoListForm.getMaWoMonthWoCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoMonthWoListForm.getStrutsAction())
        {
            case MaWoMonthWoListAction.MONTH_WO_LIST_FIND:
                findSchedList(request, maWoMonthWoListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoMonthWoListAction.MONTH_WO_FIND:
            	findSchedule(request,maWoMonthWoListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoMonthWoListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoMonthWoListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoMonthWoListAction.MONTH_WO_LIST_DELETE:
            	deleteSched(request, maWoMonthWoListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoMonthWoListAction.BASE_GRID_EXPORT:
            	findSchedList(request, maWoMonthWoListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaWoMonthWoListAction.BASE_ACTION_REPORT:
            	findReport(request, maWoMonthWoListForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            default:
                returnActionForward = mapping.findForward("maWoMonthWoList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoMonthWoListForm maWoMonthWoListForm) throws IOException
    {
        super.setHeader(request, response, maWoMonthWoListForm.getListId(),maWoMonthWoListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoMonthWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoMonthWoListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, MaWoMonthWoListForm maWoMonthWoListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaWoMonthWoListService maWoMonthWoListService = (MaWoMonthWoListService) getBean("maWoMonthWoListService");        

    	MaWoMonthWoCommonDTO maWoMonthWoCommonDTO = maWoMonthWoListForm.getMaWoMonthWoCommonDTO();
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoMonthWoListForm.getMaWoResultMstrCommonDTO();
    	maWoMonthWoCommonDTO.setCompNo(getUser(request).getCompNo());

    	//Paging
        maWoMonthWoCommonDTO.setIsLoadMaxCount("Y".equals(maWoMonthWoListForm.getIsLoadMaxCount())?true:false);
        maWoMonthWoCommonDTO.setFirstRow(maWoMonthWoListForm.getFirstRow());
        maWoMonthWoCommonDTO.setOrderBy(maWoMonthWoListForm.getOrderBy());
        maWoMonthWoCommonDTO.setDirection(maWoMonthWoListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maWoMonthWoListService.findSchedList(maWoMonthWoCommonDTO,maWoResultMstrCommonDTO, getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoMonthWoListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoMonthWoListService.findTotalCount(maWoMonthWoCommonDTO, maWoResultMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoMonthWoListForm.getListId(),maWoMonthWoListForm.getCurrentPageId(), maWoMonthWoListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}
    
    /**
     * schedule find
     * @author  kim2107
     * @version $Id: MaWoMonthWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoMonthWoListForm
     * @param response
     * @throws Exception
     */
    private void findSchedule(HttpServletRequest request, MaWoMonthWoListForm maWoMonthWoListForm, HttpServletResponse response) throws Exception
    {
    	MaWoMonthWoListService maWoMonthWoListService = (MaWoMonthWoListService) getBean("maWoMonthWoListService");        

    	MaWoMonthWoCommonDTO maWoMonthWoCommonDTO = maWoMonthWoListForm.getMaWoMonthWoCommonDTO();
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoMonthWoListForm.getMaWoResultMstrCommonDTO();
    	maWoMonthWoCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        String result = maWoMonthWoListService.findSchedule(maWoMonthWoCommonDTO,maWoResultMstrCommonDTO, getUser(request));

        response.getWriter().print(result);
	}
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaWoMonthWoListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoMonthWoListForm
     */
    private void deleteSched(HttpServletRequest request, MaWoMonthWoListForm maWoMonthWoListForm) throws Exception
    {
    	MaWoMonthWoListService maWoMonthWoListService = (MaWoMonthWoListService) getBean("maWoMonthWoListService");        

    	String[] deleteRows = maWoMonthWoListForm.getDeleteRows();
    	
    	maWoMonthWoListService.updateDeleteTagSched(deleteRows, getUser(request));
    	
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
    private void findReport(HttpServletRequest request, MaWoMonthWoListForm maWoMonthWoListForm) throws JRException, DRException, IOException
    {
    	MaWoMonthWoListService maWoMonthWoListService = (MaWoMonthWoListService) getBean("maWoMonthWoListService");        
    	MaWoMonthWoCommonDTO maWoMonthWoCommonDTO = maWoMonthWoListForm.getMaWoMonthWoCommonDTO();
    	maWoMonthWoCommonDTO.setEnterBy(getUser(request).getUserId());
    	maWoMonthWoCommonDTO.setCompNo(getUser(request).getCompNo());
    	maWoMonthWoCommonDTO.setUserLang(getUser(request).getLangId());
        
        List reportList = maWoMonthWoListService.getReportView(maWoMonthWoCommonDTO);
        
        Map map = (Map)reportList.get(0);
        Gson gson = new Gson();
        String strJson = gson.toJson(map);
        
        ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
        String destFileName = rs.viewReport("maWoResultMstrList",strJson, getUser(request));

        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);

        // 조회한 List 를 form에 셋팅한다.
//        request.setAttribute(REPORT_NAME_ATTRIBUTE, "woList");
//        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
}
