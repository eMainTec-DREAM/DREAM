package dream.req.qna.service;

import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.dto.MaQnaDetailDTO;

/**
 * 질의 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaQnaDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaQnaDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaQnaDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaCommonDTO
     * @return
     * @throws Exception
     */
    public MaQnaDetailDTO findDetail(MaQnaCommonDTO maQnaCommonDTO)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaQnaDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaQnaDetailDTO maQnaDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaQnaDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaQnaDetailDTO maQnaDetailDTO) throws Exception;
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: MaQnaDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailDTO
     * @return
     * @throws Exception
     */
    public int confirmDetail(MaQnaDetailDTO maQnaDetailDTO) throws Exception;
}
