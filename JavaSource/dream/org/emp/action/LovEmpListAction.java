package dream.org.emp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.parser.ParseException;

import common.struts.AuthAction;
import dream.org.emp.dto.LovEmpListDTO;
import dream.org.emp.form.LovEmpListForm;
import dream.org.emp.service.LovEmpListService;

/**
 * 사원 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovEmpList" name="lovEmpListForm"
 *                input="/dream/org/emp/lovEmpPopup.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/lovEmpAcList" name="lovEmpListForm"
 *                input="/dream/org/emp/lovEmpAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEmpPopup" path="/dream/org/emp/lovEmpPopup.jsp"
 *                        redirect="false"
 */
public class LovEmpListAction extends AuthAction
{
    public static final int LOV_EMP_DEFAULT 	= 1001;
    public static final int LOV_EMP_FIND     = 1002;
    
    public static final int LOV_EMP_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEmpListForm lovEmpListForm = (LovEmpListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEmpListForm.getStrutsAction())
        {
            case LovEmpListAction.LOV_EMP_DEFAULT :
            	setJsonParam(request, lovEmpListForm,response);
                returnActionForward = mapping.findForward("lovEmpPopup");
                break;
            case LovEmpListAction.LOV_EMP_FIND :
                findEmpList(request, lovEmpListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEmpListAction.LOV_EMP_AC_FIND :
                findEmpAcList(request, lovEmpListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
                
            case LovEmpListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEmpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
    private void setJsonParam(HttpServletRequest request, LovEmpListForm lovEmpListForm, HttpServletResponse response) throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	
    	LovEmpListService lovEmpListService = (LovEmpListService)getBean("lovEmpListService", request);
    	
    	LovEmpListDTO lovEmpListDTO = lovEmpListForm.getLovEmpListDTO();
        
    	LovEmpListDTO resultDTO = lovEmpListService.setJsonParm(lovEmpListDTO);
    	
    	lovEmpListForm.setLovEmpListDTO(resultDTO);
	}

    private void findEmpAcList(HttpServletRequest request, LovEmpListForm lovEmpListForm, HttpServletResponse response, boolean excelExport) throws IOException {
    	LovEmpListService lovEmpListService = (LovEmpListService)getBean("lovEmpListService", request);
        
        LovEmpListDTO lovEmpListDTO = lovEmpListForm.getLovEmpListDTO();

    	//Paging
        lovEmpListDTO.setIsLoadMaxCount("Y".equals(lovEmpListForm.getIsLoadMaxCount())?true:false);
        lovEmpListDTO.setFirstRow(lovEmpListForm.getFirstRow());
        lovEmpListDTO.setOrderBy(lovEmpListForm.getOrderBy());
        lovEmpListDTO.setDirection(lovEmpListForm.getDirection());
        List resultList = lovEmpListService.findEmpAcList(lovEmpListDTO,getUser(request), lovEmpListForm);
        
      //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovEmpListService.findTotalCount(lovEmpListForm,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovEmpListForm.getListId(),lovEmpListForm.getCurrentPageId(), lovEmpListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEmpListForm lovEmpListForm) throws IOException
    {
        super.setHeader(request, response, lovEmpListForm.getListId(),lovEmpListForm.getCurrentPageId()); 
    }

    /**
     * TAUSER 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovEmpListForm
     */
    private void findEmpList(HttpServletRequest request,
            LovEmpListForm lovEmpListForm,HttpServletResponse response) throws IOException
    {
        LovEmpListService lovEmpListService = (LovEmpListService)getBean("lovEmpListService", request);
        
        LovEmpListDTO lovEmpListDTO = lovEmpListForm.getLovEmpListDTO();
        
        List resultList = lovEmpListService.findEmpList(lovEmpListDTO,getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovEmpListForm.getListId());
    	
    }

}