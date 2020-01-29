package dream.req.qna.service;

import java.util.List;

import common.bean.User;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * 질의 - 목록 service
 * @author  kim21017
 * @version $Id: MaQnaListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaQnaListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaQnaListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maQnaCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findQnaList(MaQnaCommonDTO maQnaCommonDTO);    

    /**
     * delete
     * @author kim21017
     * @version $Id: MaQnaListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDTOList
     * @return
     * @throws Exception
     */
    public int deleteQna(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(MaQnaCommonDTO maQnaCommonDTO, User user);

}
