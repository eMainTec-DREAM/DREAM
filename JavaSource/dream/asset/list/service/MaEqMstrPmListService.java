package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmListDTO;

/**
 * 설비 점검 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqMstrPmListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrPmListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findInsList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser);
    public List findRplList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser);
    public String findInsTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser);
    public String findRplTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser);

}
