package dream.main.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.main.dto.MaDbListDTO;
import dream.main.form.MaDbListForm;
import dream.main.service.MaDbListService;

/**
 * Dashboard Alarm List - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maDbList" name="maDbListForm"
 *                input="/dream/main/maDbList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maDbList" path="/dream/main/maDbList.jsp"
 *                        redirect="false"
 */
public class MaDbListAction extends AuthAction
{
    /** 조회 */
    public static final int DASHBOARD_LIST_FIND = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaDbListForm maDbListForm = (MaDbListForm) form;
        
        switch (maDbListForm.getStrutsAction())
        {
            case MaDbListAction.BASE_SET_HEADER:
                setHeader(request, response, maDbListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDbListAction.DASHBOARD_LIST_FIND:
                findList(request, response, maDbListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;                     
            default:
                returnActionForward = mapping.findForward("maDbList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaDbListForm maDbListForm) throws IOException
    {
        super.setHeader(request, response, maDbListForm.getListId(), maDbListForm.getCurrentPageId()); 
    }    
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maDbListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaDbListForm maDbListForm) throws Exception
    {
    	MaDbListService maDbListService = (MaDbListService) getBean("maDbListService", request);        

    	MaDbListDTO maDbListDTO = maDbListForm.getMaDbListDTO();

        //리스트를 조회한다.
        List resultList = maDbListService.findDbList(maDbListDTO, getUser(request));

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response, maDbListForm.getListId());
    }
        
}
