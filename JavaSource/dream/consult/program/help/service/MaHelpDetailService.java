package dream.consult.program.help.service;

import dream.consult.program.help.dto.MaHelpCommonDTO;
import dream.consult.program.help.dto.MaHelpDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaHelpDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     * @throws Exception
     */
    public MaHelpDetailDTO findDetail(MaHelpCommonDTO maHelpCommonDTO)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maHelpDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaHelpDetailDTO maHelpDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maHelpDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaHelpDetailDTO maHelpDetailDTO) throws Exception;

	/**
	 * Request HelpDesk 
	 * @param maHelpDetailDTO
	 */
	public void requestDetail(MaHelpDetailDTO maHelpDetailDTO) throws Exception;
}
