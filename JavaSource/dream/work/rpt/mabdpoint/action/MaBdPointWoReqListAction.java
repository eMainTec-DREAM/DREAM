package dream.work.rpt.mabdpoint.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoReqListDTO;
import dream.work.rpt.mabdpoint.form.MaBdPointWoReqListForm;
import dream.work.rpt.mabdpoint.service.MaBdPointWoReqListService;

/**
 * 이상점검조치 - 작업요청 목록 action
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maBdPointWoReqList" name="maBdPointWoReqListForm"
 *                input="/dream/work/rpt/bdpoint/maBdPointWoReqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBdPointWoReqList" path="/dream/work/rpt/bdpoint/maBdPointWoReqList.jsp"
 *                        redirect="false"
 */
public class MaBdPointWoReqListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND			= 1001;
    /** 삭제 */
    public static final int LIST_DELETE     	= 7002;
    /** 신규 작업요청 저장 */
    public static final int LIST_WOREQ_INPUT	= 5001;
    /** 기존 작업요청 연결 */
    public static final int LIST_WOREQ_LINK		= 5002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBdPointWoReqListForm maBdPointWoReqListForm = (MaBdPointWoReqListForm) form;

        //super.updateAudit(maBdPointWoReqListForm.getMaBdPointWoReqListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maBdPointWoReqListForm.getStrutsAction())
        {
            case MaBdPointWoReqListAction.LIST_FIND:
            	findList(request, maBdPointWoReqListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBdPointWoReqListAction.LIST_DELETE:
            	deleteList(request, maBdPointWoReqListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaBdPointWoReqListAction.LIST_WOREQ_INPUT:
            	insertWoReqList(request, maBdPointWoReqListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBdPointWoReqListAction.LIST_WOREQ_LINK:
            	woReqLinkList(request, maBdPointWoReqListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBdPointWoReqListAction.BASE_SET_HEADER:
                setHeader(request, response, maBdPointWoReqListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBdPointWoReqListAction.BASE_GRID_EXPORT:
            	findList(request, maBdPointWoReqListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maBdPointWoReqList");
                break;
        }

        return returnActionForward;
    }

    private void deleteList(HttpServletRequest request, MaBdPointWoReqListForm maBdPointWoReqListForm)
    {
        MaBdPointWoReqListService maBdPointWoReqListService = (MaBdPointWoReqListService) getBean("maBdPointWoReqListService");

        String[] deleteRows = maBdPointWoReqListForm.getDeleteRows();    // sheet 내역

        maBdPointWoReqListService.deleteList(deleteRows,getUser(request).getCompNo());

        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaBdPointWoReqListForm maBdPointWoReqListForm) throws IOException
    {
        super.setHeader(request, response, maBdPointWoReqListForm.getListId(), maBdPointWoReqListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     *
     * @param request
     * @param maBdPointWoReqListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaBdPointWoReqListForm maBdPointWoReqListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaBdPointWoReqListService maBdPointWoReqListService = (MaBdPointWoReqListService) getBean("maBdPointWoReqListService");

    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointWoReqListForm.getMaBdPointCommonDTO();
    	MaBdPointWoReqListDTO maBdPointWoReqListDTO = maBdPointWoReqListForm.getMaBdPointWoReqListDTO();

    	//Paging
        maBdPointWoReqListDTO.setIsLoadMaxCount("Y".equals(maBdPointWoReqListForm.getIsLoadMaxCount())?true:false);
        maBdPointWoReqListDTO.setFirstRow(maBdPointWoReqListForm.getFirstRow());
        maBdPointWoReqListDTO.setOrderBy(maBdPointWoReqListForm.getOrderBy());
        maBdPointWoReqListDTO.setDirection(maBdPointWoReqListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maBdPointWoReqListService.findList(maBdPointCommonDTO, maBdPointWoReqListDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maBdPointWoReqListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maBdPointWoReqListService.findTotalCount(maBdPointCommonDTO, maBdPointWoReqListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maBdPointWoReqListForm.getListId(),maBdPointWoReqListForm.getCurrentPageId(), maBdPointWoReqListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * 신규 작업요청
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointWoPlanListForm
     * @throws Exception
     */
    private void insertWoReqList(HttpServletRequest request, MaBdPointWoReqListForm maBdPointWoReqListForm) throws Exception {
    	// Service 객체 생성
    	MaBdPointWoReqListService maBdPointWoReqListService = (MaBdPointWoReqListService) getBean("maBdPointWoReqListService");
    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointWoReqListForm.getMaBdPointCommonDTO();
    	MaBdPointWoReqListDTO maBdPointWoReqListDTO = maBdPointWoReqListForm.getMaBdPointWoReqListDTO();
    	
    	maBdPointWoReqListService.insertWoNgPointRes(maBdPointCommonDTO, maBdPointWoReqListDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    /**
     * 기존 요청 연결 (작업요청)
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointWoPlanListForm
     * @throws Exception
     */
    private void woReqLinkList(HttpServletRequest request, MaBdPointWoReqListForm maBdPointWoReqListForm) throws Exception {
    	// Service 객체 생성
    	MaBdPointWoReqListService maBdPointWoReqListService = (MaBdPointWoReqListService) getBean("maBdPointWoReqListService");
    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointWoReqListForm.getMaBdPointCommonDTO();
    	MaBdPointWoReqListDTO maBdPointWoReqListDTO = maBdPointWoReqListForm.getMaBdPointWoReqListDTO();
    	
    	maBdPointWoReqListService.linkWoReq(maBdPointCommonDTO, maBdPointWoReqListDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }

}
