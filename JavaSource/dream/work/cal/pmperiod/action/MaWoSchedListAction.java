package dream.work.cal.pmperiod.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.struts.AuthAction;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;
import dream.work.cal.pmperiod.form.MaWoSchedListForm;
import dream.work.cal.pmperiod.service.MaWoSchedListService;

/**
 * 예방작업일정(기간) - 목록 action
 * @author  kim21017
 * @version $Id: MaWoSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoSchedList" name="maWoSchedListForm"
 *                input="/dream/work/cal/pmperiod/maWoSchedList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workCalPmcPeriodList" name="maWoSchedListForm"
 *                input="/dream/work/cal/pmperiod/workCalPmcPeriodList.jsp" scope="request"
 *                validate="false"
 */
public class MaWoSchedListAction extends AuthAction
{
    /** 조회 */
    public static final int MONTH_SCHED_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int MONTH_SCHED_LIST_DELETE 		= 7002;

    /** 스케줄 변경 */
    public static final int MONTH_SCHED_CHANGE              = 1006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoSchedListForm maWoSchedListForm = (MaWoSchedListForm) form;
        
        super.updateAudit(maWoSchedListForm.getMaWoSchedCommonDTO().getAuditKey()==""?maWoSchedListForm.getMaWoSchedCommonDTO().getAuditKey():maWoSchedListForm.getMaWoSchedCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoSchedListForm.getStrutsAction())
        {
            case MaWoSchedListAction.MONTH_SCHED_LIST_FIND:
                findSchedList(request, maWoSchedListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoSchedListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoSchedListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoSchedListAction.MONTH_SCHED_LIST_DELETE:
            	deleteSched(request, maWoSchedListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;

            case MaWoSchedListAction.BASE_GRID_EXPORT:
            	findSchedList(request, maWoSchedListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaWoSchedListAction.MONTH_SCHED_CHANGE:
                changeSchedule(request,maWoSchedListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void changeSchedule(HttpServletRequest request,MaWoSchedListForm maWoSchedListForm, HttpServletResponse response) throws IOException
    {
        MaWoSchedListService maWoSchedListService = (MaWoSchedListService) getBean("maWoSchedListService");     
        
        List<Map> gridList = setGridMap(request);

        maWoSchedListService.updateSchedule(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
        
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoSchedListForm maWoSchedListForm) throws IOException
    {
        super.setHeader(request, response, maWoSchedListForm.getListId(),maWoSchedListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoSchedListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, MaWoSchedListForm maWoSchedListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoSchedListService maWoSchedListService = (MaWoSchedListService) getBean("maWoSchedListService", request);        

    	MaWoSchedCommonDTO maWoSchedCommonDTO = maWoSchedListForm.getMaWoSchedCommonDTO();
    	maWoSchedCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maWoSchedCommonDTO.setIsLoadMaxCount("Y".equals(maWoSchedListForm.getIsLoadMaxCount())?true:false);
    	maWoSchedCommonDTO.setFirstRow(maWoSchedListForm.getFirstRow());
    	maWoSchedCommonDTO.setOrderBy(maWoSchedListForm.getOrderBy());
    	maWoSchedCommonDTO.setDirection(maWoSchedListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maWoSchedListService.findSchedList(maWoSchedCommonDTO,getUser(request));
    	        
    	//Paging
    	String totalCount = "";
    	        
    	if(Integer.parseInt(maWoSchedListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoSchedListService.findTotalCount(maWoSchedCommonDTO,getUser(request));
    	        
    	if(excelExport) super.makeGridExport(resultList, request, response, maWoSchedListForm.getListId(),maWoSchedListForm.getCurrentPageId(), maWoSchedListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, totalCount);
	}

    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaWoSchedListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoSchedListForm
     */
    private void deleteSched(HttpServletRequest request, MaWoSchedListForm maWoSchedListForm) throws Exception
    {
    	MaWoSchedListService maWoSchedListService = (MaWoSchedListService) getBean("maWoSchedListService");        

    	String[] deleteRows = maWoSchedListForm.getDeleteRows();
    	
    	maWoSchedListService.deleteSched(deleteRows, getUser(request));
    	
        setAjaxStatus(request);
    }

}
