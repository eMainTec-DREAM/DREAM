package dream.note.daily.service;

import java.util.List;

import common.bean.User;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * 목록 service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoDailyListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param maWoDailyCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaWoDailyCommonDTO maWoDailyCommonDTO, User user);
    
    /**
     * 삭제
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return 
     */
    public int deleteList(String[] deleteRows, String compNo);
    
    public String findTotalCount(MaWoDailyCommonDTO maWoDailyCommonDTO, User user) throws Exception;

}
