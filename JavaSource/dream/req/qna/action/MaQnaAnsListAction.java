package dream.req.qna.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.form.MaQnaAnsListForm;
import dream.req.qna.service.MaQnaAnsListService;

/**
 * 응답  목록
 * @author  kim21017
 * @version $Id: MaQnaAnsListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maQnaAnsList" name="maQnaAnsListForm"
 *                input="/dream/req/qna/maQnaAnsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maQnaAnsList" path="/dream/req/qna/maQnaAnsList.jsp"
 *                        redirect="false"
 */
public class MaQnaAnsListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_ANS_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int QNA_ANS_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaQnaAnsListForm maQnaAnsListForm = (MaQnaAnsListForm) form;
        
        super.updateAudit(maQnaAnsListForm.getMaQnaAnsListDTO().getAuditKey()==""?maQnaAnsListForm.getMaQnaAnsListDTO().getAuditKey():maQnaAnsListForm.getMaQnaAnsListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maQnaAnsListForm.getStrutsAction())
        {
            case MaQnaListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maQnaAnsListForm.getListId(), maQnaAnsListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaQnaAnsListAction.QNA_ANS_LIST_FIND:
                findAnsList(request, response, maQnaAnsListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaQnaAnsListAction.QNA_ANS_LIST_DELETE:
            	deleteAnsList(request,maQnaAnsListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaQnaAnsListAction.BASE_GRID_EXPORT:
            	findAnsList(request,response, maQnaAnsListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maQnaAnsList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaQnaAnsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maQnaAnsListForm
     * @throws Exception
     */
    private void findAnsList(HttpServletRequest request, HttpServletResponse response, MaQnaAnsListForm maQnaAnsListForm, boolean excelExport) throws Exception
    {
        MaQnaAnsListService maQnaAnsListService = (MaQnaAnsListService) getBean("maQnaAnsListService");
        MaQnaCommonDTO maQnaCommonDTO = maQnaAnsListForm.getMaQnaCommonDTO();
        maQnaCommonDTO.setCompNo(getUser(request).getCompNo());
        MaQnaAnsListDTO maQnaAnsListDTO = maQnaAnsListForm.getMaQnaAnsListDTO();
        List resultList = maQnaAnsListService.findAnsList(maQnaCommonDTO, maQnaAnsListDTO);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maQnaAnsListForm.getListId(),maQnaAnsListForm.getCurrentPageId(), maQnaAnsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, maQnaAnsListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaQnaAnsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maQnaAnsListForm
     * @throws Exception
     */
    private void deleteAnsList(HttpServletRequest request, MaQnaAnsListForm maQnaAnsListForm) throws Exception
    {
    	MaQnaAnsListService maQnaAnsListService = (MaQnaAnsListService) getBean("maQnaAnsListService");
        
    	maQnaAnsListService.deleteAnsList(maQnaAnsListForm.getDeleteRows(), maQnaAnsListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
}