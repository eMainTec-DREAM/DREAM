package dream.work.pm.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.list.dto.LovPmNoListDTO;
import dream.work.pm.list.form.LovPmNoListForm;
import dream.work.pm.list.service.LovPmNoListService;

/**
 * 예방점검 팝업
 * @author  kim21017
 * @version $Id: LovPmNoListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovPmNoList" name="lovPmNoListForm"
 *                input="/dream/work/pm/list/lovPmNoList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/pmValLov" name="lovPmNoListForm"
 *                input="/dream/work/pm/list/pmValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovPmNoPopup" path="/dream/work/pm/list/lovPmNoPopup.jsp"
 *                        redirect="false"
 */
public class LovPmNoListAction extends AuthAction
{
    public static final int LOV_PM_DEFAULT	 	= 1001;
    public static final int LOV_PM_FIND 		= 1002;
    public static final int LOV_PM_AC_FIND 		= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPmNoListForm lovPmNoListForm = (LovPmNoListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPmNoListForm.getStrutsAction())
        {
            case LovPmNoListAction.LOV_PM_DEFAULT :
            	returnActionForward = mapping.getInputForward();
                break;
            case LovPmNoListAction.LOV_PM_FIND :
                findPmList(request, lovPmNoListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPmNoListAction.LOV_PM_AC_FIND :
                findPmAcList(request, lovPmNoListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPmNoListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPmNoListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPmNoListForm lovPmNoListForm) throws IOException
    {
        super.setHeader(request, response, lovPmNoListForm.getListId(),lovPmNoListForm.getCurrentPageId()); 
    }
    /**
     * TAPMLST 를 검색한다.
     * @author  kim21017
     * @version $Id: LovPmNoListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovPmNoListForm
     */
    private void findPmList(HttpServletRequest request,
            LovPmNoListForm lovPmNoListForm,HttpServletResponse response) throws IOException
    {
        LovPmNoListService lovPmNoListService = (LovPmNoListService)getBean("lovPmNoListService");
        
        LovPmNoListDTO lovPmNoListDTO = lovPmNoListForm.getLovPmNoListDTO();
        List resultList = lovPmNoListService.findPmList(lovPmNoListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovPmNoListForm.getListId());
    	
    }
    /**
     * TAPMLST AC LOC 를 검색한다.
     * @author  kim21017
     * @version $Id: LovPmNoListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovPmNoListForm
     */
    private void findPmAcList(HttpServletRequest request,
            LovPmNoListForm lovPmNoListForm,HttpServletResponse response, boolean excelExport) throws IOException
    {
        LovPmNoListService lovPmNoListService = (LovPmNoListService)getBean("lovPmNoListService");
        LovPmNoListDTO lovPmNoListDTO = lovPmNoListForm.getLovPmNoListDTO();

    	//Paging
        lovPmNoListDTO.setIsLoadMaxCount("Y".equals(lovPmNoListForm.getIsLoadMaxCount())?true:false);
        lovPmNoListDTO.setFirstRow(lovPmNoListForm.getFirstRow());
        lovPmNoListDTO.setOrderBy(lovPmNoListForm.getOrderBy());
        lovPmNoListDTO.setDirection(lovPmNoListForm.getDirection());
    	
        List resultList = lovPmNoListService.findPmAcList(lovPmNoListForm, getUser(request));
        
      //Paging
        String totalCount = "";
        if(Integer.parseInt(lovPmNoListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovPmNoListService.findTotalCount(lovPmNoListForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,lovPmNoListForm.getListId(),lovPmNoListForm.getCurrentPageId(), lovPmNoListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }
    

}