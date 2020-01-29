package dream.consult.comp.wrkcal.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetListDTO;
import dream.consult.comp.wrkcal.form.ConsultCompWrkcalDaysetListForm;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDaysetListService;

/**
 * 휴무일 설정  - 목록 action
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDaysetListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/consultCompWrkcalDaysetList" name="consultCompWrkcalDaysetListForm"
 *                input="/dream/consult/comp/wrkcal/consultCompWrkcalDaysetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompWrkcalDaysetList" path="/dream/consult/comp/wrkcal/consultCompWrkcalDaysetList.jsp"
 *                        redirect="false"
 */
public class ConsultCompWrkcalDaysetListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int WRKCAL_DAYSET_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WRKCAL_DAYSET_LIST_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompWrkcalDaysetListForm consultCompWrkcalDaysetListForm = (ConsultCompWrkcalDaysetListForm) form;

        switch (consultCompWrkcalDaysetListForm.getStrutsAction())
        {
            case ConsultCompWrkcalDaysetListAction.WRKCAL_DAYSET_LIST_FIND:
                findDaysetList(request, consultCompWrkcalDaysetListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompWrkcalDaysetListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompWrkcalDaysetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompWrkcalDaysetListAction.WRKCAL_DAYSET_LIST_DELETE:
            	deleteWrkcal(request, consultCompWrkcalDaysetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultCompWrkcalDaysetListAction.BASE_GRID_EXPORT:
            	findDaysetList(request, consultCompWrkcalDaysetListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompWrkcalDaysetList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompWrkcalDaysetListForm consultCompWrkcalDaysetListForm) throws IOException
    {
        super.setHeader(request, response, consultCompWrkcalDaysetListForm.getListId(),consultCompWrkcalDaysetListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: ConsultCompWrkcalDaysetListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompWrkcalDaysetListForm
     * @param response
     * @throws Exception
     */
    private void findDaysetList(HttpServletRequest request, ConsultCompWrkcalDaysetListForm consultCompWrkcalDaysetListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	ConsultCompWrkcalDaysetListService consultCompWrkcalDaysetListService = (ConsultCompWrkcalDaysetListService) getBean("consultCompWrkcalDaysetListService");

    	ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = consultCompWrkcalDaysetListForm.getConsultCompWrkcalCommonDTO();
    	ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO = consultCompWrkcalDaysetListForm.getConsultCompWrkcalDaysetListDTO();
    	
    	consultCompWrkcalCommonDTO.setUserLang(getUser(request).getLangId());

        //Paging
        consultCompWrkcalCommonDTO.setIsLoadMaxCount("Y".equals(consultCompWrkcalDaysetListForm.getIsLoadMaxCount())?true:false);
        consultCompWrkcalCommonDTO.setFirstRow(consultCompWrkcalDaysetListForm.getFirstRow());
        consultCompWrkcalCommonDTO.setOrderBy(consultCompWrkcalDaysetListForm.getOrderBy());
        consultCompWrkcalCommonDTO.setDirection(consultCompWrkcalDaysetListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = consultCompWrkcalDaysetListService.findDaysetList(consultCompWrkcalCommonDTO, consultCompWrkcalDaysetListDTO, user);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultCompWrkcalDaysetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompWrkcalDaysetListService.findTotalCount(consultCompWrkcalCommonDTO,consultCompWrkcalDaysetListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultCompWrkcalDaysetListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDaysetListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompWrkcalDaysetListForm
     */
    private void deleteWrkcal(HttpServletRequest request, ConsultCompWrkcalDaysetListForm consultCompWrkcalDaysetListForm) throws Exception
    {
    	ConsultCompWrkcalDaysetListService consultCompWrkcalDaysetListService = (ConsultCompWrkcalDaysetListService) getBean("consultCompWrkcalDaysetListService");

    	String[] deleteRows = consultCompWrkcalDaysetListForm.getDeleteRows();

    	consultCompWrkcalDaysetListService.deleteWrkcal(deleteRows);

        setAjaxStatus(request);
    }
}
