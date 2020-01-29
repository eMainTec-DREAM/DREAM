package dream.part.rpt.mastat.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mastat.dto.MaPtRecStatCommonDTO;

/**
 * 자재입고내역 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRecStatListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecStatCommonDTO
     * @return List
     */
    public List findList(MaPtRecStatCommonDTO maPtRecStatCommonDTO);

    public String findTotalCount(MaPtRecStatCommonDTO maPtRecStatCommonDTO, User user);
}