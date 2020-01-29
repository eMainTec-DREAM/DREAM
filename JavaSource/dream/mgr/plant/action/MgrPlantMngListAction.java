package dream.mgr.plant.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;
import dream.mgr.plant.form.MgrPlantMngListForm;
import dream.mgr.plant.service.MgrPlantMngListService;

/**
 * 공장설정 - 목록 action
 * @author  euna0207
 * @version $Id: MgrPlantMngListAction.java,v 1.0 2019/08/19 09:10:21 euna0207 Exp $
 * @since   1.0
 * @struts:action path="/mgrPlantMngList" name="mgrPlantMngListForm"
 *                input="/dream/mgr/plant/mgrPlantMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPlantMngList" path="/dream/mgr/plant/mgrPlantMngList.jsp"
 *                        redirect="false"
 */
public class MgrPlantMngListAction extends AuthAction
{
    /** 조회 */
    public static final int PLANT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PLANT_LIST_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrPlantMngListForm mgrPlantMngListForm = (MgrPlantMngListForm) form;

        switch (mgrPlantMngListForm.getStrutsAction())
        {
            case MgrPlantMngListAction.PLANT_LIST_FIND:
                findPlantList(request, mgrPlantMngListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrPlantMngListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrPlantMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrPlantMngListAction.PLANT_LIST_DELETE:
            	deletePlant(request, mgrPlantMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrPlantMngListAction.BASE_GRID_EXPORT:
            	findPlantList(request, mgrPlantMngListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrPlantMngList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrPlantMngListForm mgrPlantMngListForm) throws IOException
    {
        super.setHeader(request, response, mgrPlantMngListForm.getListId(),mgrPlantMngListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: MgrPlantMngListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrPlantMngListForm
     * @param response
     * @throws Exception
     */
    private void findPlantList(HttpServletRequest request, MgrPlantMngListForm mgrPlantMngListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MgrPlantMngListService mgrPlantMngListService = (MgrPlantMngListService) getBean("mgrPlantMngListService");

    	MgrPlantMngCommonDTO mgrPlantMngCommonDTO = mgrPlantMngListForm.getMgrPlantMngCommonDTO();

    	mgrPlantMngCommonDTO.setIsLoadMaxCount("Y".equals(mgrPlantMngListForm.getIsLoadMaxCount())?true:false);
    	mgrPlantMngCommonDTO.setFirstRow(mgrPlantMngListForm.getFirstRow());
    	mgrPlantMngCommonDTO.setOrderBy(mgrPlantMngListForm.getOrderBy());
    	mgrPlantMngCommonDTO.setDirection(mgrPlantMngListForm.getDirection());
    	
        List resultList = mgrPlantMngListService.findPlantList(mgrPlantMngCommonDTO, getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(
        		mgrPlantMngListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrPlantMngListService.findTotalCount(mgrPlantMngCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrPlantMngListForm);//.makeGridExport(resultList, request, response,mgrPlantMngListForm.getListId(),mgrPlantMngListForm.getCurrentPageId(), mgrPlantMngListForm.getFileName());
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  euna0207
     * @version $Id: MgrPlantMngListAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrPlantMngListForm
     */
    private void deletePlant(HttpServletRequest request, MgrPlantMngListForm mgrPlantMngListForm) throws Exception
    {
    	MgrPlantMngListService mgrPlantMngListService = (MgrPlantMngListService) getBean("mgrPlantMngListService");

    	String[] deleteRows = mgrPlantMngListForm.getDeleteRows();

    	mgrPlantMngListService.deletePlant(deleteRows, getUser(request));

        setAjaxStatus(request);
    }
}
