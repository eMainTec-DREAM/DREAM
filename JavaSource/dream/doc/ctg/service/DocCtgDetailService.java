package dream.doc.ctg.service;

import common.bean.User;
import dream.doc.ctg.dto.DocCtgDetailDTO;
;

/**
 * 문서분류체계 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface DocCtgDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgCommonDTO
     * @return
     * @throws Exception
     */
    public DocCtgDetailDTO findDetail(String docCtgId, User user)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(DocCtgDetailDTO docCtgDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(DocCtgDetailDTO docCtgDetailDTO, User loginUser) throws Exception;
    
    public int updateFullDesc(String docCtgId, User user) throws Exception;
    
    public int updateFullDesc(User user) throws Exception;
}
