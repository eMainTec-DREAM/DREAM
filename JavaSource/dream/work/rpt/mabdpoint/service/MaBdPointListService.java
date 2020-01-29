package dream.work.rpt.mabdpoint.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;

/**
 * 이상점검조치 - 목록 service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaBdPointListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param maBdPointCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO, User user);
    
    public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, User user);
}
