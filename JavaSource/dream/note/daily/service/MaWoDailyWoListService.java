package dream.note.daily.service;

import java.util.List;

import common.bean.User;
import dream.note.daily.dto.MaWoDailyDetailDTO;

/**
 * �۾� ��� service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoDailyWoListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param maWoDailyCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaWoDailyDetailDTO maWoDailyDetailDTO,User user);
    public String findTotalCount(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception;

}
