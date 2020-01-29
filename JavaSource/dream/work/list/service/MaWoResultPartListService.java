package dream.work.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.dto.MaWoResultPartListDTO;

/**
 * 작업결과 투입자재  목록
 * @author  kim21017
 * @version $Id: MaWoResultPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultPartListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultPartListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findPartList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPartListDTO maWoResultPartListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaWoResultPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deletePartList(String[] deleteRows, String compNo) throws Exception;

    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPartListDTO maWoResultPartListDTO, User user) throws Exception;
    public int inputPartList(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
    public int inputIssPartList(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;

    // List 저장
    public void saveList(List<Map> gridList, User user) throws Exception;
    public int inputPtIssList(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user) throws Exception;
    
}
