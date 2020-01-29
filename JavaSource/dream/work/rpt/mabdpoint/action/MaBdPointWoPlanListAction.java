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
import common.util.CommonUtil;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoPlanListDTO;
import dream.work.rpt.mabdpoint.form.MaBdPointWoPlanListForm;
import dream.work.rpt.mabdpoint.service.MaBdPointWoPlanListService;

/**
 * 이상점검조치 - 작업계획 목록 action
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maBdPointWoPlanList" name="maBdPointWoPlanListForm"
 *                input="/dream/work/rpt/bdpoint/maBdPointWoPlanList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBdPointWoPlanList" path="/dream/work/rpt/bdpoint/maBdPointWoPlanList.jsp"
 *                        redirect="false"
 */
public class MaBdPointWoPlanListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         	= 1001;
    /** 삭제 */
    public static final int LIST_DELETE       	= 7001;
    /** 신규 W/O 생성 (작업계획)*/
    public static final int LIST_WOPLAN_INPUT	= 5001;
    /** 기존 W/O 연결 (작업계획)*/
    public static final int LIST_WOPLAN_LINK  	= 5002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBdPointWoPlanListForm maBdPointWoPlanListForm = (MaBdPointWoPlanListForm) form;
        
        //super.updateAudit(maBdPointWoPlanListForm.getMaBdPointWoPlanListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maBdPointWoPlanListForm.getStrutsAction())
        {
            case MaBdPointWoPlanListAction.LIST_FIND:
            	findList(request, maBdPointWoPlanListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBdPointWoPlanListAction.LIST_DELETE:
            	deleteList(request, maBdPointWoPlanListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaBdPointWoPlanListAction.LIST_WOPLAN_INPUT:
            	insertWoPlanList(request, maBdPointWoPlanListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBdPointWoPlanListAction.LIST_WOPLAN_LINK:
            	woPlanLinkList(request, maBdPointWoPlanListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBdPointWoPlanListAction.BASE_SET_HEADER:
                setHeader(request, response, maBdPointWoPlanListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBdPointWoPlanListAction.BASE_GRID_EXPORT:
            	findList(request, maBdPointWoPlanListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

	private void deleteList(HttpServletRequest request, MaBdPointWoPlanListForm maBdPointWoPlanListForm)
    {
        MaBdPointWoPlanListService maBdPointWoPlanListService = (MaBdPointWoPlanListService) getBean("maBdPointWoPlanListService");        

        String[] deleteRows = maBdPointWoPlanListForm.getDeleteRows();    // sheet 내역
        
        maBdPointWoPlanListService.deleteList(deleteRows,getUser(request).getCompNo());
        
        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaBdPointWoPlanListForm maBdPointWoPlanListForm) throws IOException
    {
        super.setHeader(request, response, maBdPointWoPlanListForm.getListId(), maBdPointWoPlanListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointWoPlanListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaBdPointWoPlanListForm maBdPointWoPlanListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaBdPointWoPlanListService maBdPointWoPlanListService = (MaBdPointWoPlanListService) getBean("maBdPointWoPlanListService");        

    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointWoPlanListForm.getMaBdPointCommonDTO();
    	MaBdPointWoPlanListDTO maBdPointWoPlanListDTO = maBdPointWoPlanListForm.getMaBdPointWoPlanListDTO();
    	
        //Paging
        maBdPointWoPlanListDTO.setIsLoadMaxCount("Y".equals(maBdPointWoPlanListForm.getIsLoadMaxCount())?true:false);
        maBdPointWoPlanListDTO.setFirstRow(maBdPointWoPlanListForm.getFirstRow());
        maBdPointWoPlanListDTO.setOrderBy(maBdPointWoPlanListForm.getOrderBy());
        maBdPointWoPlanListDTO.setDirection(maBdPointWoPlanListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maBdPointWoPlanListService.findList(maBdPointCommonDTO,maBdPointWoPlanListDTO,user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maBdPointWoPlanListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maBdPointWoPlanListService.findTotalCount(maBdPointCommonDTO,maBdPointWoPlanListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,maBdPointWoPlanListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * 신규 w/o 생성 (작업계획)
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointWoPlanListForm
     * @throws Exception
     */
    private void insertWoPlanList(HttpServletRequest request, MaBdPointWoPlanListForm maBdPointWoPlanListForm) throws Exception {
    	// Service 객체 생성
    	MaBdPointWoPlanListService maBdPointWoPlanListService = (MaBdPointWoPlanListService) getBean("maBdPointWoPlanListService");
    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointWoPlanListForm.getMaBdPointCommonDTO();
    	MaBdPointWoPlanListDTO maBdPointWoPlanListDTO = maBdPointWoPlanListForm.getMaBdPointWoPlanListDTO();
    	
    	maBdPointWoPlanListService.insertWoNgPointRes(maBdPointCommonDTO, maBdPointWoPlanListDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
    /**
     * 기존 w/o 연결 (작업계획)
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointWoPlanListForm
     * @throws Exception
     */
    private void woPlanLinkList(HttpServletRequest request, MaBdPointWoPlanListForm maBdPointWoPlanListForm) throws Exception {
    	// Service 객체 생성
    	MaBdPointWoPlanListService maBdPointWoPlanListService = (MaBdPointWoPlanListService) getBean("maBdPointWoPlanListService");
    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointWoPlanListForm.getMaBdPointCommonDTO();
    	MaBdPointWoPlanListDTO maBdPointWoPlanListDTO = maBdPointWoPlanListForm.getMaBdPointWoPlanListDTO();
    	
    	maBdPointWoPlanListService.linkWoPlan(maBdPointCommonDTO, maBdPointWoPlanListDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
}
