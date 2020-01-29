package dream.edu.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.form.EduListForm;
import dream.edu.list.service.EduListService;

/**
 * 자격증분류 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/eduList" name="eduListForm"
 *                input="/dream/edu/list/eduList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="eduList" path="/dream/edu/list/eduList.jsp"
 *                        redirect="false"
 */
public class EduListAction extends AuthAction
{
    /** 조회 */
    public static final int EDU_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int EDU_LIST_DELETE   = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        EduListForm eduListForm = (EduListForm) form;
        
        switch (eduListForm.getStrutsAction())
        {
            case EduListAction.EDU_LIST_FIND:
            	findPtRecList(request, eduListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EduListAction.BASE_SET_HEADER:
                setHeader(request, response, eduListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EduListAction.EDU_LIST_DELETE:
            	deletePtRec(request, eduListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case EduListAction.BASE_GRID_EXPORT:
            	findPtRecList(request, eduListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("eduList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, EduListForm eduListForm) throws IOException
    {
        super.setHeader(request, response, eduListForm.getListId(), eduListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param eduListForm
     * @throws Exception
     */
    private void findPtRecList(HttpServletRequest request, EduListForm eduListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	EduListService eduListService = (EduListService) getBean("eduListService");        

    	EduCommonDTO eduCommonDTO = eduListForm.getEduCommonDTO();
    	eduCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	eduCommonDTO.setIsLoadMaxCount("Y".equals(eduListForm.getIsLoadMaxCount())?true:false);
    	eduCommonDTO.setFirstRow(eduListForm.getFirstRow());
    	eduCommonDTO.setOrderBy(eduListForm.getOrderBy());
    	eduCommonDTO.setDirection(eduListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = eduListService.findList(eduCommonDTO,getUser(request));
      
        //Paging
        String totalCount = "";
        if(Integer.parseInt(eduListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = eduListService.findTotalCount(eduCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,eduListForm.getListId(),eduListForm.getCurrentPageId(), eduListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduListForm
     * @param request
     */
    private void deletePtRec(HttpServletRequest request, EduListForm eduListForm) throws Exception
    {
    	EduListService eduListService = (EduListService) getBean("eduListService");
    	String[] deleteRows = eduListForm.getDeleteRows();    // sheet 내역
        
        eduListService.deleteList(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
}
