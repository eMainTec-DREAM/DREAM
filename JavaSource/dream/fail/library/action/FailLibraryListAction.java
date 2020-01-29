package dream.fail.library.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.fail.library.dto.FailLibraryCommonDTO;
import dream.fail.library.form.FailLibraryListForm;
import dream.fail.library.service.FailLibraryListService;

/**
 * 고장library - 목록 action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/failLibraryList" name="failLibraryListForm"
 *                input="/dream/fail/library/failLibraryList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="failLibraryList" path="/dream/fail/library/failLibraryList.jsp"
 *                        redirect="false"
 */
public class FailLibraryListAction extends AuthAction
{
    /** 조회 */
    public static final int FAILLIB_LIST_FIND   = 1001;
    /** 삭제 */
    public static final int FAILLIB_LIST_DELETE = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        FailLibraryListForm failLibraryListForm = (FailLibraryListForm) form;
        
        switch (failLibraryListForm.getStrutsAction())
        {
            case FailLibraryListAction.BASE_SET_HEADER:
                setHeader(request, response, failLibraryListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case FailLibraryListAction.FAILLIB_LIST_FIND:
                findList(request, response, failLibraryListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case FailLibraryListAction.FAILLIB_LIST_DELETE:
            	deleteList(request, failLibraryListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case FailLibraryListAction.BASE_GRID_EXPORT:
            	findList(request, response, failLibraryListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("failLibraryList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, FailLibraryListForm failLibraryListForm) throws IOException
    {
        super.setHeader(request, response, failLibraryListForm.getListId(), failLibraryListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param failLibraryListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, FailLibraryListForm failLibraryListForm) throws IOException
    {
    	FailLibraryListService failLibraryListService = (FailLibraryListService) getBean("failLibraryListService");        

    	FailLibraryCommonDTO failLibraryCommonDTO = failLibraryListForm.getFailLibraryCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	failLibraryCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        //리스트를 조회한다.
        List resultList = failLibraryListService.findList(failLibraryCommonDTO,getUser(request));

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response, failLibraryListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, FailLibraryListForm failLibraryListForm) throws Exception
    {
    	FailLibraryListService failLibraryListService = (FailLibraryListService) getBean("failLibraryListService");        

        String[] deleteRows = failLibraryListForm.getDeleteRows();    // sheet 내역
        
        failLibraryListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
