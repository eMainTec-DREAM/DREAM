package dream.req.qna.dao;

import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.dto.MaQnaDetailDTO;

/**
 * 질의 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaQnaDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaCommonDTO
     * @return
     */
    public MaQnaDetailDTO findDetail(MaQnaCommonDTO maQnaCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailDTO
     * @return
     */
    public int insertDetail(MaQnaDetailDTO maQnaDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailDTO
     * @return
     */
    public int updateDetail(MaQnaDetailDTO maQnaDetailDTO);

    /**
     * detail confirm
     * @author kim21017
     * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailDTO
     * @return
     */
    public int confirmDetail(MaQnaDetailDTO maQnaDetailDTO);
}