package dream.pers.priv.pgm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;
import dream.pers.priv.pgm.form.MaGrdUsrColListForm;
import dream.pers.priv.pgm.service.MaGrdUsrColListService;

/**
 * 칼럼 목록
 * @author  jung7126
 * @version $Id: MaGrdUsrColListAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maGrdUsrColList" name="maGrdUsrColListForm"
 *                input="/dream/pers/priv/pgm/maGrdUsrColList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maGrdUsrColList" path="/dream/pers/priv/pgm/maGrdUsrColList.jsp"
 *                        redirect="false"
 */
public class MaGrdUsrColListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int GRD_USR_COL_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaGrdUsrColListForm maGrdUsrColListForm = (MaGrdUsrColListForm) form;
        
        switch (maGrdUsrColListForm.getStrutsAction())
        {
        
            case MaGrdUsrColListAction.GRD_USR_COL_LIST_FIND:
                findColList(request,response, maGrdUsrColListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaGrdUsrColListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maGrdUsrColListForm.getListId(), maGrdUsrColListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maGrdUsrColList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaGrdUsrColListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdUsrColListForm
     * @throws Exception
     */
    private void findColList(HttpServletRequest request,HttpServletResponse response, MaGrdUsrColListForm maGrdUsrColListForm, boolean excelExport) throws Exception
    {
        MaGrdUsrColListService maGrdUsrColListService = (MaGrdUsrColListService) getBean("maGrdUsrColListService");
        
        MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrColListForm.getMaGrdUsrCommonDTO();
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = maGrdUsrColListForm.getMaGrdUsrDetailDTO();
        
        //Paging
        maGrdUsrDetailDTO.setIsLoadMaxCount("Y".equals(maGrdUsrColListForm.getIsLoadMaxCount())?true:false);
        maGrdUsrDetailDTO.setFirstRow(maGrdUsrColListForm.getFirstRow());
        maGrdUsrDetailDTO.setOrderBy(maGrdUsrColListForm.getOrderBy());
        maGrdUsrDetailDTO.setDirection(maGrdUsrColListForm.getDirection());
        
        List resultList = maGrdUsrColListService.findColList(maGrdUsrCommonDTO, maGrdUsrDetailDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maGrdUsrColListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maGrdUsrColListService.findTotalCount(maGrdUsrCommonDTO, maGrdUsrDetailDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maGrdUsrColListForm.getListId(),maGrdUsrColListForm.getCurrentPageId(), maGrdUsrColListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}