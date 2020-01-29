package dream.part.pur.req.action;


import java.io.IOException;
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
import dream.part.pur.req.dto.MaPtReqCommonDTO;
import dream.part.pur.req.form.MaPtPurReqListForm;
import dream.part.pur.req.service.MaPtPurReqListService;

/**
 * 부품수리 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtPurReqList" name="maPtPurReqListForm"
 *                input="/dream/part/pur/req/maPtPurReqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtPurReqList" path="/dream/part/pur/req/maPtPurReqList.jsp"
 *                        redirect="false"
 */
public class MaPtPurReqListAction extends AuthAction
{
    /** 조회 */
    public static final int PTREQ_LIST_FIND     = 8001;
    /** 삭제 */
    public static final int PTREQ_LIST_DELETE   = 7002;
    /** Edit */
    public static final int PTREQ_LIST_SAVE     = 6001;
    /** 요청 */
    public static final int PART_REQUEST        = 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtPurReqListForm maPtPurReqListForm = (MaPtPurReqListForm) form;
        
        switch (maPtPurReqListForm.getStrutsAction())
        {
            case MaPtPurReqListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtPurReqListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtPurReqListAction.BASE_GRID_EXPORT:
                findPtReqList(request, maPtPurReqListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaPtPurReqListAction.PTREQ_LIST_SAVE:
            	saveList(request, response, maPtPurReqListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtPurReqListAction.PTREQ_LIST_FIND:
                findPtReqList(request, maPtPurReqListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtPurReqListAction.PTREQ_LIST_DELETE:
            	deletePtReq(request, maPtPurReqListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtPurReqListAction.PART_REQUEST:
                updateStatus(request, response, maPtPurReqListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtPurReqList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtPurReqListForm maPtPurReqListForm) throws IOException
    {
        super.setHeader(request, response, maPtPurReqListForm.getListId(), maPtPurReqListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtPurReqListForm
     * @throws Exception
     */
    private void findPtReqList(HttpServletRequest request, MaPtPurReqListForm maPtPurReqListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPtPurReqListService maPtPurReqListService = (MaPtPurReqListService) getBean("maPtPurReqListService");        

    	MaPtReqCommonDTO maPtReqCommonDTO = maPtPurReqListForm.getMaPtReqCommonDTO();
    	maPtReqCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        //Paging
    	maPtReqCommonDTO.setIsLoadMaxCount("Y".equals(maPtPurReqListForm.getIsLoadMaxCount())?true:false);
    	maPtReqCommonDTO.setFirstRow(maPtPurReqListForm.getFirstRow());
    	maPtReqCommonDTO.setOrderBy(maPtPurReqListForm.getOrderBy());
    	maPtReqCommonDTO.setDirection(maPtPurReqListForm.getDirection());
    	User user = getUser(request);
        
        //리스트를 조회한다.
        List resultList = maPtPurReqListService.findList(maPtReqCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPtPurReqListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtPurReqListService.findTotalCount(maPtReqCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPtPurReqListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * grid save
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param request
     * @param response
     * @param maPtPurReqListForm
     * @throws Exception
     */
    private void saveList(HttpServletRequest request, HttpServletResponse response, MaPtPurReqListForm maPtPurReqListForm) throws Exception 
    {
        MaPtPurReqListService maPtPurReqListService = (MaPtPurReqListService) getBean("maPtPurReqListService");
        
        List<Map> gridList = CommonUtil.setGridMap(request);

        maPtPurReqListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
    /**
     * update status
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param request
     * @param response 
     * @param maPtPurReqListForm
     */
    private void updateStatus(HttpServletRequest request, HttpServletResponse response, MaPtPurReqListForm maPtPurReqListForm) throws Exception
    {
        MaPtPurReqListService maPtPurReqListService = (MaPtPurReqListService) getBean("maPtPurReqListService", request);
        String[] updateRows = maPtPurReqListForm.getDeleteRows();
        maPtPurReqListService.updateStatus(getUser(request), updateRows);
        
        setAjaxStatus(request);
    }
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqListForm
     * @param request
     */
    private void deletePtReq(HttpServletRequest request, MaPtPurReqListForm maPtPurReqListForm) throws Exception
    {
    	MaPtPurReqListService maPtPurReqListService = (MaPtPurReqListService) getBean("maPtPurReqListService");
    	String[] deleteRows = maPtPurReqListForm.getDeleteRows();    // sheet 내역
        
        maPtPurReqListService.deleteList(getUser(request), deleteRows);
        
        setAjaxStatus(request);
    }
}
