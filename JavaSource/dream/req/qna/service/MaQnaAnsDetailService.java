package dream.req.qna.service;

import dream.req.qna.dto.MaQnaAnsDetailDTO;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * ´äº¯ - detail
 * @author  kim210117
 * @version $Id: MaQnaAnsDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaQnaAnsDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaQnaAnsDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsListDTO
     * @param maQnaCommonDTO
     * @return
     * @throws Exception
     */
    public MaQnaAnsDetailDTO findDetail(MaQnaAnsListDTO maQnaAnsListDTO, MaQnaCommonDTO maQnaCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaQnaAnsDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsDetailDTO
     * @param maQnaCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaQnaAnsDetailDTO maQnaAnsDetailDTO, MaQnaCommonDTO maQnaCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaQnaAnsDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsDetailDTO
     * @param maQnaCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaQnaAnsDetailDTO maQnaAnsDetailDTO, MaQnaCommonDTO maQnaCommonDTO) throws Exception;
}
