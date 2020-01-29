package common.mafinder.mamstr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.mafinder.mamstr.dto.LovWoPlanAcListDTO;
import common.mafinder.mamstr.form.LovWoPlanAcListForm;
import common.mafinder.mamstr.service.LovWoPlanAcListService;
import common.struts.SuperAuthAction;

/**
 * 작업 팝업
 * @author  kim21017
 * @version $Id: LovWoPlanAcListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovWoPlanAcList" name="lovWoPlanAcListForm"
 *                input="/common/mafinder/mamstr/lovWoPlanAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWoPlanAcList" path="/common/mafinder/mamstr/lovWoPlanAcList.jsp"
 *                        redirect="false"
 */
public class LovWoPlanAcListAction extends SuperAuthAction
{
    public static final int LOV_WO_DEFAULT 	    = 1001;
    public static final int LOV_WO_FIND 		= 1002;
    
    public static final int LOV_WO_AC_FIND 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWoPlanAcListForm lovWoPlanAcListForm = (LovWoPlanAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWoPlanAcListForm.getStrutsAction())
        {
            case LovWoPlanAcListAction.LOV_WO_DEFAULT :
                returnActionForward = mapping.findForward("lovWoPlanAcList");
                break;
            case LovWoPlanAcListAction.LOV_WO_FIND :
                findWoPlanAcList(request, lovWoPlanAcListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWoPlanAcListAction.LOV_WO_AC_FIND :
                findWoAcList(request, lovWoPlanAcListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWoPlanAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWoPlanAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findWoAcList(HttpServletRequest request, LovWoPlanAcListForm lovWoPlanAcListForm, HttpServletResponse response,boolean excelExport) throws IOException {
    	LovWoPlanAcListService lovWoPlanAcListService = (LovWoPlanAcListService)getBean("lovWoPlanAcListService");
        
        LovWoPlanAcListDTO lovWoPlanAcListDTO = lovWoPlanAcListForm.getLovWoPlanAcListDTO();
        
        //Paging
        lovWoPlanAcListDTO.setIsLoadMaxCount("Y".equals(lovWoPlanAcListForm.getIsLoadMaxCount())?true:false);
        lovWoPlanAcListDTO.setFirstRow(lovWoPlanAcListForm.getFirstRow());
        lovWoPlanAcListDTO.setOrderBy(lovWoPlanAcListForm.getOrderBy());
        lovWoPlanAcListDTO.setDirection(lovWoPlanAcListForm.getDirection());
        
        List resultList = lovWoPlanAcListService.findWoAcList(lovWoPlanAcListDTO, getUser(request), lovWoPlanAcListForm);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovWoPlanAcListForm.getIsTotalCount()) == 0)
            totalCount = lovWoPlanAcListService.findWoListTotalCount(lovWoPlanAcListDTO, getUser(request), lovWoPlanAcListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response, lovWoPlanAcListForm.getListId(),lovWoPlanAcListForm.getCurrentPageId(), lovWoPlanAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWoPlanAcListForm lovWoPlanAcListForm) throws IOException
    {
        super.setHeader(request, response, lovWoPlanAcListForm.getListId(),lovWoPlanAcListForm.getCurrentPageId()); 
    }

    /**
     * 사용창고 리스트 검색.
     * @author  kim21017
     * @version $Id: LovWoPlanAcListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovWoPlanAcListForm
     */
    private void findWoPlanAcList(HttpServletRequest request,LovWoPlanAcListForm lovWoPlanAcListForm,HttpServletResponse response,boolean excelExport) throws IOException
    {
        LovWoPlanAcListService lovWoPlanAcListService = (LovWoPlanAcListService)getBean("lovWoPlanAcListService");
        LovWoPlanAcListDTO lovWoPlanAcListDTO = lovWoPlanAcListForm.getLovWoPlanAcListDTO();

        //Paging
        lovWoPlanAcListDTO.setIsLoadMaxCount("Y".equals(lovWoPlanAcListForm.getIsLoadMaxCount())?true:false);
        lovWoPlanAcListDTO.setFirstRow(lovWoPlanAcListForm.getFirstRow());
        lovWoPlanAcListDTO.setOrderBy(lovWoPlanAcListForm.getOrderBy());
        lovWoPlanAcListDTO.setDirection(lovWoPlanAcListForm.getDirection());
        
        List resultList = lovWoPlanAcListService.findWoPlanAcList(lovWoPlanAcListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovWoPlanAcListForm.getIsTotalCount()) == 0)
            totalCount = lovWoPlanAcListService.findWoListTotalCount(lovWoPlanAcListDTO, getUser(request), lovWoPlanAcListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response, lovWoPlanAcListForm.getListId(),lovWoPlanAcListForm.getCurrentPageId(), lovWoPlanAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }

}