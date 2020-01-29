package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultCraftListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 작업자  목록
 * @author  kim21017
 * @version $Id: MaWoResultCraftListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultCraftListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultCraftListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultCraftListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findCraftList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultCraftListDTO maWoResultCraftListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaWoResultCraftListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteCraftList(String[] deleteRows, String compNo) throws Exception;
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultCraftListDTO maWoResultCraftListDTO, User user) throws Exception;
    
    public int inputCraftList(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;

}
