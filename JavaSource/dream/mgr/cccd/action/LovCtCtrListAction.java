package dream.mgr.cccd.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.mgr.cccd.dto.LovCtCtrListDTO;
import dream.mgr.cccd.form.LovCtCtrListForm;
import dream.mgr.cccd.service.LovCtCtrListService;

/**
 * CP 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovCtCtrList" name="lovCtCtrListForm"
 *                input="/dream/mgr/cccd/lovCtCtrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovCtCtrPopup" path="/dream/mgr/cccd/lovCtCtrPopup.jsp"
 *                        redirect="false"
 */
public class LovCtCtrListAction extends SuperAuthAction
{
    public static final int LOV_CTCTR_DEFAULT 	= 1001;
    public static final int LOV_CTCTR_FIND     = 1002;
    
    public static final int LOV_CTCTR_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovCtCtrListForm lovCtCtrListForm = (LovCtCtrListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovCtCtrListForm.getStrutsAction())
        {
            case LovCtCtrListAction.LOV_CTCTR_DEFAULT :
                returnActionForward = mapping.findForward("lovCtCtrPopup");
                break;
            case LovCtCtrListAction.LOV_CTCTR_FIND :
                findCtCtrList(request, lovCtCtrListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovCtCtrListAction.LOV_CTCTR_AC_FIND :
                findCtCtrACList(request, lovCtCtrListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovCtCtrListAction.BASE_SET_HEADER:
                setHeader(request, response, lovCtCtrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovCtCtrListForm lovCtCtrListForm) throws IOException
    {
        super.setHeader(request, response, lovCtCtrListForm.getListId(),lovCtCtrListForm.getCurrentPageId()); 
    }

    /**
     * TACTCTR 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovCtCtrListForm
     */
    private void findCtCtrList(HttpServletRequest request,
            LovCtCtrListForm lovCtCtrListForm,HttpServletResponse response) throws IOException
    {
        LovCtCtrListService lovCtCtrListService = (LovCtCtrListService)getBean("lovCtCtrListService");
        
        LovCtCtrListDTO lovCtCtrListDTO = lovCtCtrListForm.getLovCtCtrListDTO();
        List resultList = lovCtCtrListService.findCtCtrList(lovCtCtrListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovCtCtrListForm.getListId());
    	
    }
    
    private void findCtCtrACList(HttpServletRequest request,
            LovCtCtrListForm lovCtCtrListForm,HttpServletResponse response,boolean excelExport) throws IOException
    {
        LovCtCtrListService lovCtCtrListService = (LovCtCtrListService)getBean("lovCtCtrListService");
        
        LovCtCtrListDTO lovCtCtrListDTO = lovCtCtrListForm.getLovCtCtrListDTO();
        
        //Paging
        lovCtCtrListDTO.setIsLoadMaxCount("Y".equals(lovCtCtrListForm.getIsLoadMaxCount())?true:false);
        lovCtCtrListDTO.setFirstRow(lovCtCtrListForm.getFirstRow());
        lovCtCtrListDTO.setOrderBy(lovCtCtrListForm.getOrderBy());
        lovCtCtrListDTO.setDirection(lovCtCtrListForm.getDirection());

        List resultList = lovCtCtrListService.findCtCtrACList(lovCtCtrListDTO, getUser(request),lovCtCtrListForm);
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(lovCtCtrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovCtCtrListService.findTotalCount(lovCtCtrListDTO,getUser(request),lovCtCtrListForm);
                
        if(excelExport) super.makeGridExport(resultList, request, response, lovCtCtrListForm.getListId(),lovCtCtrListForm.getCurrentPageId(), lovCtCtrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }

}