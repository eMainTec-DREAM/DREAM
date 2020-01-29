package dream.doc.manual.service;

import common.bean.User;
import dream.doc.manual.dto.DocManualDetailDTO;

/**
 * 사용자매뉴얼 - 상세 service
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface DocManualDetailService
{    
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public DocManualDetailDTO findDetail(String id, User user)throws Exception;
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualDetailDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public int insertDetail(DocManualDetailDTO docManualDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualDetailDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public int updateDetail(DocManualDetailDTO docManualDetailDTO, User user) throws Exception;
}
