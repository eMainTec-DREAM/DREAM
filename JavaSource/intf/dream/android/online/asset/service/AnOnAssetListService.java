package intf.dream.android.online.asset.service;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.asset.dto.AnOnAssetCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnOnAssetListService
{     
    public List findAssetList(AnOnAssetCommonDTO anOnAssetCommonDTO,Map map) throws Exception;
    public List findAssetPartList(Map map) throws Exception;
    public List findAssetSpecList(Map map) throws Exception;
    public List findAssetToolList(Map map) throws Exception;
    public List findDocumentList(Map map) throws Exception;
    public List findDocumentCtgList(Map map) throws Exception;
    public List fileCopyList(Map map) throws Exception;
    public List findWoHistList(Map map) throws Exception;
    public String findTotalCount(AnOnAssetCommonDTO anOnAssetCommonDTO,Map map) throws Exception;

    public int insertAsset(List list) throws Exception;
    public int updateAsset(List list) throws Exception;
    
    public List eqAsmbList(Map map) throws Exception;
    public int insertEqAsmb(List list) throws Exception;
    public int updateEqAsmb(List list) throws Exception;

    public List eqSpecList(Map map) throws Exception;
    public int insertEqSpec(List list) throws Exception;
    public int updateEqSpec(List list) throws Exception;

    public List eqPartList(Map map) throws Exception;
    public int insertEqPart(List list) throws Exception;
    public int updateEqPart(List list) throws Exception;
}
