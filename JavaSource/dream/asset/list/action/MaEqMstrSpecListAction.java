package dream.asset.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;
import dream.asset.list.form.MaEqMstrSpecListForm;
import dream.asset.list.service.MaEqMstrSpecListService;

/**
 * 설비제원(스펙) 목록
 * @author  kim21017
 * @version $Id: MaEqMstrSpecListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrSpecList" name="maEqMstrSpecListForm"
 *                input="/dream/asset/list/maEqMstrSpecList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrSpecList" path="/dream/asset/list/maEqMstrSpecList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrSpecListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_SPEC_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_SPEC_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int EQ_MSTR_SPEC_LIST_INPUT         = 5003;
    /** LIST 저장  */
    public static final int LIST_SAVE 						= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrSpecListForm maEqMstrSpecListForm = (MaEqMstrSpecListForm) form;
        
        switch (maEqMstrSpecListForm.getStrutsAction())
        {
            case MaEqMstrSpecListAction.EQ_MSTR_SPEC_LIST_FIND:
                findSpecList(request,response, maEqMstrSpecListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrSpecListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrSpecListForm.getListId(), maEqMstrSpecListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrSpecListAction.EQ_MSTR_SPEC_LIST_DELETE:
            	deleteSpecList(request,maEqMstrSpecListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrSpecListAction.BASE_GRID_EXPORT:
            	findSpecList(request,response, maEqMstrSpecListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaEqMstrSpecListAction.EQ_MSTR_SPEC_LIST_INPUT:
            	inputEqCtgSpecList(request,response, maEqMstrSpecListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrSpecListAction.LIST_SAVE:
            	saveList(request,response, maEqMstrSpecListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMstrSpecList");
                break;
        }

        return returnActionForward;
    }
    
	/**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrSpecListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrSpecListForm
     * @throws Exception
     */
    private void findSpecList(HttpServletRequest request,HttpServletResponse response, MaEqMstrSpecListForm maEqMstrSpecListForm, boolean excelExport) throws Exception
    {
        MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) getBean("maEqMstrSpecListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrSpecListForm.getMaEqMstrCommonDTO();
        MaEqMstrSpecListDTO maEqMstrSpecListDTO = maEqMstrSpecListForm.getMaEqMstrSpecListDTO();
        
        //Paging
        maEqMstrSpecListDTO.setIsLoadMaxCount("Y".equals(maEqMstrSpecListForm.getIsLoadMaxCount())?true:false);
        maEqMstrSpecListDTO.setFirstRow(maEqMstrSpecListForm.getFirstRow());
        maEqMstrSpecListDTO.setOrderBy(maEqMstrSpecListForm.getOrderBy());
        maEqMstrSpecListDTO.setDirection(maEqMstrSpecListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maEqMstrSpecListService.findSpecList(maEqMstrCommonDTO, maEqMstrSpecListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maEqMstrSpecListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrSpecListService.findTotalCount(maEqMstrCommonDTO, maEqMstrSpecListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maEqMstrSpecListForm.getListId(),maEqMstrSpecListForm.getCurrentPageId(), maEqMstrSpecListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrSpecListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrSpecListForm
     * @throws Exception
     */
    private void deleteSpecList(HttpServletRequest request, MaEqMstrSpecListForm maEqMstrSpecListForm) throws Exception
    {
    	MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) getBean("maEqMstrSpecListService");
        
    	String[] deleteRows = maEqMstrSpecListForm.getDeleteRows();
    
    	maEqMstrSpecListService.deleteSpecList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    private void saveList(HttpServletRequest request,HttpServletResponse response, MaEqMstrSpecListForm maEqMstrSpecListForm) throws Exception
    {
    	MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) getBean("maEqMstrSpecListService");

        List<Map> gridList = CommonUtil.setGridMap(request);

        maEqMstrSpecListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }

    private void inputEqCtgSpecList(HttpServletRequest request, HttpServletResponse response, MaEqMstrSpecListForm maEqMstrSpecListForm) throws Exception {
    	MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) getBean("maEqMstrSpecListService");
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrSpecListForm.getMaEqMstrCommonDTO();
    	MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = maEqMstrSpecListForm.getMaEqMstrSpecDetailDTO();
    	
    	User user = getUser(request);
    	
    	maEqMstrSpecListService.inputEqCtgSpecList(maEqMstrCommonDTO,maEqMstrSpecDetailDTO, user);		
    	
    	setAjaxStatus(request);
	}

}
