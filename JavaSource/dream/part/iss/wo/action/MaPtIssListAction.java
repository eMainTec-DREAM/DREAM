package dream.part.iss.wo.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;
import dream.part.iss.wo.form.MaPtIssListForm;
import dream.part.iss.wo.service.MaPtIssListService;

/**
 * 자재출고확정 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtIssList" name="maPtIssListForm"
 *                input="/dream/part/iss/wo/maPtIssList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtIssList" path="/dream/part/iss/wo/maPtIssList.jsp"
 *                        redirect="false"
 */
public class MaPtIssListAction extends AuthAction
{
    /** 조회 */
    public static final int PTISS_LIST_FIND 	= 8001;
    /** 삭제 */
    public static final int PTISS_LIST_DELETE   = 7002;
    /** Serial check */
    public static final int PTISS_SERIAL_CHECK  = 1003;
    /** 저장 */
    public static final int PTISS_LIST_INPUT    = 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtIssListForm maPtIssListForm = (MaPtIssListForm) form;
        
        super.updateAudit(maPtIssListForm.getMaPtIssCommonDTO().getAuditKey()==""?maPtIssListForm.getMaPtIssDetailDTO().getAuditKey():maPtIssListForm.getMaPtIssCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtIssListForm.getStrutsAction())
        {
            case MaPtIssListAction.PTISS_LIST_FIND:
            	findPtIssList(request, maPtIssListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtIssListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtIssListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtIssListAction.BASE_GRID_EXPORT:
            	findPtIssList(request, maPtIssListForm,response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaPtIssListAction.PTISS_LIST_DELETE:
                deleteKey(request, maPtIssListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            /*case MaPtIssListAction.PTISS_SERIAL_CHECK:
                serialCheck(request, maPtIssListForm);
                returnActionForward = mapping.findForward("maPtIssList");
                break;*/
            case MaPtIssListAction.PTISS_LIST_INPUT:
                insertPtIssList(request, maPtIssListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtIssList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtIssListForm maPtIssListForm) throws IOException
    {
        super.setHeader(request, response, maPtIssListForm.getListId(), maPtIssListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtIssListForm
     * @throws ServiceException 
     * @throws Exception
     */
    private void findPtIssList(HttpServletRequest request, MaPtIssListForm maPtIssListForm, HttpServletResponse response, boolean excelExport) throws IOException, ServiceException
    {
    	MaPtIssListService maPtIssListService = (MaPtIssListService) getBean("maPtIssListService");        

    	MaPtIssCommonDTO maPtIssCommonDTO = maPtIssListForm.getMaPtIssCommonDTO();
    	maPtIssCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPtIssCommonDTO.setIsLoadMaxCount("Y".equals(maPtIssListForm.getIsLoadMaxCount())?true:false);
    	maPtIssCommonDTO.setFirstRow(maPtIssListForm.getFirstRow());
    	maPtIssCommonDTO.setOrderBy(maPtIssListForm.getOrderBy());
    	maPtIssCommonDTO.setDirection(maPtIssListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtIssListService.findPtIssList(maPtIssCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maPtIssListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtIssListService.findTotalCount(maPtIssCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPtIssListForm.getListId(),maPtIssListForm.getCurrentPageId(), maPtIssListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    
    private void deleteKey(HttpServletRequest request, MaPtIssListForm maPtIssListForm) throws Exception
    {
        MaPtIssListService maPtIssListService = (MaPtIssListService) getBean("maPtIssListService");        
        
        String[] deleteRows = maPtIssListForm.getDeleteRows();
        
        maPtIssListService.deleteKey(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtIssListForm
     */
    private void insertPtIssList(HttpServletRequest request, MaPtIssListForm maPtIssListForm) throws Exception
    {
        MaPtIssListService maPtIssListService = (MaPtIssListService) getBean("maPtIssListService");
        
        MaPtIssDetailDTO maPtIssDetailDTO = maPtIssListForm.getMaPtIssDetailDTO();
        
        maPtIssDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtIssListService.insertPtIssList(maPtIssDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}
