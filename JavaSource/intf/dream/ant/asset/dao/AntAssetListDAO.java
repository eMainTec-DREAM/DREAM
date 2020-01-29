package intf.dream.ant.asset.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AntAssetListDAO extends BaseJdbcDaoSupportIntf
{
    public List findAssetList(Map map) throws Exception;
    public List findAssetFileList(Map map) throws Exception;

    //public int insertAssetList(Map map, String woReqId) throws Exception;
    public int changeFileSeq(Map map, String woReqId) throws Exception;
    
}