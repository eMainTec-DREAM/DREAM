package dream.edu.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.edu.list.dto.EduDetailDTO;

/**
 * �ڰ����з� - �� dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface EduDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param prRecListId
     * @return
     */
    public EduDetailDTO findDetail(User user, String prRecListId);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduDetailDTO
     * @return
     */
    public int insertDetail(EduDetailDTO eduDetailDTO);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduDetailDTO
     * @return
     */
    public int updateDetail(EduDetailDTO eduDetailDTO);
    
  
}