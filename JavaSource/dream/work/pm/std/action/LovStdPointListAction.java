package dream.work.pm.std.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.LovStdPointListDTO;
import dream.work.pm.std.form.LovStdPointListForm;
import dream.work.pm.std.service.LovStdPointListService;

/**
 * 표준항목선택 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovStdPointList" name="lovStdPointListForm"
 *                input="/dream/work/pm/std/lovStdPointPopup.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovStdPointPopup" path="/dream/work/pm/std/lovStdPointPopup.jsp"
 *                        redirect="false"
 */
public class LovStdPointListAction extends AuthAction
{
    public static final int LOV_STDPOINT_DEFAULT 	= 1001;
    public static final int LOV_STDPOINT_FIND     = 1002;
    
    public static final int LOV_STDPOINT_INPUT     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovStdPointListForm lovStdPointListForm = (LovStdPointListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovStdPointListForm.getStrutsAction())
        {
            case LovStdPointListAction.LOV_STDPOINT_DEFAULT :
                returnActionForward = mapping.findForward("lovStdPointPopup");
                break;
            case LovStdPointListAction.LOV_STDPOINT_FIND :
                findStdPoint(request, lovStdPointListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovStdPointListAction.LOV_STDPOINT_INPUT :
                inputStdPoint(request, lovStdPointListForm,response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case LovStdPointListAction.BASE_SET_HEADER:
                setHeader(request, response, lovStdPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    /**
     * Input selected std Pointes
     * @param request
     * @param lovStdPointListForm
     * @param response
     */
    private void inputStdPoint(HttpServletRequest request, LovStdPointListForm lovStdPointListForm,HttpServletResponse response) 
    {
        LovStdPointListService lovStdPointListService = (LovStdPointListService)getBean("lovStdPointListService");

    	String[] deleteRows = lovStdPointListForm.getDeleteRows();    // sheet 내역
        LovStdPointListDTO lovStdPointListDTO = lovStdPointListForm.getLovStdPointListDTO();
        
    	lovStdPointListService.inputStdPoint(deleteRows, getUser(request), lovStdPointListDTO);
        
        setAjaxStatus(request);
		
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovStdPointListForm lovStdPointListForm) throws IOException
    {
        super.setHeader(request, response, lovStdPointListForm.getListId(),lovStdPointListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovStdPointListForm
     */
    private void findStdPoint(HttpServletRequest request, LovStdPointListForm lovStdPointListForm,HttpServletResponse response) throws IOException
    {
    	LovStdPointListService lovStdPointListService = (LovStdPointListService)getBean("lovStdPointListService");
        
        LovStdPointListDTO lovStdPointListDTO = lovStdPointListForm.getLovStdPointListDTO();
        List resultList = lovStdPointListService.findStdPointList(lovStdPointListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovStdPointListForm.getListId());
    	
    }
 }