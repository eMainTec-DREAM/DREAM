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
import dream.work.pm.std.dto.MaStdWoTypeListDTO;
import dream.work.pm.std.form.MaStdWoTypeListForm;
import dream.work.pm.std.service.MaStdWoTypeListService;

/**
 * ǥ���׸� ����Ʈ - ��� action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maStdWoTypeList" name="maStdWoTypeListForm"
 *                input="/dream/work/pm/std/maStdWoTypeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdWoTypeList" path="/dream/work/pm/std/maStdWoTypeList.jsp"
 *                        redirect="false"
 */
public class MaStdWoTypeListAction extends AuthAction
{
    /** ��ȸ */
    public static final int STD_LIST_FIND      = 1001;
    /** ���� */
    public static final int STD_LIST_DELETE    = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaStdWoTypeListForm maStdWoTypeListForm = (MaStdWoTypeListForm) form;
        
        switch (maStdWoTypeListForm.getStrutsAction())
        {
            case MaStdWoTypeListAction.BASE_SET_HEADER:
                setHeader(request, response, maStdWoTypeListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaStdWoTypeListAction.STD_LIST_FIND:
                findList(request, response, maStdWoTypeListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;               
            case MaStdWoTypeListAction.STD_LIST_DELETE:
                deleteList(request, maStdWoTypeListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaStdWoTypeListAction.BASE_GRID_EXPORT:
            	findList(request, response, maStdWoTypeListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maStdWoTypeList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaStdWoTypeListForm maStdWoTypeListForm) throws IOException
    {
        super.setHeader(request, response, maStdWoTypeListForm.getListId(), maStdWoTypeListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maStdWoTypeListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaStdWoTypeListForm maStdWoTypeListForm)  throws IOException
    {
    	MaStdWoTypeListService maStdWoTypeListService = (MaStdWoTypeListService) getBean("maStdWoTypeListService");        

    	MaStdPointCommonDTO maStdPointCommonDTO = maStdWoTypeListForm.getMaStdPointCommonDTO();
    	MaStdWoTypeListDTO maStdWoTypeListDTO = maStdWoTypeListForm.getMaStdWoTypeListDTO();
    	// �α� comp_no �� �����Ѵ�.
    	maStdPointCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maStdWoTypeListService.findStdPointList(maStdPointCommonDTO,maStdWoTypeListDTO, getUser(request));

     // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response, maStdWoTypeListForm.getListId());
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWoTypeListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaStdWoTypeListForm maStdWoTypeListForm) throws Exception
    {
        MaStdWoTypeListService maStdWoTypeListService = (MaStdWoTypeListService) getBean("maStdWoTypeListService");        

        String[] deleteRows = maStdWoTypeListForm.getDeleteRows();    // sheet ����
        
        maStdWoTypeListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
