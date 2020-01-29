
package dream.main.board.form;

import common.struts.BaseForm;
import dream.main.board.dto.BoardPopupCommonDTO;
import dream.main.board.dto.BoardPopupDetailDTO;

/**
 * 게시판 - 상세
 * 
 * @author pochul2423
 * @version $Id: BoardPopupDetailForm.java,v 1.2 2014/01/14 01:35:42 pochul2423 Exp $
 * @since
 * @struts.form name="boardPopupDetailForm"
 */
public class BoardPopupDetailForm extends BaseForm
{
    /** 게시판 상세 DTO*/
    private BoardPopupDetailDTO BoardPopupDetailDTO = new BoardPopupDetailDTO();
    
    /** 게시판 공통 */
    private BoardPopupCommonDTO BoardPopupCommonDTO = new BoardPopupCommonDTO();

    public BoardPopupDetailDTO getBoardPopupDetailDTO()
    {
        return BoardPopupDetailDTO;
    }

    public void setBoardPopupDetailDTO(BoardPopupDetailDTO BoardPopupDetailDTO)
    {
        this.BoardPopupDetailDTO = BoardPopupDetailDTO;
    }

    public BoardPopupCommonDTO getBoardPopupCommonDTO()
    {
        return BoardPopupCommonDTO;
    }

    public void setBoardPopupCommonDTO(BoardPopupCommonDTO BoardPopupCommonDTO)
    {
        this.BoardPopupCommonDTO = BoardPopupCommonDTO;
    }
}