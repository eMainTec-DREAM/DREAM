package dream.tool.adj.service;

import java.util.List;

import common.bean.User;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;

/**
 * ¸ñ·Ï
 * @author  kim21017
 * @version $Id: MaPttDisPartsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPttDisPartsListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPttDisPartsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisCommonDTO
     * @param maPttDisPartsListDTO
     * @throws Exception
     */
    public List findAnsList(MaPttDisCommonDTO maPttDisCommonDTO, MaPttDisPartsListDTO maPttDisPartsListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaPttDisPartsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteItemList(String[] m_id, String[] d_id) throws Exception;
	public String findTotalCount(MaPttDisCommonDTO maPttDisCommonDTO, MaPttDisPartsListDTO maPttDisPartsListDTO, User user);

}
