package dream.req.work.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import dream.req.work.dto.LovWoReqAcListDTO;
import dream.req.work.form.LovWoReqAcListForm;
import dream.req.work.service.LovWoReqAcListService;

/**
 * 작업요청 팝업
 * @author  syyang
 * @version $Id: $
 * @since   1.0
 * 
 * @struts:action path="/lovWoReqAcList" name="lovWoReqAcListForm"
 *                input="/dream/req/work/lovWoReqAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWoReqAcList" path="/dream/req/work/lovWoReqAcList.jsp"
 *                        redirect="false"
 */
public class LovWoReqAcListAction extends SuperAuthAction
{
    public static final int LOV_WOREQ_DEFAULT 	    = 1001;
    
    public static final int LOV_WOREQ_AC_FIND		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWoReqAcListForm lovWoReqAcListForm = (LovWoReqAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWoReqAcListForm.getStrutsAction())
        {
            case LovWoReqAcListAction.LOV_WOREQ_DEFAULT :
                returnActionForward = mapping.findForward("lovWoReqAcList");
                break;
            case LovWoReqAcListAction.LOV_WOREQ_AC_FIND :
            	findWoReqAcList(request, lovWoReqAcListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWoReqAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWoReqAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findWoReqAcList(HttpServletRequest request, LovWoReqAcListForm lovWoReqAcListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	LovWoReqAcListService lovWoReqAcListService = (LovWoReqAcListService)getBean("lovWoReqAcListService");
        
        LovWoReqAcListDTO lovWoReqAcListDTO = lovWoReqAcListForm.getLovWoReqAcListDTO();
        
        //Paging
        lovWoReqAcListDTO.setIsLoadMaxCount("Y".equals(lovWoReqAcListForm.getIsLoadMaxCount()));
        lovWoReqAcListDTO.setFirstRow(lovWoReqAcListForm.getFirstRow());
        lovWoReqAcListDTO.setOrderBy(lovWoReqAcListForm.getOrderBy());
        lovWoReqAcListDTO.setDirection(lovWoReqAcListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovWoReqAcListService.findWoReqAcList(lovWoReqAcListDTO, user, lovWoReqAcListForm);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovWoReqAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovWoReqAcListService.findTotalCount(lovWoReqAcListDTO,user,lovWoReqAcListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response, lovWoReqAcListForm.getListId(), lovWoReqAcListForm.getCurrentPageId(), lovWoReqAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWoReqAcListForm lovWoReqAcListForm) throws IOException
    {
        super.setHeader(request, response, lovWoReqAcListForm.getListId(),lovWoReqAcListForm.getCurrentPageId()); 
    }

}