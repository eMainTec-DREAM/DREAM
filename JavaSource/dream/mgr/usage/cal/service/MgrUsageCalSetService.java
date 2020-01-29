package dream.mgr.usage.cal.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;

/**
 * ���޷¼��� - ��� service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrUsageCalSetService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param mgrUsageCalSetDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List<MgrUsageCalSetDTO> findList(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user);

    public String findTotalCount(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user);

    public int[] deleteList(String[] deleteRows, User user) throws Exception;
    
    public MgrUsageCalSetDTO findDetail(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user)throws Exception;

    public int updateDetail(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user) throws Exception;
    
    public int insertDetail(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user) throws Exception;
    
    public int[] insertDetail(List<MgrUsageCalSetDTO> list, User user) throws Exception;
}
