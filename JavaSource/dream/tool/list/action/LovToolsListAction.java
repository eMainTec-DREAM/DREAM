package dream.tool.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.tool.list.dto.LovToolsListDTO;
import dream.tool.list.form.LovToolsListForm;
import dream.tool.list.service.LovToolsListService;

/**
 * 자재 팝업
 * @author  kim21017
 * @version $Id: LovToolsListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovToolsList" name="lovToolsListForm"
 *                input="/dream/tool/list/lovToolsList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/lovToolPartsAcList" name="lovToolsListForm"
 *                input="/dream/tool/list/lovToolPartsAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovToolsPopup" path="/dream/tool/list/lovToolsPopup.jsp"
 *                        redirect="false"
 */
public class LovToolsListAction extends AuthAction
{
    public static final int LOV_PARTS_DEFAULT 	= 1001;
    public static final int LOV_PARTS_FIND 		= 1002;
    public static final int LOV_PARTS_AC_FIND 	= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovToolsListForm lovToolsListForm = (LovToolsListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovToolsListForm.getStrutsAction())
        {
            case LovToolsListAction.LOV_PARTS_DEFAULT :
                returnActionForward = mapping.findForward("lovToolsPopup");
                break;
            case LovToolsListAction.LOV_PARTS_FIND :
                findPartsList(request, lovToolsListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovToolsListAction.LOV_PARTS_AC_FIND :
                findToolAcList(request, lovToolsListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovToolsListAction.BASE_SET_HEADER:
                setHeader(request, response, lovToolsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovToolsListForm lovToolsListForm) throws IOException
    {
        super.setHeader(request, response, lovToolsListForm.getListId(),lovToolsListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  kim21017
     * @version $Id: LovToolsListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovToolsListForm
     */
    private void findPartsList(HttpServletRequest request,
            LovToolsListForm lovToolsListForm,HttpServletResponse response) throws IOException
    {
        LovToolsListService lovToolsListService = (LovToolsListService)getBean("lovToolsListService");
        
        LovToolsListDTO lovToolsListDTO = lovToolsListForm.getLovToolsListDTO();
        List resultList = lovToolsListService.findPartsList(lovToolsListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovToolsListForm.getListId());
    	
    }
    /**
     * TAPARTS 공기구를 검색한다.
     * @author  kim21017
     * @version $Id: LovToolsListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovToolsListForm
     * @param excelExport
     */
    private void findToolAcList(HttpServletRequest request,
    		LovToolsListForm lovToolsListForm,HttpServletResponse response, boolean excelExport) throws IOException, Exception
    {
    	LovToolsListService lovToolsListService = (LovToolsListService)getBean("lovToolsListService");
    	
    	LovToolsListDTO lovToolsListDTO = lovToolsListForm.getLovToolsListDTO();
    	
    	//Paging
    	lovToolsListDTO.setIsLoadMaxCount("Y".equals(lovToolsListForm.getIsLoadMaxCount()));
    	lovToolsListDTO.setFirstRow(lovToolsListForm.getFirstRow());
    	lovToolsListDTO.setOrderBy(lovToolsListForm.getOrderBy());
    	lovToolsListDTO.setDirection(lovToolsListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovToolsListService.findToolAcList(lovToolsListDTO, user, lovToolsListForm);

        //Paging
        String totalCount = "";
    	
        if(Integer.parseInt(lovToolsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovToolsListService.findTotalCount(lovToolsListDTO, user, lovToolsListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response, lovToolsListForm.getListId(), lovToolsListForm.getCurrentPageId(), lovToolsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}