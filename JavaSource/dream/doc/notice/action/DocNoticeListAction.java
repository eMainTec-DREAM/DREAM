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
import dream.doc.notice.form.DocNoticeListForm;
import dream.doc.notice.service.DocNoticeListService;

/**
 * DocNotice Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/docNoticeList" name="docNoticeListForm"
 *                input="/dream/doc/notice/docNoticeList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/docNoticeCheckList" name="docNoticeListForm"
 *                input="/dream/doc/notice/docNoticeCheckList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docNoticeList" path="/dream/doc/notice/docNoticeList.jsp"
 *                        redirect="false"
 */
public class DocNoticeListAction extends AuthAction
{
    /** 조회하기 */
    public static final int LIST_FIND       = 1001;
    /** 삭제하기 */
    public static final int LIST_DELETE     = 7002;
    /** 조회하기(확인용) */
    public static final int LIST_CHECK_FIND = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        DocNoticeListForm docNoticeListForm = (DocNoticeListForm) form;
        
        switch (docNoticeListForm.getStrutsAction())
        {
            case DocNoticeListAction.BASE_SET_HEADER:
                setHeader(request, response, docNoticeListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case DocNoticeListAction.LIST_FIND:
                findList(request, response, docNoticeListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case DocNoticeListAction.LIST_CHECK_FIND:
            	findCheckList(request, response, docNoticeListForm,false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;    
            case DocNoticeListAction.LIST_DELETE:
                deleteList(request, docNoticeListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case DocNoticeListAction.BASE_GRID_EXPORT:
                findList(request, response, docNoticeListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, DocNoticeListForm docNoticeListForm) throws IOException
    {
        super.setHeader(request, response, docNoticeListForm.getListId(), docNoticeListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param docNoticeListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, DocNoticeListForm docNoticeListForm, boolean excelExport) throws Exception
    {
        DocNoticeListService docNoticeListService = (DocNoticeListService) getBean("docNoticeListService",request);
        DocNoticeCommonDTO docNoticeCommonDTO = docNoticeListForm.getDocNoticeCommonDTO();
      
        //Paging
        docNoticeCommonDTO.setIsLoadMaxCount("Y".equals(docNoticeListForm.getIsLoadMaxCount())?true:false);
        docNoticeCommonDTO.setFirstRow(docNoticeListForm.getFirstRow());
        docNoticeCommonDTO.setOrderBy(docNoticeListForm.getOrderBy());
        docNoticeCommonDTO.setDirection(docNoticeListForm.getDirection());
        
        User user = getUser(request);
        List resultList = docNoticeListService.findList(docNoticeCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(docNoticeListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = docNoticeListService.findTotalCount(docNoticeCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,docNoticeListForm.getListId(),docNoticeListForm.getCurrentPageId(), docNoticeListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    private void findCheckList(HttpServletRequest request, HttpServletResponse response, DocNoticeListForm docNoticeListForm, boolean excelExport) throws Exception
    {
    	DocNoticeListService docNoticeListService = (DocNoticeListService) getBean("docNoticeListService",request);
    	DocNoticeCommonDTO docNoticeCommonDTO = docNoticeListForm.getDocNoticeCommonDTO();
    	
    	//Paging
    	docNoticeCommonDTO.setIsLoadMaxCount("Y".equals(docNoticeListForm.getIsLoadMaxCount())?true:false);
    	docNoticeCommonDTO.setFirstRow(docNoticeListForm.getFirstRow());
    	docNoticeCommonDTO.setOrderBy(docNoticeListForm.getOrderBy());
    	docNoticeCommonDTO.setDirection(docNoticeListForm.getDirection());
    	
    	User user = getUser(request);
    	List resultList = docNoticeListService.findCheckList(docNoticeCommonDTO, user);
    	
    	//Paging
    	String totalCount = "";
    	
    	if(Integer.parseInt(docNoticeListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = docNoticeListService.findCheckTotalCount(docNoticeCommonDTO,user);
    	
    	if(excelExport) super.makeGridExport(resultList, request, response,docNoticeListForm.getListId(),docNoticeListForm.getCurrentPageId(), docNoticeListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param docNoticeListForm
     */
    private void deleteList(HttpServletRequest request, DocNoticeListForm docNoticeListForm) throws Exception
    {
        DocNoticeListService docNoticeListService = (DocNoticeListService) getBean("docNoticeListService");
        String[] deleteRows = docNoticeListForm.getDeleteRows();
        
        User user = getUser(request);
        docNoticeListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    
}