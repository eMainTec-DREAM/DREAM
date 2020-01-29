package dream.work.pm.std.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;
import dream.work.pm.std.form.MaStdPartListForm;
import dream.work.pm.std.service.MaStdPartListService;

/**
 * ǥ���׸� ����Ʈ - ��� action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maStdPartList" name="maStdPartListForm"
 *                input="/dream/work/pm/std/maStdPartList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdPartList" path="/dream/work/pm/std/maStdPartList.jsp"
 *                        redirect="false"
 */
public class MaStdPartListAction extends AuthAction
{
    /** ��ȸ */
    public static final int STD_LIST_FIND      = 1001;
    /** ���� */
    public static final int STD_LIST_DELETE    = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaStdPartListForm maStdPartListForm = (MaStdPartListForm) form;
        
        switch (maStdPartListForm.getStrutsAction())
        {
            case MaStdPartListAction.BASE_SET_HEADER:
                setHeader(request, response, maStdPartListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaStdPartListAction.STD_LIST_FIND:
                findList(request, response, maStdPartListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;               
            case MaStdPartListAction.STD_LIST_DELETE:
                deleteList(request, maStdPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaStdPartListAction.BASE_GRID_EXPORT:
            	findList(request, response, maStdPartListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maStdPartList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaStdPartListForm maStdPartListForm) throws IOException
    {
        super.setHeader(request, response, maStdPartListForm.getListId(), maStdPartListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maStdPartListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaStdPartListForm maStdPartListForm)  throws IOException
    {
    	MaStdPartListService maStdPartListService = (MaStdPartListService) getBean("maStdPartListService");        

    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPartListForm.getMaStdPointCommonDTO();
    	MaStdPartListDTO maStdPartListDTO = maStdPartListForm.getMaStdPartListDTO();
    	// �α� comp_no �� �����Ѵ�.
    	maStdPointCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maStdPartListService.findStdPointList(maStdPointCommonDTO,maStdPartListDTO, getUser(request));

     // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response, maStdPartListForm.getListId());
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPartListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaStdPartListForm maStdPartListForm) throws Exception
    {
        MaStdPartListService maStdPartListService = (MaStdPartListService) getBean("maStdPartListService");        

        String[] deleteRows = maStdPartListForm.getDeleteRows();    // sheet ����
        
        maStdPartListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
