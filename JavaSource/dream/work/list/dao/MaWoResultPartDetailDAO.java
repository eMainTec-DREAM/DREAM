package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;

/**
 * 작업결과-투입자재 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultPartDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultPartDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPartId
     * @param compNo
     * @return
     */
    public MaWoResultPartDetailDTO findDetail(String wkOrId, String woPartId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPartDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPartDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);

    /**
     * 재고확인
     */
    public String getStockQty(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser);

    public int insertIssPartDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);

    public int updateEmgPart(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);

    public List getIssQty(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser);

    public int insertPtIssDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user);
}