package dream.consult.comp.time.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;

/**
 * 가동시간설정 - 목록 service
 * @author  kim21017
 * @version $Id: MaLineTimeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaLineTimeListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaLineTimeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maLineTimeCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqLocList(MaLineTimeCommonDTO maLineTimeCommonDTO, User user);

    public String findTotalCount(MaLineTimeCommonDTO maLineTimeCommonDTO, User user);

    public int deleteList(String[] deleteRows, String[] deleteRowsExt) throws Exception;
}
