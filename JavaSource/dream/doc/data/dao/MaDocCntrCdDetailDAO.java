package dream.doc.data.dao;

import common.bean.User;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdDetailDTO;

/**
 * 일반자료실 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaDocCntrCdDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param docCntrId
     * @return
     */
    public MaDocCntrCdDetailDTO findDetail(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdDetailDTO
     * @return
     */
    public int insertDetail(MaDocCntrCdDetailDTO maDocCntrCdDetailDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdDetailDTO
     * @return
     */
    public int updateDetail(MaDocCntrCdDetailDTO maDocCntrCdDetailDTO, User loginUser);
}