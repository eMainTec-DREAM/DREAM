package common.file.dao.sqlImpl;

import java.util.List;

import common.file.MwareFile;
import common.file.dao.FileUploadDAO;
import common.file.dto.FileUploadDTO;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;

/**
 * 파일첨부
 * @author javaworker
 * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
 * @since 1.0
 * @spring.bean id="fileUploadDAOTarget"
 * @spring.txbn id="fileUploadDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class FileUploadDAOSqlImpl extends BaseJdbcDaoSupportSql implements FileUploadDAO
{
    /**
     * 파일정보를 저장한다.
     * @author  javaworker
     * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
     * @since   1.0
     * 
     * @param mwareFile
     * @param fileUploadDTO
     * @return
     * @throws Exception
     */
    public int insertFileInfo(MwareFile mwareFile,
            FileUploadDTO fileUploadDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO T2FILE (                              ");
        query.append("    enter_by,    enter_date,                      ");
        query.append("    file_name,   file_type,                       ");
        query.append("    file_size,   file_no,                         ");
        query.append("    object_no,   doc_type,                        ");
        query.append("    enter_time                                    ");
        query.append(")                                                 ");
        query.append("VALUES (                                          ");
        query.append("    ?,           CONVERT(VARCHAR, GETDATE(), 112),");
        query.append("    ?,           ?,                               ");
        query.append("    ?,           ?,                               ");
        query.append("    ?,           ?,                               ");
        query.append("    LEFT(REPLACE(CONVERT(VARCHAR, GETDATE(), 108),':',''),4)                    ");
        query.append(")                                                 ");

        Object[] objects = new Object[] {
                fileUploadDTO.getEnterBy(),        // enter_by
                mwareFile.getFileName(),            // file_name
                mwareFile.getFileType(),            // file_type
                mwareFile.getFileSize()+"",         // file_size
                mwareFile.getPhysicalFileName(),    // file_no
                fileUploadDTO.getObjectNo(),       // object_no
                fileUploadDTO.getDocType()      // image_type
        };     
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * 이미지 해더 번호로 첨부파일을 조회한다.
     * @author  javaworker
     * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
     * @since   1.0
     * 
     * @param imageHdrNo
     * @return
     * @throws Exception
     */
    public MwareFile getFileInfo(String fileNo)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        query.append("SELECT                                        ");
        query.append("       file_name fileName,                    ");
        query.append("       file_ext fileType,                     ");
        query.append("       file_size fileSize,                    ");
        query.append("       nf_file_path docType,                  ");
        query.append("       docdata_id physicalFileName            ");
        query.append("FROM   TADOCDATA x                            ");
        query.append("WHERE  docdata_id = '" + fileNo + "'          ");
        
        MwareFile mwareFile = 
            (MwareFile)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MwareFile()));
        
        return mwareFile;
    }

    /**
     * DB 첨부파일 내역을 삭제한다.
     * @author  javaworker
     * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
     * @since   1.0
     * 
     * @param imageNo
     * @return
     * @throws Exception
     */
    public int deleteImage(String fileNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE T2FILE                         ");        
        query.append("WHERE  file_no = '" + fileNo + "'     ");

        return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * 이미지 해더 번호로 첨부파일을 조회한다.
     * @author  javaworker
     * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
     * @since   1.0
     * 
     * @param imageHdrNo
     * @return
     * @throws Exception
     */
    public String[][] getFileInfoArray(FileUploadDTO fileUploadDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       file_name,                                             ");
        query.append("       file_size,                                             ");
        query.append("       file_no                                                ");
        query.append("FROM   T2FILE                                                 ");
        query.append("WHERE  object_no = '" + fileUploadDTO.getObjectNo() + "'      ");
        query.append("  AND  doc_type = '" + fileUploadDTO.getDocType() + "'        ");
        query.append("ORDER BY enter_date                                           ");
        
        List resultList = this.getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.toStringArray(resultList);
    }

    public MwareFile getImgFileInfo(String fileNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        ");
        query.append("       file_name fileName,                    ");
        query.append("       file_ext fileType,                     ");
        query.append("       file_size fileSize,                    ");
        query.append("       nf_file_path docType,                  ");
        query.append("       imgdata_id physicalFileName            ");
        query.append("FROM   TAIMGDATA x                            ");
        query.append("WHERE  imgdata_id = '" + fileNo + "'          ");
        
        MwareFile mwareFile = 
            (MwareFile)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MwareFile()));
        
        return mwareFile;
    }
}
