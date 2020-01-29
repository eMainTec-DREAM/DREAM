package dream.consult.comp.plant.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;
import dream.consult.comp.plant.form.MaPlantMngListForm;
import dream.consult.comp.plant.service.MaPlantMngListService;

/**
 * 회사설정 - 목록 action
 * @author  kim21017
 * @version $Id: MaPlantMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPlantMngList" name="maPlantMngListForm"
 *                input="/dream/consult/comp/plant/maPlantMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPlantMngList" path="/dream/consult/comp/plant/maPlantMngList.jsp"
 *                        redirect="false"
 */
public class MaPlantMngListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int PLANT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PLANT_LIST_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPlantMngListForm maPlantMngListForm = (MaPlantMngListForm) form;

        switch (maPlantMngListForm.getStrutsAction())
        {
            case MaPlantMngListAction.PLANT_LIST_FIND:
                findPlantList(request, maPlantMngListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPlantMngListAction.BASE_SET_HEADER:
                setHeader(request, response, maPlantMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPlantMngListAction.PLANT_LIST_DELETE:
            	deletePlant(request, maPlantMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPlantMngListAction.BASE_GRID_EXPORT:
            	findPlantList(request, maPlantMngListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPlantMngList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPlantMngListForm maPlantMngListForm) throws IOException
    {
        super.setHeader(request, response, maPlantMngListForm.getListId(),maPlantMngListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPlantMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param maPlantMngListForm
     * @param response
     * @throws Exception
     */
    private void findPlantList(HttpServletRequest request, MaPlantMngListForm maPlantMngListForm, HttpServletResponse response,  boolean excelExport) throws IOException
    {
    	MaPlantMngListService maPlantMngListService = (MaPlantMngListService) getBean("maPlantMngListService");

    	MaPlantMngCommonDTO maPlantMngCommonDTO = maPlantMngListForm.getMaPlantMngCommonDTO();

    	maPlantMngCommonDTO.setIsLoadMaxCount("Y".equals(maPlantMngListForm.getIsLoadMaxCount())?true:false);
    	maPlantMngCommonDTO.setFirstRow(maPlantMngListForm.getFirstRow());
    	maPlantMngCommonDTO.setOrderBy(maPlantMngListForm.getOrderBy());
    	maPlantMngCommonDTO.setDirection(maPlantMngListForm.getDirection());
    	
        //리스트를 조회한다.
    	
        List resultList = maPlantMngListService.findPlantList(maPlantMngCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(
        		maPlantMngListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPlantMngListService.findTotalCount(maPlantMngCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,maPlantMngListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaPlantMngListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param maPlantMngListForm
     */
    private void deletePlant(HttpServletRequest request, MaPlantMngListForm maPlantMngListForm) throws Exception
    {
    	MaPlantMngListService maPlantMngListService = (MaPlantMngListService) getBean("maPlantMngListService");

    	String[] deleteRows = maPlantMngListForm.getDeleteRows();
    	String[] deleteRowsExt = maPlantMngListForm.getDeleteRowsExt();

    	maPlantMngListService.deletePlant(deleteRows,deleteRowsExt, getUser(request));

        setAjaxStatus(request);
    }
}
