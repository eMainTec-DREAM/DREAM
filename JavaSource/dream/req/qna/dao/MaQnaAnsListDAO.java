package dream.req.qna.dao;

import java.util.List;

import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * 답변 목록 dao
 * @author  kim21017
 * @version $Id: MaQnaAnsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaQnaAnsListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaQnaAnsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaCommonDTO
     * @param maQnaAnsListDTO
     * @return List
     */
    public List findAnsList(MaQnaCommonDTO maQnaCommonDTO, MaQnaAnsListDTO maQnaAnsListDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaQnaAnsListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteAnsList(String deleteRow, String deleteRowsExt);
}