package dream.part.list.dao;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrListDTO;

/**
 * 보전자재분류(마스터) - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPtMstrListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @return List
     */
    public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int updateDeletePartsFlag(MaPtMstrListDTO maPtMstrListDTO, User loginUser);

    public int deleteStock(MaPtMstrListDTO maPtMstrListDTO, User loginUser);

    public int deleteSaftyStock(MaPtMstrListDTO maPtMstrListDTO, User loginUser);
    
    

    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user);
    
    public void SP_IF_UPD_TXPARTS(User loginUser) throws Exception;

    public String getPartsSeq();
    
    public int insertCopyDetail(String newId, String oldId, User loginUser);

	public String getData(User user, MaPtMstrCommonDTO maPtMstrCommonDTO);
}