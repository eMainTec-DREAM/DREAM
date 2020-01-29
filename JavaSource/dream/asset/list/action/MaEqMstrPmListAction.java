package dream.asset.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmListDTO;
import dream.asset.list.form.MaEqMstrPmListForm;
import dream.asset.list.service.MaEqMstrPmListService;

/**
 * 설비 점검 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrInsList" name="maEqMstrPmListForm"
 *                input="/dream/asset/list/maEqMstrInsList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMstrPmwRplList" name="maEqMstrPmListForm"
 *                input="/dream/asset/list/maEqMstrPmwRplList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMstrPmiList" name="maEqMstrPmListForm"
 *                input="/dream/asset/list/maEqMstrPmiList.jsp" scope="request"
 *                validate="false"
 */
public class MaEqMstrPmListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_RINS_LIST_FIND 		   = 8001;
    
    public static final int EQ_MSTR_RPL_LIST_FIND          = 8002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPmListForm maEqMstrPmListForm = (MaEqMstrPmListForm) form;
        
        super.updateAudit(maEqMstrPmListForm.getMaEqMstrPmListDTO().getAuditKey()==""?maEqMstrPmListForm.getMaEqMstrPmListDTO().getAuditKey():maEqMstrPmListForm.getMaEqMstrPmListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqMstrPmListForm.getStrutsAction())
        {
        
            case MaEqMstrPmListAction.EQ_MSTR_RINS_LIST_FIND:
                findInsList(request,response, maEqMstrPmListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmListAction.EQ_MSTR_RPL_LIST_FIND:
                findRplList(request,response, maEqMstrPmListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrPmListForm.getListId(), maEqMstrPmListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmListAction.BASE_GRID_EXPORT:
            	findInsList(request,response, maEqMstrPmListForm,true);
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
     * @version $Id: MaEqMstrPmListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmListForm
     * @throws Exception
     */
    private void findInsList(HttpServletRequest request,HttpServletResponse response, MaEqMstrPmListForm maEqMstrPmListForm, boolean excelExport) throws Exception
    {
        MaEqMstrPmListService maEqMstrPmListService = (MaEqMstrPmListService) getBean("maEqMstrPmListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmListForm.getMaEqMstrCommonDTO();
        MaEqMstrPmListDTO maEqMstrPmListDTO = maEqMstrPmListForm.getMaEqMstrPmListDTO();

        maEqMstrPmListDTO.setIsLoadMaxCount("Y".equals(maEqMstrPmListForm.getIsLoadMaxCount())?true:false);
        maEqMstrPmListDTO.setFirstRow(maEqMstrPmListForm.getFirstRow());
        maEqMstrPmListDTO.setOrderBy(maEqMstrPmListForm.getOrderBy());
        maEqMstrPmListDTO.setDirection(maEqMstrPmListForm.getDirection());
        
        List resultList = maEqMstrPmListService.findInsList(maEqMstrCommonDTO,maEqMstrPmListDTO, getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maEqMstrPmListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrPmListService.findInsTotalCount(maEqMstrCommonDTO,maEqMstrPmListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maEqMstrPmListForm.getListId(),maEqMstrPmListForm.getCurrentPageId(), maEqMstrPmListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }
    
    private void findRplList(HttpServletRequest request,HttpServletResponse response, MaEqMstrPmListForm maEqMstrPmListForm, boolean excelExport) throws Exception
    {
        MaEqMstrPmListService maEqMstrPmListService = (MaEqMstrPmListService) getBean("maEqMstrPmListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmListForm.getMaEqMstrCommonDTO();
        MaEqMstrPmListDTO maEqMstrPmListDTO = maEqMstrPmListForm.getMaEqMstrPmListDTO();

        maEqMstrPmListDTO.setIsLoadMaxCount("Y".equals(maEqMstrPmListForm.getIsLoadMaxCount())?true:false);
        maEqMstrPmListDTO.setFirstRow(maEqMstrPmListForm.getFirstRow());
        maEqMstrPmListDTO.setOrderBy(maEqMstrPmListForm.getOrderBy());
        maEqMstrPmListDTO.setDirection(maEqMstrPmListForm.getDirection());
        
        List resultList = maEqMstrPmListService.findRplList(maEqMstrCommonDTO,maEqMstrPmListDTO, getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maEqMstrPmListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrPmListService.findRplTotalCount(maEqMstrCommonDTO,maEqMstrPmListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maEqMstrPmListForm.getListId(),maEqMstrPmListForm.getCurrentPageId(), maEqMstrPmListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }
}