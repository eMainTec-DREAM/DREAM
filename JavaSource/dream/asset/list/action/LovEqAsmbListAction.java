package dream.asset.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.parser.ParseException;

import common.struts.SuperAuthAction;
import dream.asset.list.dto.LovEqAsmbListDTO;
import dream.asset.list.form.LovEqAsmbListForm;
import dream.asset.list.service.LovEqAsmbListService;

/**
 * 설비부위 팝업
 * @author  hyosung
 * @version $Id: LovEqAsmbListAction.java,v 1.0 2015/01/18 07:49:29 hyosung Exp $
 * @since   1.0
 * 
 *
 * @struts:action path="/eqAsmbValLov" name="lovEqAsmbListForm"
 *                input="/dream/asset/list/eqAsmbValLov.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/eqAsmbByPmValLov" name="lovEqAsmbListForm"
 *                input="/dream/asset/list/eqAsmbByPmValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="eqAsmbValLov" path="/dream/asset/list/eqAsmbValLov.jsp"
 *                        redirect="false"
 *
 */
public class LovEqAsmbListAction extends SuperAuthAction
{
    public static final int LOV_EQASMB_DEFAULT 	         = 1001;
    
    public static final int LOV_EQASMB_AC_FIND           = 1003;
    
    public static final int LOV_EQASMB_BY_PM_AC_FIND           = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEqAsmbListForm lovEqAsmbListForm = (LovEqAsmbListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEqAsmbListForm.getStrutsAction())
        {
            case LovEqAsmbListAction.LOV_EQASMB_DEFAULT :
            	setJsonParam(request, lovEqAsmbListForm,response);
                returnActionForward = mapping.getInputForward();
                break;
           case LovEqAsmbListAction.LOV_EQASMB_AC_FIND :
                findEqAsmbAcList(request, lovEqAsmbListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
           case LovEqAsmbListAction.LOV_EQASMB_BY_PM_AC_FIND :
               findEqAsmbByPmAcList(request, lovEqAsmbListForm,response);
               returnActionForward = mapping.findForward("jsonPage");
               break;
            case LovEqAsmbListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEqAsmbListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	setJsonParam(request, lovEqAsmbListForm,response);
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
    
    
    private void setJsonParam(HttpServletRequest request, LovEqAsmbListForm lovEqAsmbListForm, HttpServletResponse response) throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
    	LovEqAsmbListService lovEqAsmbListService = (LovEqAsmbListService)getBean("lovEqAsmbListService");
    	
    	LovEqAsmbListDTO lovEqAsmbListDTO=lovEqAsmbListForm.getLovEqAsmbListDTO();
        
    	LovEqAsmbListDTO resultDTO = lovEqAsmbListService.setJsonParm(lovEqAsmbListDTO, lovEqAsmbListForm);
    	
    	lovEqAsmbListForm.setLovEqAsmbListDTO(resultDTO);
	}

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEqAsmbListForm lovEqAsmbListForm) throws IOException
    {
        super.setHeader(request, response, lovEqAsmbListForm.getListId(),lovEqAsmbListForm.getCurrentPageId()); 
    }

    /**
     * 설비부위를 검색한다.
     * @author  hyosun
     * @version $Id: LovEqAsmbListAction.java,v 1.2 2014/01/28 07:49:29 hyosun Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovEqAsmbListForm
     */
    private void findEqAsmbAcList(HttpServletRequest request, LovEqAsmbListForm lovEqAsmbListForm,
            HttpServletResponse response) throws IOException 
    {
        LovEqAsmbListService lovEqAsmbListService = (LovEqAsmbListService)getBean("lovEqAsmbListService");
        
        LovEqAsmbListDTO lovEqAsmbListDTO = lovEqAsmbListForm.getLovEqAsmbListDTO();
        List resultList = lovEqAsmbListService.findEqAsmbAcList(lovEqAsmbListDTO, getUser(request), lovEqAsmbListForm);
        

        super.makeTreeJsonResult(resultList, request, response, lovEqAsmbListForm.getListId());
        
    }
    
    private void findEqAsmbByPmAcList(HttpServletRequest request, LovEqAsmbListForm lovEqAsmbListForm,
            HttpServletResponse response) throws IOException 
    {
        LovEqAsmbListService lovEqAsmbListService = (LovEqAsmbListService)getBean("lovEqAsmbListService");
        
        LovEqAsmbListDTO lovEqAsmbListDTO = lovEqAsmbListForm.getLovEqAsmbListDTO();
        List resultList = lovEqAsmbListService.findEqAsmbByPmAcList(lovEqAsmbListDTO, getUser(request), lovEqAsmbListForm);
        

        super.makeTreeJsonResult(resultList, request, response, lovEqAsmbListForm.getListId());
        
    }

}