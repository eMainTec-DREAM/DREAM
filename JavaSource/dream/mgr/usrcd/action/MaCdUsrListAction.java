package dream.mgr.usrcd.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.form.MaCdUsrListForm;
import dream.mgr.usrcd.service.MaCdUsrListService;

/**		
 * 사용자코드관리 Action
 * @author ssong
 * @version $Id: MaCdUsrListAction.java, $
 * @since 1.0
 * 
 * @struts:action path="/maCdUsrList" name="maCdUsrListForm"
 *                input="/dream/mgr/usrcd/maCdUsrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCdUsrList" path="/dream/mgr/usrcd/maCdUsrList.jsp"
 *                        redirect="false"           
 */
public class MaCdUsrListAction extends AuthAction
{ 
    /** grid 조회할때 */
    public static final int CDUSR_FINDSHEET   = 1001;
    /** 삭제 */
    public static final int CDUSR_LIST_DELETE = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaCdUsrListForm maCdUsrListForm = (MaCdUsrListForm) form;
        
        switch (maCdUsrListForm.getStrutsAction())
        {
            case MaCdUsrListAction.CDUSR_FINDSHEET:
                this.findSheet(request, maCdUsrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break; 
            case MaCdUsrListAction.BASE_SET_HEADER:
                setHeader(request, response, maCdUsrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;   
            case MaCdUsrListAction.CDUSR_LIST_DELETE:
            	deleteList(request, maCdUsrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case BASE_GRID_EXPORT:
                this.findSheet(request, maCdUsrListForm, response, true);
                returnActionForward = new ActionForward("/gridExport", false);
                break;
            default:
                returnActionForward = mapping.findForward("maCdUsrList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaCdUsrListForm maCdUsrListForm) throws IOException
    {
        super.setHeader(request, response, maCdUsrListForm.getListId(), maCdUsrListForm.getCurrentPageId()); 
    }
    
    /**
     * 코드 조회
     * @author  ssong
     * @version $Id: MaCdUsrListAction.java $
     * @since   1.0
     * 
     * @param request
     * @param maCdUsrListForm
     */
    private void findSheet(HttpServletRequest request,  MaCdUsrListForm maCdUsrListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
        MaCdUsrListService maCdUsrListService = (MaCdUsrListService) getBean("maCdUsrListService");        

        MaCdUsrCommonDTO maCdUsrCommonDTO = maCdUsrListForm.getMaCdUsrCommonDTO();
        
    	// 로긴 comp_no 를 셋팅한다.
        maCdUsrCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
        
        //Paging
        maCdUsrCommonDTO.setIsLoadMaxCount("Y".equals(maCdUsrListForm.getIsLoadMaxCount())?true:false);
        maCdUsrCommonDTO.setFirstRow(maCdUsrListForm.getFirstRow());
        maCdUsrCommonDTO.setOrderBy(maCdUsrListForm.getOrderBy());
        maCdUsrCommonDTO.setDirection(maCdUsrListForm.getDirection());

        List resultList = maCdUsrListService.findSheet(maCdUsrCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maCdUsrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maCdUsrListService.findTotalCount(maCdUsrCommonDTO,getUser(request));
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maCdUsrListForm);//.makeGridExport(resultList, request, response, maCdUsrListForm.getListId(),maCdUsrListForm.getCurrentPageId(), maCdUsrListForm.getFileName());
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author ssong 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaCdUsrListForm maCdUsrListForm) throws Exception
    {
    	
    	MaCdUsrListService maCdUsrListService = (MaCdUsrListService) getBean("maCdUsrListService");        

    	String[] deleteRows = maCdUsrListForm.getDeleteRows();    // sheet 내역
        
        maCdUsrListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}