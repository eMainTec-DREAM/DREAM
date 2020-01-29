package dream.cert.list.service;

import common.bean.User;
import dream.cert.list.dto.CertDetailDTO;

/**
 * 자격증분류 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface CertDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecListId
     * @return
     * @throws Exception
     */
    public CertDetailDTO findDetail(User user, String certListId)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(CertDetailDTO certDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(CertDetailDTO certDetailDTO) throws Exception;
    

}
