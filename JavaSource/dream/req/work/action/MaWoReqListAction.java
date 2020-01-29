package dream.req.work.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.ResponseDTO;
import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.form.MaWoReqListForm;
import dream.req.work.service.MaWoReqListService;

/**
 * 작업요청서 - 목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maWoReqList" name="maWoReqListForm"
 *                input="/dream/req/work/maWoReqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoReqList" path="/dream/req/work/maWoReqList.jsp"
 *                        redirect="false"
 */
public class MaWoReqListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    /** 삭제 */
    public static final int LIST_DELETE       = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoReqListForm maWoReqListForm = (MaWoReqListForm) form;
        
        super.updateAudit(maWoReqListForm.getMaWoReqCommonDTO().getAuditKey()==""?maWoReqListForm.getMaWoReqCommonDTO().getAuditKey():maWoReqListForm.getMaWoReqCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoReqListForm.getStrutsAction())
        {
            case MaWoReqListAction.LIST_FIND:
            	findList(request, maWoReqListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoReqListAction.LIST_DELETE:
            	deleteList(request, response, maWoReqListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoReqListAction.BASE_GRID_EXPORT:
            	findList(request, maWoReqListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaWoReqListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoReqListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maWoReqList");
                break;
        }

        return returnActionForward;
    }

    private void deleteList(HttpServletRequest request, HttpServletResponse response, MaWoReqListForm maWoReqListForm) throws Exception
    {
        MaWoReqListService maWoReqListService = (MaWoReqListService) getBean("maWoReqListService");        

        String[] deleteRows = maWoReqListForm.getDeleteRows();    // sheet 내역
        
        ResponseDTO responseDTO = maWoReqListService.deleteList(deleteRows,getUser(request));
        
        CommonUtil.makeJsonResult(responseDTO, response);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoReqListForm maWoReqListForm) throws IOException
    {
        super.setHeader(request, response, maWoReqListForm.getListId(), maWoReqListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoReqListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaWoReqListForm maWoReqListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaWoReqListService maWoReqListService = (MaWoReqListService) getBean("maWoReqListService");        

    	MaWoReqCommonDTO maWoReqCommonDTO = maWoReqListForm.getMaWoReqCommonDTO();
    	maWoReqCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        maWoReqCommonDTO.setIsLoadMaxCount("Y".equals(maWoReqListForm.getIsLoadMaxCount())?true:false);
        maWoReqCommonDTO.setFirstRow(maWoReqListForm.getFirstRow());
        maWoReqCommonDTO.setOrderBy(maWoReqListForm.getOrderBy());
        maWoReqCommonDTO.setDirection(maWoReqListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maWoReqListService.findList(maWoReqCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoReqListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoReqListService.findTotalCount(maWoReqCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoReqListForm.getListId(),maWoReqListForm.getCurrentPageId(), maWoReqListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
   
}
