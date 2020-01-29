package dream.req.work.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResListDTO;
import dream.req.work.form.MaWoReqResListForm;
import dream.req.work.service.MaWoReqResListService;

/**
 * 작업요청서-처리사항 목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maWoReqResList" name="maWoReqResListForm"
 *                input="/dream/req/work/maWoReqResList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqInvRecWorkResList" name="maWoReqResListForm"
 *                input="/dream/req/work/reqInvRecWorkResList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoReqResList" path="/dream/req/work/maWoReqResList.jsp"
 *                        redirect="false"
 */
public class MaWoReqResListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    /** 삭제 */
    public static final int LIST_DELETE       = 7002;
    /** 기존 W/O 연결 (작업결과)*/
    public static final int LIST_WO_LINK      = 5003;
    /** 기존 투자 연결 (투자결과) */
    public static final int LIST_INVT_LINK	  = 5004;
    /** 기존 W/O 연결 (작업계획)*/
    public static final int LIST_WOPLAN_LINK  = 5005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoReqResListForm maWoReqResListForm = (MaWoReqResListForm) form;
        
        switch (maWoReqResListForm.getStrutsAction())
        {
            case MaWoReqResListAction.LIST_FIND:
            	findList(request, maWoReqResListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoReqResListAction.LIST_DELETE:
            	deleteList(request, maWoReqResListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoReqResListAction.LIST_WO_LINK:
            	linkList(request, maWoReqResListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoReqResListAction.LIST_WOPLAN_LINK:
            	woPlanLinkList(request, maWoReqResListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoReqResListAction.LIST_INVT_LINK:
            	invtLinkList(request, maWoReqResListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoReqResListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoReqResListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoReqResListAction.BASE_GRID_EXPORT:
            	findList(request, maWoReqResListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void linkList(HttpServletRequest request, MaWoReqResListForm maWoReqResListForm) throws Exception {
    	// Service 객체 생성
        MaWoReqResListService maWoReqResListService = (MaWoReqResListService) getBean("maWoReqResListService");        

        String status = maWoReqResListService.linkWo(maWoReqResListForm.getMaWoReqCommonDTO(), getUser(request));
        
        maWoReqResListForm.getMaWoReqResDetailDTO().setResStatusId(status);
        
        setAjaxDesc(request, status);
	}
    
    /**
     * 기존 투자 연결
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoReqResListForm
     * @throws Exception
     */
    private void invtLinkList(HttpServletRequest request, MaWoReqResListForm maWoReqResListForm) throws Exception {
    	// Service 객체 생성
        MaWoReqResListService maWoReqResListService = (MaWoReqResListService) getBean("maWoReqResListService");        

        maWoReqResListService.linkInvt(maWoReqResListForm.getMaWoReqCommonDTO(), getUser(request));
        
        setAjaxStatus(request);
	}
    
    /**
     * 기존 w/o 연결 (작업계획)
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoReqResListForm
     * @throws Exception
     */
    private void woPlanLinkList(HttpServletRequest request, MaWoReqResListForm maWoReqResListForm) throws Exception {
    	// Service 객체 생성
    	MaWoReqResListService maWoReqResListService = (MaWoReqResListService) getBean("maWoReqResListService");        
    	
    	maWoReqResListService.linkWoPlan(maWoReqResListForm.getMaWoReqCommonDTO(), getUser(request));
    	
    	setAjaxStatus(request);
    }

	private void deleteList(HttpServletRequest request, MaWoReqResListForm maWoReqResListForm) throws Exception
    {
        MaWoReqResListService maWoReqResListService = (MaWoReqResListService) getBean("maWoReqResListService");        
                
        String[] deleteRows = maWoReqResListForm.getDeleteRows();    // sheet 내역
        
        maWoReqResListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoReqResListForm maWoReqResListForm) throws IOException
    {
        super.setHeader(request, response, maWoReqResListForm.getListId(), maWoReqResListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoReqResListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaWoReqResListForm maWoReqResListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaWoReqResListService maWoReqResListService = (MaWoReqResListService) getBean("maWoReqResListService");        

    	MaWoReqCommonDTO maWoReqCommonDTO = maWoReqResListForm.getMaWoReqCommonDTO();
    	maWoReqCommonDTO.setCompNo(getUser(request).getCompNo());
    	MaWoReqResListDTO maWoReqResListDTO = maWoReqResListForm.getMaWoReqResListDTO();
    	
        //Paging
        maWoReqResListDTO.setIsLoadMaxCount("Y".equals(maWoReqResListForm.getIsLoadMaxCount())?true:false);
        maWoReqResListDTO.setFirstRow(maWoReqResListForm.getFirstRow());
        maWoReqResListDTO.setOrderBy(maWoReqResListForm.getOrderBy());
        maWoReqResListDTO.setDirection(maWoReqResListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maWoReqResListService.findList(maWoReqCommonDTO,maWoReqResListDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoReqResListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoReqResListService.findTotalCount(maWoReqCommonDTO,maWoReqResListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoReqResListForm.getListId(),maWoReqResListForm.getCurrentPageId(), maWoReqResListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, maWoReqResListForm.getListId());
    }
    
}
