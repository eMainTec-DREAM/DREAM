package dream.mgr.usage.cal.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDowSetDTO;

/**
 * 요일별 설정  목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrUsageCalSetDowSetService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrUsageCalSetDTO
     * @param mgrUsageCalSetDowSetDTO
     * @param loginUser
     * @throws Exception
     */
    public List<MgrUsageCalSetDTO> findDowList(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User loginUser);
    /**
     *  delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRows
     * @param deleteRowsExt
     * @throws Exception
     */
    public int[] deleteDowList(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user);

    public MgrUsageCalSetDowSetDTO findDetail(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user)throws Exception;

    public int updateDetail(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) throws Exception;

    public int insertDetail(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) throws Exception;

    public int[] insertDetail(List<MgrUsageCalSetDowSetDTO> list, User user) throws Exception;

    public String validDay(MgrUsageCalSetDTO mgrUsageCalSetDTO, MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) throws Exception;
}
