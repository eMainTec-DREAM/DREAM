package dream.consult.comp.time.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;

/**
 * 요일별 설정 목록 dao
 * @author  kim21017
 * @version $Id: MaLineTimeDowListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaLineTimeDowListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaLineTimeDowListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeCommonDTO
     * @param maLineTimeDowListDTO
     * @param loginUser
     * @return List
     */
    public List findDowList(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowListDTO maLineTimeDowListDTO, User loginUser);
    public String findTotalCount(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowListDTO maLineTimeDowListDTO, User loginUser);
    /**
     * delete
     * @author kim21017
     * @version $Id: MaLineTimeDowListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteDowList(String id, String compNo);
}