package dream.mgr.lang.service;

import common.bean.User;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.dto.MaResDetailDTO;

/**
 * 언어 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaResDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param keyTypeNo
     * @param langId
     * @return
     * @throws Exception
     */
    public MaResDetailDTO findDetail(User user, MaResCommonDTO maResCommonDTO)throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maResDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaResDetailDTO maResDetailDTO, User user) throws Exception;
    
    /**
     * detail insert
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maResDetailForm
     * @param request
     */
    public int insertDetail(MaResDetailDTO maResDetailDTO, MaResCommonDTO maResCommonDTO, User user) throws Exception;
}
