package dream.asset.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCtgAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비부위 목록
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrAsmbListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqMstrAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrAsmbListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findAsmbList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAsmbListDTO maEqMstrAsmbListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaEqMstrAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteAsmbList(String[] deleteRows, String compNo) throws Exception;

    public int inputAsmb(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO, String equipId, User user) throws Exception;
    
    public int inputAsmbList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAsmbDetailDTO maEqMtrAsmbDetailDTO, User user) throws Exception;
    /**
     * 설비의 설비종류에 따라 공통부위 생성
     * 
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int inputAsmbList(String eqCtgId, String equipId, User user) throws Exception;

    // List 저장
    public void saveList(List<Map> gridList, User user) throws Exception;
    
    public String getEqAsmb(String equipId, String eqCtgAsmbId, User user) throws Exception;
    
    public int undoEqCtgAsmb(String eqCtgAsmbId, User user) throws Exception;
    
    public int unlinkOldEqAsmb(String equipId, User user) throws Exception;
    
}
