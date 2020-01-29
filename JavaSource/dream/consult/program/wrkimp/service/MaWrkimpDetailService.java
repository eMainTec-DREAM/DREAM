package dream.consult.program.wrkimp.service;

import common.bean.User;

import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;
import dream.consult.program.wrkimp.dto.MaWrkimpDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaWrkimpDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     * @throws Exception
     */
    public MaWrkimpDetailDTO findDetail(MaWrkimpCommonDTO maWrkimpCommonDTO, User user)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWrkimpDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWrkimpDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user) throws Exception;
}
