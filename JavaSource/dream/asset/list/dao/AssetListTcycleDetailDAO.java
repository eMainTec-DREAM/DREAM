package dream.asset.list.dao;


import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.asset.list.dto.AssetListTcycleDetailDTO;

/**
 * 교정주기 상세 dao
 * @author  kim21017
 * @version $Id: AssetListTcycleDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface AssetListTcycleDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: AssetListTcycleDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqSpecId
     * @param compNo
     * @return
     */
    public AssetListTcycleDetailDTO findDetail(String equipId, String eqPmCycleId, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: AssetListTcycleDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param assetListTcycleDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(AssetListTcycleDetailDTO assetListTcycleDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO);

    public String getLabelDesc(User user, String key_no);
}