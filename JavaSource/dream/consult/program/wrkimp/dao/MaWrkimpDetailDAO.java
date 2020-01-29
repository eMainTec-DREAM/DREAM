package dream.consult.program.wrkimp.dao;

import common.bean.User;

import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;
import dream.consult.program.wrkimp.dto.MaWrkimpDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaWrkimpDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param empId
     * @return
     */
    public MaWrkimpDetailDTO findDetail(MaWrkimpCommonDTO maWrkimpCommonDTO, User user);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWrkimpDetailDTO
     * @return
     */
    public int insertDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWrkimpDetailDTO
     * @return
     */
    public int updateHelpDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user);
    
    public int updateDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user);
}