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
import dream.doc.notice.dto.DocNoticeTargetListDTO;
import dream.doc.notice.form.DocNoticeTargetListForm;
import dream.doc.notice.service.DocNoticeTargetListService;

/**
 * DocNoticeTarget Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/docNoticeTargetList" name="docNoticeTargetListForm"
 *                input="/dream/doc/notice/docNoticeTargetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docNoticeTargetList" path="/dream/doc/notice/docNoticeTargetList.jsp"
 *                        redirect="false"
 */
public class DocNoticeTargetListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        DocNoticeTargetListForm docNoticeTargetListForm = (DocNoticeTargetListForm) form;
        
        switch (docNoticeTargetListForm.getStrutsAction())
        {
            case DocNoticeTargetListAction.BASE_SET_HEADER:
                setHeader(request, response, docNoticeTargetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case DocNoticeTargetListAction.LIST_FIND:
                findList(request, response, docNoticeTargetListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case DocNoticeTargetListAction.LIST_DELETE:
                deleteList(request, docNoticeTargetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case DocNoticeTargetListAction.BASE_GRID_EXPORT:
                findList(request, response, docNoticeTargetListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("docNoticeTargetList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, DocNoticeTargetListForm docNoticeTargetListForm) throws IOException
    {
        super.setHeader(request, response, docNoticeTargetListForm.getListId(), docNoticeTargetListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param docNoticeTargetListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, DocNoticeTargetListForm docNoticeTargetListForm, boolean excelExport) throws Exception
    {
        DocNoticeTargetListService docNoticeTargetListService = (DocNoticeTargetListService) getBean("docNoticeTargetListService");
        DocNoticeTargetListDTO docNoticeTargetListDTO = docNoticeTargetListForm.getDocNoticeTargetListDTO();
        DocNoticeCommonDTO docNoticeCommonDTO = docNoticeTargetListForm.getDocNoticeCommonDTO();
      
        //Paging
        docNoticeTargetListDTO.setIsLoadMaxCount("Y".equals(docNoticeTargetListForm.getIsLoadMaxCount())?true:false);
        docNoticeTargetListDTO.setFirstRow(docNoticeTargetListForm.getFirstRow());
        docNoticeTargetListDTO.setOrderBy(docNoticeTargetListForm.getOrderBy());
        docNoticeTargetListDTO.setDirection(docNoticeTargetListForm.getDirection());
        
        User user = getUser(request);
        List resultList = docNoticeTargetListService.findList(docNoticeCommonDTO, docNoticeTargetListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(docNoticeTargetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = docNoticeTargetListService.findTotalCount(docNoticeCommonDTO,docNoticeTargetListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,docNoticeTargetListForm.getListId(),docNoticeTargetListForm.getCurrentPageId(), docNoticeTargetListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param docNoticeTargetListForm
     */
    private void deleteList(HttpServletRequest request, DocNoticeTargetListForm docNoticeTargetListForm) throws Exception
    {
        DocNoticeTargetListService docNoticeTargetListService = (DocNoticeTargetListService) getBean("docNoticeTargetListService");
        String[] deleteRows = docNoticeTargetListForm.getDeleteRows();
        
        User user = getUser(request);
        docNoticeTargetListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    
}