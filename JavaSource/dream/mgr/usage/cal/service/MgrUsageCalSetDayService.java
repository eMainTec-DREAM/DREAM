package dream.mgr.usage.cal.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDayDTO;

/**
 * 사용달력일별횟수설정 - 목록 service
 * @author youngjoo38
 * @version $Id: $
 * @since   1.0
 */
public interface MgrUsageCalSetDayService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id: $
     * @param mgrUsageCalSetDayDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List<MgrUsageCalSetDayDTO> findList(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user);    
   
    /**
     * delete List
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int[] deleteList(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user);
    
    public MgrUsageCalSetDayDTO findDetail(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user)throws Exception;

    public int insertDetail(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user) throws Exception;
    
    public int[] insertDetail(List<MgrUsageCalSetDayDTO> list, User user) throws Exception;
    
    public int updateDetail(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user) throws Exception;

    public String validLineRunPlan(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user) throws Exception;

}
