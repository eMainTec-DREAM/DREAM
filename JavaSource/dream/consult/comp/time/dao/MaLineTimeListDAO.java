package dream.consult.comp.time.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;

/**
 * 가동시간설정 - 목록 
 * @author  kim21017
 * @version $Id: MaLineTimeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaLineTimeListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaLineTimeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeCommonDTO
     * @return List
     */
    public List findEqLocList(MaLineTimeCommonDTO maLineTimeCommonDTO,User user);

    public String findTotalCount(MaLineTimeCommonDTO maLineTimeCommonDTO, User user);

    public int deleteList(String id, String compNo);
}