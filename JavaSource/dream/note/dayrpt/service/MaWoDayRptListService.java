package dream.note.dayrpt.service;

import java.util.List;

import common.bean.User;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;

/**
 * 목록 service
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
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User user);
    
    /**
     * 삭제
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
