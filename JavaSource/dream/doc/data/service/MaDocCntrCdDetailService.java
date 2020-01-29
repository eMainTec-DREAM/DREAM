package dream.doc.data.service;

import common.bean.User;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdDetailDTO;

/**
 * 일반자료실 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaDocCntrCdDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDocCntrCdCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaDocCntrCdDetailDTO findDetail(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDocCntrCdDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaDocCntrCdDetailDTO maDocCntrCdDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDocCntrCdDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaDocCntrCdDetailDTO maDocCntrCdDetailDTO, User loginUser) throws Exception;
    
}
