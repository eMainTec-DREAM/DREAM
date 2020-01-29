package dream.doc.manual.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.doc.manual.dto.DocManualCommonDTO;
import dream.doc.manual.form.DocManualListForm;
import dream.doc.manual.service.DocManualListService;

/**
 * 사용자매뉴얼 - 목록 action
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/docManualList" name="docManualListForm"
 *                input="/dream/doc/manual/docManualList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docManualList" path="/dream/doc/manual/docManualList.jsp"
 *                        redirect="false"
 */
public class DocManualListAction extends AuthAction
{
    /** 조회 */
    public static final int HELP_LIST_FIND 	    = 1001;
    /** 삭제 */
    public static final int HELP_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        DocManualListForm docManualListForm = (DocManualListForm) form;
        
        switch (docManualListForm.getStrutsAction())
        {
            case DocManualListAction.HELP_LIST_FIND:
            	findHelpList(request, docManualListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case DocManualListAction.BASE_SET_HEADER:
                setHeader(request, response, docManualListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case DocManualListAction.HELP_LIST_DELETE:
            	deleteHelp(request, docManualListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case DocManualListAction.BASE_GRID_EXPORT:
            	findHelpList(request, docManualListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, DocManualListForm docManualListForm) throws IOException
    {
        super.setHeader(request, response, docManualListForm.getListId(), docManualListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param docManualListForm
     * @throws Exception
     */
    private void findHelpList(HttpServletRequest request, DocManualListForm docManualListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	DocManualListService docManualListService = (DocManualListService) getBean("docManualListService");        

    	DocManualCommonDTO docManualCommonDTO = docManualListForm.getDocManualCommonDTO();
    	
        //Paging
        docManualCommonDTO.setIsLoadMaxCount("Y".equals(docManualListForm.getIsLoadMaxCount())?true:false);
        docManualCommonDTO.setFirstRow(docManualListForm.getFirstRow());
        docManualCommonDTO.setOrderBy(docManualListForm.getOrderBy());
        docManualCommonDTO.setDirection(docManualListForm.getDirection());
        
        List resultList = docManualListService.findHelpList(docManualCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(docManualListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = docManualListService.findTotalCount(docManualCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,docManualListForm.getListId(),docManualListForm.getCurrentPageId(), docManualListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualListForm
     * @param request
     */
    private void deleteHelp(HttpServletRequest request, DocManualListForm docManualListForm) throws Exception
    {
    	DocManualListService docManualListService = (DocManualListService) getBean("docManualListService");
        
    	String[] deleteRows = docManualListForm.getDeleteRows();    // sheet 내역
        
        docManualListService.deleteHelp(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
