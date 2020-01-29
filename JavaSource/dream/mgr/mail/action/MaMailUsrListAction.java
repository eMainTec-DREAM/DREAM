package dream.mgr.mail.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;
import dream.mgr.mail.form.MaMailUsrListForm;
import dream.mgr.mail.service.MaMailUsrListService;

/**
 * 메일수신자설정- 수신자 목록
 * @author  kim21017
 * @version $Id: MaMailUsrListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maMailUsrList" name="maMailUsrListForm"
 *                input="/dream/mgr/mail/maMailUsrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMailUsrList" path="/dream/mgr/mail/maMailUsrList.jsp"
 *                        redirect="false"
 */
public class MaMailUsrListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MAIL_USR_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int MAIL_USR_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaMailUsrListForm maMailUsrListForm = (MaMailUsrListForm) form;
        
        switch (maMailUsrListForm.getStrutsAction())
        {
            case MaMailListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maMailUsrListForm.getListId(), maMailUsrListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMailUsrListAction.MAIL_USR_LIST_FIND:
                findUsrList(request, response, maMailUsrListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMailUsrListAction.MAIL_USR_LIST_DELETE:
            	deleteUsrList(request,maMailUsrListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaMailUsrListAction.BASE_GRID_EXPORT:
            	findUsrList(request,response, maMailUsrListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maMailUsrList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaMailUsrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMailUsrListForm
     * @throws Exception
     */
    private void findUsrList(HttpServletRequest request, HttpServletResponse response, MaMailUsrListForm maMailUsrListForm, boolean excelExport) throws Exception
    {
        MaMailUsrListService maMailUsrListService = (MaMailUsrListService) getBean("maMailUsrListService");
        MaMailCommonDTO maMailCommonDTO = maMailUsrListForm.getMaMailCommonDTO();
        MaMailUsrListDTO maMailUsrListDTO = maMailUsrListForm.getMaMailUsrListDTO();
        
        //Paging
        maMailUsrListDTO.setIsLoadMaxCount("Y".equals(maMailUsrListForm.getIsLoadMaxCount())?true:false);
        maMailUsrListDTO.setFirstRow(maMailUsrListForm.getFirstRow());
        maMailUsrListDTO.setOrderBy(maMailUsrListForm.getOrderBy());
        maMailUsrListDTO.setDirection(maMailUsrListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maMailUsrListService.findUsrList(maMailCommonDTO, maMailUsrListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maMailUsrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maMailUsrListService.findTotalCount(maMailCommonDTO, maMailUsrListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maMailUsrListForm.getListId(),maMailUsrListForm.getCurrentPageId(), maMailUsrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaMailUsrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMailUsrListForm
     * @throws Exception
     */
    private void deleteUsrList(HttpServletRequest request, MaMailUsrListForm maMailUsrListForm) throws Exception
    {
    	MaMailUsrListService maMailUsrListService = (MaMailUsrListService) getBean("maMailUsrListService");
        
    	maMailUsrListService.deleteUsrList(maMailUsrListForm.getDeleteRows(), maMailUsrListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
}