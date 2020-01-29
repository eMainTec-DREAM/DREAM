package dream.part.rpt.mastat.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mastat.dto.MaPtRepStatCommonDTO;

/**
 * ����������� - ��� service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRepStatListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtRepStatCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaPtRepStatCommonDTO maPtRepStatCommonDTO, User user);
    
    public String findTotalCount(MaPtRepStatCommonDTO maPtRepStatCommonDTO, User user);
    
}
