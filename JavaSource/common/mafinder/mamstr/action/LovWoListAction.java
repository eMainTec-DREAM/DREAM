package common.mafinder.mamstr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.mafinder.mamstr.dto.LovWoListDTO;
import common.mafinder.mamstr.form.LovWoListForm;
import common.mafinder.mamstr.service.LovWoListService;
import common.struts.SuperAuthAction;

/**
 * 작업 팝업
 * @author  kim21017
 * @version $Id: LovWoListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovWoAcList" name="lovWoListForm"
 *                input="/common/mafinder/mamstr/lovWoAcList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/lovWoAcList" name="lovWoListForm"
 *                input="/common/mafinder/mamstr/lovWoAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWoPopup" path="/common/mafinder/mamstr/lovWoPopup.jsp"
 *                        redirect="false"
 */
public class LovWoListAction extends SuperAuthAction
{
    public static final int LOV_WO_DEFAULT        = 1001;
    public static final int LOV_WO_FIND       = 1002;
    
    public static final int LOV_WO_AC_FIND       = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWoListForm lovWoListForm = (LovWoListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWoListForm.getStrutsAction())
        {
            case LovWoListAction.LOV_WO_DEFAULT :
                returnActionForward = mapping.findForward("lovWoPopup");
                break;
            case LovWoListAction.LOV_WO_FIND :
                findWoList(request, lovWoListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWoListAction.LOV_WO_AC_FIND :
                findWoAcList(request, lovWoListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWoListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWoListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
               returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findWoAcList(HttpServletRequest request, LovWoListForm lovWoListForm, HttpServletResponse response,boolean excelExport) throws IOException {
       LovWoListService lovWoListService = (LovWoListService)getBean("lovWoListService");
        
        LovWoListDTO lovWoListDTO = lovWoListForm.getLovWoListDTO();
        
        //Paging
        lovWoListDTO.setIsLoadMaxCount("Y".equals(lovWoListForm.getIsLoadMaxCount())?true:false);
        lovWoListDTO.setFirstRow(lovWoListForm.getFirstRow());
        lovWoListDTO.setOrderBy(lovWoListForm.getOrderBy());
        lovWoListDTO.setDirection(lovWoListForm.getDirection());
        
        List resultList = lovWoListService.findWoAcList(lovWoListDTO, getUser(request), lovWoListForm);
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(lovWoListForm.getIsTotalCount()) == 0)
            totalCount = lovWoListService.findWoListTotalCount(lovWoListDTO, getUser(request), lovWoListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response, lovWoListForm.getListId(),lovWoListForm.getCurrentPageId(), lovWoListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
   }

   private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWoListForm lovWoListForm) throws IOException
    {
        super.setHeader(request, response, lovWoListForm.getListId(),lovWoListForm.getCurrentPageId()); 
    }

    /**
     * 사용창고 리스트 검색.
     * @author  kim21017
     * @version $Id: LovWoListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovWoListForm
     */
    private void findWoList(HttpServletRequest request,LovWoListForm lovWoListForm,HttpServletResponse response,boolean excelExport) throws IOException
    {
        LovWoListService lovWoListService = (LovWoListService)getBean("lovWoListService");
        
        LovWoListDTO lovWoListDTO = lovWoListForm.getLovWoListDTO();
        
        //Paging
        lovWoListDTO.setIsLoadMaxCount("Y".equals(lovWoListForm.getIsLoadMaxCount())?true:false);
        lovWoListDTO.setFirstRow(lovWoListForm.getFirstRow());
        lovWoListDTO.setOrderBy(lovWoListForm.getOrderBy());
        lovWoListDTO.setDirection(lovWoListForm.getDirection());
        
        List resultList = lovWoListService.findWoList(lovWoListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(lovWoListForm.getIsTotalCount()) == 0)
           totalCount = lovWoListService.findWoListTotalCount(lovWoListDTO,getUser(request), lovWoListForm);
                
        if(excelExport) super.makeGridExport(resultList, request, response, lovWoListForm.getListId(),lovWoListForm.getCurrentPageId(), lovWoListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
       
    }

}