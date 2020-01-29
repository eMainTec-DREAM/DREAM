package dream.main.board.service;

import dream.main.board.dto.BoardPopupCommonDTO;
import dream.main.board.dto.BoardPopupDetailDTO;

/**
 * �Խ��� - ��
 * @author  pochul2423
 * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
 * @since   1.0
 *
 */
public interface BoardPopupDetailService
{

    /**
     * �������� Detail �ش� ��ȸ
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupCommonDTO
     * @return
     */
    BoardPopupDetailDTO findDetail(BoardPopupCommonDTO BoardPopupCommonDTO);

    /**
     * �������� Detail Sheet ����
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO :��� �κ�
     * @return 
     */
    void insertDetail(BoardPopupDetailDTO BoardPopupDetailDTO);

    /**
     * �󼼳����� ���� �����Ѵ�.
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @throws Exception
     */
    void updateDetail(BoardPopupDetailDTO BoardPopupDetailDTO);
    
    /**
     * �󼼳����� ���� �����Ѵ�.
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @throws Exception
     */
    void deleteDetail(BoardPopupDetailDTO BoardPopupDetailDTO);
    
    /**
     * sequence nextReply
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param sequenceName
     * @return
     */
    String findNextReply(String boardNo);
}