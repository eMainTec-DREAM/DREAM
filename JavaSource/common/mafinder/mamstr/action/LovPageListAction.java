package common.mafinder.mamstr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.mafinder.mamstr.dto.LovPageListDTO;
import common.mafinder.mamstr.form.LovPageListForm;
import common.mafinder.mamstr.service.LovPageListService;
import common.struts.SuperAuthAction;

/**
 * 페이지 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovPageList" name="lovPageListForm"
 *                input="/common/mafinder/mamstr/lovPageList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/pageValLov" name="lovPageListForm"
 *                input="/common/mafinder/mamstr/pageValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovPagePopup" path="/common/mafinder/mamstr/lovPagePopup.jsp"
 *                        redirect="false"
 */
public class LovPageListAction extends SuperAuthAction
{
    public static final int LOV_PAGE_DEFAULT 	= 1001;
    public static final int LOV_PAGE_FIND     = 1002;
    
    public static final int LOV_PAGE_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPageListForm lovPageListForm = (LovPageListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPageListForm.getStrutsAction())
        {
            case LovPageListAction.LOV_PAGE_DEFAULT :
                returnActionForward = mapping.getInputForward();
                break;
            case LovPageListAction.LOV_PAGE_FIND :
                findPageList(request, lovPageListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPageListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPageListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPageListAction.LOV_PAGE_AC_FIND :
                findPageAcList(request, lovPageListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPageListForm lovPageListForm) throws IOException
    {
        super.setHeader(request, response, lovPageListForm.getListId(),lovPageListForm.getCurrentPageId()); 
    }

    /**
     * TAPAGE 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovPageListForm
     * @throws Exception 
     */
    private void findPageList(HttpServletRequest request,
            LovPageListForm lovPageListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
        LovPageListService lovPageListService = (LovPageListService)getBean("lovPageListService");

        User user = getUser(request);
        LovPageListDTO lovPageListDTO = lovPageListForm.getLovPageListDTO();

        //Paging
        lovPageListDTO.setIsLoadMaxCount("Y".equals(lovPageListForm.getIsLoadMaxCount())?true:false);
        lovPageListDTO.setFirstRow(lovPageListForm.getFirstRow());
        lovPageListDTO.setOrderBy(lovPageListForm.getOrderBy());
        lovPageListDTO.setDirection(lovPageListForm.getDirection());
        
        List resultList = lovPageListService.findPageList(lovPageListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovPageListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovPageListService.findTotalCount(lovPageListForm, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovPageListForm.getListId(),lovPageListForm.getCurrentPageId(), lovPageListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }
    private void findPageAcList(HttpServletRequest request,
            LovPageListForm lovPageListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
        LovPageListService lovPageListService = (LovPageListService)getBean("lovPageListService");
        LovPageListDTO lovPageListDTO = lovPageListForm.getLovPageListDTO();
        
        //Paging
        lovPageListDTO.setIsLoadMaxCount("Y".equals(lovPageListForm.getIsLoadMaxCount())?true:false);
        lovPageListDTO.setFirstRow(lovPageListForm.getFirstRow());
        lovPageListDTO.setOrderBy(lovPageListForm.getOrderBy());
        lovPageListDTO.setDirection(lovPageListForm.getDirection());

        User user = getUser(request);
        List resultList = lovPageListService.findPageAcList(lovPageListDTO, user, lovPageListForm);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovPageListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovPageListService.findTotalCount(lovPageListForm, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovPageListForm.getListId(),lovPageListForm.getCurrentPageId(), lovPageListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
   
    }

}