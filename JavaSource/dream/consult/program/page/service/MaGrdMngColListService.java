package dream.consult.program.page.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;

/**
 * Ä®·³ ¸ñ·Ï
 * @author  jung7126
 * @version $Id: MaGrdMngColListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdMngColListService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: MaGrdMngColListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngCommonDTO
     * @param user 
     * @throws Exception
     */
    public List findColList(MaGrdMngCommonDTO maGrdMngCommonDTO, User user);
    /**
     *  delete
     * @author  jung7126
     * @version $Id: MaGrdMngColListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @throws Exception
     */
    public int deleteColList(String[] deleteRows) throws Exception;
    public int sysYColList(String[] deleteRows) throws Exception;
    public int sysNColList(String[] deleteRows) throws Exception;
    public String findTotalCount(MaGrdMngCommonDTO maGrdMngCommonDTO, User user) throws Exception;

}
