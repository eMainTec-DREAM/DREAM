package mobile.dream.mapm.mains.service;

import common.bean.User;
import mobile.dream.mapm.mains.dto.MaPmInsDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author jung7126
 * @version $Id: MaPmInsDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since 1.0
 */
public interface MaPmInsDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmInsDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaPmInsDetailDTO findDetail(String eqCtgId,User user)throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmInsDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmInsDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception;
}
