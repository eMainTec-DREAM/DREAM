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
import dream.work.pm.std.dto.MaStdWorkListDTO;
import dream.work.pm.std.form.MaStdWorkListForm;
import dream.work.pm.std.service.MaStdWorkListService;

/**
 * 표준항목 리스트 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maStdWorkList" name="maStdWorkListForm"
 *                input="/dream/work/pm/std/maStdWorkList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdWorkList" path="/dream/work/pm/std/maStdWorkList.jsp"
 *                        redirect="false"
 */
public class MaStdWorkListAction extends AuthAction
{
    /** 조회 */
    public static final int STD_LIST_FIND      = 1001;
    /** 삭제 */
    public static final int STD_LIST_DELETE    = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaStdWorkListForm maStdWorkListForm = (MaStdWorkListForm) form;
        
        switch (maStdWorkListForm.getStrutsAction())
        {
            case MaStdWorkListAction.BASE_SET_HEADER:
                setHeader(request, response, maStdWorkListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaStdWorkListAction.STD_LIST_FIND:
                findList(request, response, maStdWorkListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;               
            case MaStdWorkListAction.STD_LIST_DELETE:
                deleteList(request, maStdWorkListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaStdWorkListAction.BASE_GRID_EXPORT:
            	findList(request, response, maStdWorkListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maStdWorkList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaStdWorkListForm maStdWorkListForm) throws IOException
    {
        super.setHeader(request, response, maStdWorkListForm.getListId(), maStdWorkListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maStdWorkListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaStdWorkListForm maStdWorkListForm)  throws IOException
    {
    	MaStdWorkListService maStdWorkListService = (MaStdWorkListService) getBean("maStdWorkListService");        

    	MaStdPointCommonDTO maStdPointCommonDTO = maStdWorkListForm.getMaStdPointCommonDTO();
    	MaStdWorkListDTO maStdWorkListDTO = maStdWorkListForm.getMaStdWorkListDTO();
    	// 로긴 comp_no 를 셋팅한다.
    	maStdPointCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        //리스트를 조회한다.
        List resultList = maStdWorkListService.findStdPointList(maStdPointCommonDTO,maStdWorkListDTO, getUser(request));

     // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response, maStdWorkListForm.getListId());
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWorkListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaStdWorkListForm maStdWorkListForm) throws Exception
    {
        MaStdWorkListService maStdWorkListService = (MaStdWorkListService) getBean("maStdWorkListService");        

        String[] deleteRows = maStdWorkListForm.getDeleteRows();    // sheet 내역
        
        maStdWorkListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
