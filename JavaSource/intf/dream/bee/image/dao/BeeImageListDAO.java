package intf.dream.bee.image.dao;

import java.util.List;
import java.util.Map;

import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeImageListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findImageList(Map map) throws Exception;
    public String findHeader(Map map) throws Exception;
    public int createHeader(Map map) throws Exception;
    public int createImgData(MwareFile mwareFile, Map map) throws Exception;
    public int deleteImgData(Map map) throws Exception;
    public int deleteImage(Map map) throws Exception;
    public String findImgDataPath(Map map) throws Exception;
}