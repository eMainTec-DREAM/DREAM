package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * ��ǰ������ ����̷� - ��� service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrWoPtHistListService
{     
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @param maPtMstrCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findPtMstrWoPtHistList(MaPtMstrCommonDTO maPtMstrCommonDTO, User user);
    
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception;
}
