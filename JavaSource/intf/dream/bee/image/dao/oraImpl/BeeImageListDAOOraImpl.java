package intf.dream.bee.image.dao.oraImpl;

import java.io.File;
import java.util.List;
import java.util.Map;

import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import intf.dream.bee.image.dao.BeeImageListDAO;
/**
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeImageListDAOTarget"
 * @spring.txbn id="beeImageListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeImageListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeImageListDAO
{
	public List findImageList(Map map) throws Exception
    {
		
		String compNo = String.valueOf(map.get("compNo"));
		String objectId = String.valueOf(map.get("objectId"));
		String objectType = String.valueOf(map.get("objectType"));
		String imgDataId = String.valueOf(map.get("imgDataId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT y.imgdata_id AS imgDataId						");
        query.append("		,x.description as description					");
        query.append("		,y.file_name as fileName						");
        query.append("		,y.file_ext as fileExt							");
        query.append("		,y.nf_file_path as filePath						");
        query.append("		,x.image_id as imageId							");
        query.append("		,'dream/android/image/'||y.imgdata_id as url	");
        query.append("FROM TAIMAGE x INNER JOIN TAIMGDATA y					");
        query.append("ON x.comp_no = y.comp_no								");
        query.append("AND x.image_id = y.image_id							");
        query.append("WHERE 1=1												");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getStringEqualQuery("x.object_id", objectId);
        query.getStringEqualQuery("x.object_type", objectType);
        query.getStringEqualQuery("y.imgdata_id", imgDataId);
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public String findHeader(Map map) throws Exception {
		String compNo = convertString(map.get("compNo"));
		String imageId = convertString(map.get("imageId"));
		
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)				");
        query.append("FROM TAIMAGE x				");
        query.append("WHERE 1=1						");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.image_id",imageId);

        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

	@Override
	public int createHeader(Map map) throws Exception {
    	
		QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAIMAGE					");
    	query.append("	(image_id,		description,		");
    	query.append("	 object_id,		object_type,	    ");
    	query.append("   dept_id,       reg_id,             ");
    	query.append("   reg_date,      comp_no,            ");
    	query.append("   sub_img_type    	                ");
    	query.append("  )                                   ");
    	query.append("	VALUES					            ");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("   ?,				?,                  ");
    	query.append("   ?,				?,                  ");
    	query.append("   ?                				    ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("imageId"))
    			,convertString(map.get("description"))
    			,convertString(map.get("objectId"))
    			,convertString(map.get("objectType"))
    			,convertString(map.get("deptId"))
    			,convertString(map.get("regId"))
    			,DateUtil.getDate()
    			,convertString(map.get("compNo"))
    			,convertString(map.get("subImgType"))
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public int createImgData(MwareFile mwareFile, Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAIMGDATA (                               ");
        query.append("    comp_no,     imgdata_id,                          ");
        query.append("    image_id,    file_name,                           ");
        query.append("    file_ext,    file_size,                           ");
        query.append("    nf_file_name,nf_file_path                         ");
        query.append(")                                                     ");
        query.append("VALUES (                                              ");
        query.append("    ?,           ?,                                   ");
        query.append("    ?,           ?,                                   ");
        query.append("    ?,           ?,                                   ");
        query.append("    ?,           ?                                    ");
        query.append(")                                                     ");

        Object[] objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("imgDataId"))
        		,convertString(map.get("imageId"))
        		,mwareFile.getFileName()
                ,mwareFile.getFileType()
                ,mwareFile.getFileSize()+""
        		,convertString(map.get("imgDataId"))
        		,convertString(map.get("filePath"))
        };     
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public int deleteImgData(Map map) throws Exception {
        
		QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TAIMGDATA      ");
        query.append("WHERE imgdata_id = ?       ");
        query.append("AND   comp_no    = ?       ");
        
        Object[] objects = new Object[] {
        		convertString(map.get("imgDataId"))
        		,convertString(map.get("compNo"))
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
	public int deleteImage(Map map)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TAIMAGE                     ");
        query.append("WHERE image_id = ?       ");
        query.append("AND   comp_no    = ?       ");
        
        Object[] objects = new Object[] {
        		convertString(map.get("imageId"))
        		,convertString(map.get("compNo"))
        };
        
        return this.getJdbcTemplate().update(query.toString(),objects);
    }
	@Override
	public String findImgDataPath(Map map) throws Exception {
		
		String compNo = String.valueOf(map.get("compNo"));
		String objectId = String.valueOf(map.get("objectId"));
		String objectType = String.valueOf(map.get("objectType"));
		String imgDataId = String.valueOf(map.get("imgDataId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT y.nf_file_path as file_path					");
        query.append("FROM TAIMAGE x INNER JOIN TAIMGDATA y					");
        query.append("ON x.comp_no = y.comp_no								");
        query.append("AND x.image_id = y.image_id							");
        query.append("WHERE 1=1												");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getStringEqualQuery("x.object_id", objectId);
        query.getStringEqualQuery("x.object_type", objectType);
        query.getStringEqualQuery("y.imgdata_id", imgDataId);
    	
        StringBuilder sb = new StringBuilder();
        sb.append(QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString())));
        sb.append(File.separator);
        sb.append(imgDataId);
        
        return sb.toString();
    } 
}