package dream.work.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultCraftListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.MaWoResultCraftListForm;
import dream.work.list.service.MaWoResultCraftListService;

/**
 * 작업결과-작업자 목록
 * @author  kim21017
 * @version $Id: MaWoResultCraftListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/maWoResultCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultMonthCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/month/maWoResultMonthCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmInsCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/pmi/maWoResultPmInsCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRprCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/pmw/maWoResultPmRprCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRplCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/pmw/maWoResultPmRplCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmClnCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/pmw/maWoResultPmClnCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmOilCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/pmw/maWoResultPmOilCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRprCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/bm/maWoResultBmRprCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRplCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/bm/maWoResultBmRplCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmOilCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/bm/maWoResultBmOilCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRprCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/cm/maWoResultCmRprCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRplCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/cm/maWoResultCmRplCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultTrEleCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/maWoResultTrEleCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultGnlCaCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/pmc/maWoResultGnlCaCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultSclCaCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/pmc/maWoResultSclCaCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPrsCaCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/pmc/maWoResultPrsCaCraftList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmLocBaseCraftList" name="maWoResultCraftListForm"
 *                input="/dream/work/list/cm/maWoResultCmLocBaseCraftList.jsp" scope="request"
 *                validate="false"
 */
public class MaWoResultCraftListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_CRAFT_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_RESULT_CRAFT_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int WO_RESULT_CRAFT_LIST_INPUT          = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultCraftListForm maWoResultCraftListForm = (MaWoResultCraftListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultCraftListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(maWoResultCraftListForm.getMaWoResultCraftListDTO().getAuditKey()==""?maWoResultCraftListForm.getMaWoResultCraftListDTO().getAuditKey():maWoResultCraftListForm.getMaWoResultCraftListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoResultCraftListForm.getStrutsAction())
        {
        
            case MaWoResultCraftListAction.WO_RESULT_CRAFT_LIST_FIND:
                findCraftList(request,response, maWoResultCraftListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultCraftListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maWoResultCraftListForm.getListId(), maWoResultCraftListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultCraftListAction.WO_RESULT_CRAFT_LIST_DELETE:
            	deleteCraftList(request,maWoResultCraftListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultCraftListAction.WO_RESULT_CRAFT_LIST_INPUT:
                inputCraftList(request,maWoResultCraftListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoResultCraftListAction.BASE_GRID_EXPORT:
            	findCraftList(request,response, maWoResultCraftListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoResultCraftListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultCraftListForm
     * @throws Exception
     */
    private void findCraftList(HttpServletRequest request,HttpServletResponse response, MaWoResultCraftListForm maWoResultCraftListForm, boolean excelExport) throws Exception
    {
        MaWoResultCraftListService maWoResultCraftListService = (MaWoResultCraftListService) getBean("maWoResultCraftListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultCraftListForm.getMaWoResultMstrCommonDTO();
        MaWoResultCraftListDTO maWoResultCraftListDTO = maWoResultCraftListForm.getMaWoResultCraftListDTO();
        
        //Paging
        maWoResultCraftListDTO.setIsLoadMaxCount("Y".equals(maWoResultCraftListForm.getIsLoadMaxCount())?true:false);
        maWoResultCraftListDTO.setFirstRow(maWoResultCraftListForm.getFirstRow());
        maWoResultCraftListDTO.setOrderBy(maWoResultCraftListForm.getOrderBy());
        maWoResultCraftListDTO.setDirection(maWoResultCraftListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maWoResultCraftListService.findCraftList(maWoResultMstrCommonDTO, maWoResultCraftListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoResultCraftListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultCraftListService.findTotalCount(maWoResultMstrCommonDTO,maWoResultCraftListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultCraftListForm.getListId(),maWoResultCraftListForm.getCurrentPageId(), maWoResultCraftListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaWoResultCraftListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultCraftListForm
     * @throws Exception
     */
    private void deleteCraftList(HttpServletRequest request, MaWoResultCraftListForm maWoResultCraftListForm) throws Exception
    {
    	MaWoResultCraftListService maWoResultCraftListService = (MaWoResultCraftListService) getBean("maWoResultCraftListService");
        
    	String[] deleteRows = maWoResultCraftListForm.getDeleteRows();
    
    	maWoResultCraftListService.deleteCraftList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoResultCraftListForm
     * @throws Exception
     */
    private void inputCraftList(HttpServletRequest request, MaWoResultCraftListForm maWoResultCraftListForm) throws Exception
    {
        MaWoResultCraftListService maWoResultCraftListService = (MaWoResultCraftListService) getBean("maWoResultCraftListService");
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultCraftListForm.getMaWoResultMstrCommonDTO();
        
        MaWoResultCraftDetailDTO maWoResultCraftDetailDTO = maWoResultCraftListForm.getMaWoResultCraftDetailDTO();

        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultCraftListService.inputCraftList(maWoResultCraftDetailDTO, maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}