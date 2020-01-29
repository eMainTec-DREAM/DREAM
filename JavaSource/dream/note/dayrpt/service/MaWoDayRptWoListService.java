package dream.note.dayrpt.service;

import java.util.List;

import common.bean.User;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;

/**
 * 작업 목록 service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoDayRptWoListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param maWoDayRptDetailDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaWoDayRptDetailDTO maWoDayRptDetailDTO,User user);
}
