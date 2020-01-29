package dream.org.wrkgrp.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.wrkgrp.dto.MaWkCtrCommonDTO;
import dream.org.wrkgrp.form.MaWkCtrListForm;
import dream.org.wrkgrp.service.MaWkCtrListService;

/**
 * 작업그룹 - 목록 action
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maWkCtrList" name="maWkCtrListForm"
 *                input="/dream/org/wrkgrp/maWkCtrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWkCtrList" path="/dream/org/wrkgrp/maWkCtrList.jsp"
 *                        redirect="false"
 */
public class MaWkCtrListAction extends AuthAction
{
    /** 조회 */
    public static final int WKCTR_LIST_FIND   = 1001;
    /** 삭제 */
    public static final int WKCTR_LIST_DELETE = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWkCtrListForm maWkCtrListForm = (MaWkCtrListForm) form;
        
        switch (maWkCtrListForm.getStrutsAction())
        {
            case MaWkCtrListAction.BASE_SET_HEADER:
                setHeader(request, response, maWkCtrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWkCtrListAction.WKCTR_LIST_FIND:
                findList(request, response, maWkCtrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaWkCtrListAction.WKCTR_LIST_DELETE:
            	deleteList(request, maWkCtrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MaWkCtrListAction.BASE_GRID_EXPORT:
            	findList(request, response, maWkCtrListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maWkCtrList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWkCtrListForm maWkCtrListForm) throws IOException
    {
        super.setHeader(request, response, maWkCtrListForm.getListId(), maWkCtrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maWkCtrListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaWkCtrListForm maWkCtrListForm) throws IOException
    {
    	MaWkCtrListService maWkCtrListService = (MaWkCtrListService) getBean("maWkCtrListService");        

    	MaWkCtrCommonDTO maWkCtrCommonDTO = maWkCtrListForm.getMaWkCtrCommonDTO();
    	maWkCtrCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maWkCtrListService.findWkCtrList(maWkCtrCommonDTO,getUser(request));
        super.makeTreeJsonResult(resultList, request, response, maWkCtrListForm.getListId());
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaWkCtrListForm maWkCtrListForm) throws Exception
    {
    	MaWkCtrListService maWkCtrListService = (MaWkCtrListService) getBean("maWkCtrListService");        

        String[] deleteRows = maWkCtrListForm.getDeleteRows();    // sheet 내역
        
        maWkCtrListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
