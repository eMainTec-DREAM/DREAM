package dream.tool.adj.dao;

import java.util.List;

import common.bean.User;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;

/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: MaPttDisPartsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPttDisPartsListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPttDisPartsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisCommonDTO
     * @param maPttDisPartsListDTO
     * @return List
     */
    public List findAnsList(MaPttDisCommonDTO maPttDisCommonDTO, MaPttDisPartsListDTO maPttDisPartsListDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPttDisPartsListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @return
     */
    public int deleteItemList(String deleteRow);

	public String findTotalCount(MaPttDisCommonDTO maPttDisCommonDTO, MaPttDisPartsListDTO maPttDisPartsListDTO, User user);
}