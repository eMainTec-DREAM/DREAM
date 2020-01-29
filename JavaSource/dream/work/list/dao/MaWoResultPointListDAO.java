package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointDetailDTO;
import dream.work.list.dto.MaWoResultPointListDTO;

/**
 * 작업결과 검사항목 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultPointListDTO
     * @param loginUser
     * @return List
     */
    public List findPointList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPointListDTO maWoResultPointListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultPointListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] deletePointList(List<MaWoResultPointDetailDTO> list, User user);
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPointListDTO maWoResultPointListDTO, User loginUser) throws Exception;
}