package dream.main.board.service.spring;

import java.util.List;

import dream.main.board.dao.BoardPopupListDAO;
import dream.main.board.dto.BoardPopupCommonDTO;
import dream.main.board.service.BoardPopupListService;


/**
 * 게시판 - 목록
 * @author pochul2423
 * @version $Id: BoardPopupListServiceImpl.java,v 1.2 2014/01/14 01:35:42 pochul2423 Exp $
 * @since 1.0
 * 
 * @spring.bean id="boardPopupListServiceTarget"
 * @spring.txbn id="boardPopupListService"
 * @spring.property name="boardPopupListDAO" ref="boardPopupListDAO"
 */
public class BoardPopupListServiceImpl implements BoardPopupListService
{
    private BoardPopupListDAO BoardPopupListDAO = null;

    public BoardPopupListDAO getBoardPopupListDAO()
    {
        return BoardPopupListDAO;
    }

    public void setBoardPopupListDAO(BoardPopupListDAO BoardPopupListDAO)
    {
        this.BoardPopupListDAO = BoardPopupListDAO;
    }
    
    public List findBoardPopupList(BoardPopupCommonDTO BoardPopupCommonDTO)
    {      
        return BoardPopupListDAO.findBoardPopupList(BoardPopupCommonDTO);
    }
}