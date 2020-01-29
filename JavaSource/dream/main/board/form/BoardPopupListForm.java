package dream.main.board.form;

import common.struts.BaseForm;
import dream.main.board.dto.BoardPopupCommonDTO;

/**
 * 게시판 - 목록
 * @author  pochul2423
 * @version $Id: BoardPopupListForm.java,v 1.2 2014/01/14 01:35:42 pochul2423 Exp $
 * @since   1.0
 *
 * @struts.form name="boardPopupListForm"
 */
public class BoardPopupListForm extends BaseForm
{    
    //===============================================================
    /**  공통 */
    private BoardPopupCommonDTO BoardPopupCommonDTO = new BoardPopupCommonDTO();

    public BoardPopupCommonDTO getBoardPopupCommonDTO()
    {
        return BoardPopupCommonDTO;
    }
    public void setBoardPopupCommonDTO(BoardPopupCommonDTO BoardPopupCommonDTO)
    {
        this.BoardPopupCommonDTO = BoardPopupCommonDTO;
    }
}