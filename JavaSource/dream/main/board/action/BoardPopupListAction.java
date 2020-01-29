package dream.main.board.action;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.main.board.dto.BoardPopupCommonDTO;
import dream.main.board.form.BoardPopupListForm;
import dream.main.board.service.BoardPopupListService;

/**
 * 게시판 - 목록 
 * @author  pochul2423
 * @version $Id: BoardPopupListAction.java,v 1.2 2014/01/14 01:35:45 pochul2423 Exp $
 * @since   1.0
 * @struts:action path="/boardPopupList" name="boardPopupListForm"
 *                input="/dream/main/board/boardPopupList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="success" path="/dream/main/board/boardPopupList.jsp"
 *                        redirect="false"
 */
public class BoardPopupListAction extends AuthAction
{
    /** 조회 */
    public static final int BD_POPUP_FIND = 1001;
  
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BoardPopupListForm BoardPopupListForm = (BoardPopupListForm) form;
        
        switch (BoardPopupListForm.getStrutsAction())
        {
            case BoardPopupListAction.BD_POPUP_FIND:
                findBoardPopupList(request, BoardPopupListForm);
                returnActionForward = mapping.findForward("gridList");
                break;               
            case BoardPopupListAction.BASE_GRID_EXPORT:
                findBoardPopupList(request, BoardPopupListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("success");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  pochul2423
     * @version $Id: BoardPopupListAction.java,v 1.2 2014/01/14 01:35:45 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param BoardPopupListForm
     * @throws Exception
     */
    private void findBoardPopupList(HttpServletRequest request, BoardPopupListForm BoardPopupListForm) 
    {
        BoardPopupListService BoardPopupListService = (BoardPopupListService) getBean("boardPopupListService");        

        BoardPopupCommonDTO BoardPopupCommonDTO = BoardPopupListForm.getBoardPopupCommonDTO();

        // 마스터 리스트를 조회한다.
        List resultList = BoardPopupListService.findBoardPopupList(BoardPopupCommonDTO);

        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(FIND_XML_ATTRIBUTE, resultList);
    }
}