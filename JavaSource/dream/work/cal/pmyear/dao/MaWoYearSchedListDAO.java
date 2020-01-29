package dream.work.cal.pmyear.dao;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.cal.pmyear.dto.MaWoYearSchedCommonDTO;

/**
 * 연간작업일정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaWoYearSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoYearSchedListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoYearSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoYearSchedCommonDTO
     * @return List
     */
    public List findSchedList(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user);

    public String findTotalCount(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user);
    
    public String checkSched(String pmSchedId, User user);
    
    public int updateScheduleDate(String id, String scheDate, String remark, User user);
    
    public int updateResultSchedDetail(String id, String scheDate, User user);
    
    public void SP_PM_MAKE_WO_BYONE(String compNo, String userNo, String pmschedId) throws Exception;  

    public int updatePmSchedStatus(AppReqDetailDTO appReqDetailDTO, User user, String pmSchedStatus);
}