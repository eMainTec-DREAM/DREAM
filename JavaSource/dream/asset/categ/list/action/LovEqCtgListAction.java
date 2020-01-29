package dream.asset.categ.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.asset.categ.list.dto.LovEqCtgListDTO;
import dream.asset.categ.list.form.LovEqCtgListForm;
import dream.asset.categ.list.service.LovEqCtgListService;

/**
 * 설비종류 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovEqCtgList" name="lovEqCtgListForm"
 *                input="/dream/asset/categ/list/lovEqCtgPopup.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/lovEqCtgAcList" name="lovEqCtgListForm"
 *                input="/dream/asset/categ/list/lovEqCtgAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEqCtgPopup" path="/dream/asset/categ/list/lovEqCtgPopup.jsp"
 *                        redirect="false"
 */
public class LovEqCtgListAction extends SuperAuthAction
{
    public static final int LOV_EQCTG_DEFAULT 	= 1001;
    public static final int LOV_EQCTG_FIND      = 1002;
    
    public static final int LOV_EQCTG_AC_FIND   = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEqCtgListForm lovEqCtgListForm = (LovEqCtgListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEqCtgListForm.getStrutsAction())
        {
            case LovEqCtgListAction.LOV_EQCTG_DEFAULT :
                returnActionForward = mapping.findForward("lovEqCtgPopup");
                break;
            case LovEqCtgListAction.LOV_EQCTG_FIND :
                findEqCtgList(request, lovEqCtgListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqCtgListAction.LOV_EQCTG_AC_FIND :
                findEqCtgAcList(request, lovEqCtgListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqCtgListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEqCtgListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findEqCtgAcList(HttpServletRequest request, LovEqCtgListForm lovEqCtgListForm,
			HttpServletResponse response) throws IOException {
    	LovEqCtgListService lovEqCtgListService = (LovEqCtgListService)getBean("lovEqCtgListService");
        
        LovEqCtgListDTO lovEqCtgListDTO = lovEqCtgListForm.getLovEqCtgListDTO();
        List resultList = lovEqCtgListService.findEqCtgAcList(lovEqCtgListDTO, getUser(request), lovEqCtgListForm);
        
        super.makeTreeJsonResult(resultList, request, response, lovEqCtgListForm.getListId());
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEqCtgListForm lovEqCtgListForm) throws IOException
    {
        super.setHeader(request, response, lovEqCtgListForm.getListId(),lovEqCtgListForm.getCurrentPageId()); 
    }

    /**
     * TAEQCTG 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovEqCtgListForm
     */
    private void findEqCtgList(HttpServletRequest request,
            LovEqCtgListForm lovEqCtgListForm,HttpServletResponse response) throws IOException
    {
        LovEqCtgListService lovEqCtgListService = (LovEqCtgListService)getBean("lovEqCtgListService");
        
        LovEqCtgListDTO lovEqCtgListDTO = lovEqCtgListForm.getLovEqCtgListDTO();
        List resultList = lovEqCtgListService.findEqCtgList(lovEqCtgListDTO, getUser(request));
        
        super.makeTreeJsonResult(resultList, request, response, lovEqCtgListForm.getListId());
        //super.makeJsonResult(resultList, request, response, lovEqCtgListForm.getListId());
    	
    }

}