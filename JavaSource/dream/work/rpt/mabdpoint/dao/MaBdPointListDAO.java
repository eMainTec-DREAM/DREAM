package dream.work.rpt.mabdpoint.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;

/**
 * 이상점검조치 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaBdPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @return List
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO, User user);
    
    public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, User user);
}