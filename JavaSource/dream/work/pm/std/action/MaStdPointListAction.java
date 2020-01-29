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
import dream.work.pm.std.dto.MaStdPointListDTO;
import dream.work.pm.std.form.MaStdPointListForm;
import dream.work.pm.std.service.MaStdPointListService;

/**
 * 표준항목 리스트 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maStdPointList" name="maStdPointListForm"
 *                input="/dream/work/pm/std/maStdPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdPointList" path="/dream/work/pm/std/maStdPointList.jsp"
 *                        redirect="false"
 */
public class MaStdPointListAction extends AuthAction
{
    /** 조회 */
    public static final int STD_LIST_FIND      = 8001;
    /** 삭제 */
    public static final int STD_LIST_DELETE    = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaStdPointListForm maStdPointListForm = (MaStdPointListForm) form;
        
        switch (maStdPointListForm.getStrutsAction())
        {
            case MaStdPointListAction.BASE_SET_HEADER:
                setHeader(request, response, maStdPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaStdPointListAction.STD_LIST_FIND:
                findList(request, response, maStdPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;               
            case MaStdPointListAction.STD_LIST_DELETE:
                deleteList(request, maStdPointListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaStdPointListAction.BASE_GRID_EXPORT:
            	findList(request, response, maStdPointListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maStdPointList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaStdPointListForm maStdPointListForm) throws IOException
    {
        super.setHeader(request, response, maStdPointListForm.getListId(), maStdPointListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maStdPointListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaStdPointListForm maStdPointListForm)  throws IOException
    {
    	MaStdPointListService maStdPointListService = (MaStdPointListService) getBean("maStdPointListService");        

    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPointListForm.getMaStdPointCommonDTO();
    	MaStdPointListDTO maStdPointListDTO = maStdPointListForm.getMaStdPointListDTO();
    	// 로긴 comp_no 를 셋팅한다.
    	maStdPointCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        //리스트를 조회한다.
        List resultList = maStdPointListService.findStdPointList(maStdPointCommonDTO,maStdPointListDTO, getUser(request));

     // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response, maStdPointListForm.getListId());
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaStdPointListForm maStdPointListForm) throws Exception
    {
        MaStdPointListService maStdPointListService = (MaStdPointListService) getBean("maStdPointListService");        

        String[] deleteRows = maStdPointListForm.getDeleteRows();    // sheet 내역
        
        maStdPointListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
