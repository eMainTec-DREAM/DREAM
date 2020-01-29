package dream.pers.appln.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;
import dream.pers.appln.form.MaAppLineUsrListForm;
import dream.pers.appln.service.MaAppLineUsrListService;

/**
 * 응답  목록
 * @author  kim21017
 * @version $Id: MaAppLineUsrListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maAppLineUsrList" name="maAppLineUsrListForm"
 *                input="/dream/pers/appln/maAppLineUsrList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maAppLineUsrPopupList" name="maAppLineUsrListForm"
 *                input="/dream/pers/appln/maAppLineUsrPopupList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maAppLineUsrList" path="/dream/pers/appln/maAppLineUsrList.jsp"
 *                        redirect="false"
 */
public class MaAppLineUsrListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_ANS_LIST_FIND 			= 8001;
    /** 삭제 */
    public static final int QNA_ANS_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaAppLineUsrListForm maAppLineUsrListForm = (MaAppLineUsrListForm) form;
        
        super.updateAudit(maAppLineUsrListForm.getMaAppLineUsrListDTO().getAuditKey()==""?maAppLineUsrListForm.getMaAppLineUsrListDTO().getAuditKey():maAppLineUsrListForm.getMaAppLineUsrListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maAppLineUsrListForm.getStrutsAction())
        {
            case MaAppLineListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maAppLineUsrListForm.getListId(), maAppLineUsrListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaAppLineUsrListAction.QNA_ANS_LIST_FIND:
                findAnsList(request, response, maAppLineUsrListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaAppLineUsrListAction.QNA_ANS_LIST_DELETE:
            	deleteAnsList(request,maAppLineUsrListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaAppLineUsrListAction.BASE_GRID_EXPORT:
            	findAnsList(request,response, maAppLineUsrListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaAppLineUsrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maAppLineUsrListForm
     * @throws Exception
     */
    private void findAnsList(HttpServletRequest request, HttpServletResponse response, MaAppLineUsrListForm maAppLineUsrListForm, boolean excelExport) throws Exception
    {
        MaAppLineUsrListService maAppLineUsrListService = (MaAppLineUsrListService) getBean("maAppLineUsrListService");
        MaAppLineCommonDTO maAppLineCommonDTO = maAppLineUsrListForm.getMaAppLineCommonDTO();
        maAppLineCommonDTO.setCompNo(getUser(request).getCompNo());
        maAppLineCommonDTO.setUserLang(getUser(request).getLangId());
        MaAppLineUsrListDTO maAppLineUsrListDTO = maAppLineUsrListForm.getMaAppLineUsrListDTO();
        
        //Paging
        maAppLineUsrListDTO.setIsLoadMaxCount("Y".equals(maAppLineUsrListForm.getIsLoadMaxCount())?true:false);
        maAppLineUsrListDTO.setFirstRow(maAppLineUsrListForm.getFirstRow());
        maAppLineUsrListDTO.setOrderBy(maAppLineUsrListForm.getOrderBy());
        maAppLineUsrListDTO.setDirection(maAppLineUsrListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maAppLineUsrListService.findAnsList(maAppLineCommonDTO, maAppLineUsrListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maAppLineUsrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maAppLineUsrListService.findTotalCount(maAppLineCommonDTO, maAppLineUsrListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maAppLineUsrListForm.getListId(),maAppLineUsrListForm.getCurrentPageId(), maAppLineUsrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaAppLineUsrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maAppLineUsrListForm
     * @throws Exception
     */
    private void deleteAnsList(HttpServletRequest request, MaAppLineUsrListForm maAppLineUsrListForm) throws Exception
    {
    	MaAppLineUsrListService maAppLineUsrListService = (MaAppLineUsrListService) getBean("maAppLineUsrListService");
        
    	maAppLineUsrListService.deleteAnsList(maAppLineUsrListForm.getDeleteRows(), maAppLineUsrListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
}