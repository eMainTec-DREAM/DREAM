package dream.note.dayrpt.dao;

import java.util.List;

import common.bean.User;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;

/**
 * ��������-woList - �۾� ��� dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoDayRptWoListDAO
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
    public List findList(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User user);
}