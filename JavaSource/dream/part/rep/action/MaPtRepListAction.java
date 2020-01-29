package dream.part.rep.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;
import dream.part.rep.form.MaPtRepListForm;
import dream.part.rep.service.MaPtRepListService;

/**
 * 부품수리 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtRepList" name="maPtRepListForm"
 *                input="/dream/part/rep/maPtRepList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRepList" path="/dream/part/rep/maPtRepList.jsp"
 *                        redirect="false"
 */
public class MaPtRepListAction extends AuthAction
{
    /** 조회 */
    public static final int PTREP_LIST_FIND     = 8001;
    /** 삭제 */
    public static final int PTREP_LIST_DELETE   = 7002;
    /** 저장 */
    public static final int PTREP_LIST_INPUT    = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtRepListForm maPtRepListForm = (MaPtRepListForm) form;
        
        super.updateAudit(maPtRepListForm.getMaPtRepCommonDTO().getAuditKey()==""?maPtRepListForm.getMaPtRepDetailDTO().getAuditKey():maPtRepListForm.getMaPtRepCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtRepListForm.getStrutsAction())
        {
            case MaPtRepListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtRepListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRepListAction.PTREP_LIST_FIND:
            	findPtRepList(request, maPtRepListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRepListAction.PTREP_LIST_DELETE:
            	deletePtRep(request, maPtRepListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRepListAction.PTREP_LIST_INPUT:
                insertPtRepList(request, maPtRepListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRepListAction.BASE_GRID_EXPORT:
            	findPtRepList(request, maPtRepListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtRepList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtRepListForm maPtRepListForm) throws IOException
    {
        super.setHeader(request, response, maPtRepListForm.getListId(), maPtRepListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRepListForm
     * @throws Exception
     */
    private void findPtRepList(HttpServletRequest request, MaPtRepListForm maPtRepListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPtRepListService maPtRepListService = (MaPtRepListService) getBean("maPtRepListService");        

    	MaPtRepCommonDTO maPtRepCommonDTO = maPtRepListForm.getMaPtRepCommonDTO();
    	maPtRepCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPtRepCommonDTO.setIsLoadMaxCount("Y".equals(maPtRepListForm.getIsLoadMaxCount())?true:false);
    	maPtRepCommonDTO.setFirstRow(maPtRepListForm.getFirstRow());
    	maPtRepCommonDTO.setOrderBy(maPtRepListForm.getOrderBy());
    	maPtRepCommonDTO.setDirection(maPtRepListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maPtRepListService.findList(maPtRepCommonDTO,getUser(request));

        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maPtRepListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtRepListService.findTotalCount(maPtRepCommonDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, maPtRepListForm.getListId(),maPtRepListForm.getCurrentPageId(), maPtRepListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepListForm
     * @param request
     */
    private void deletePtRep(HttpServletRequest request, MaPtRepListForm maPtRepListForm) throws Exception
    {
    	MaPtRepListService maPtRepListService = (MaPtRepListService) getBean("maPtRepListService");
    	String[] deleteRows = maPtRepListForm.getDeleteRows();    // sheet 내역
        
        maPtRepListService.deleteList(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRepListForm
     */
    private void insertPtRepList(HttpServletRequest request, MaPtRepListForm maPtRepListForm) throws Exception
    {
        MaPtRepListService maPtRepListService = (MaPtRepListService) getBean("maPtRepListService");
        
        MaPtRepDetailDTO maPtRepDetailDTO = maPtRepListForm.getMaPtRepDetailDTO();
        
        maPtRepDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtRepListService.insertPtRepList(maPtRepDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}
