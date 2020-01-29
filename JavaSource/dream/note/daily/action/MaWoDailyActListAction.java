package dream.note.daily.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.note.daily.dto.MaWoDailyActListDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.form.MaWoDailyActListForm;
import dream.note.daily.service.MaWoDailyActListService;

/**
 * 일일작업 - Main Activities 목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maWoDailyActList" name="maWoDailyActListForm"
 *                input="/dream/note/daily/maWoDailyActList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDailyActList" path="/dream/note/daily/maWoDailyActList.jsp"
 *                        redirect="false"
 */
public class MaWoDailyActListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    /** 삭제 */
    public static final int LIST_DELETE       = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDailyActListForm maWoDailyActListForm = (MaWoDailyActListForm) form;
        
        switch (maWoDailyActListForm.getStrutsAction())
        {
            case MaWoDailyActListAction.LIST_FIND:
            	findList(request, maWoDailyActListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoDailyActListAction.LIST_DELETE:
            	deleteList(request, maWoDailyActListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoDailyActListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoDailyActListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maWoDailyActList");
                break;
        }

        return returnActionForward;
    }

    private void deleteList(HttpServletRequest request, MaWoDailyActListForm maWoDailyActListForm)
    {
        MaWoDailyActListService maWoDailyActListService = (MaWoDailyActListService) getBean("maWoDailyActListService");        

        String[] deleteRows = maWoDailyActListForm.getDeleteRows();    // sheet 내역
        
        maWoDailyActListService.deleteList(deleteRows,getUser(request).getCompNo());
        
        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoDailyActListForm maWoDailyActListForm) throws IOException
    {
        super.setHeader(request, response, maWoDailyActListForm.getListId(), maWoDailyActListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoDailyActListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaWoDailyActListForm maWoDailyActListForm, HttpServletResponse response) throws IOException
    {
    	MaWoDailyActListService maWoDailyActListService = (MaWoDailyActListService) getBean("maWoDailyActListService");        

    	MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyActListForm.getMaWoDailyCommonDTO();
    	maWoDailyCommonDTO.setCompNo(getUser(request).getCompNo());
    	MaWoDailyActListDTO maWoDailyActListDTO = maWoDailyActListForm.getMaWoDailyActListDTO();
        //리스트를 조회한다.
        List resultList = maWoDailyActListService.findList(maWoDailyCommonDTO,maWoDailyActListDTO,getUser(request));
        super.makeJsonResult(resultList, request, response, maWoDailyActListForm.getListId());
    }
   
}
