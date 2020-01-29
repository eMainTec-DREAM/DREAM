package dream.doc.file.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class DocFileLovDAOOraImpl extends BaseJdbcDaoSupportOra implements DocFileLovDAO
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
        QueryBuffer query = new QueryBuffer();
        String compNo = maDocLibCommonDTO.getCompNo();
        
        query.append("SELECT                                                                            ");
        query.append("       '' seqNo,                                                                  ");
        query.append("       '' isDelCheck,                                                             ");
        query.append("       x.doc_no docNo,                                                            ");
        query.append("       x.description,                                                             ");
        query.append("       SFACODE_TO_DESC(x.doc_categ , 'DOC_CATEG', 'USR', '"+compNo+"','"+user.getLangId()+"') docCateg,      ");
        query.append("       SFACODE_TO_DESC(x.secur_grade , 'SECUR_GRADE', 'SYS', '"+compNo+"','"+user.getLangId()+"') securGrade,");
        query.append("       SFACODE_TO_DESC(x.object_type , 'OBJECT_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') objectType,");
        query.append("       SFAIDTODESC(x.dept_id , '', 'DEPT', '"+compNo+"') deptId,                  ");
        query.append("       SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"') regId,                     ");
        query.append("       docctg_id docctgId,														");
        query.append("       (SELECT a.description FROM TADOCCTG a WHERE a.docctg_id = x.docctg_id) docctgDesc,");
        query.append("       pubdoc_yn pubdocYn,														");
        query.append("       TO_CHAR(TO_DATE (x.reg_date,'yyyy-mm-dd'),'yyyy-mm-dd') regDate,           ");
        query.append("       x.comp_no compNo,                                                          ");
        query.append("       x.doc_id docId                                                             ");
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
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", maDocLibCommonDTO.getCompNo());
        if (!"".equals(maDocLibCommonDTO.getDocId()))
        {
            query.getAndQuery("x.doc_id", maDocLibCommonDTO.getDocId());
            return query.toString();
        }
        query.getAndQuery("pubdoc_yn", maDocLibCommonDTO.getPubdocYn());
        
        query.getLikeQuery("x.description", maDocLibCommonDTO.getDocDesc());
        query.getUsrCdQuery("x.doc_categ", maDocLibCommonDTO.getDocCateg(), maDocLibCommonDTO.getDocCategDesc(), "DOC_CATEG", compNo,user.getLangId());
        query.getDeptLevelQuery("x.dept_id", maDocLibCommonDTO.getRegDeptId(), maDocLibCommonDTO.getRegDeptDesc(), compNo);
        query.getCodeLikeQuery("x.reg_id", "SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"')",  maDocLibCommonDTO.getRegId(),  maDocLibCommonDTO.getRegDesc());
        query.getCodeLikeQuery("docctg_id", "(SELECT a.description FROM TADOCCTG a WHERE a.docctg_id = x.docctg_id)",  maDocLibCommonDTO.getDocctgId(),  maDocLibCommonDTO.getDocctgDesc());


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