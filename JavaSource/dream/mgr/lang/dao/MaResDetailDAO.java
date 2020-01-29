package dream.mgr.lang.dao;

import common.bean.User;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.dto.MaResDetailDTO;

/**
 * 언어 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaResDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param keyTypeNo
     * @param langId
     * @return
     */
    public MaResDetailDTO findDetail(User user, MaResCommonDTO maResCommonDTO);

    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maResDetailDTO
     * @return
     */
    public int updateDetail(MaResDetailDTO maResDetailDTO, User user);

	public int insertDetail(MaResDetailDTO maResDetailDTO, User user);
}