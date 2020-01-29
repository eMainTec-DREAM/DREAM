package dream.req.qna.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.form.MaQnaListForm;
import dream.req.qna.service.MaQnaListService;

/**
 * 질의 - 목록 action
 * @author  kim21017
 * @version $Id: MaQnaListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maQnaList" name="maQnaListForm"
 *                input="/dream/req/qna/maQnaList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maQnaList" path="/dream/req/qna/maQnaList.jsp"
 *                        redirect="false"
 */
public class MaQnaListAction extends AuthAction
{
    /** 조회 */
    public static final int QNA_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int QNA_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaQnaListForm maQnaListForm = (MaQnaListForm) form;
        
        super.updateAudit(maQnaListForm.getMaQnaCommonDTO().getAuditKey()==""?maQnaListForm.getMaQnaCommonDTO().getAuditKey():maQnaListForm.getMaQnaCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));        
        
        switch (maQnaListForm.getStrutsAction())
        {
            case MaQnaListAction.QNA_LIST_FIND:
                findQnaList(request, maQnaListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaQnaListAction.BASE_SET_HEADER:
                setHeader(request, response, maQnaListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaQnaListAction.QNA_LIST_DELETE:
                deleteQna(request, maQnaListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaQnaListAction.BASE_GRID_EXPORT:
            	findQnaList(request, maQnaListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maQnaList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaQnaListForm maQnaListForm) throws IOException
    {
        super.setHeader(request, response, maQnaListForm.getListId(), maQnaListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaQnaListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maQnaListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findQnaList(HttpServletRequest request, MaQnaListForm maQnaListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	MaQnaListService maQnaListService = (MaQnaListService) getBean("maQnaListService");        

    	MaQnaCommonDTO maQnaCommonDTO = maQnaListForm.getMaQnaCommonDTO();
    	maQnaCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maQnaCommonDTO.setIsLoadMaxCount("Y".equals(maQnaListForm.getIsLoadMaxCount())?true:false);
    	maQnaCommonDTO.setFirstRow(maQnaListForm.getFirstRow());
    	maQnaCommonDTO.setOrderBy(maQnaListForm.getOrderBy());
    	maQnaCommonDTO.setDirection(maQnaListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maQnaListService.findQnaList(maQnaCommonDTO);
 
        //Paging
    	String totalCount = "";
    	        
    	if(Integer.parseInt(maQnaListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maQnaListService.findTotalCount(maQnaCommonDTO,getUser(request));
    	        
    	if(excelExport) super.makeGridExport(resultList, request, response, maQnaListForm.getListId(),maQnaListForm.getCurrentPageId(), maQnaListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaQnaListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaListForm
     * @param request
     */
    private void deleteQna(HttpServletRequest request, MaQnaListForm maQnaListForm) throws Exception
    {
    	MaQnaListService maQnaListService = (MaQnaListService) getBean("maQnaListService");        

    	String[] deleteRows = maQnaListForm.getDeleteRows();    // sheet 내역
        
    	maQnaListService.deleteQna(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
