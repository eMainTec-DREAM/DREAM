package dream.part.iss.emg.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.list.form.MaPtIssEmgListForm;
import dream.part.iss.emg.list.service.MaPtIssEmgListService;

/**
 * 긴급출고 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtIssEmgList" name="maPtIssEmgListForm"
 *                input="/dream/part/iss/emg/list/maPtIssEmgList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtIssEmgList" path="/dream/part/iss/emg/list/maPtIssEmgList.jsp"
 *                        redirect="false"
 */
public class MaPtIssEmgListAction extends AuthAction
{
    /** 조회 */
    public static final int PTISSEMG_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int PTISSEMG_LIST_DELETE = 7002;
    /** 저장 */
    public static final int PTISSEMG_LIST_INPUT  = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtIssEmgListForm maPtIssEmgListForm = (MaPtIssEmgListForm) form;
        
        switch (maPtIssEmgListForm.getStrutsAction())
        {
            case MaPtIssEmgListAction.PTISSEMG_LIST_FIND:
            	findPtIssEmgList(request, maPtIssEmgListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtIssEmgListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtIssEmgListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtIssEmgListAction.BASE_GRID_EXPORT:
            	findPtIssEmgList(request, maPtIssEmgListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaPtIssEmgListAction.PTISSEMG_LIST_DELETE:
                deleteKey(request, maPtIssEmgListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtIssEmgListAction.PTISSEMG_LIST_INPUT:
                insertPtIssEmgList(request, maPtIssEmgListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtIssEmgListForm maPtIssEmgListForm) throws IOException
    {
        super.setHeader(request, response, maPtIssEmgListForm.getListId(), maPtIssEmgListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtIssEmgListForm
     * @throws ServiceException 
     * @throws Exception
     */
    private void findPtIssEmgList(HttpServletRequest request, MaPtIssEmgListForm maPtIssEmgListForm, HttpServletResponse response, boolean excelExport) throws IOException, ServiceException
    {
    	MaPtIssEmgListService maPtIssEmgListService = (MaPtIssEmgListService) getBean("maPtIssEmgListService");        

    	MaPtIssEmgCommonDTO maPtIssEmgCommonDTO = maPtIssEmgListForm.getMaPtIssEmgCommonDTO();
    	maPtIssEmgCommonDTO.setCompNo(getUser(request).getCompNo());
    	maPtIssEmgCommonDTO.setPtemgissreqId(maPtIssEmgListForm.getPartIssEmgReqCommonDTO().getPtEmgIssReqId());
    	
    	//Paging
    	maPtIssEmgCommonDTO.setIsLoadMaxCount("Y".equals(maPtIssEmgListForm.getIsLoadMaxCount())?true:false);
    	maPtIssEmgCommonDTO.setFirstRow(maPtIssEmgListForm.getFirstRow());
    	maPtIssEmgCommonDTO.setOrderBy(maPtIssEmgListForm.getOrderBy());
    	maPtIssEmgCommonDTO.setDirection(maPtIssEmgListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtIssEmgListService.findPtIssEmgList(maPtIssEmgCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maPtIssEmgListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtIssEmgListService.findTotalCount(maPtIssEmgCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPtIssEmgListForm.getListId(),maPtIssEmgListForm.getCurrentPageId(), maPtIssEmgListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteKey(HttpServletRequest request, MaPtIssEmgListForm maPtIssEmgListForm) throws Exception
    {
        MaPtIssEmgListService maPtIssEmgListService = (MaPtIssEmgListService) getBean("maPtIssEmgListService");        
        
        String[] deleteRows = maPtIssEmgListForm.getDeleteRows();
        
        maPtIssEmgListService.deleteKey(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtIssEmgListForm
     */
    private void insertPtIssEmgList(HttpServletRequest request, MaPtIssEmgListForm maPtIssEmgListForm) throws Exception
    {
        MaPtIssEmgListService maPtIssEmgListService = (MaPtIssEmgListService) getBean("maPtIssEmgListService");
        
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgListForm.getMaPtIssEmgDetailDTO();
        
        maPtIssEmgDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssEmgDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtIssEmgListService.insertPtIssEmgList(maPtIssEmgDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}
