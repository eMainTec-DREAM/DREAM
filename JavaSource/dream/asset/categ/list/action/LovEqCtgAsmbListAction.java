package dream.asset.categ.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.categ.list.dto.LovEqCtgAsmbListDTO;
import dream.asset.categ.list.form.LovEqCtgAsmbListForm;
import dream.asset.categ.list.service.LovEqCtgAsmbListService;

/**
 * 설비종류작업부위 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovEqCtgAsmbList" name="lovEqCtgAsmbListForm"
 *                input="/dream/asset/categ/list/lovEqCtgAsmbList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEqCtgAsmbPopup" path="/dream/asset/categ/list/lovEqCtgAsmbPopup.jsp"
 *                        redirect="false"
 */
public class LovEqCtgAsmbListAction extends AuthAction
{
    public static final int LOV_EQCTGASMB_DEFAULT 	= 1001;
    public static final int LOV_EQCTGASMB_FIND      = 1002;
    
    public static final int LOV_EQCTGASMB_AC_FIND      = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEqCtgAsmbListForm lovEqCtgAsmbListForm = (LovEqCtgAsmbListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEqCtgAsmbListForm.getStrutsAction())
        {
            case LovEqCtgAsmbListAction.LOV_EQCTGASMB_DEFAULT :
                returnActionForward = mapping.findForward("lovEqCtgAsmbPopup");
                break;
            case LovEqCtgAsmbListAction.LOV_EQCTGASMB_FIND :
                findEqCtgAsmbList(request, lovEqCtgAsmbListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqCtgAsmbListAction.LOV_EQCTGASMB_AC_FIND :
                findEqCtgAsmbACList(request, lovEqCtgAsmbListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqCtgAsmbListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEqCtgAsmbListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEqCtgAsmbListForm lovEqCtgAsmbListForm) throws IOException
    {
        super.setHeader(request, response, lovEqCtgAsmbListForm.getListId(),lovEqCtgAsmbListForm.getCurrentPageId()); 
    }

    /**
     * TAEQCTGASMB 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovEqCtgAsmbListForm
     * @throws Exception 
     */
    private void findEqCtgAsmbList(HttpServletRequest request,
            LovEqCtgAsmbListForm lovEqCtgAsmbListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
        LovEqCtgAsmbListService lovEqCtgAsmbListService = (LovEqCtgAsmbListService)getBean("lovEqCtgAsmbListService");
        LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO = lovEqCtgAsmbListForm.getLovEqCtgAsmbListDTO();
        
        //Paging
        lovEqCtgAsmbListDTO.setIsLoadMaxCount("Y".equals(lovEqCtgAsmbListForm.getIsLoadMaxCount())?true:false);
        lovEqCtgAsmbListDTO.setFirstRow(lovEqCtgAsmbListForm.getFirstRow());
        lovEqCtgAsmbListDTO.setOrderBy(lovEqCtgAsmbListForm.getOrderBy());
        lovEqCtgAsmbListDTO.setDirection(lovEqCtgAsmbListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovEqCtgAsmbListService.findEqCtgAsmbList(lovEqCtgAsmbListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovEqCtgAsmbListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovEqCtgAsmbListService.findTotalCount(lovEqCtgAsmbListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovEqCtgAsmbListForm.getListId(),lovEqCtgAsmbListForm.getCurrentPageId(), lovEqCtgAsmbListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }
    
    private void findEqCtgAsmbACList(HttpServletRequest request,
            LovEqCtgAsmbListForm lovEqCtgAsmbListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
        LovEqCtgAsmbListService lovEqCtgAsmbListService = (LovEqCtgAsmbListService)getBean("lovEqCtgAsmbListService");
        LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO = lovEqCtgAsmbListForm.getLovEqCtgAsmbListDTO();
        
        //Paging
        lovEqCtgAsmbListDTO.setIsLoadMaxCount("Y".equals(lovEqCtgAsmbListForm.getIsLoadMaxCount())?true:false);
        lovEqCtgAsmbListDTO.setFirstRow(lovEqCtgAsmbListForm.getFirstRow());
        lovEqCtgAsmbListDTO.setOrderBy(lovEqCtgAsmbListForm.getOrderBy());
        lovEqCtgAsmbListDTO.setDirection(lovEqCtgAsmbListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovEqCtgAsmbListService.findEqCtgAsmbACList(lovEqCtgAsmbListDTO, user, lovEqCtgAsmbListForm);
      
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovEqCtgAsmbListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovEqCtgAsmbListService.findTotalCount(lovEqCtgAsmbListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovEqCtgAsmbListForm.getListId(),lovEqCtgAsmbListForm.getCurrentPageId(), lovEqCtgAsmbListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, totalCount);
            //super.makeJsonResult(resultList, request, response, lovEqCtgAsmbListForm.getListId());
        
    }

}