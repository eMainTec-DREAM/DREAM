package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistListDTO;

/**
 * 구성자재 목록
 * @author  kim21017
 * @version $Id: MaEqMstrMoldModHistListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrMoldModHistListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqMstrMoldModHistListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrMoldModHistListDTO
     * @param loginUserr
     * @throws Exception
     */
    public List findMoldProdList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO, User loginUserr);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaEqMstrMoldModHistListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteMoldProdList(String[] deleteRows, String compNo) throws Exception;

    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO, User user) throws Exception;

}
