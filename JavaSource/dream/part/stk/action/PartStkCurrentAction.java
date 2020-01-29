package dream.part.stk.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import common.util.CommonUtil;
import dream.part.stk.dto.PartStkCurrentDTO;
import dream.part.stk.form.PartStkCurrentForm;
import dream.part.stk.service.PartStkCurrentService;

/**
 * 목록
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 * @struts:action path="/partStkCurrentList" name="partStkCurrentForm"
 *                input="/dream/part/stk/partStkCurrentList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/partStkCurrentDetail" name="partStkCurrentForm"
 *                input="/dream/part/stk/partStkCurrentDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partStkCurrentList" path="/dream/part/stk/partStkCurrentList.jsp"
 *                        redirect="false" 
 * @struts.action-forward name="partStkCurrentDetail" path="/dream/part/stk/partStkCurrentDetail.jsp"
 *                        redirect="false"                       
 */
public class PartStkCurrentAction extends SuperAuthAction
{
    /** 조회 */
    public static final int PTSTCK_LIST_FIND 		= 8001;
    /** 상세 조회 */
    public static final int PTSTCK_DETAIL_INIT       = 8003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PartStkCurrentForm partStkCurrentForm = (PartStkCurrentForm) form;
        
        super.updateAudit(partStkCurrentForm.getPartStkCurrentDTO().getAuditKey()==""?partStkCurrentForm.getPartStkCurrentDTO().getAuditKey():partStkCurrentForm.getPartStkCurrentDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (partStkCurrentForm.getStrutsAction())
        {
	        case PartStkCurrentAction.PTSTCK_LIST_FIND:
	        	findPtStckList(request, partStkCurrentForm, response, false);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
	        case PartStkCurrentAction.BASE_SET_HEADER:
	            setHeader(request, response, partStkCurrentForm);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
	        case PartStkCurrentAction.BASE_GRID_EXPORT:
	        	findPtStckList(request, partStkCurrentForm,response, true);
	            returnActionForward = new ActionForward("/gridExport");
	            break;
	        case PartStkCurrentAction.PTSTCK_DETAIL_INIT:
	            findDetail(request, response, partStkCurrentForm);
	            returnActionForward = mapping.findForward("partStkCurrentDetail");
	            break;      	
	        default:
	            returnActionForward = mapping.getInputForward();
	            break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartStkCurrentForm partStkCurrentForm) throws IOException
    {
        super.setHeader(request, response, partStkCurrentForm.getListId(), partStkCurrentForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partStkCurrentForm
     * @throws Exception
     */
    private void findPtStckList(HttpServletRequest request, PartStkCurrentForm partStkCurrentForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	PartStkCurrentService partStkCurrentService = (PartStkCurrentService) getBean("partStkCurrentService", request);        

    	PartStkCurrentDTO partStkCurrentDTO = partStkCurrentForm.getPartStkCurrentDTO();
    	
    	//Paging
    	partStkCurrentDTO.setIsLoadMaxCount("Y".equals(partStkCurrentForm.getIsLoadMaxCount())?true:false);
    	partStkCurrentDTO.setFirstRow(partStkCurrentForm.getFirstRow());
    	partStkCurrentDTO.setOrderBy(partStkCurrentForm.getOrderBy());
    	partStkCurrentDTO.setDirection(partStkCurrentForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = partStkCurrentService.findPtStckList(partStkCurrentDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(partStkCurrentForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partStkCurrentService.findTotalCount(partStkCurrentDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, partStkCurrentForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * 자재재고 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partStkCurrentForm
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, PartStkCurrentForm partStkCurrentForm)throws Exception 
    {   
        // Service 객체 생성
    	PartStkCurrentService partStkCurrentService = (PartStkCurrentService)getBean("partStkCurrentService");
    	PartStkCurrentDTO partStkCurrentDTO = partStkCurrentForm.getPartStkCurrentDTO();

    	partStkCurrentDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
    	partStkCurrentDTO = partStkCurrentService.findDetail(partStkCurrentDTO, getUser(request));
        // 조회된 상세 셋팅 한다.
        partStkCurrentForm.setPartStkCurrentDTO(partStkCurrentDTO);
    }

}