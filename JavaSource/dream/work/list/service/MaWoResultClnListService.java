package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultClnListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 작업설비  목록
 * @author  kim21017
 * @version $Id: MaWoResultClnListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultClnListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultClnListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultClnListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findClnList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultClnListDTO maWoResultClnListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaWoResultClnListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteClnList(String[] deleteRows, String compNo) throws Exception;

    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultClnListDTO maWoResultClnListDTO, User loginUser) throws Exception;
}
