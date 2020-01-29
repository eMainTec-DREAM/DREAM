package dream.doc.file.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.doc.file.dao.MaDocLibDetailDAO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;

/**
 * 첨부문서 - 상세 dao
 * 
 * @author jung7126
 * @version $Id: MaDocLibDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="maDocLibDetailDAOTarget"
 * @spring.txbn id="maDocLibDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDocLibDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaDocLibDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaDocLibDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @param compNo 
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public MaDocLibDetailDTO findDetail(String docId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT                                                                            ");
        query.append("        x.doc_id docId,                                                           ");
        query.append("        x.doc_no docNo,                                                           ");
        query.append("        x.description,                                                            ");
        query.append("        x.doc_categ docCateg,                                                     ");
        query.append("        SFACODE_TO_DESC(x.doc_categ , 'DOC_CATEG', 'USR', '"+compNo+"','"+user.getLangId()+"') docCategDesc,         ");
        query.append("        x.object_type objectType,                                                     ");
        query.append("        SFACODE_TO_DESC(x.object_type , 'OBJECT_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') objectTypeDesc,         ");
        query.append("        x.secur_grade securGrade,                                                 ");
        query.append("        SFACODE_TO_DESC(x.secur_grade , 'SECUR_GRADE', 'SYS', '"+compNo+"','"+user.getLangId()+"') securGradeDesc, ");
        query.append("        x.dept_id deptId,                                                         ");
        query.append("        SFAIDTODESC(x.dept_id , '', 'DEPT', '"+compNo+"') deptIdDesc,             ");
        query.append("        docctg_id docctgId,														");
        query.append("       (SELECT a.description FROM TADOCCTG a WHERE a.docctg_id = x.docctg_id AND a.comp_no = x.comp_no) docctgDesc,");
        query.append("        x.reg_id regId,                                                           ");
        query.append("        SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"') regIdDesc,                ");
        query.append("        x.reg_date regDate                                                        ");
        query.append("		, x.plant							plantId									");
        query.append("      , (SELECT a.description 													");
        query.append("           FROM TAPLANT a 														");
        query.append("          WHERE a.comp_no = x.comp_no 											");
        query.append("            AND a.plant = x.plant) 		plantDesc								");
        query.append("		, x.remark							remark									");
        query.append("FROM    TADOCUMENT x                                                              ");
        query.append("WHERE   x.doc_id = '"+docId+"'	                                                ");
    
        MaDocLibDetailDTO maDocLibDetailDTO = 
        		(MaDocLibDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaDocLibDetailDTO()));
        
        return maDocLibDetailDTO;
    }
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaDocLibDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @param maDocLibCommonDTO 
     * @since   1.0
     * 
     * @param maDocLibDetailDTO
     * @return
     */
    public int insertDetail(MaDocLibCommonDTO maDocLibCommonDTO, MaDocLibDetailDTO maDocLibDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TADOCUMENT		        ");
    	query.append("	(doc_id,		description,		");
    	query.append("	 doc_no,		doc_categ,			");
    	query.append("   secur_grade,   dept_id,            ");
    	query.append("   reg_id,        reg_date,           ");
    	query.append("   docctg_id,     pubdoc_yn,          ");
        query.append("   comp_no,       object_type         ");
    	query.append("	,plant		   ,remark				");
    	query.append("  )                                   ");
    	query.append("	VALUES					            ");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("   ?,             ?,					");
    	query.append("   ?,             TO_CHAR(sysdate, 'yyyymmdd'), ");
    	query.append("   ?,             ?,					");
        query.append("   ?,             ?                   ");
        query.append("  ,?             ,?                   ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maDocLibDetailDTO.getDocId(),
    	        maDocLibDetailDTO.getDescription(),
    	        maDocLibDetailDTO.getDocNo(),
    	        maDocLibDetailDTO.getDocCateg(),
    	        maDocLibDetailDTO.getSecurGrade(),
    	        maDocLibDetailDTO.getDeptId(),
    	        maDocLibDetailDTO.getRegId(),
//    	        maDocLibDetailDTO.getCompNo()==""?"100":maDocLibDetailDTO.getCompNo(),
    	        
    	        maDocLibDetailDTO.getDocctgId(),
    	        maDocLibDetailDTO.getPubdocYn(),
    	        		
    	        maDocLibDetailDTO.getCompNo()==""?"100":maDocLibDetailDTO.getCompNo(),
                maDocLibCommonDTO.getObjectType()
    	        ,maDocLibDetailDTO.getPlantId()
    	        ,maDocLibDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaDocLibDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibDetailDTO
     * @return
     */
    public int updateDetail(MaDocLibDetailDTO maDocLibDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TADOCUMENT SET		   ");
    	query.append("	description	     = ?,	   ");
    	query.append("	doc_no	         = ?,	   ");
    	query.append("	doc_categ        = ?,	   ");
    	query.append("  secur_grade      = ?,      ");
    	query.append("  docctg_id      	 = ?,      ");
    	query.append("  dept_id          = ?,      ");
    	query.append("  reg_id           = ?,      ");
    	query.append("	reg_date		 = ?,      ");
    	query.append("	plant			 = ?,      ");
    	query.append("	remark			 = ?,      ");
    	query.append("  comp_no          = ?       ");
    	query.append("WHERE doc_id       = ?	   ");
    	
    	Object[] objects = new Object[] {
    	        maDocLibDetailDTO.getDescription(),
    	        maDocLibDetailDTO.getDocNo(),
    	        maDocLibDetailDTO.getDocCateg(),
    	        maDocLibDetailDTO.getSecurGrade(),
    	        maDocLibDetailDTO.getDocctgId(),
    	        maDocLibDetailDTO.getDeptId(),
    	        maDocLibDetailDTO.getRegId(),
    	        maDocLibDetailDTO.getRegDate(),
    	        maDocLibDetailDTO.getPlantId(),
    	        maDocLibDetailDTO.getRemark(),
    	        maDocLibDetailDTO.getCompNo(),
    	        maDocLibDetailDTO.getDocId(),
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int insertFileInfo(MwareFile mwareFile,MaDocLibDetailDTO maDocLibDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TADOCDATA (                               ");
        query.append("    comp_no,    docdata_id,                           ");
        query.append("    doc_id,     file_name,                            ");
        query.append("    file_ext,   file_size,                            ");
        query.append("    nf_file_path                                      ");
        query.append(")                                                     ");
        query.append("VALUES (                                              ");
        query.append("    ?,           ?,                                   ");
        query.append("    ?,           ?,                                   ");
        query.append("    ?,           ?,                                   ");
        query.append("    ?                                                 ");
        query.append(")                                                     ");


        Object[] objects = new Object[] {
                maDocLibDetailDTO.getCompNo()==""?"100":maDocLibDetailDTO.getCompNo(),
                maDocLibDetailDTO.getDocDataId(),
                maDocLibDetailDTO.getDocId(),
                mwareFile.getFileName(),            // file_name
                mwareFile.getFileType(),            // file_type
                mwareFile.getFileSize()+"",         // file_size
                maDocLibDetailDTO.getNfFilePath()
        };     
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int deleteFileInfo(String docId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TADOCDATA                     ");
        query.append("WHERE docdata_id = '"+docId+"'            ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

    public String getFileInfo(String docdataId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT nf_file_path                               ");
        query.append("FROM   TADOCDATA                                  ");
        query.append("WHERE  docdata_id = '"+docdataId+"'               ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
    
    
    public int insertObjDoc(MaDocLibCommonDTO maDocLibCommonDTO,MaDocLibDetailDTO maDocLibDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAOBJDOC (                                ");
        query.append("    comp_no,    objdoc_id,                            ");
        query.append("    object_type,object_id,                            ");
        query.append("    doc_id                                            ");
        query.append(")                                                     ");
        query.append("VALUES (                                              ");
        query.append("    ?,           SQAOBJDOC_ID.nextval,                ");
        query.append("    ?,           ?,                                   ");
        query.append("    ?                                                 ");
        query.append(")                                                     ");


        Object[] objects = new Object[] {
                maDocLibDetailDTO.getCompNo()==""?"100":maDocLibDetailDTO.getCompNo(),
                maDocLibCommonDTO.getObjectType(),
                maDocLibCommonDTO.getObjectId(),
                maDocLibDetailDTO.getDocId()
        };     
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String[] getWkorId(String mobinsWkorId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT wkor_id                                   ");
        query.append("       ,wo_title                                  ");
        query.append(" FROM   TXPDAWORKORDER                            ");
        query.append(" WHERE  mobins_wkor_id = '" + mobinsWkorId + "'   ");

        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.singleRowToStringArray(resultList);
//        return QueryBuffer.listToString(resultList);

    }
    public String[] getWkorDesc(String wkorId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT wkor_id                                   ");
        query.append("       ,description                               ");
        query.append(" FROM   TAWORKORDER                               ");
        query.append(" WHERE  wkor_id = '" + wkorId + "'   ");

        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.singleRowToStringArray(resultList);
    }

}