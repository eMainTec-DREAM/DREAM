package dream.consult.program.table.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.dto.MaTableColListDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * 데이터 테이블 detail - code 목록
 * @author  kim21017
 * @version $Id: MaTableColListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaTableColListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaTableColListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableCommonDTO
     * @param maTableColListDTO
     * @throws Exception
     */
    public List findCodeList(MaTableCommonDTO maTableCommonDTO, MaTableColListDTO maTableColListDTO, String lang);
    public String findTotalCount(MaTableCommonDTO maTableCommonDTO, MaTableColListDTO maTableColListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaTableColListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteCodeList(String[] m_id, String[] d_id) throws Exception;

}
