package dream.part.rpt.mastat.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mastat.dto.MaPtRepStatCommonDTO;

/**
 * 자재수리내역 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRepStatListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepStatCommonDTO
     * @return List
     */
    public List findList(MaPtRepStatCommonDTO maPtRepStatCommonDTO, User user);
    
    public String findTotalCount(MaPtRepStatCommonDTO maPtRepStatCommonDTO, User user);
}