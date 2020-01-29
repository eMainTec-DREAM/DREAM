package dream.asset.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;

/**
 * 备己磊犁 格废
 * @author  kim21017
 * @version $Id: MaEqMstrPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPartListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqMstrPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrPartListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaEqMstrPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deletePartList(String[] deleteRows, String compNo) throws Exception;
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrPartListDTO
     * @return
     */
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User user) throws Exception;
    public int inputPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartDetailDTO maEqMtrPartDetailDTO, User user) throws Exception;
    public int inputEqCtgPart(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, String equipId, User user) throws Exception;
    public int inputEqCtgPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User user) throws Exception;
    public int inputEqCtgPartList(String eqCtgId, String equipId, User user) throws Exception;
    
    // List 历厘
    public void saveList(List<Map> gridList, User user) throws Exception;
    
    public int undoEqCtgPart(String eqCtgPartId, User user) throws Exception;
    
    public int unlinkOldEqPart(String equipId, User user) throws Exception;
}
