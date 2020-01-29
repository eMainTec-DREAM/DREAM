package dream.mgr.usrgrp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrgrp.dto.LovUsrGrpListDTO;
import dream.mgr.usrgrp.form.LovUsrGrpListForm;
import dream.mgr.usrgrp.service.LovUsrGrpListService;

/**
 * 메뉴 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovUsrGrpList" name="lovUsrGrpListForm"
 *                input="/dream/mgr/usrgrp/lovUsrGrpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovUsrGrpPopup" path="/dream/mgr/usrgrp/lovUsrGrpPopup.jsp"
 *                        redirect="false"
 */
public class LovUsrGrpListAction extends AuthAction
{
    public static final int LOV_USRGRP_DEFAULT 	= 1001;
    public static final int LOV_USRGRP_FIND     = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovUsrGrpListForm lovUsrGrpListForm = (LovUsrGrpListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovUsrGrpListForm.getStrutsAction())
        {
            case LovUsrGrpListAction.LOV_USRGRP_DEFAULT :
                returnActionForward = mapping.findForward("lovUsrGrpPopup");
                break;
            case LovUsrGrpListAction.LOV_USRGRP_FIND :
                findUsrGrpList(request, lovUsrGrpListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovUsrGrpListAction.BASE_SET_HEADER:
                setHeader(request, response, lovUsrGrpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovUsrGrpListForm lovUsrGrpListForm) throws IOException
    {
        super.setHeader(request, response, lovUsrGrpListForm.getListId(),lovUsrGrpListForm.getCurrentPageId()); 
    }

    /**
     * TAMENU 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovUsrGrpListForm
     */
    private void findUsrGrpList(HttpServletRequest request,
            LovUsrGrpListForm lovUsrGrpListForm,HttpServletResponse response) throws IOException
    {
        LovUsrGrpListService lovUsrGrpListService = (LovUsrGrpListService)getBean("lovUsrGrpListService");
        
        LovUsrGrpListDTO lovUsrGrpListDTO = lovUsrGrpListForm.getLovUsrGrpListDTO();
        List resultList = lovUsrGrpListService.findUsrGrpList(lovUsrGrpListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovUsrGrpListForm.getListId());
    	
    }

}