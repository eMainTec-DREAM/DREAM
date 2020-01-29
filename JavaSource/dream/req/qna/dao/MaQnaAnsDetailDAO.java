package dream.req.qna.dao;

import dream.req.qna.dto.MaQnaAnsDetailDTO;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * 답변 상세 dao
 * @author  kim21017
 * @version $Id: MaQnaAnsDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaQnaAnsDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaQnaAnsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsListDTO
     * @param maQnaCommonDTO
     * @return
     */
    public MaQnaAnsDetailDTO findDetail(MaQnaAnsListDTO maQnaAnsListDTO, MaQnaCommonDTO maQnaCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaQnaAnsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsDetailDTO
     * @param maQnaCommonDTO
     * @return
     */
    public int updateDetail(MaQnaAnsDetailDTO maQnaAnsDetailDTO, MaQnaCommonDTO maQnaCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaQnaAnsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsDetailDTO
     * @param maQnaCommonDTO
     * @return
     */
    public int insertDetail(MaQnaAnsDetailDTO maQnaAnsDetailDTO, MaQnaCommonDTO maQnaCommonDTO);
}