package dream.note.dayrpt.dao;

import common.bean.User;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;

/**
 *  - »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 */
public interface MaWoDayRptDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param IfFailureCommonDTO
     * @param loginUser
     * @return
     */
    public MaWoDayRptDetailDTO findDetail(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param IfFailureDetailDTO
     * @return
     */
    public int updateDetail(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User loginUser);

    public int insertDetail(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User loginUser);
}