package dream.work.cal.pmyear.action;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.ResponseDTO;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.cal.pmyear.dto.MaWoYearSchedCommonDTO;
import dream.work.cal.pmyear.form.MaWoYearSchedListForm;
import dream.work.cal.pmyear.service.MaWoYearSchedListService;

/**
 * 연간작업일정 - 목록 action
 * @author  kim21017
 * @version $Id: MaWoYearSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoYearSchedList" name="maWoYearSchedListForm"
 *                input="/dream/work/cal/pmyear/maWoYearSchedList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workCalPmcYearList" name="maWoYearSchedListForm"
 *                input="/dream/work/cal/pmyear/workCalPmcYearList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoYearSchedList" path="/dream/work/cal/pmyear/maWoYearSchedList.jsp"
 *                        redirect="false"
 */
public class MaWoYearSchedListAction extends AuthAction
{
    /** 조회 */
    public static final int YEAR_SCHED_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int YEAR_SCHED_LIST_DELETE 		= 7002;
    /** 스케쥴조회 */
    public static final int YEAR_SCHED_FIND				= 1003;
    /** 스케쥴조정 */
    public static final int YEAR_SCHED_CHANGE           = 1004;
    /** 일단위 확정 */
    public static final int YEAR_SCHED_DAILY_SCHEDULED	= 6001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoYearSchedListForm maWoYearSchedListForm = (MaWoYearSchedListForm) form;
        
        switch (maWoYearSchedListForm.getStrutsAction())
        {
            case MaWoYearSchedListAction.YEAR_SCHED_LIST_FIND:
                findSchedList(request, maWoYearSchedListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoYearSchedListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoYearSchedListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoYearSchedListAction.YEAR_SCHED_LIST_DELETE:
            	deleteSched(request, maWoYearSchedListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoYearSchedListAction.YEAR_SCHED_FIND:
            	findSchedule(request,maWoYearSchedListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoYearSchedListAction.YEAR_SCHED_DAILY_SCHEDULED:
            	dailyScheduled(request, response, maWoYearSchedListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoYearSchedListAction.BASE_GRID_EXPORT:
            	findSchedList(request, maWoYearSchedListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaWoYearSchedListAction.YEAR_SCHED_CHANGE:
                changeSchedule(request, maWoYearSchedListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void changeSchedule(HttpServletRequest request, MaWoYearSchedListForm maWoYearSchedListForm,  HttpServletResponse response) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException
    {
        MaWoYearSchedListService maWoYearSchedListService = (MaWoYearSchedListService) getBean("maWoYearSchedListService");    
        
        List<Map> gridList = setGridMap(request);

//        for(Map mapDto : gridList)
//        {
//        	MaPmMstrDetailDTO dto = (MaPmMstrDetailDTO)CommonUtil.makeDTO(mapDto, MaPmMstrDetailDTO.class);
//        }
        
        maWoYearSchedListService.updateSchedule(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoYearSchedListForm maWoYearSchedListForm) throws IOException
    {
        super.setHeader(request, response, maWoYearSchedListForm.getListId(),maWoYearSchedListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoYearSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoYearSchedListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, MaWoYearSchedListForm maWoYearSchedListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoYearSchedListService maWoYearSchedListService = (MaWoYearSchedListService) getBean("maWoYearSchedListService", request);        

    	MaWoYearSchedCommonDTO maWoYearSchedCommonDTO = maWoYearSchedListForm.getMaWoYearSchedCommonDTO();
    	maWoYearSchedCommonDTO.setCompNo(getUser(request).getCompNo());

    	// Paging
    	maWoYearSchedCommonDTO.setIsLoadMaxCount("Y".equals(maWoYearSchedListForm.getIsLoadMaxCount())?true:false);
        maWoYearSchedCommonDTO.setFirstRow(maWoYearSchedListForm.getFirstRow());
        maWoYearSchedCommonDTO.setOrderBy(maWoYearSchedListForm.getOrderBy());
        maWoYearSchedCommonDTO.setDirection(maWoYearSchedListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maWoYearSchedListService.findSchedList(maWoYearSchedCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maWoYearSchedListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoYearSchedListService.findTotalCount(maWoYearSchedCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maWoYearSchedListForm.getListId(),maWoYearSchedListForm.getCurrentPageId(), maWoYearSchedListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}
    
    /**
     * schedule find
     * @author  kim2107
     * @version $Id: MaWoYearSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoYearSchedListForm
     * @param response
     * @throws Exception
     */
    private void findSchedule(HttpServletRequest request, MaWoYearSchedListForm maWoYearSchedListForm, HttpServletResponse response) throws Exception
    {
    	MaWoYearSchedListService maWoYearSchedListService = (MaWoYearSchedListService) getBean("maWoYearSchedListService", request);        

    	MaWoYearSchedCommonDTO maWoYearSchedCommonDTO = maWoYearSchedListForm.getMaWoYearSchedCommonDTO();
    	
    	String result = maWoYearSchedListService.findSchedule(maWoYearSchedCommonDTO,getUser(request));
    	
    	response.getWriter().print(result);
	}
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaWoYearSchedListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoYearSchedListForm
     */
    private void deleteSched(HttpServletRequest request, MaWoYearSchedListForm maWoYearSchedListForm) throws Exception
    {
    	MaWoYearSchedListService maWoYearSchedListService = (MaWoYearSchedListService) getBean("maWoYearSchedListService");        

    	String[] deleteRows = maWoYearSchedListForm.getDeleteRows();
    	
    	maWoYearSchedListService.deleteSched(deleteRows, getUser(request));
    	
        setAjaxStatus(request);
    }

    
    /**
     * 일별 확정
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maWoYearSchedListForm
     */
    private void dailyScheduled(HttpServletRequest request, HttpServletResponse response, MaWoYearSchedListForm maWoYearSchedListForm) throws Exception
    {
    	MaWoYearSchedListService maWoYearSchedListService = (MaWoYearSchedListService) getBean("maWoYearSchedListService");        
    	MaWoYearSchedCommonDTO maWoYearSchedCommonDTO = maWoYearSchedListForm.getMaWoYearSchedCommonDTO();
    	maWoYearSchedCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	String[] fixRows = maWoYearSchedListForm.getSelectRows();
    	
    	ResponseDTO responseDTO = maWoYearSchedListService.dailyScheduled(fixRows,maWoYearSchedCommonDTO,getUser(request));
    	
        CommonUtil.makeJsonResult(responseDTO, response);
    }
}
