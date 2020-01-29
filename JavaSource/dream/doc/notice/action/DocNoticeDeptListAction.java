package dream.doc.notice.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;
import dream.doc.notice.form.DocNoticeDeptListForm;
import dream.doc.notice.service.DocNoticeDeptListService;

/**
 * DocNoticeDept Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/docNoticeDeptList" name="docNoticeDeptListForm"
 *                input="/dream/doc/notice/docNoticeDeptList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docNoticeDeptList" path="/dream/doc/notice/docNoticeDeptList.jsp"
 *                        redirect="false"
 */
public class DocNoticeDeptListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        DocNoticeDeptListForm docNoticeDeptListForm = (DocNoticeDeptListForm) form;
        
        switch (docNoticeDeptListForm.getStrutsAction())
        {
            case DocNoticeDeptListAction.BASE_SET_HEADER:
                setHeader(request, response, docNoticeDeptListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case DocNoticeDeptListAction.LIST_FIND:
                findList(request, response, docNoticeDeptListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case DocNoticeDeptListAction.LIST_DELETE:
                deleteList(request, docNoticeDeptListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case DocNoticeDeptListAction.BASE_GRID_EXPORT:
                findList(request, response, docNoticeDeptListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("docNoticeDeptList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, DocNoticeDeptListForm docNoticeDeptListForm) throws IOException
    {
        super.setHeader(request, response, docNoticeDeptListForm.getListId(), docNoticeDeptListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param docNoticeDeptListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, DocNoticeDeptListForm docNoticeDeptListForm, boolean excelExport) throws Exception
    {
        DocNoticeDeptListService docNoticeDeptListService = (DocNoticeDeptListService) getBean("docNoticeDeptListService");
        DocNoticeDeptListDTO docNoticeDeptListDTO = docNoticeDeptListForm.getDocNoticeDeptListDTO();
        DocNoticeCommonDTO docNoticeCommonDTO = docNoticeDeptListForm.getDocNoticeCommonDTO();
      
        //Paging
        docNoticeDeptListDTO.setIsLoadMaxCount("Y".equals(docNoticeDeptListForm.getIsLoadMaxCount())?true:false);
        docNoticeDeptListDTO.setFirstRow(docNoticeDeptListForm.getFirstRow());
        docNoticeDeptListDTO.setOrderBy(docNoticeDeptListForm.getOrderBy());
        docNoticeDeptListDTO.setDirection(docNoticeDeptListForm.getDirection());
        
        User user = getUser(request);
        List resultList = docNoticeDeptListService.findList(docNoticeCommonDTO, docNoticeDeptListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(docNoticeDeptListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = docNoticeDeptListService.findTotalCount(docNoticeCommonDTO,docNoticeDeptListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,docNoticeDeptListForm.getListId(),docNoticeDeptListForm.getCurrentPageId(), docNoticeDeptListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param docNoticeDeptListForm
     */
    private void deleteList(HttpServletRequest request, DocNoticeDeptListForm docNoticeDeptListForm) throws Exception
    {
        DocNoticeDeptListService docNoticeDeptListService = (DocNoticeDeptListService) getBean("docNoticeDeptListService");
        String[] deleteRows = docNoticeDeptListForm.getDeleteRows();
        
        User user = getUser(request);
        docNoticeDeptListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    
}