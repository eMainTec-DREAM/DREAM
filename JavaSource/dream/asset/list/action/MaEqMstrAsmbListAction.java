package dream.asset.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.struts.AuthAction;
import common.util.CommonUtil;
import common.util.JsonUtil;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.form.MaEqMstrAsmbListForm;
import dream.asset.list.service.MaEqMstrAsmbListService;

/**
 * 설비부위 목록
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrAsmbList" name="maEqMstrAsmbListForm"
 *                input="/dream/asset/list/maEqMstrAsmbList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrAsmbList" path="/dream/asset/list/maEqMstrAsmbList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrAsmbListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_ASMB_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_ASMB_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int EQ_MSTR_ASMB_LIST_INPUT         = 5003;
    /** LIST 저장  */
    public static final int LIST_SAVE 						= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrAsmbListForm maEqMstrAsmbListForm = (MaEqMstrAsmbListForm) form;

        switch (maEqMstrAsmbListForm.getStrutsAction())
        {
        
            case MaEqMstrAsmbListAction.EQ_MSTR_ASMB_LIST_FIND:
                findAsmbList(request,response, maEqMstrAsmbListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrAsmbListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrAsmbListForm.getListId(), maEqMstrAsmbListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrAsmbListAction.EQ_MSTR_ASMB_LIST_DELETE:
            	deleteAsmbList(request,maEqMstrAsmbListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrAsmbListAction.EQ_MSTR_ASMB_LIST_INPUT:
                inputAsmbList(request,maEqMstrAsmbListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaEqMstrAsmbListAction.LIST_SAVE:
            	saveList(request,response, maEqMstrAsmbListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaEqMstrAsmbListAction.BASE_GRID_EXPORT:
            	findAsmbList(request,response, maEqMstrAsmbListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrAsmbListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrAsmbListForm
     * @throws Exception
     */
    private void findAsmbList(HttpServletRequest request,HttpServletResponse response, MaEqMstrAsmbListForm maEqMstrAsmbListForm, boolean excelExport) throws Exception
    {
        MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) getBean("maEqMstrAsmbListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrAsmbListForm.getMaEqMstrCommonDTO();
        MaEqMstrAsmbListDTO maEqMstrAsmbListDTO = maEqMstrAsmbListForm.getMaEqMstrAsmbListDTO();
        
        List resultList = JsonUtil.convertorTreeMap(maEqMstrAsmbListService.findAsmbList(maEqMstrCommonDTO,maEqMstrAsmbListDTO, getUser(request)), "0", "EQASMBID", "PEQASMBID", "ORDNO");
        
        if(excelExport) super.makeTreeGridExport(resultList, request, response,maEqMstrAsmbListForm.getListId(),maEqMstrAsmbListForm.getCurrentPageId(), maEqMstrAsmbListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, maEqMstrAsmbListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrAsmbListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrAsmbListForm
     * @throws Exception
     */
    private void deleteAsmbList(HttpServletRequest request, MaEqMstrAsmbListForm maEqMstrAsmbListForm) throws Exception
    {
    	MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) getBean("maEqMstrAsmbListService");
        
    	String[] deleteRows = maEqMstrAsmbListForm.getSelectRows();
    
    	maEqMstrAsmbListService.deleteAsmbList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }

    /**
     * input
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrAsmbListForm
     * @throws Exception
     */
    private void inputAsmbList(HttpServletRequest request, MaEqMstrAsmbListForm maEqMstrAsmbListForm) throws Exception
    {
        MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) getBean("maEqMstrAsmbListService");
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrAsmbListForm.getMaEqMstrCommonDTO();
        MaEqMstrAsmbDetailDTO maEqMtrAsmbDetailDTO = maEqMstrAsmbListForm.getMaEqMstrAsmbDetailDTO();
        
        maEqMstrAsmbListService.inputAsmbList(maEqMstrCommonDTO, maEqMtrAsmbDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void saveList(HttpServletRequest request,HttpServletResponse response, MaEqMstrAsmbListForm maEqMstrAsmbListForm) throws Exception
    {
        MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) getBean("maEqMstrAsmbListService");
        
        List<Map> gridList = CommonUtil.setGridMap(request);

        maEqMstrAsmbListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
    
}