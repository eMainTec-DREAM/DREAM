package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 부품마스터 사용이력 - 목록 service
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
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPtMstrWoPtHistList(MaPtMstrCommonDTO maPtMstrCommonDTO, User user);
    
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception;
}
