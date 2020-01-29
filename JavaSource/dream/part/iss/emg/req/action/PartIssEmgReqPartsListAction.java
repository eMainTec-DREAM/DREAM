package dream.part.iss.emg.req.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;
import dream.part.iss.emg.req.form.PartIssEmgReqPartsListForm;
import dream.part.iss.emg.req.service.PartIssEmgReqPartsListService;

/**
 * 긴급출고 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/partIssEmgReqPartsList" name="partIssEmgReqPartsListForm"
 *                input="/dream/part/iss/emg/req/partIssEmgReqPartsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partIssEmgReqPartsList" path="/dream/part/iss/emg/req/partIssEmgReqPartsList.jsp"
 *                        redirect="false"
 */
public class PartIssEmgReqPartsListAction extends AuthAction
{
    /** 조회 */
    public static final int PTISSEMG_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int PTISSEMG_LIST_DELETE = 7002;
    /** 저장 */
    public static final int PTISSEMG_LIST_INPUT  = 5003;
    /** LIST 저장 */
    public static final int EDIT_LIST_SAVE       = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartIssEmgReqPartsListForm partIssEmgReqPartsListForm = (PartIssEmgReqPartsListForm) form;
        
        switch (partIssEmgReqPartsListForm.getStrutsAction())
        {
            case PartIssEmgReqPartsListAction.PTISSEMG_LIST_FIND:
            	findPtIssEmgList(request, partIssEmgReqPartsListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartIssEmgReqPartsListAction.BASE_SET_HEADER:
                setHeader(request, response, partIssEmgReqPartsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartIssEmgReqPartsListAction.BASE_GRID_EXPORT:
            	findPtIssEmgList(request, partIssEmgReqPartsListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case PartIssEmgReqPartsListAction.PTISSEMG_LIST_DELETE:
                deleteKey(request, partIssEmgReqPartsListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartIssEmgReqPartsListAction.PTISSEMG_LIST_INPUT:
                insertPtIssEmgList(request, partIssEmgReqPartsListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartIssEmgReqPartsListAction.EDIT_LIST_SAVE:
                saveList(request, response, partIssEmgReqPartsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void saveList(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqPartsListForm partIssEmgReqPartsListForm) throws Exception
    {
        PartIssEmgReqPartsListService partIssEmgReqPartsListService = (PartIssEmgReqPartsListService) getBean("partIssEmgReqPartsListService");

        List<Map> gridList = CommonUtil.setGridMap(request);
        
        User user = getUser(request);
        partIssEmgReqPartsListService.saveList(gridList, user);
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqPartsListForm partIssEmgReqPartsListForm) throws IOException
    {
        super.setHeader(request, response, partIssEmgReqPartsListForm.getListId(), partIssEmgReqPartsListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partIssEmgReqPartsListForm
     * @throws ServiceException 
     * @throws Exception
     */
    private void findPtIssEmgList(HttpServletRequest request, PartIssEmgReqPartsListForm partIssEmgReqPartsListForm, HttpServletResponse response, boolean excelExport) throws IOException, ServiceException
    {
    	PartIssEmgReqPartsListService partIssEmgReqPartsListService = (PartIssEmgReqPartsListService) getBean("partIssEmgReqPartsListService");        

    	PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = partIssEmgReqPartsListForm.getPartIssEmgReqCommonDTO();
    	//Paging
    	partIssEmgReqCommonDTO.setIsLoadMaxCount("Y".equals(partIssEmgReqPartsListForm.getIsLoadMaxCount())?true:false);
    	partIssEmgReqCommonDTO.setFirstRow(partIssEmgReqPartsListForm.getFirstRow());
    	partIssEmgReqCommonDTO.setOrderBy(partIssEmgReqPartsListForm.getOrderBy());
    	partIssEmgReqCommonDTO.setDirection(partIssEmgReqPartsListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = partIssEmgReqPartsListService.findPtIssEmgList(partIssEmgReqCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(partIssEmgReqPartsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partIssEmgReqPartsListService.findTotalCount(partIssEmgReqCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,partIssEmgReqPartsListForm.getListId(),partIssEmgReqPartsListForm.getCurrentPageId(), partIssEmgReqPartsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteKey(HttpServletRequest request, PartIssEmgReqPartsListForm partIssEmgReqPartsListForm) throws Exception
    {
        PartIssEmgReqPartsListService partIssEmgReqPartsListService = (PartIssEmgReqPartsListService) getBean("partIssEmgReqPartsListService");        
        
        String[] deleteRows = partIssEmgReqPartsListForm.getDeleteRows();
        
        partIssEmgReqPartsListService.deleteKey(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partIssEmgReqPartsListForm
     */
    private void insertPtIssEmgList(HttpServletRequest request, PartIssEmgReqPartsListForm partIssEmgReqPartsListForm) throws Exception
    {
        PartIssEmgReqPartsListService partIssEmgReqPartsListService = (PartIssEmgReqPartsListService) getBean("partIssEmgReqPartsListService");
        
        PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO = partIssEmgReqPartsListForm.getPartIssEmgReqPartsDetailDTO();
        
        partIssEmgReqPartsListService.insertPtIssEmgList(partIssEmgReqPartsDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}
