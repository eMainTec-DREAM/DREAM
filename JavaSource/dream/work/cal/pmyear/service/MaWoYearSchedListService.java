package dream.work.cal.pmyear.service;

import java.util.List;

import common.bean.ResponseDTO;
import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.cal.pmyear.dto.MaWoYearSchedCommonDTO;

/**
 * 연간작업일정 - 목록 service
 * @author  kim21017
 * @version $Id: MaWoYearSchedListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoYearSchedListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoYearSchedListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoYearSchedCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findSchedList(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoYearSchedListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteSched(String[] deleteRows, User user) throws Exception;
    
    /**
     * 스케줄 조정
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param gridList
     * @param user
     */
    public void updateSchedule(List gridList, User user);
    
    public String findTotalCount(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user);
    
	public String findSchedule(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user) throws Exception;

    /**
     * 일단위 확정
     * @author syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param fixRows
     * @param maWoYearSchedCommonDTO
     * @return
     * @throws Exception
     */
    public ResponseDTO dailyScheduled(String[] fixRows, MaWoYearSchedCommonDTO maWoYearSchedCommonDTO, User user) throws Exception;
    /**
     * 결재 시 스케쥴 상태 변경
     * @author nhkim8548
     * @version $Id: MaWoYearSchedListService.java,v 1.0 2019/01/16 08:51:40 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param maWoYearSchedCommonDTO
     * @return
     * @throws Exception
     */
    public void apprProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
}
