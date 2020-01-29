package dream.mapt.mastat.dao;

import java.util.List;

import dream.mapt.mastat.dto.MaPtExpStatCommonDTO;

/**
 * 자재비용분석 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtExpStatListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtExpStatCommonDTO
     * @return List
     */
    public List findList(MaPtExpStatCommonDTO maPtExpStatCommonDTO);
}