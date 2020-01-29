package dream.pers.mamylink.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.struts.BaseAction;
import dream.main.service.MainService;
import dream.pers.mamylink.dto.MaMyLinkDTO;
import dream.pers.mamylink.form.MaMyLinkForm;
import dream.pers.mamylink.service.MaMyLinkService;

/**
 * ���ѱ׷� - ��� action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maMyLink" name="maMyLinkForm"
 *                input="/dream/pers/mamylink/maMyLink.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMyLink" path="/dream/pers/mamylink/maMyLink.jsp"
 *                        redirect="false"
 */
public class MaMyLinkAction extends AuthAction
{
    /** ��ȸ */
    public static final int USRGRP_AUTH_LIST_FIND = 1001;
    /** ���� */
    public static final int USRGRP_AUTH_LIST_DELETE = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaMyLinkForm maMyLinkForm = (MaMyLinkForm) form;
        
        switch (maMyLinkForm.getStrutsAction())
        {
            case MaMyLinkAction.BASE_SET_HEADER:
                setHeader(request, response, maMyLinkForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMyLinkAction.USRGRP_AUTH_LIST_FIND:
                findList(request, response, maMyLinkForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;        
            case MaMyLinkAction.USRGRP_AUTH_LIST_DELETE:
                deleteList(request, maMyLinkForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;               
            case MaMyLinkAction.BASE_GRID_EXPORT:
            	findList(request, response, maMyLinkForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maMyLink");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaMyLinkForm maMyLinkForm) throws IOException
    {
        super.setHeader(request, response, maMyLinkForm.getListId(), maMyLinkForm.getCurrentPageId()); 
    }    
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maMyLinkForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaMyLinkForm maMyLinkForm) throws Exception
    {
    	MaMyLinkService maMyLinkService = (MaMyLinkService) getBean("maMyLinkService");        

    	MaMyLinkDTO maMyLinkDTO = maMyLinkForm.getMaMyLinkDTO();
    	
    	// �α� comp_no �� �����Ѵ�.
    	maMyLinkDTO.setFilterCompNo((getUser(request).getCompNo()));
    	maMyLinkDTO.setUserId((getUser(request).getUserId()));
    	maMyLinkDTO.setUserLang((getUser(request).getLangId()));
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maMyLinkService.findUseGrpAuthList(maMyLinkDTO);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeTreeJsonResult(resultList, request, response, maMyLinkForm.getListId());
    }
        
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maMyLinkForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaMyLinkForm maMyLinkForm) throws Exception
    {
        MaMyLinkService maMyLinkService = (MaMyLinkService) getBean("maMyLinkService");        

        String[] menuIdArr   = maMyLinkForm.getDeleteRows();    // sheet ����
        String[] stateArr = maMyLinkForm.getDeleteRowsExt();
        
        MaMyLinkDTO maMyLinkDTO = maMyLinkForm.getMaMyLinkDTO();
        maMyLinkDTO.setCompNo(getUser(request).getCompNo());
        maMyLinkDTO.setUserId(getUser(request).getUserId());
        maMyLinkService.deleteList(maMyLinkDTO, menuIdArr,  stateArr);
        
        //������ ����ڸ޴� ���ε� �Ѵ�.
        MainService mainService = (MainService) BaseAction.ctx.getBean("mainService");
        User loginUser = getUser(request);
        String [][] userMenuList = mainService.findLinkedMenu(loginUser);
        HttpSession session = request.getSession();
        session.setAttribute("LINKEDMENU", userMenuList);
        setAjaxStatus(request);
    }
}
