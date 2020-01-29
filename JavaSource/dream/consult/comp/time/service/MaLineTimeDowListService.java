package dream.consult.comp.time.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;

/**
 * 요일별 설정  목록
 * @author  kim21017
 * @version $Id: MaLineTimeDowListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaLineTimeDowListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaLineTimeDowListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeCommonDTO
     * @param maLineTimeDowListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDowList(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowListDTO maLineTimeDowListDTO, User loginUser);
    public String findTotalCount(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowListDTO maLineTimeDowListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaLineTimeDowListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param deleteRowsExt
     * @throws Exception
     */
    public int deleteDowList(String[] deleteRows, String[] deleteRowsExt) throws Exception;

}
