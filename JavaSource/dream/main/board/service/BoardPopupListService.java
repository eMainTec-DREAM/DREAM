package dream.main.board.service;

import java.util.List;

import dream.main.board.dto.BoardPopupCommonDTO;

/**
 * �Խ���- ���
 * @author  pochul2423
 * @version $Id: BoardPopupListService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
 * @since   1.0
 */
public interface BoardPopupListService
{     
    /**
     *  grid find
     * @author  pochul2423
     * @version $Id: BoardPopupListService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @param BoardPopupCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findBoardPopupList(BoardPopupCommonDTO BoardPopupCommonDTO);    

}