package dream.part.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.form.MaPtMstrRecStatListForm;
import dream.part.list.service.MaPtMstrRecStatListService;

/**
 * 부품마스터 입고이력 - 목록 action
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtMstrRecStatList" name="maPtMstrRecStatListForm"
 *                input="/dream/part/list/maPtMstrRecStatList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrRecStatList" path="/dream/part/list/maPtMstrRecStatList.jsp"
 *                        redirect="false"
 */
public class MaPtMstrRecStatListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrRecStatListForm maPtMstrRecStatListForm = (MaPtMstrRecStatListForm) form;
        
        switch (maPtMstrRecStatListForm.getStrutsAction())
        {
            case MaPtMstrRecStatListAction.LIST_FIND:
            	findPtMstrRecStatList(request, maPtMstrRecStatListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrRecStatListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtMstrRecStatListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrRecStatListAction.BASE_GRID_EXPORT:
            	findPtMstrRecStatList(request, maPtMstrRecStatListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtMstrRecStatListForm maPtMstrRecStatListForm) throws IOException
    {
        super.setHeader(request, response, maPtMstrRecStatListForm.getListId(), maPtMstrRecStatListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrRecStatListForm
     * @throws Exception
     */
    private void findPtMstrRecStatList(HttpServletRequest request, MaPtMstrRecStatListForm maPtMstrRecStatListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPtMstrRecStatListService maPtMstrRecStatListService = (MaPtMstrRecStatListService) getBean("maPtMstrRecStatListService", request);        

    	MaPtMstrCommonDTO maPtMstrCommonDTO = maPtMstrRecStatListForm.getMaPtMstrCommonDTO();
    	
    	//Paging
    	maPtMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPtMstrRecStatListForm.getIsLoadMaxCount())?true:false);
    	maPtMstrCommonDTO.setFirstRow(maPtMstrRecStatListForm.getFirstRow());
    	maPtMstrCommonDTO.setOrderBy(maPtMstrRecStatListForm.getOrderBy());
    	maPtMstrCommonDTO.setDirection(maPtMstrRecStatListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtMstrRecStatListService.findPtMstrRecStatList(maPtMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maPtMstrRecStatListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtMstrRecStatListService.findTotalCount(maPtMstrCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPtMstrRecStatListForm.getListId(),maPtMstrRecStatListForm.getCurrentPageId(), maPtMstrRecStatListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
