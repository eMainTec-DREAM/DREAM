package dream.part.rpt.mastat.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mastat.dto.MaPtRecStatCommonDTO;

/**
 * �����԰��� - ��� service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRecStatListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtRecStatCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaPtRecStatCommonDTO maPtRecStatCommonDTO);

    public String findTotalCount(MaPtRecStatCommonDTO maPtRecStatCommonDTO, User user);
    
}
