package dream.cert.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.cert.list.dto.CertDetailDTO;

/**
 * 자격증분류 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface CertDetailDAO extends BaseJdbcDaoSupportIntf
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
    public CertDetailDTO findDetail(User user, String prRecListId);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certDetailDTO
     * @return
     */
    public int insertDetail(CertDetailDTO certDetailDTO);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certDetailDTO
     * @return
     */
    public int updateDetail(CertDetailDTO certDetailDTO);
    
  
}