package dream.mgr.usrcd.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.form.MaCdUsrCdListForm;
import dream.mgr.usrcd.service.MaCdUsrCdListService;

/**
 * 사용자코드관리 Action
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @struts:action path="/maCdUsrCdList" name="maCdUsrCdListForm"
 *                input="/dream/mgr/usrcd/maCdUsrCdList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCdUsrCdList" path="/dream/mgr/usrcd/maCdUsrCdList.jsp"
 *                        redirect="false"           
 */
public class MaCdUsrCdListAction extends AuthAction
{ 
    /** 유형별 세부코드 grid 조회 */
    public static final int CDUSR_CD_FINDSHEET   = 8001;
    /** Grid 삭제 */
    public static final int CDUSR_CD_GRID_DELETE = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaCdUsrCdListForm maCdUsrCdListForm = (MaCdUsrCdListForm) form;
        
        super.updateAudit(maCdUsrCdListForm.getMaCdUsrCommonDTO().getAuditKey()==""?maCdUsrCdListForm.getMaCdUsrCommonDTO().getAuditKey():maCdUsrCdListForm.getMaCdUsrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maCdUsrCdListForm.getStrutsAction()) 
        {
            case MaCdUsrListAction.BASE_SET_HEADER:
                setHeader(request, response, maCdUsrCdListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaCdUsrCdListAction.CDUSR_CD_FINDSHEET:
            	findList(request, response, maCdUsrCdListForm, false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaCdUsrCdListAction.CDUSR_CD_GRID_DELETE:
            	deleteList(request, maCdUsrCdListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;   
            case MaCdUsrCdListAction.BASE_GRID_EXPORT:
                findList(request, response, maCdUsrCdListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maCdUsrCdList");
                break;
        }
        return returnActionForward;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaCdUsrCdListForm maCdUsrCdListForm) throws IOException
    {
        super.setHeader(request, response, maCdUsrCdListForm.getListId(), maCdUsrCdListForm.getCurrentPageId()); 
    }
    
    /**
     * 코드 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maCdUsrCdListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response,  MaCdUsrCdListForm maCdUsrCdListForm, boolean excelExport) throws Exception
    {
        MaCdUsrCdListService maCdUsrCdListService = (MaCdUsrCdListService) getBean("maCdUsrCdListService");        

        MaCdUsrCommonDTO maCdUsrCommonDTO = maCdUsrCdListForm.getMaCdUsrCommonDTO();
        
        // 로긴 회사코드를 셋팅한다.
        maCdUsrCommonDTO.setCompNo((getUser(request).getCompNo()));
      
        List resultList = maCdUsrCdListService.findSheet(maCdUsrCommonDTO,getUser(request), excelExport);
      
        if(excelExport) super.makeTreeGridExport(resultList, request, response, maCdUsrCdListForm.getListId(),maCdUsrCdListForm.getCurrentPageId(), maCdUsrCdListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, maCdUsrCdListForm.getListId());
    }
    
    /**
     * delete Grid 
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaCdUsrCdListForm maCdUsrCdListForm) throws Exception
    {
    	MaCdUsrCdListService maCdUsrCdListService = (MaCdUsrCdListService) getBean("maCdUsrCdListService");        

    	String[] deleteRows = maCdUsrCdListForm.getDeleteRows();    // sheet 내역
        
        maCdUsrCdListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
    
}