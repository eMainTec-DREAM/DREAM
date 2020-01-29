package dream.note.dayrpt.dao;

import java.util.List;

import common.bean.User;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;

/**
 * 업무일지 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoDayRptListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDayRptCommonDTO
     * @return List
     */
    public List findList(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User user);
    
    public int deleteList(String id, String compNo);

    public String findTotalCount(MaWoDayRptCommonDTO maWoDayRptCommonDTO,
            User user);
}