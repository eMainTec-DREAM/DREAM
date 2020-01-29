package dream.ass.base.action;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValDetailDTO;
import dream.ass.base.dto.AssBasePointValListDTO;
import dream.ass.base.form.AssBasePointValScriptForm;
import dream.ass.base.service.AssBasePointValDetailService;
import dream.ass.base.service.AssBasePointValScriptService;


/**
 * 메뉴 - 목록 action
 * @author  youngjoo38
 * @version $Id: AssBasePointValScriptAction.java,v 1.0 2017/11/06 16:00:21 youngjoo38 Exp $
 * @since   1.0
 * @struts:action path="/assBasePointValScript" name="assBasePointValScriptForm"
 *                input="/dream/ass/base/assBasePointValScript.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBasePointValScript" path="/dream/ass/base/assBasePointValScript.jsp"
 *                        redirect="false"
 */
public class AssBasePointValScriptAction extends AuthAction
{
    /** 조회 */
    public static final int DATA_LIST_FIND 		= 1001;
    /** 조회 */
    public static final int DATA_SCRIPT_FIND 	= 1003;
    /** 수정 */
    public static final int DATA_SCRIPT_UPDATE 	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssBasePointValScriptForm assBasePointValScriptForm = (AssBasePointValScriptForm) form;
        
        switch (assBasePointValScriptForm.getStrutsAction())
        {
            case AssBasePointValScriptAction.DATA_LIST_FIND:
                findMenuList(request, assBasePointValScriptForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssBasePointValScriptAction.BASE_SET_HEADER:
                setHeader(request, response, assBasePointValScriptForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssBasePointValScriptAction.DATA_SCRIPT_FIND:
            	findScript(request, assBasePointValScriptForm);
                returnActionForward = mapping.findForward("assBasePointValScript");
                break;
            case AssBasePointValScriptAction.DATA_SCRIPT_UPDATE:
            	updateScript(request, assBasePointValScriptForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AssBasePointValScriptAction.BASE_GRID_EXPORT:
            	findMenuList(request, assBasePointValScriptForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assBasePointValScript");
                break;
        }

        return returnActionForward;
    }

    private void findScript(HttpServletRequest request, AssBasePointValScriptForm assBasePointValScriptForm) {
		// TODO Auto-generated method stub
    	AssBasePointValScriptService assBasePointValScriptService = (AssBasePointValScriptService) getBean("assBasePointValScriptService");        

    	AssBasePointValDetailDTO assBasePointValDetailDTO = assBasePointValScriptForm.getAssBasePointValDetailDTO();
        // 조회된 상세 부분
    	AssBasePointValDetailDTO resultDTO = assBasePointValScriptService.findScript(assBasePointValDetailDTO);
        // 조회된 상세  셋팅한다.
        assBasePointValScriptForm.setAssBasePointValDetailDTO(resultDTO);
	}

	private void updateScript(HttpServletRequest request, AssBasePointValScriptForm assBasePointValScriptForm) 
    {
    	AssBasePointValScriptService assBasePointValScriptService = (AssBasePointValScriptService) getBean("assBasePointValScriptService");        

    	assBasePointValScriptService.updateScript(assBasePointValScriptForm.getAssBasePointValDetailDTO(), getUser(request));
        
        setAjaxStatus(request);
	}

    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, AssBasePointValScriptForm assBasePointValScriptForm) throws IOException
    {
        super.setHeader(request, response, assBasePointValScriptForm.getListId(),assBasePointValScriptForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: AssBasePointValScriptAction.java,v 1.0 2017/11/06 16:00:21 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param request
     * @param assBasePointValScriptForm
     * @throws Exception
     */
    private void findMenuList(HttpServletRequest request, AssBasePointValScriptForm assBasePointValScriptForm, HttpServletResponse response) throws Exception
    {
    	AssBasePointValDetailService assBasePointValDetailService = (AssBasePointValDetailService) getBean("assBasePointValDetailService");        

    	AssBaseCommonDTO assBaseCommonDTO = assBasePointValScriptForm.getAssBaseCommonDTO();
    	AssBasePointValDetailDTO assBasePointValDetailDTO = assBasePointValScriptForm.getAssBasePointValDetailDTO();
    	AssBasePointListDTO assBasePointListDTO = assBasePointValScriptForm.getAssBasePointListDTO();
    	AssBasePointValListDTO assBasePointValListDTO = assBasePointValScriptForm.getAssBasePointValListDTO();

    	assBasePointValDetailDTO = assBasePointValDetailService.findDetail(assBaseCommonDTO,assBasePointListDTO,assBasePointValListDTO,getUser(request));

    	assBasePointValScriptForm.setAssBasePointValDetailDTO(assBasePointValDetailDTO);
    }
    
}
