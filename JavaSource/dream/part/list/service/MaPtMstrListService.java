package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 보전자재분류(마스터) - 목록 service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPtMstrListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser);    
   
    /**
     * delete List
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;

    /**
     * find Total Count
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @return
     */
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user);
    public List copyParts(String[] selectRows, User loginUser)throws Exception;

	public String getData(User user, MaPtMstrCommonDTO maPtMstrCommonDTO);

}
