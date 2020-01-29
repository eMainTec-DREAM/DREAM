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
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetListDTO;
import dream.consult.comp.wrkcal.form.ConsultCompWrkcalDowsetListForm;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDowsetListService;

/**
 * 휴무요일 설정  - 목록 action
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDowsetListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/consultCompWrkcalDowsetList" name="consultCompWrkcalDowsetListForm"
 *                input="/dream/consult/comp/wrkcal/consultCompWrkcalDowsetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompWrkcalDowsetList" path="/dream/consult/comp/wrkcal/consultCompWrkcalDowsetList.jsp"
 *                        redirect="false"
 */
public class ConsultCompWrkcalDowsetListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int WRKCAL_DOWSET_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WRKCAL_DOWSET_LIST_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompWrkcalDowsetListForm consultCompWrkcalDowsetListForm = (ConsultCompWrkcalDowsetListForm) form;

        switch (consultCompWrkcalDowsetListForm.getStrutsAction())
        {
            case ConsultCompWrkcalDowsetListAction.WRKCAL_DOWSET_LIST_FIND:
                findDowsetList(request, consultCompWrkcalDowsetListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompWrkcalDowsetListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompWrkcalDowsetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompWrkcalDowsetListAction.WRKCAL_DOWSET_LIST_DELETE:
            	deleteWrkcal(request, consultCompWrkcalDowsetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultCompWrkcalDowsetListAction.BASE_GRID_EXPORT:
            	findDowsetList(request, consultCompWrkcalDowsetListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompWrkcalDowsetList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompWrkcalDowsetListForm consultCompWrkcalDowsetListForm) throws IOException
    {
        super.setHeader(request, response, consultCompWrkcalDowsetListForm.getListId(),consultCompWrkcalDowsetListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: ConsultCompWrkcalDowsetListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompWrkcalDowsetListForm
     * @param response
     * @throws Exception
     */
    private void findDowsetList(HttpServletRequest request, ConsultCompWrkcalDowsetListForm consultCompWrkcalDowsetListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	ConsultCompWrkcalDowsetListService consultCompWrkcalDowsetListService = (ConsultCompWrkcalDowsetListService) getBean("consultCompWrkcalDowsetListService");

    	ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = consultCompWrkcalDowsetListForm.getConsultCompWrkcalCommonDTO();
    	ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO = consultCompWrkcalDowsetListForm.getConsultCompWrkcalDowsetListDTO();
    	
    	//Paging
        consultCompWrkcalCommonDTO.setIsLoadMaxCount("Y".equals(consultCompWrkcalDowsetListForm.getIsLoadMaxCount())?true:false);
        consultCompWrkcalCommonDTO.setFirstRow(consultCompWrkcalDowsetListForm.getFirstRow());
        consultCompWrkcalCommonDTO.setOrderBy(consultCompWrkcalDowsetListForm.getOrderBy());
        consultCompWrkcalCommonDTO.setDirection(consultCompWrkcalDowsetListForm.getDirection());
        
        User user = getUser(request);
        
        consultCompWrkcalCommonDTO.setUserLang(user.getLangId());

        //리스트를 조회한다.
        List resultList = consultCompWrkcalDowsetListService.findDowsetList(consultCompWrkcalCommonDTO, consultCompWrkcalDowsetListDTO, user);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultCompWrkcalDowsetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompWrkcalDowsetListService.findTotalCount(consultCompWrkcalCommonDTO,consultCompWrkcalDowsetListDTO,user);

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, consultCompWrkcalDowsetListForm); 
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDowsetListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompWrkcalDowsetListForm
     */
    private void deleteWrkcal(HttpServletRequest request, ConsultCompWrkcalDowsetListForm consultCompWrkcalDowsetListForm) throws Exception
    {
    	ConsultCompWrkcalDowsetListService consultCompWrkcalDowsetListService = (ConsultCompWrkcalDowsetListService) getBean("consultCompWrkcalDowsetListService");

    	String[] deleteRows = consultCompWrkcalDowsetListForm.getDeleteRows();

    	consultCompWrkcalDowsetListService.deleteWrkcal(deleteRows);

        setAjaxStatus(request);
    }
}
