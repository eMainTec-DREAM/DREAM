package intf.dream.bee.asset.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.asset.dto.BeeAssetCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeAssetListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findAssetList(BeeAssetCommonDTO beeAssetCommonDTO, Map map) throws Exception;
    public List findAssetCount(BeeAssetCommonDTO beeAssetCommonDTO, Map map) throws Exception;
    public List findAssetPartList(Map map) throws Exception;
    public List findAssetSpecList(Map map) throws Exception;
    public List findAssetToolList(Map map) throws Exception;
    public List findDocumentList(Map map) throws Exception;
    public List findDocumentCtgList(Map map) throws Exception;
    public List findWoHistList(Map map) throws Exception;
    public String findTotalCount(BeeAssetCommonDTO beeAssetCommonDTO,Map map) throws Exception;

    public int insertAsset(Map map) throws Exception;
    public int updateAsset(Map map) throws Exception;

    public List eqAsmbList(Map map) throws Exception;
    public int insertEqAsmb(Map map) throws Exception;
    public int updateEqAsmb(Map map) throws Exception;
    public void makeEqAsmbFullDesc(Map map) throws Exception;

    public List eqSpecList(Map map) throws Exception;
    public int insertEqSpec(Map map) throws Exception;
    public int updateEqSpec(Map map) throws Exception;

    public List eqPartList(Map map) throws Exception;
    public int insertEqPart(Map map) throws Exception;
    public int updateEqPart(Map map) throws Exception;
}