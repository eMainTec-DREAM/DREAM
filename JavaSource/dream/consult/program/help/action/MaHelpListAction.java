package dream.consult.program.help.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.consult.program.help.dto.MaHelpCommonDTO;
import dream.consult.program.help.form.MaHelpListForm;
import dream.consult.program.help.service.MaHelpListService;

/**
 * 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maHelpList" name="maHelpListForm"
 *                input="/dream/consult/program/help/maHelpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maHelpList" path="/dream/consult/program/help/maHelpList.jsp"
 *                        redirect="false"
 */
public class MaHelpListAction extends AuthAction
{
    /** 조회 */
    public static final int HELP_LIST_FIND   = 1001;
    /** 삭제 */
    public static final int HELP_LIST_DELETE = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaHelpListForm maHelpListForm = (MaHelpListForm) form;

        switch (maHelpListForm.getStrutsAction())
        {
            case MaHelpListAction.BASE_SET_HEADER:
                setHeader(request, response, maHelpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaHelpListAction.HELP_LIST_FIND:
                findList(request, response, maHelpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;      
            case MaHelpListAction.HELP_LIST_DELETE:
                deleteList(request, maHelpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;                
            case MaHelpListAction.BASE_GRID_EXPORT:
            	findList(request, response, maHelpListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maHelpList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaHelpListForm maHelpListForm) throws IOException
    {
        super.setHeader(request, response, maHelpListForm.getListId(), maHelpListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maHelpListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaHelpListForm maHelpListForm, boolean excelExport)  throws Exception
    {
    	MaHelpListService maHelpListService = (MaHelpListService) getBean("maHelpListService");        
    	MaHelpCommonDTO maHelpCommonDTO = maHelpListForm.getMaHelpCommonDTO();

    	//Paging
        maHelpCommonDTO.setIsLoadMaxCount("Y".equals(maHelpListForm.getIsLoadMaxCount())?true:false);
        maHelpCommonDTO.setFirstRow(maHelpListForm.getFirstRow());
        maHelpCommonDTO.setOrderBy(maHelpListForm.getOrderBy());
        maHelpCommonDTO.setDirection(maHelpListForm.getDirection());
        
        User user = getUser(request);
    	//리스트를 조회한다.
        List resultList = maHelpListService.findHelpList(maHelpCommonDTO, user);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maHelpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maHelpListService.findTotalCount(maHelpCommonDTO,user);

        // 조회한 List 를 form에 셋팅한다.
        if(excelExport) super.makeGridExport(resultList, request, response,maHelpListForm.getListId(),maHelpListForm.getCurrentPageId(), maHelpListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maHelpListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaHelpListForm maHelpListForm) throws Exception
    {
        MaHelpListService maHelpListService = (MaHelpListService) getBean("maHelpListService");        

        String[] deleteRows = maHelpListForm.getDeleteRows();    // sheet 내역
        
        maHelpListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
