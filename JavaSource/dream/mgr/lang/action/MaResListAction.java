package dream.mgr.lang.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.form.MaResListForm;
import dream.mgr.lang.service.MaResListService;

/**
 * 언어 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maResList" name="maResListForm"
 *                input="/dream/mgr/lang/maResList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maResList" path="/dream/mgr/lang/maResList.jsp"
 *                        redirect="false"
 */
public class MaResListAction extends AuthAction
{
    /** 조회 */
    public static final int RES_LIST_FIND   = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaResListForm maResListForm = (MaResListForm) form;
        
        switch (maResListForm.getStrutsAction())
        {
            case MaResListAction.BASE_SET_HEADER:
                setHeader(request, response, maResListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaResListAction.RES_LIST_FIND:
                findList(request, response, maResListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;      
            case MaResListAction.BASE_GRID_EXPORT:
            	findList(request, response, maResListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maResList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaResListForm maResListForm) throws IOException
    {
        super.setHeader(request, response, maResListForm.getListId(), maResListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maResListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaResListForm maResListForm, boolean excelExport)  throws IOException
    {
    	MaResListService maResListService = (MaResListService) getBean("maResListService");        

    	MaResCommonDTO maResCommonDTO = maResListForm.getMaResCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	maResCommonDTO.setCompNo((getUser(request).getCompNo()));
    	
    	//Paging
        maResCommonDTO.setIsLoadMaxCount("Y".equals(maResListForm.getIsLoadMaxCount())?true:false);
        maResCommonDTO.setFirstRow(maResListForm.getFirstRow());
        maResCommonDTO.setOrderBy(maResListForm.getOrderBy());
        maResCommonDTO.setDirection(maResListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maResListService.findResList(maResCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maResListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maResListService.findTotalCount(maResCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maResListForm.getListId(),maResListForm.getCurrentPageId(), maResListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
