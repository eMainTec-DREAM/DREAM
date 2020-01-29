package dream.asset.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCtgSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;

/**
 * 설비제원(스펙) 목록
 * @author  kim21017
 * @version $Id: MaEqMstrSpecListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrSpecListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqMstrSpecListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrSpecListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findSpecList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecListDTO maEqMstrSpecListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaEqMstrSpecListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteSpecList(String[] deleteRows, String compNo) throws Exception;

    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecListDTO maEqMstrSpecListDTO, User user) throws Exception;

    public int inputEqCtgSpecList(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, String equipId, User user) throws Exception;
    
    public int inputEqCtgSpecList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, User user) throws Exception;
    
    public int inputEqCtgSpecList(String eqCtgId, String equipId, User user) throws Exception;
    
    // List 저장
    public void saveList(List<Map> gridList, User user) throws Exception;
    
    public int undoEqCtgSpec(String eqCtgSpecId, User user) throws Exception;
    
    public int unlinkOldEqSpec(String equipId, User user) throws Exception;
}
