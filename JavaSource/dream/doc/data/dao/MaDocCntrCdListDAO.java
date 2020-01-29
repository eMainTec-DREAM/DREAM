package dream.doc.data.dao;

import java.util.List;

import common.bean.User;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdListDTO;

/**
 * 일반자료실 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaDocCntrCdListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdCommonDTO
     * @return List
     */
    public List findList(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser);
    

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdListDTO
     * @param loginUser
     * @return
     */
    public int deleteDocCntr(MaDocCntrCdListDTO maDocCntrCdListDTO, User loginUser);
    
    /**
     * 자재 첨부파일 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdListDTO
     * @param loginUser
     * @return
     */
    public int deleteObjDoc(MaDocCntrCdListDTO maDocCntrCdListDTO, User loginUser);

    public String findTotalCount(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User user);
    
}