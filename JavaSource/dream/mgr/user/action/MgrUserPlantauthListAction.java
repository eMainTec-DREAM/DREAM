package dream.mgr.user.action;


import java.io.IOException;
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
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;
import dream.mgr.user.form.MgrUserPlantauthListForm;
import dream.mgr.user.service.MgrUserPlantauthListService;

/**
 * 사용자 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/mgrUserPlantauthList" name="mgrUserPlantauthListForm"
 *                input="/dream/mgr/user/mgrUserPlantauthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUserPlantauthList" path="/dream/mgr/user/mgrUserPlantauthList.jsp"
 *                        redirect="false"
 */
public class MgrUserPlantauthListAction extends AuthAction
{
    /** 조회 */
    public static final int USERPLANT_LIST_FIND      = 8001;
    /** 삭제 */
    public static final int USERPLANT_LIST_DELETE    = 7002;
    /** 저장 */
    public static final int LIST_SAVE			     = 1003;
    /** 일괄등록 */
    public static final int LIST_INSERT			     = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrUserPlantauthListForm mgrUserPlantauthListForm = (MgrUserPlantauthListForm) form;
        
        super.updateAudit(mgrUserPlantauthListForm.getMaUserCommonDTO().getAuditKey()==""?mgrUserPlantauthListForm.getMaUserCommonDTO().getAuditKey():mgrUserPlantauthListForm.getMaUserCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrUserPlantauthListForm.getStrutsAction())
        {
            case MgrUserPlantauthListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrUserPlantauthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUserPlantauthListAction.USERPLANT_LIST_FIND:
                findList(request, response, mgrUserPlantauthListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;               
            case MgrUserPlantauthListAction.LIST_SAVE:
            	saveList(request, response, mgrUserPlantauthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;               
            case MgrUserPlantauthListAction.LIST_INSERT:
                insertList(request, mgrUserPlantauthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrUserPlantauthListAction.USERPLANT_LIST_DELETE:
            	deleteList(request, mgrUserPlantauthListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUserPlantauthListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrUserPlantauthListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrUserPlantauthList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrUserPlantauthListForm mgrUserPlantauthListForm) throws IOException
    {
        super.setHeader(request, response, mgrUserPlantauthListForm.getListId(), mgrUserPlantauthListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrUserPlantauthListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrUserPlantauthListForm mgrUserPlantauthListForm, boolean excelExport)  throws IOException
    {
    	MgrUserPlantauthListService mgrUserPlantauthListService = (MgrUserPlantauthListService) getBean("mgrUserPlantauthListService");        

    	MaUserCommonDTO maUserCommonDTO = mgrUserPlantauthListForm.getMaUserCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	maUserCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	maUserCommonDTO.setCompNo((getUser(request).getCompNo()));
    	maUserCommonDTO.setIsLoadMaxCount("Y".equals(mgrUserPlantauthListForm.getIsLoadMaxCount())?true:false);
    	maUserCommonDTO.setFirstRow(mgrUserPlantauthListForm.getFirstRow());
    	maUserCommonDTO.setOrderBy(mgrUserPlantauthListForm.getOrderBy());
    	maUserCommonDTO.setDirection(mgrUserPlantauthListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = mgrUserPlantauthListService.findUserList(maUserCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(mgrUserPlantauthListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrUserPlantauthListService.findTotalCount(maUserCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrUserPlantauthListForm.getListId(),mgrUserPlantauthListForm.getCurrentPageId(), mgrUserPlantauthListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUserPlantauthListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MgrUserPlantauthListForm mgrUserPlantauthListForm) throws Exception
    {
        MgrUserPlantauthListService mgrUserPlantauthListService = (MgrUserPlantauthListService) getBean("mgrUserPlantauthListService");        
 
        String[] deleteRows = mgrUserPlantauthListForm.getDeleteRows();    // sheet 내역
        
        mgrUserPlantauthListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUserPlantauthListForm
     * @param request
     */
    private void insertList(HttpServletRequest request, MgrUserPlantauthListForm mgrUserPlantauthListForm) throws Exception
    {
    	MgrUserPlantauthListService mgrUserPlantauthListService = (MgrUserPlantauthListService) getBean("mgrUserPlantauthListService");        
    	MaUserCommonDTO maUserCommonDTO = mgrUserPlantauthListForm.getMaUserCommonDTO();
    	MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = mgrUserPlantauthListForm.getMgrUserPlantauthDetailDTO();
    	
    	mgrUserPlantauthListService.insertList(maUserCommonDTO, mgrUserPlantauthDetailDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    /**
     * list save
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUserPlantauthListForm
     * @param request
     */
    private void saveList(HttpServletRequest request, HttpServletResponse response, MgrUserPlantauthListForm mgrUserPlantauthListForm) throws Exception
    {
    	MgrUserPlantauthListService mgrUserPlantauthListService = (MgrUserPlantauthListService) getBean("mgrUserPlantauthListService");        
    	
    	List<Map> gridList = CommonUtil.setGridMap(request);
    	
    	mgrUserPlantauthListService.savePointList(gridList, getUser(request));
    	
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
}
