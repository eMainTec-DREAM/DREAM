package dream.doc.manual.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.doc.manual.dto.DocManualDetailDTO;

/**
 * 사용자매뉴얼 - 상세 dao
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface DocManualDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public DocManualDetailDTO findDetail(String id, User user);
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualDetailDTO
     * @param user 
     * @return
     */
    public int insertDetail(DocManualDetailDTO docManualDetailDTO, User user);

    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualDetailDTO
     * @param user 
     * @return
     */
    public int updateDetail(DocManualDetailDTO docManualDetailDTO, User user);
}