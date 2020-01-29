package dream.consult.consult.user.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.form.McUserListForm;
import dream.consult.consult.user.service.McUserListService;

/**
 * 사용자 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/mcUserList" name="mcUserListForm"
 *                input="/dream/consult/consult/user/mcUserList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcUserList" path="/dream/consult/consult/user/mcUserList.jsp"
 *                        redirect="false"
 */
public class McUserListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int USER_LIST_FIND      = 1001;
    /** 삭제 */
    public static final int USER_LIST_DELETE    = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        McUserListForm mcUserListForm = (McUserListForm) form;
        
        switch (mcUserListForm.getStrutsAction())
        {
            case McUserListAction.BASE_SET_HEADER:
                setHeader(request, response, mcUserListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case McUserListAction.USER_LIST_FIND:
                findList(request, response, mcUserListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;               
            case McUserListAction.USER_LIST_DELETE:
                deleteList(request, mcUserListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case McUserListAction.BASE_GRID_EXPORT:
            	findList(request, response, mcUserListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, McUserListForm mcUserListForm) throws IOException
    {
        super.setHeader(request, response, mcUserListForm.getListId(), mcUserListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mcUserListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, McUserListForm mcUserListForm)  throws IOException
    {
    	McUserListService mcUserListService = (McUserListService) getBean("mcUserListService");        

    	McUserCommonDTO mcUserCommonDTO = mcUserListForm.getMcUserCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	mcUserCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	mcUserCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        //리스트를 조회한다.
        List resultList = mcUserListService.findUserList(mcUserCommonDTO, getUser(request));

     // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response, mcUserListForm.getListId());
    }
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, McUserListForm mcUserListForm) throws Exception
    {
        McUserListService mcUserListService = (McUserListService) getBean("mcUserListService");        

        String[] deleteRows = mcUserListForm.getDeleteRows();    // sheet 내역
        
        mcUserListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
