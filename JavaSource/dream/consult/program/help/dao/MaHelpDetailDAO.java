package dream.consult.program.help.dao;

import common.spring.BaseJdbcDaoSupportIntf;
import dream.consult.program.help.dto.MaHelpCommonDTO;
import dream.consult.program.help.dto.MaHelpDetailDTO;

/**
 * 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaHelpDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param empId
     * @return
     */
    public MaHelpDetailDTO findDetail(MaHelpCommonDTO maHelpCommonDTO);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maHelpDetailDTO
     * @return
     */
    public int insertDetail(MaHelpDetailDTO maHelpDetailDTO);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maHelpDetailDTO
     * @return
     */
    public int updateDetail(MaHelpDetailDTO maHelpDetailDTO);
		
    /**
     * 요청
     * @param maHelpDetailDTO
     */
    public void insertGowrkimp(MaHelpDetailDTO maHelpDetailDTO);

    public void updateGowrkimp(MaHelpDetailDTO maHelpDetailDTO);
}