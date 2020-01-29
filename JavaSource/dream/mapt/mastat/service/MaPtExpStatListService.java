package dream.mapt.mastat.service;

import java.util.List;

import dream.mapt.mastat.dto.MaPtExpStatCommonDTO;

/**
 * 자재비용분석 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtExpStatListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtExpStatCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPtExpStatCommonDTO maPtExpStatCommonDTO);
    
}
