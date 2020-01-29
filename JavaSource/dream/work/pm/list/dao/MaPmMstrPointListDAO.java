package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;

/**
 * 검사항목 목록 dao
 * @author  jung7126
 * @version $Id: MaPmMstrPointListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface MaPmMstrPointListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaPmMstrPointListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findPointList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser);
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaPmMstrPointListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] updateDeleteTag(List<MaPmMstrPointDetailDTO> list, User user);
    
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;

}