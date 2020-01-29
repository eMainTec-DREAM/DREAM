package dream.doc.img.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.doc.img.dao.MaDocImgDetailDAO;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.doc.img.dto.MaDocImgDetailDTO;

/**
 * 첨부문서 - 상세 dao
 * 
 * @author jung7126
 * @version $Id: MaDocImgDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="maDocImgDetailDAOTarget"
 * @spring.txbn id="maDocImgDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDocImgDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaDocImgDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaDocImgDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @param compNo 
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public MaDocImgDetailDTO findDetail(String docId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT                                                                                    ");
        query.append("        x.image_id imageId,                                                               ");
        query.append("        x.object_id objectId,                                                             ");
        query.append("        x.description,                                                                    ");
        query.append("        x.object_type objectType,                                                         ");
        query.append("        dbo.SFACODE_TO_DESC(x.object_type , 'OBJECT_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') objectTypeDesc, ");
        query.append("        x.dept_id deptId,                                                                 ");
        query.append("        dbo.SFAIDTODESC(x.dept_id , '', 'DEPT', '"+compNo+"') deptDesc,                       ");
        query.append("        x.reg_id regId,                                                                   ");
        query.append("        dbo.SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"') regDesc,                         ");
        query.append("        x.reg_date regDate                                                                ");
        query.append("FROM    TAIMAGE x                                                                         ");
        query.append("WHERE   x.image_id = '"+docId+"'	                                                        ");
    
        MaDocImgDetailDTO maDocImgDetailDTO = 
        		(MaDocImgDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaDocImgDetailDTO()));
        
        return maDocImgDetailDTO;
    }
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaDocImgDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgDetailDTO
     * @return
     */
    public int insertDetail(MaDocImgCommonDTO maDocImgCommonDTO, MaDocImgDetailDTO maDocImgDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    

    	query.append("INSERT INTO TAIMAGE		            ");
    	query.append("	(image_id,		description,		");
    	query.append("	 object_id,		object_type,	    ");
    	query.append("                  dept_id,            ");
    	query.append("   reg_id,        reg_date,           ");
    	query.append("   comp_no,       sub_img_type        ");
    	query.append("  )                                   ");
    	query.append("	VALUES					            ");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("                  ?,                  ");
    	query.append("   ?,             ?,                  ");
    	query.append("   ?,             ?                   ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maDocImgDetailDTO.getImageId(),
    	        maDocImgDetailDTO.getDescription(),
    	        maDocImgDetailDTO.getObjectId(),
    	        maDocImgDetailDTO.getObjectType(),
    	        maDocImgDetailDTO.getDeptId(),
    	        maDocImgDetailDTO.getRegId(),
    	        CommonUtil.getRowDateToNum(maDocImgDetailDTO.getRegDate()),
    	        maDocImgDetailDTO.getCompNo(),
    	        maDocImgCommonDTO.getSubImgType()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaDocImgDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgDetailDTO
     * @return
     */
    public int updateDetail(MaDocImgDetailDTO maDocImgDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAIMAGE SET		   ");
    	query.append("	description	     = ?,	   ");
    	query.append("	object_id	     = ?,	   ");
    	query.append("	object_type      = ?,	   ");
    	query.append("  dept_id          = ?,      ");
    	query.append("  reg_id           = ?,      ");
    	query.append("	reg_date		 = ?,      ");
    	query.append("  comp_no          = ?       ");
    	query.append("WHERE image_id     = ?	   ");
    	
    	Object[] objects = new Object[] {
    	        maDocImgDetailDTO.getDescription(),
    	        maDocImgDetailDTO.getObjectId(),
    	        maDocImgDetailDTO.getObjectType(),
    	        maDocImgDetailDTO.getDeptId(),
    	        maDocImgDetailDTO.getRegId(),
    	        maDocImgDetailDTO.getRegDate(),
    	        maDocImgDetailDTO.getCompNo(),
    	        maDocImgDetailDTO.getImageId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int insertFileInfo(MwareFile mwareFile,MaDocImgDetailDTO maDocImgDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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
                maDocImgDetailDTO.getCompNo(),
                maDocImgDetailDTO.getImgDataId(),
                maDocImgDetailDTO.getImageId(),
                mwareFile.getFileName(),            // file_name
                mwareFile.getFileType(),            // file_type
                mwareFile.getFileSize()+"",         // file_size
                maDocImgDetailDTO.getImgDataId(),
                maDocImgDetailDTO.getNfFilePath()
        };     
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int deleteFileInfo(String docId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAIMGDATA                     ");
        query.append("WHERE imgdata_id = '"+docId+"'            ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

    public String getFileInfo(String docdataId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT nf_file_path                               ");
        query.append("FROM   TAIMGDATA                                  ");
        query.append("WHERE  imgdata_id = '"+docdataId+"'               ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
    
    
    public int insertObjDoc(MaDocImgCommonDTO maDocImgCommonDTO,MaDocImgDetailDTO maDocImgDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAOBJDOC (                                ");
        query.append("    comp_no,    objdoc_id,                            ");
        query.append("    object_type,object_id,                            ");
        query.append("    doc_id                                            ");
        query.append(")                                                     ");
        query.append("VALUES (                                              ");
        query.append("    ?,           NEXT VALUE FOR SQAOBJDOC_ID,                ");
        query.append("    ?,           ?,                                   ");
        query.append("    ?                                                 ");
        query.append(")                                                     ");


        Object[] objects = new Object[] {
//                maDocImgDetailDTO.getCompNo(),
//                maDocImgCommonDTO.getObjectType(),
//                maDocImgCommonDTO.getObjectId(),
//                maDocImgDetailDTO.getDocId()
        };     
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    

    public String findOjbectTypeDesc(String objectType, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       ISNULL((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+loginUser.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       description										");
        query.append("FROM   TACDSYSD x                                         ");
        query.append("WHERE  cdsysd_id =  '"+objectType+"'                      ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
    
    
    public List getImgFileList(String objectId, String objectType, String param)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        ");
        query.append("       x.imgdata_id                          	");
        query.append("     , convert(varchar,x.imgdata_id)				FILE_NAME   ");
        query.append("     , x.file_ext                            	");
        query.append("     , x.nf_file_path nfFilePath				");
        query.append("     , x.image_id               				");
        query.append("FROM   TAIMGDATA x                            ");
        query.append("WHERE x.image_id IN (SELECT a.image_id        ");
        query.append("                     FROM   TAIMAGE a         ");
        query.append("                     WHERE  a.object_id = '"+objectId+"'      ");
        query.append("                       AND  a.object_type = '"+objectType+"'  ");
        query.getAndQuery("a.sub_img_type", param);
        query.append("                                 )            ");
        query.append("ORDER BY imgdata_id DESC                      ");

        return getJdbcTemplate().queryForList(query.toString());
    }
	@Override
	public String getImageCount(MaDocImgCommonDTO maDocImgCommonDTO, User user) {

		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT COUNT(*)									");
		query.append("FROM TAIMAGE x INNER JOIN TAIMGDATA y				");
		query.append("ON x.comp_no = y.comp_no							");
		query.append("AND x.image_id = y.image_id 						");
		query.getStringEqualQuery("x.object_type", maDocImgCommonDTO.getObjectType());
		query.getStringEqualQuery("x.sub_img_type", maDocImgCommonDTO.getSubImgType());
		query.getAndQuery("x.object_id", maDocImgCommonDTO.getObjectId());
		query.getAndQuery("x.comp_no", user.getCompNo());


        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
	@Override
	public int insertCopyDetail(String newImgId, String oldImgId, String newObjectId, User user) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
    
    	query.append("INSERT INTO TAIMAGE		            ");
    	query.append("	(image_id		,description		");
    	query.append("	 ,object_id		,object_type	    ");
    	query.append("   ,dept_id       ,reg_id     		");
    	query.append("   ,comp_no		,reg_date      		");
    	query.append("   ,sub_img_type       				");
    	query.append("  )                                   ");
    	query.append("SELECT 					            ");
    	query.append("	?				,description+'-Copyed'	");
    	query.append("	,?				,object_type	    ");
    	query.append("  ,dept_id        ,?     				");
    	query.append("  ,?				,convert(varchar(8), getdate(), 112)	");
    	query.append("  ,sub_img_type        				");
    	query.append("FROM    TAIMAGE                       ");
        query.append("WHERE   image_id = ?	            	");
        query.append(" AND    comp_no  = ?	            	");
    	
    	Object[] objects = new Object[] {
    			  newImgId
    	        , newObjectId
    	        , user.getUserId()
    	        , user.getCompNo()
    	        , oldImgId
    	        , user.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	@Override
	public int insertCopyFileInfo(String oldImgdataId, MaDocImgDetailDTO maDocImgDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAIMGDATA (                   ");
        query.append("    comp_no     		,imgdata_id			");
        query.append("    ,image_id    		,file_name          ");
        query.append("    ,file_ext    		,file_size          ");
        query.append("    ,nf_file_name		,nf_file_path		");
        query.append(")                                         ");
        query.append("SELECT	                               	");
        query.append("    ?     			,?					");
        query.append("    ,?    			,file_name          ");
        query.append("    ,file_ext    		,file_size          ");
        query.append("    ,?				,?					");
        query.append("FROM   TAIMGDATA                          ");
        query.append("WHERE  imgdata_id = ?               		");
        query.append(" AND 	 comp_no 	= ?               		");
        
        Object[] objects = new Object[] {
        		  user.getCompNo()
        		, maDocImgDetailDTO.getImgDataId()
                , maDocImgDetailDTO.getImageId()
                , maDocImgDetailDTO.getImgDataId()
                , maDocImgDetailDTO.getNfFilePath()
                , oldImgdataId
                , user.getCompNo()
        };     
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	
}