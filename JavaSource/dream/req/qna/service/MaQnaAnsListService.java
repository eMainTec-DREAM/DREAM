package dream.req.qna.service;

import java.util.List;

import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * 답변 목록
 * @author  kim21017
 * @version $Id: MaQnaAnsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaQnaAnsListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaQnaAnsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaCommonDTO
     * @param maQnaAnsListDTO
     * @throws Exception
     */
    public List findAnsList(MaQnaCommonDTO maQnaCommonDTO, MaQnaAnsListDTO maQnaAnsListDTO);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaQnaAnsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteAnsList(String[] m_id, String[] d_id) throws Exception;

}
