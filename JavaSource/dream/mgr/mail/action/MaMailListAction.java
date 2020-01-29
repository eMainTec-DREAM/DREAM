package dream.mgr.mail.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.form.MaMailListForm;
import dream.mgr.mail.service.MaMailListService;

/**
 * 메일수신자설정 - 목록 action
 * @author  kim21017
 * @version $Id: MaMailListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maMailList" name="maMailListForm"
 *                input="/dream/mgr/mail/maMailList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMailList" path="/dream/mgr/mail/maMailList.jsp"
 *                        redirect="false"
 */
public class MaMailListAction extends AuthAction
{
    /** 조회 */
    public static final int MAIL_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int MAIL_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaMailListForm maMailListForm = (MaMailListForm) form;
        
        switch (maMailListForm.getStrutsAction())
        {
            case MaMailListAction.MAIL_LIST_FIND:
                findMailList(request, maMailListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMailListAction.BASE_SET_HEADER:
                setHeader(request, response, maMailListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMailListAction.MAIL_LIST_DELETE:
                deleteMail(request, maMailListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaMailListAction.BASE_GRID_EXPORT:
            	findMailList(request, maMailListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maMailList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaMailListForm maMailListForm) throws IOException
    {
        super.setHeader(request, response, maMailListForm.getListId(), maMailListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaMailListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMailListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findMailList(HttpServletRequest request, MaMailListForm maMailListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	MaMailListService maMailListService = (MaMailListService) getBean("maMailListService");        

    	MaMailCommonDTO maMailCommonDTO = maMailListForm.getMaMailCommonDTO();

    	//Paging
    	maMailCommonDTO.setIsLoadMaxCount("Y".equals(maMailListForm.getIsLoadMaxCount())?true:false);
    	maMailCommonDTO.setFirstRow(maMailListForm.getFirstRow());
    	maMailCommonDTO.setOrderBy(maMailListForm.getOrderBy());
    	maMailCommonDTO.setDirection(maMailListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maMailListService.findMailList(maMailCommonDTO, getUser(request));
      
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maMailListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maMailListService.findTotalCount(maMailCommonDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, maMailListForm.getListId(),maMailListForm.getCurrentPageId(), maMailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaMailListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailListForm
     * @param request
     */
    private void deleteMail(HttpServletRequest request, MaMailListForm maMailListForm) throws Exception
    {
    	MaMailListService maMailListService = (MaMailListService) getBean("maMailListService");        

    	String[] deleteRows = maMailListForm.getDeleteRows();    // sheet 내역
        
    	maMailListService.deleteMail(deleteRows);
        
        setAjaxStatus(request);
    }
}
