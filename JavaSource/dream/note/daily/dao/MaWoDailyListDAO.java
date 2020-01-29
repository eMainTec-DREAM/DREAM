package dream.note.daily.dao;

import java.util.List;

import common.bean.User;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * 일일작업 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoDailyListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyCommonDTO
     * @return List
     */
    public List findList(MaWoDailyCommonDTO maWoDailyCommonDTO, User user);
    
    public int deleteList(String id, String compNo);
    
    public int deleteActList(String id, String compNo);
    
    public String findTotalCount(MaWoDailyCommonDTO maWoDailyCommonDTO, User user) throws Exception;

}