package dream.pers.mamymenu.action;


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
import dream.pers.mamymenu.dto.MaMyMenuDTO;
import dream.pers.mamymenu.form.MaMyMenuForm;
import dream.pers.mamymenu.service.MaMyMenuService;

/**
 * ����ڸ޴� - ��� action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maMyMenu" name="maMyMenuForm"
 *                input="/dream/pers/mamymenu/maMyMenu.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMyMenu" path="/dream/pers/mamymenu/maMyMenu.jsp"
 *                        redirect="false"
 */
public class MaMyMenuAction extends AuthAction
{
    /** ��ȸ */
    public static final int USRGRP_AUTH_LIST_FIND = 1001;
    /** ���� */
    public static final int USRGRP_AUTH_LIST_DELETE = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaMyMenuForm maMyMenuForm = (MaMyMenuForm) form;
        
        switch (maMyMenuForm.getStrutsAction())
        {
            case MaMyMenuAction.BASE_SET_HEADER:
                setHeader(request, response, maMyMenuForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMyMenuAction.USRGRP_AUTH_LIST_FIND:
                findList(request, response, maMyMenuForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;        
            case MaMyMenuAction.USRGRP_AUTH_LIST_DELETE:
                deleteList(request, maMyMenuForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;               
            case MaMyMenuAction.BASE_GRID_EXPORT:
            	findList(request, response, maMyMenuForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maMyMenu");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaMyMenuForm maMyMenuForm) throws IOException
    {
        super.setHeader(request, response, maMyMenuForm.getListId(), maMyMenuForm.getCurrentPageId()); 
    }    
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maMyMenuForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaMyMenuForm maMyMenuForm) throws Exception
    {
    	MaMyMenuService maMyMenuService = (MaMyMenuService) getBean("maMyMenuService");        

    	MaMyMenuDTO maMyMenuDTO = maMyMenuForm.getMaMyMenuDTO();
    	
    	// �α� comp_no �� �����Ѵ�.
    	maMyMenuDTO.setFilterCompNo((getUser(request).getCompNo()));
    	maMyMenuDTO.setUserId((getUser(request).getUserId()));
    	maMyMenuDTO.setUserLang((getUser(request).getLangId()));
    	
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maMyMenuService.findUseGrpAuthList(maMyMenuDTO);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeTreeJsonResult(resultList, request, response, maMyMenuForm.getListId());
    }
        
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maMyMenuForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaMyMenuForm maMyMenuForm) throws Exception
    {
        MaMyMenuService maMyMenuService = (MaMyMenuService) getBean("maMyMenuService");        

        String[] menuIdArr   = maMyMenuForm.getDeleteRows();    // sheet ����
        String[] stateArr = maMyMenuForm.getDeleteRowsExt();
        
        MaMyMenuDTO maMyMenuDTO = maMyMenuForm.getMaMyMenuDTO();
        maMyMenuDTO.setCompNo(getUser(request).getCompNo());
        maMyMenuDTO.setUserId(getUser(request).getUserId());
        maMyMenuService.deleteList(maMyMenuDTO, menuIdArr,  stateArr);
        
        //������ ����ڸ޴� ���ε� �Ѵ�.
        MainService mainService = (MainService) BaseAction.ctx.getBean("mainService");
        User loginUser = getUser(request);
        List userMenuList = mainService.findUserMenuList(loginUser);
        HttpSession session = request.getSession();
        session.setAttribute("USERMENU", userMenuList);
        setAjaxStatus(request);
    }
}
