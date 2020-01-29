package dream.part.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.list.dto.LovPartsListDTO;
import dream.part.list.form.LovPartsListForm;
import dream.part.list.service.LovPartsListService;

/**
 * 자재 팝업
 * @author  kim21017
 * @version $Id: LovPartsListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovPartsList" name="lovPartsListForm"
 *                input="/dream/part/list/lovPartsList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/partsValLov" name="lovPartsListForm"
 *                input="/dream/part/list/partsValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovPartsPopup" path="/dream/part/list/lovPartsPopup.jsp"
 *                        redirect="false"
 */
public class LovPartsListAction extends AuthAction
{
    public static final int LOV_PARTS_DEFAULT 		= 1001;
    public static final int LOV_PARTS_FIND 			= 1002;
    public static final int LOV_PARTS_AC_FIND		= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPartsListForm lovPartsListForm = (LovPartsListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPartsListForm.getStrutsAction())
        {
            case LovPartsListAction.LOV_PARTS_DEFAULT :
            	returnActionForward = mapping.getInputForward();
                break;
            case LovPartsListAction.LOV_PARTS_FIND :
                findPartsList(request, lovPartsListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPartsListAction.LOV_PARTS_AC_FIND :
                findPartsAcList(request, lovPartsListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPartsListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPartsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPartsListForm lovPartsListForm) throws IOException
    {
        super.setHeader(request, response, lovPartsListForm.getListId(),lovPartsListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  kim21017
     * @version $Id: LovPartsListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovPartsListForm
     */
    private void findPartsList(HttpServletRequest request,
            LovPartsListForm lovPartsListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
        LovPartsListService lovPartsListService = (LovPartsListService)getBean("lovPartsListService", request);
        LovPartsListDTO lovPartsListDTO = lovPartsListForm.getLovPartsListDTO();
        
        //Paging
        lovPartsListDTO.setIsLoadMaxCount("Y".equals(lovPartsListForm.getIsLoadMaxCount())?true:false);
        lovPartsListDTO.setFirstRow(lovPartsListForm.getFirstRow());
        lovPartsListDTO.setOrderBy(lovPartsListForm.getOrderBy());
        lovPartsListDTO.setDirection(lovPartsListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovPartsListService.findPartsList(lovPartsListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovPartsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovPartsListService.findTotalCount(lovPartsListForm,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovPartsListForm.getListId(),lovPartsListForm.getCurrentPageId(), lovPartsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }
    /**
     * TAPARTS AC LOV를 검색한다.
     * @author  kim21017
     * @version $Id: LovPartsListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovPartsListForm
     */
    private void findPartsAcList(HttpServletRequest request,
            LovPartsListForm lovPartsListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
    	LovPartsListService lovPartsListService = (LovPartsListService)getBean("lovPartsListService", request);
        LovPartsListDTO lovPartsListDTO = lovPartsListForm.getLovPartsListDTO();
        
    	//Paging
        lovPartsListDTO.setIsLoadMaxCount("Y".equals(lovPartsListForm.getIsLoadMaxCount())?true:false);
        lovPartsListDTO.setFirstRow(lovPartsListForm.getFirstRow());
        lovPartsListDTO.setOrderBy(lovPartsListForm.getOrderBy());
        lovPartsListDTO.setDirection(lovPartsListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovPartsListService.findPartsAcList(lovPartsListForm, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovPartsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovPartsListService.findTotalCount(lovPartsListForm,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovPartsListForm.getListId(),lovPartsListForm.getCurrentPageId(), lovPartsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}