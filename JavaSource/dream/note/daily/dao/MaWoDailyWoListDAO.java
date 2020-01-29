package dream.note.daily.dao;

import java.util.List;

import common.bean.User;
import dream.note.daily.dto.MaWoDailyDetailDTO;

/**
 * 일일작업확인 - 작업 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoDailyWoListDAO
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
    public List findList(MaWoDailyDetailDTO maWoDailyDetailDTO, User user);
    public String findTotalCount(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception;

}