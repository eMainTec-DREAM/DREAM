package dream.note.dayrpt.service;

import java.util.List;

import common.bean.User;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;

/**
 * ��� service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoDayRptListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param maWoDayRptCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User user);
    
    /**
     * ����
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return 
     */
    public int deleteList(String[] deleteRows, String compNo);

    public String findTotalCount(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User user);
}
