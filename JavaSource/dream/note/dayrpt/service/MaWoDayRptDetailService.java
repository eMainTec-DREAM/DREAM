package dream.note.dayrpt.service;

import common.bean.User;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;

/**
 * 업무일지 - 상세 service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaWoDayRptDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecListId
     * @return
     * @throws Exception
     */
    public MaWoDayRptDetailDTO findDetail(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User loginUser)throws Exception;
   
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDayRptDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDayRptDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User user) throws Exception;


    
    
}
