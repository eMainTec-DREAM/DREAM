package dream.note.daily.dao;

import java.util.List;

import common.bean.User;
import dream.note.daily.dto.MaWoDailyActListDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * 일일작업 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoDailyActListDAO
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
    public List findList(MaWoDailyCommonDTO maWoDailyCommonDTO,MaWoDailyActListDTO maWoDailyActListDTO, User user);

    public int deleteList(String id, String compNo);
}