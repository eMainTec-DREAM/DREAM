package dream.ass.base.action;


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
import common.util.CommonUtil;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;
import dream.ass.base.form.AssBaseGradeListForm;
import dream.ass.base.service.AssBaseGradeListService;

/**
 * 등급기준 - List Action
 * 
 * @author kim21017
 * @version $Id: AssBaseGradeListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBaseGradeList" name="assBaseGradeListForm"
 *                input="/dream/ass/base/assBaseGradeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBaseGradeList" path="/dream/ass/base/assBaseGradeList.jsp"
 *                        redirect="false"
 */

public class AssBaseGradeListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    public static final int LIST_SAVE	 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssBaseGradeListForm assBaseGradeListForm = (AssBaseGradeListForm) form;
        
        super.updateAudit(assBaseGradeListForm.getAssBaseGradeListDTO().getAuditKey()==""?assBaseGradeListForm.getAssBaseGradeListDTO().getAuditKey():assBaseGradeListForm.getAssBaseGradeListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assBaseGradeListForm.getStrutsAction())
        {
            case AssBaseGradeListAction.BASE_SET_HEADER:
                setHeader(request, response, assBaseGradeListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssBaseGradeListAction.LIST_FIND:
                findList(request, response, assBaseGradeListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssBaseGradeListAction.LIST_SAVE:
            	saveList(request, response, assBaseGradeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;    
            case AssBaseGradeListAction.LIST_DELETE:
            	deleteList(request, assBaseGradeListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssBaseGradeListAction.BASE_GRID_EXPORT:
            	findList(request, response, assBaseGradeListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assBaseGradeList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssBaseGradeListForm assBaseGradeListForm) throws IOException
    {
        super.setHeader(request, response, assBaseGradeListForm.getListId(), assBaseGradeListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assBaseGradeListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssBaseGradeListForm assBaseGradeListForm, boolean excelExport) throws Exception
    {
    	AssBaseGradeListService assBaseGradeListService = (AssBaseGradeListService) getBean("assBaseGradeListService");
    	AssBaseCommonDTO assBaseCommonDTO = assBaseGradeListForm.getAssBaseCommonDTO();
    	AssBaseGradeListDTO assBaseGradeListDTO = assBaseGradeListForm.getAssBaseGradeListDTO();
        
    	//Paging
    	assBaseCommonDTO.setIsLoadMaxCount("Y".equals(assBaseGradeListForm.getIsLoadMaxCount())?true:false);
    	assBaseCommonDTO.setFirstRow(assBaseGradeListForm.getFirstRow());
    	assBaseCommonDTO.setOrderBy(assBaseGradeListForm.getOrderBy());
    	assBaseCommonDTO.setDirection(assBaseGradeListForm.getDirection());
    	
    	List resultList = assBaseGradeListService.findList(assBaseCommonDTO,assBaseGradeListDTO, getUser(request));
        
    	//Paging
        String totalCount = "";
        if(Integer.parseInt(assBaseGradeListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assBaseGradeListService.findTotalCount(assBaseCommonDTO, assBaseGradeListDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,assBaseGradeListForm.getListId(),assBaseGradeListForm.getCurrentPageId(), assBaseGradeListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assBaseGradeListForm
     */
    private void deleteList(HttpServletRequest request, AssBaseGradeListForm assBaseGradeListForm) throws Exception
    {
    	AssBaseGradeListService assBaseGradeListService = (AssBaseGradeListService) getBean("assBaseGradeListService");
        String[] deleteRows = assBaseGradeListForm.getDeleteRows();
        assBaseGradeListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
    private void saveList(HttpServletRequest request, HttpServletResponse response, AssBaseGradeListForm assBaseGradeListForm) throws Exception
    {
    	AssBaseGradeListService assBaseGradeListService = (AssBaseGradeListService) getBean("assBaseGradeListService");
        
        List<Map> gridList = CommonUtil.setGridMap(request);

    	assBaseGradeListService.saveList(gridList, getUser(request));

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
    
}
