package dream.doc.ctg.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.JsonUtil;
import dream.doc.ctg.dto.DocCtgCommonDTO;
import dream.doc.ctg.form.DocCtgListForm;
import dream.doc.ctg.service.DocCtgListService;

/**
 * 문서분류체계 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/docCtgList" name="docCtgListForm"
 *                input="/dream/doc/ctg/docCtgList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docCtgList" path="/dream/doc/ctg/docCtgList.jsp"
 *                        redirect="false"
 */
public class DocCtgListAction extends AuthAction
{
    /** 조회 */
    public static final int DOCCTG_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int DOCCTG_LIST_DELETE  = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        DocCtgListForm docCtgListForm = (DocCtgListForm) form;
        
        super.updateAudit(docCtgListForm.getDocCtgCommonDTO().getAuditKey()==""?docCtgListForm.getDocCtgCommonDTO().getAuditKey():docCtgListForm.getDocCtgCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        
        switch (docCtgListForm.getStrutsAction())
        {
            case DocCtgListAction.DOCCTG_LIST_FIND:
            	findPtWhList(request, docCtgListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case DocCtgListAction.BASE_GRID_EXPORT:
            	findPtWhList(request, docCtgListForm, response,true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case DocCtgListAction.BASE_SET_HEADER:
                setHeader(request, response, docCtgListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case DocCtgListAction.DOCCTG_LIST_DELETE:
            	deletePtWh(request, docCtgListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("docCtgList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, DocCtgListForm docCtgListForm) throws IOException
    {
        super.setHeader(request, response, docCtgListForm.getListId(), docCtgListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param docCtgListForm
     * @throws Exception
     */
    private void findPtWhList(HttpServletRequest request, DocCtgListForm docCtgListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	DocCtgListService docCtgListService = (DocCtgListService) getBean("docCtgListService");        

    	DocCtgCommonDTO docCtgCommonDTO = docCtgListForm.getDocCtgCommonDTO();
    	docCtgCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        //리스트를 조회한다.
        List resultList = JsonUtil.convertorTreeMap(docCtgListService.findList(docCtgCommonDTO, getUser(request)),"0", "docctgId", "pDocctgId", "ORDNO");
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(docCtgListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = docCtgListService.findTotalCount(docCtgCommonDTO, getUser(request));

        if(excelExport) super.makeTreeGridExport(resultList, request, response, docCtgListForm.getListId(),docCtgListForm.getCurrentPageId(), docCtgListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgListForm
     * @param request
     */
    private void deletePtWh(HttpServletRequest request, DocCtgListForm docCtgListForm) throws Exception
    {
    	DocCtgListService docCtgListService = (DocCtgListService) getBean("docCtgListService");
    	String[] deleteRows = docCtgListForm.getDeleteRows();    // sheet 내역
        
        docCtgListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
