package dream.work.cal.pmperiod.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;

/**
 * 예방작업일정(기간) - 목록 dao
 * @author  kim21017
 * @version $Id: MaWoSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoSchedListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoSchedCommonDTO
     * @return List
     */
    public List findSchedList(MaWoSchedCommonDTO maWoSchedCommonDTO, User user);

    public String findTotalCount(MaWoSchedCommonDTO maWoSchedCommonDTO, User user);

    public int[] updateDeleteTag(List<MaWoSchedDetailDTO> list, User user) throws Exception;
}