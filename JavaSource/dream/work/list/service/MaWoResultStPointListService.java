package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointListDTO;

/**
 * 작업결과 작업필수검사항목  목록
 * @author  kim21017
 * @version $Id: MaWoResultStPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultStPointListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultStPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultStPointListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findStPointList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultStPointListDTO maWoResultStPointListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaWoResultStPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteStPointList(String[] deleteRows, String compNo) throws Exception;

    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultStPointListDTO maWoResultStPointListDTO, User loginUser) throws Exception;
}
