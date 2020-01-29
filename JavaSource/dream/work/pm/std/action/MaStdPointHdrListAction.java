package dream.work.pm.std.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.form.MaStdPointHdrListForm;
import dream.work.pm.std.service.MaStdPointHdrListService;

/**
 * 표준항목 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maStdPointHdrList" name="maStdPointHdrListForm"
 *                input="/dream/work/pm/std/maStdPointHdrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdPointHdrList" path="/dream/work/pm/std/maStdPointHdrList.jsp"
 *                        redirect="false"
 */
public class MaStdPointHdrListAction extends AuthAction
{
    /** 조회 */
    public static final int STD_HDR_LIST_FIND      = 1001;
    /** 삭제 */
    public static final int STD_HDR_LIST_DELETE    = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaStdPointHdrListForm maStdPointHdrListForm = (MaStdPointHdrListForm) form;
        
        switch (maStdPointHdrListForm.getStrutsAction())
        {
            case MaStdPointHdrListAction.BASE_SET_HEADER:
                setHeader(request, response, maStdPointHdrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaStdPointHdrListAction.STD_HDR_LIST_FIND:
                findList(request, response, maStdPointHdrListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;               
            case MaStdPointHdrListAction.STD_HDR_LIST_DELETE:
                deleteList(request, maStdPointHdrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaStdPointHdrListAction.BASE_GRID_EXPORT:
            	findList(request, response, maStdPointHdrListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maStdPointHdrList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaStdPointHdrListForm maStdPointHdrListForm) throws IOException
    {
        super.setHeader(request, response, maStdPointHdrListForm.getListId(), maStdPointHdrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maStdPointHdrListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaStdPointHdrListForm maStdPointHdrListForm, boolean excelExport)  throws Exception
    {
    	MaStdPointHdrListService maStdPointHdrListService = (MaStdPointHdrListService) getBean("maStdPointHdrListService");        

    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPointHdrListForm.getMaStdPointCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	maStdPointCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        //Paging
        maStdPointCommonDTO.setIsLoadMaxCount("Y".equals(maStdPointHdrListForm.getIsLoadMaxCount())?true:false);
        maStdPointCommonDTO.setFirstRow(maStdPointHdrListForm.getFirstRow());
        maStdPointCommonDTO.setOrderBy(maStdPointHdrListForm.getOrderBy());
        maStdPointCommonDTO.setDirection(maStdPointHdrListForm.getDirection());

        User user = getUser(request);
        
        //리스트를 조회한다.
        List resultList = maStdPointHdrListService.findStdPointHdrList(maStdPointCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maStdPointHdrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maStdPointHdrListService.findTotalCount(maStdPointCommonDTO,user);
        
        // 조회한 List 를 form에 셋팅한다.
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maStdPointHdrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointHdrListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaStdPointHdrListForm maStdPointHdrListForm) throws Exception
    {
        MaStdPointHdrListService maStdPointHdrListService = (MaStdPointHdrListService) getBean("maStdPointHdrListService");        

        String[] deleteRows = maStdPointHdrListForm.getDeleteRows();    // sheet 내역
        
        maStdPointHdrListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
