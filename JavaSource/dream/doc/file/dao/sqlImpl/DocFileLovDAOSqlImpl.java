package dream.doc.file.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.doc.file.dao.DocFileLovDAO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * 첨부파일 - 목록 dao
 * @author  jung7126
 * @version $Id: DocFileLovDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="docFileLovDAOTarget"
 * @spring.txbn id="docFileLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocFileLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements DocFileLovDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: DocFileLovDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibCommonDTO
     * @return List
     */
    public List findList(MaDocLibCommonDTO maDocLibCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maDocLibCommonDTO.getCompNo();
        
        query.append("SELECT                                                                            ");
        query.append("       '' SEQNO,                                                                  ");
        query.append("       '' ISDELCHECK,                                                             ");
        query.append("       x.doc_no DOCNO,                                                            ");
        query.append("       x.description,                                                             ");
        query.append("       dbo.SFACODE_TO_DESC(x.doc_categ , 'DOC_CATEG', 'USR', '"+compNo+"','"+user.getLangId()+"') DOCCATEG,      ");
        query.append("       dbo.SFACODE_TO_DESC(x.secur_grade , 'SECUR_GRADE', 'SYS', '"+compNo+"','"+user.getLangId()+"') SECURGRADE,");
        query.append("       dbo.SFACODE_TO_DESC(x.object_type , 'OBJECT_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') OBJECTTYPE,");
        query.append("       dbo.SFAIDTODESC(x.dept_id , '', 'DEPT', '"+compNo+"') DEPTID,              ");
        query.append("       dbo.SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"') REGID,                 ");
        query.append("       x.reg_date REGDATE,           												");
        query.append("       x.comp_no COMPNO,                                                          ");
        query.append("       x.doc_id DOCID                                                             ");
        query.append("FROM   TADOCUMENT x                                                               ");
        query.append("WHERE  1  = 1                                                                     ");
        query.append(this.getWhere(maDocLibCommonDTO,user));
        query.append("ORDER BY x.doc_id DESC                                                            ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  jung7126
     * @version $Id: DocFileLovDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaDocLibCommonDTO maDocLibCommonDTO, User user)
    {        
        String compNo = maDocLibCommonDTO.getCompNo();
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", maDocLibCommonDTO.getCompNo());
        if (!"".equals(maDocLibCommonDTO.getDocId()))
        {
            query.getAndQuery("x.doc_id", maDocLibCommonDTO.getDocId());
            return query.toString();
        }
        query.getAndQuery("x.description", maDocLibCommonDTO.getDocDesc());
        query.getUsrCdQuery("x.doc_categ", maDocLibCommonDTO.getDocCateg(), maDocLibCommonDTO.getDocCategDesc(), "DOC_CATEG", compNo,user.getLangId());
        query.getDeptLevelQuery("x.dept_id", maDocLibCommonDTO.getRegDeptId(), maDocLibCommonDTO.getRegDeptDesc(), compNo);
        query.getCodeLikeQuery("x.reg_id", "dbo.SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"')",  maDocLibCommonDTO.getRegId(),  maDocLibCommonDTO.getRegDesc());


        if(maDocLibCommonDTO.getObjectId() != "")
        {
            query.append("   AND x.doc_id IN (SELECT a.doc_id               ");
            query.append("                    FROM   TAOBJDOC a             ");
            query.append("                    WHERE 1 = 1  ");
            query.getAndQuery("a.object_id", maDocLibCommonDTO.getObjectId());
            query.getAndQuery("a.object_type", maDocLibCommonDTO.getObjectType());
            query.getAndQuery("a.comp_no", maDocLibCommonDTO.getCompNo());
            query.append("                    )                             ");

        }
        
        return query.toString();
    }
}