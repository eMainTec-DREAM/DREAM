package dream.rcm.pmtask.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;
import dream.rcm.pmtask.form.RcmPmtaskMapListForm;
import dream.rcm.pmtask.service.RcmPmtaskMapListService;

/**
 * 목록
 * @author  kim21017
 * @version $Id: RcmPmtaskMapListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmPmtaskMapList" name="rcmPmtaskMapListForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskMapList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmPmtaskMapList" path="/dream/rcm/pmtask/rcmPmtaskMapList.jsp"
 *                        redirect="false"
 */
public class RcmPmtaskMapListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int FMEA_CRITY_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int FMEA_CRITY_LIST_DELETE 		= 7002;
    
    public static final int PMTASK_QUESTION_FIND 		= 1003;
    
    public static final int PMTASK_QUESTION_INPUT 		= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmPmtaskMapListForm rcmPmtaskMapListForm = (RcmPmtaskMapListForm) form;
        
        switch (rcmPmtaskMapListForm.getStrutsAction())
        {
            case RcmPmtaskListAction.BASE_SET_HEADER:
                super.setHeader(request, response, rcmPmtaskMapListForm.getListId(), rcmPmtaskMapListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmPmtaskMapListAction.FMEA_CRITY_LIST_FIND:
                findList(request, response, rcmPmtaskMapListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmPmtaskMapListAction.PMTASK_QUESTION_FIND:
                findQuestion(request, response, rcmPmtaskMapListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmPmtaskMapListAction.FMEA_CRITY_LIST_DELETE:
            	deleteList(request,rcmPmtaskMapListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmPmtaskMapListAction.PMTASK_QUESTION_INPUT:
            	insertMapList(request,rcmPmtaskMapListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmPmtaskMapListAction.BASE_GRID_EXPORT:
            	findList(request,response, rcmPmtaskMapListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmPmtaskMapList");
                break;
        }

        return returnActionForward;
    }
    
    private void insertMapList(HttpServletRequest request, RcmPmtaskMapListForm rcmPmtaskMapListForm) {
    	RcmPmtaskMapListService rcmPmtaskMapListService = (RcmPmtaskMapListService) getBean("rcmPmtaskMapListService");
        RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskMapListForm.getRcmPmtaskCommonDTO();
        rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        RcmPmtaskMapListDTO rcmPmtaskMapListDTO = rcmPmtaskMapListForm.getRcmPmtaskMapListDTO();
        
        rcmPmtaskMapListService.insertMapList(rcmPmtaskCommonDTO, rcmPmtaskMapListDTO);
        
        setAjaxStatus(request);
	}

	/**
     * Find Questions 
     * @param request
     * @param response
     * @param rcmPmtaskMapListForm
     * @throws IOException
     */
    private void findQuestion(HttpServletRequest request, HttpServletResponse response,RcmPmtaskMapListForm rcmPmtaskMapListForm) throws IOException 
    {
    	RcmPmtaskMapListService rcmPmtaskMapListService = (RcmPmtaskMapListService) getBean("rcmPmtaskMapListService");
        RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskMapListForm.getRcmPmtaskCommonDTO();
        rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        RcmPmtaskMapListDTO rcmPmtaskMapListDTO = rcmPmtaskMapListForm.getRcmPmtaskMapListDTO();
        
        List resultList = rcmPmtaskMapListService.findQuestionList(rcmPmtaskCommonDTO, rcmPmtaskMapListDTO);
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(CommonUtil.makeJson(resultList));
        
        response.getWriter().print(jsonString);
	}

    
    
	/**
     * grid find
     * @author  kim2107
     * @version $Id: RcmPmtaskMapListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmPmtaskMapListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmPmtaskMapListForm rcmPmtaskMapListForm, boolean excelExport) throws Exception
    {
        RcmPmtaskMapListService rcmPmtaskMapListService = (RcmPmtaskMapListService) getBean("rcmPmtaskMapListService");
        RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskMapListForm.getRcmPmtaskCommonDTO();
        rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        RcmPmtaskMapListDTO rcmPmtaskMapListDTO = rcmPmtaskMapListForm.getRcmPmtaskMapListDTO();
        
    	//Paging
        rcmPmtaskCommonDTO.setIsLoadMaxCount("Y".equals(rcmPmtaskMapListForm.getIsLoadMaxCount())?true:false);
        rcmPmtaskCommonDTO.setFirstRow(rcmPmtaskMapListForm.getFirstRow());
        rcmPmtaskCommonDTO.setOrderBy(rcmPmtaskMapListForm.getOrderBy());
        rcmPmtaskCommonDTO.setDirection(rcmPmtaskMapListForm.getDirection());
    	
        List resultList = rcmPmtaskMapListService.findList(rcmPmtaskCommonDTO, rcmPmtaskMapListDTO);
        String totalCount = "";
        if(Integer.parseInt(rcmPmtaskMapListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmPmtaskMapListService.findTotalCount(rcmPmtaskCommonDTO,rcmPmtaskMapListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,rcmPmtaskMapListForm.getListId(),rcmPmtaskMapListForm.getCurrentPageId(), rcmPmtaskMapListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmPmtaskMapListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmPmtaskMapListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, RcmPmtaskMapListForm rcmPmtaskMapListForm) throws Exception
    {
    	RcmPmtaskMapListService rcmPmtaskMapListService = (RcmPmtaskMapListService) getBean("rcmPmtaskMapListService");
    	RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskMapListForm.getRcmPmtaskCommonDTO();
        rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        RcmPmtaskMapListDTO rcmPmtaskMapListDTO = rcmPmtaskMapListForm.getRcmPmtaskMapListDTO();
        
    	rcmPmtaskMapListService.deleteList(rcmPmtaskCommonDTO, rcmPmtaskMapListDTO);
    	
    	setAjaxStatus(request);
    }
}